package ii.cipriantarlev.marketmanagementapi.productscode;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCodeServiceImpl implements ProductCodeService {

	@Autowired
	private ProductCodeRepository productCodeRepository;

	@Autowired
	private ProductCodeMapper productCodeMapper;

	@Override
	public List<ProductCodeDTO> findAll() {
		return productCodeRepository.findAll().stream()
				.map(productCode -> productCodeMapper.mapEntityToDTO(productCode))
				.collect(Collectors.toList());
	}

	@Override
	public ProductCodeDTO findById(Long id) {
		Optional<ProductCode> productCode = productCodeRepository.findById(id);

		if (productCode.isPresent()) {
			return productCodeMapper.mapEntityToDTO(productCode.get());
		}

		return null;
	}

	@Override
	public ProductCodeDTO generateNewProductCode() {
		var lastProductCode = productCodeRepository.findFirst1ByOrderByValueDesc();

		if (lastProductCode == null) {
			var savedProductCode = productCodeRepository.save(new ProductCode("MD00000000"));
			return productCodeMapper.mapEntityToDTO(savedProductCode);
		}

		
		Long generatedValue = Long.parseLong(lastProductCode.getValue().substring(2)) + 1;
		var generatedProductCode = productCodeRepository.save(new ProductCode("MD" + String.format("%08d", generatedValue)));
		return productCodeMapper.mapEntityToDTO(generatedProductCode);
	}

	@Override
	public void deleteById(Long id) {
		productCodeRepository.deleteById(id);
	}

}
