/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.productscode;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

@Service
public class ProductCodeServiceImpl implements ProductCodeService {

	@Autowired
	private ProductCodeRepository productCodeRepository;

	@Autowired
	private ProductCodeMapper productCodeMapper;

	@Autowired
	private EntitiesHistoryService entitiesHistoryService;

	@Override
	public List<ProductCodeDTO> findAll() {
		List<ProductCodeDTO> productCodes = productCodeRepository.findAll().stream()
				.map(productCode -> productCodeMapper.mapEntityToDTO(productCode))
				.collect(Collectors.toList());

		if (productCodes == null || productCodes.isEmpty()) {
			throw new DTOListNotFoundException("ProductCode list not found");
		}

		return productCodes;
	}

	@Override
	public ProductCodeDTO findById(Long id) {
		Optional<ProductCode> productCode = productCodeRepository.findById(id);

		if (productCode.isPresent()) {
			return productCodeMapper.mapEntityToDTO(productCode.get());
		}

		throw new DTONotFoundException(String.format("ProductCode with %d not found", id), id);
	}

	@Override
	public ProductCodeDTO generateNewProductCode() {
		var lastProductCode = productCodeRepository.findFirst1ByOrderByValueDesc();

		if (lastProductCode == null) {
			var savedProductCode = productCodeRepository.save(new ProductCode("MD00000000"));
			return productCodeMapper.mapEntityToDTO(savedProductCode);
		}

		Long generatedValue = Long.parseLong(lastProductCode.getValue().substring(2)) + 1;
		var generatedProductCode =
				productCodeRepository.save(new ProductCode("MD" + String.format("%08d", generatedValue)));
		entitiesHistoryService.createEntityHistoryRecord(generatedProductCode, null, HistoryAction.CREATE);
		return productCodeMapper.mapEntityToDTO(generatedProductCode);
	}

	@Override
	public void deleteById(Long id) {
		entitiesHistoryService.createEntityHistoryRecord(this.findById(id), null, HistoryAction.DELETE);
		productCodeRepository.deleteById(id);
	}

}
