package ii.cipriantarlev.marketmanagementapi.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.barcode.BarcodeService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private BarcodeService barcodeService;

	@Override
	public List<ProductDTOForList> findAll() {
		return productRepository.findAll().stream()
				.map(product -> productMapper.mapEntityToDTOForList(product))
				.collect(Collectors.toList());
	}

	@Override
	public ProductDTO findById(Long id) {
		var product = productRepository.findById(id);

		if (product.isPresent()) {
			return productMapper.mapEntityToDTO(product.get());
		}
		return null;
	}

	@Override
	public ProductDTO save(ProductDTO productDTO) {
		var product = productRepository.save(productMapper.mapDTOToEntity(productDTO));
		barcodeService.deleteBarcodeWithNullProductId();
		return productMapper.mapEntityToDTO(product);
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
}
