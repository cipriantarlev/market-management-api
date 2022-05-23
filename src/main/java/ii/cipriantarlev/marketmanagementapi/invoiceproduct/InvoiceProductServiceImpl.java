/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ii.cipriantarlev.marketmanagementapi.invoice.InvoiceService;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorDTOOnlyName;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.product.ProductService;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    @Autowired
    private InvoiceProductRepository invoiceProductRepository;

    @Autowired
    private InvoiceProductMapper invoiceProductMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private InvoiceService invoiceService;

    private List<Long> listOfIds = new ArrayList<>();

    private boolean isChecked;

    @Override
    public List<InvoiceProductDTO> findAllByInvoiceId(Long invoiceId) {
        List<InvoiceProductDTO> invoiceProductList = invoiceProductRepository.findAllByInvoiceId(invoiceId).stream()
                .map(invoiceProduct -> invoiceProductMapper.mapEntityToDTO(invoiceProduct))
                .collect(Collectors.toList());

        if (invoiceProductList == null || invoiceProductList.isEmpty()) {
            throw new DTOListNotFoundException("InvoiceProduct list not found");
        }

        return invoiceProductList;
    }

    @Override
    public InvoiceProductDTO findById(Long id) {
        var invoiceProduct = invoiceProductRepository.findById(id);

        if (invoiceProduct.isPresent()) {
            return invoiceProductMapper.mapEntityToDTO(invoiceProduct.get());
        }

        throw new DTONotFoundException(String.format("InvoiceProduct with %d not found", id), id);
    }

    @Override
    public InvoiceProductDTO save(InvoiceProductDTO invoiceProductDTO) {
        if (invoiceProductDTO.getId() != null
                && invoiceProductRepository.findById(invoiceProductDTO.getId()).isPresent()) {
            throw new DTOFoundWhenSaveException(
                    String.format(
                            "InvoiceProduct with id: '%d' already exists in database. "
                                    + "Please use update in order to save the changes in database",
                            invoiceProductDTO.getId()),
                    invoiceProductDTO.getId());
        }

        var invoiceProduct = invoiceProductRepository.save(invoiceProductMapper.mapDTOToEntity(invoiceProductDTO));
        updateProduct(invoiceProductDTO);
        return invoiceProductMapper.mapEntityToDTO(invoiceProduct);
    }

    @Override
    public InvoiceProductDTO update(InvoiceProductDTO invoiceProductDTO) {
        this.findById(invoiceProductDTO.getId());
        var invoiceProduct = invoiceProductRepository.save(invoiceProductMapper.mapDTOToEntity(invoiceProductDTO));
        return invoiceProductMapper.mapEntityToDTO(invoiceProduct);
    }

    @Override
    public int updateIsProductChecked(Map<Boolean, List<Long>> productsToUpdate) {
        listOfIds.clear();
        productsToUpdate.forEach((checked, idList) -> {
            listOfIds.addAll(invoiceProductRepository.findAllById(idList)
                    .stream().map(invoiceProduct -> invoiceProduct.getProduct().getId()).collect(Collectors.toList()));
            isChecked = checked;
        });
        return productService.updateIsCheckedMarker(Collections.singletonMap(isChecked, listOfIds));
    }

    @Override
    public void deleteById(Long id) {
        var invoiceProduct = this.findById(id);
        var product = invoiceProduct.getProduct();
        product.setStock(product.getStock().subtract(invoiceProduct.getQuantity()));
        productService.update(product);
        invoiceProductRepository.deleteById(id);
    }

    private void updateProduct(InvoiceProductDTO invoiceProductDTO) {
        var invoiceDTO = invoiceService.findById(invoiceProductDTO.getInvoice().getId());
        var productDTO = invoiceProductDTO.getProduct();
        productDTO.setDefaultVendorId(invoiceDTO.getVendor().getId());
        productDTO.getVendors().add(VendorDTOOnlyName.builder().id(invoiceDTO.getVendor().getId()).build());
        var collectedList = productDTO.getVendors().stream()
                .filter(vendorDTOOnlyName -> vendorDTOOnlyName.getId() != null)
                .distinct()
                .collect(Collectors.toList());
        productDTO.setVendors(collectedList);
        productService.update(productDTO);
    }
}
