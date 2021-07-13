package ii.cipriantarlev.marketmanagementapi.productscode;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductCodeMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ProductCodeDTO mapEntityToDTO(ProductCode productCode) {
		return modelMapper.map(productCode, ProductCodeDTO.class);
	}

	public ProductCode mapDTOToEntity(ProductCodeDTO productCodeDTO) {
		return modelMapper.map(productCodeDTO, ProductCode.class);
	}
}
