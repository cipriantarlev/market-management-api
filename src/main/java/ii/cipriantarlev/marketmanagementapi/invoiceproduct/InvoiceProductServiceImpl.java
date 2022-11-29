/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import ii.cipriantarlev.marketmanagementapi.invoice.Invoice;
import ii.cipriantarlev.marketmanagementapi.invoice.InvoiceService;
import ii.cipriantarlev.marketmanagementapi.product.Product;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTO;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorDTOOnlyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.product.ProductService;

/**
 * Class to implement {@link InvoiceProductService} interface.
 */
@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    /**
     * {@link InvoiceProductRepository} used to connect with database.
     */
    @Autowired
    private InvoiceProductRepository invoiceProductRepository;

    /**
     * {@link InvoiceProductMapper} used to map entity to dto and vice-versa.
     */
    @Autowired
    private InvoiceProductMapper invoiceProductMapper;

    /**
     * {@link ProductService} used to manage {@link Product}.
     */
    @Autowired
    private ProductService productService;

    /**
     * {@link InvoiceService} used to manager {@link Invoice}.
     */
    @Autowired
    private InvoiceService invoiceService;

    @Override
    public List<InvoiceProductDTO> findAllByInvoiceId(Long invoiceId) {
        List<InvoiceProductDTO> invoiceProductList = invoiceProductRepository.findAllByInvoiceId(invoiceId).stream()
                .map(invoiceProduct -> invoiceProductMapper.mapEntityToDTO(invoiceProduct))
                .toList();

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
        return productService.updateIsCheckedMarker(productsToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        var invoiceProduct = this.findById(id);
        var product = invoiceProduct.getProduct();
        resetDeletedProduct(invoiceProduct, product);
        productService.update(product);
        invoiceProductRepository.deleteById(id);
    }

    /**
     * Method used to roll back the old values for {@link Product#getStock()}, {@link Product#getRetailPrice()},
     * {@link Product#getOldRetailPrice()} and {@link Product#isRetailPriceChanged()}.
     *
     * @param invoiceProduct {@link InvoiceProduct} which will provide the stock/quantity value
     * @param product {@link Product} which will provide the rest values.
     */
    private void resetDeletedProduct(InvoiceProductDTO invoiceProduct, ProductDTO product) {
        product.setStock(product.getStock().subtract(invoiceProduct.getQuantity()));
        if(product.isRetailPriceChanged()) {
            product.setRetailPrice(Objects.nonNull(product.getOldRetailPrice()) ? product.getOldRetailPrice() : BigDecimal.ZERO);
            product.setOldRetailPrice(BigDecimal.ZERO);
            product.setRetailPriceChanged(false);
        }
    }

    /**
     * Method used to update {@link Product} based on values provided in {@link InvoiceProduct}.
     *
     * @param invoiceProductDTO {@link InvoiceProduct} used to update {@link Product}.
     */
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