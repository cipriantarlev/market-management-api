package ii.cipriantarlev.marketmanagementapi.subcategory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryMapper {

	@Autowired
	private ModelMapper modelMapper;

	public SubcategoryDTO mapEntityToDTO(Subcategory subcategory) {
		return modelMapper.map(subcategory, SubcategoryDTO.class);
	}

	public Subcategory mapDTOToEntity(SubcategoryDTO subcategoryDTO) {
		return modelMapper.map(subcategoryDTO, Subcategory.class);
	}
}
