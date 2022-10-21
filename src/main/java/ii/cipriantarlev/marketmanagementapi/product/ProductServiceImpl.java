/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.product;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ii.cipriantarlev.marketmanagementapi.exceptions.PriceLabelGenerationException;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import ii.cipriantarlev.marketmanagementapi.pricechangingact.PriceChangingActDTO;
import ii.cipriantarlev.marketmanagementapi.product.history.ProductHistoryService;
import ii.cipriantarlev.marketmanagementapi.utils.CreateLabel;
import ii.cipriantarlev.marketmanagementapi.utils.MarketManagementFactory;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.barcode.BarcodeService;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private BarcodeService barcodeService;

	@Autowired
	private ProductHistoryService productHistoryService;

	@Autowired
	private CreateLabel createLabel;

	@Autowired
	private MarketManagementFactory marketManagementFactory;

	@Setter
	private int updatedRows;

    private final List<ProductDTOForList> markedProductsForPrint = new ArrayList<>();

	@Override
	public List<ProductDTOForList> findAll() {
		List<ProductDTOForList> products = productRepository.findAll().stream()
				.map(product -> productMapper.mapEntityToDTOForList(product))
				.collect(Collectors.toList());

		if (products == null || products.isEmpty()) {
			throw new DTOListNotFoundException("Product list not found");
		}

		return products;
	}

	@Override
	public ProductDTO findById(Long id) {
		return productMapper.mapEntityToDTO(productRepository.findById(id).orElseThrow(() ->
                new DTONotFoundException(String.format("Product with %d not found", id), id)));
	}

	@Override
	public ProductDTO save(ProductDTO productDTO) {
		if (productDTO.getId() != null && productRepository.findById(productDTO.getId()).isPresent()) {
			throw new DTOFoundWhenSaveException(
					String.format("Product with id: '%d' already exists in database. "
							+ "Please use update in order to save the changes in database", productDTO.getId()),
					productDTO.getId());
		}
		return saveProduct(productDTO, HistoryAction.CREATE);
	}

	@Override
	public ProductDTO update(ProductDTO productDTO) {
		this.findById(productDTO.getId());
		return saveProduct(productDTO, HistoryAction.UPDATE);
	}

	@Override
	public void deleteById(Long id) {
		productHistoryService.createProductHistoryRecord(this.findById(id), HistoryAction.DELETE);
		productRepository.deleteById(id);
	}

	@Override
	public ProductDTO findByBarcodeValue(String barcodeValue) {
		var product = productRepository.findByBarcodeValue(barcodeValue);

		if (product != null) {
			return productMapper.mapEntityToDTO(product);
		}

		throw new DTONotFoundException(String.format("Product with id %s not found", Long.parseLong(barcodeValue)),
				Long.parseLong(barcodeValue));
	}

	@Override
	public boolean checkIfNameRomExists(String nameRom) {
		return productRepository.findByNameRom(nameRom) != null;
	}

	@Override
	public boolean checkIfNameRusExists(String nameRus) {
		return productRepository.findByNameRus(nameRus) != null;
	}

	@Override
	public int updateIsCheckedMarker(Map<Boolean, List<Long>> productsToUpdate) {
		setUpdatedRows(0);
        productsToUpdate.forEach((isChecked, idList) -> {idList.forEach(id -> {
			var foundProduct = productMapper.mapDTOToEntity(this.findById(id));
			foundProduct.setChecked(isChecked);
			productHistoryService
					.createProductHistoryRecord(productMapper.mapEntityToDTO(foundProduct), HistoryAction.UPDATE);
			productRepository.updateIsCheckedMarker(isChecked, id);
		});
			setUpdatedRows(updatedRows + idList.size());
		});

		return updatedRows;
	}

    @Override
    public List<ProductDTOForList> findAllMarkedProduct() {
        List<ProductDTOForList> products = productRepository.findByIsCheckedTrue().stream()
                .map(product -> productMapper.mapEntityToDTOForList(product))
                .map(productDTOForList -> {
                    productDTOForList.setStock(BigDecimal.ONE);
                    return productDTOForList;
                })
                .toList();

        if (!products.isEmpty()) {
            return products;
        }
        throw new DTOListNotFoundException("Product list not found");
    }

	@Override
	public byte[] printMarkedProducts(Map<Long, Integer> productsToPrint) {
        markedProductsForPrint.clear();
		productsToPrint.forEach((id, qty) -> IntStream.rangeClosed(1, qty)
                .forEach(element -> markedProductsForPrint
                        .add(productMapper.mapEntityToDTOForList(productRepository.findById(id).orElseThrow(() ->
                            new DTONotFoundException(
									String.format("Product with %d not found during price label generation.", id), id))))));

        return createLabel.generatePriceLabel(markedProductsForPrint).orElseThrow(()
				-> new PriceLabelGenerationException("Unable to generate price label. Please try again."));
	}

	@Override
	public int updateRetailPrice(BigDecimal retailPrice, BigDecimal tradeMargin, BigDecimal oldRetailPrice, Long productId) {
		return productRepository.updateRetailPrice(retailPrice, tradeMargin, oldRetailPrice, productId);
	}

	private ProductDTO saveProduct(ProductDTO productDTO, HistoryAction create) {
		var product = productRepository.save(productMapper.mapDTOToEntity(productDTO));
		barcodeService.deleteBarcodeWithNullProductId();
		var savedProductDTO = productMapper.mapEntityToDTO(product);
		productHistoryService.createProductHistoryRecord(savedProductDTO, create);
		return savedProductDTO;
	}
}