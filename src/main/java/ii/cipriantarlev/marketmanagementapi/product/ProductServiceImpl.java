/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import ii.cipriantarlev.marketmanagementapi.product.history.ProductHistoryService;
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

	@Setter
	private int updatedRows;

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
		var product = productRepository.findById(id);

		if (product.isPresent()) {
			return productMapper.mapEntityToDTO(product.get());
		}
		throw new DTONotFoundException(String.format("Product with %d not found", id), id);
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

	private ProductDTO saveProduct(ProductDTO productDTO, HistoryAction create) {
		var product = productRepository.save(productMapper.mapDTOToEntity(productDTO));
		barcodeService.deleteBarcodeWithNullProductId();
		var savedProductDTO = productMapper.mapEntityToDTO(product);
		productHistoryService.createProductHistoryRecord(savedProductDTO, create);
		return savedProductDTO;
	}
}
