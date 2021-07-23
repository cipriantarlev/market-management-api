package ii.cipriantarlev.marketmanagementapi.product;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ProductDTO mapEntityToDTO(Product product) {
		return modelMapper.map(product, ProductDTO.class);
	}

	public Product mapDTOToEntity(ProductDTO productDTO) {
		return modelMapper.map(productDTO, Product.class);
	}

	public ProductDTOForList mapEntityToDTOForList(Product product) {
		return modelMapper.map(product, ProductDTOForList.class);
	}
}
