/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

	@Autowired
	private ModelMapper modelMapper;

	public CategoryDTO mapEntityToDTO(Category category) {
		return modelMapper.map(category, CategoryDTO.class);
	}

	public Category mapDTOToEntity(CategoryDTO categoryDTO) {
		return modelMapper.map(categoryDTO, Category.class);
	}
}
