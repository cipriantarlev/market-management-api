/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class used to map {@link Category} to {@link CategoryDTO} and vice-versa.
 */
@Component
public class CategoryMapper {

	/**
	 * {@link ModelMapper} is used to map entity to dto.
	 */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Method used to map {@link Category} to {@link CategoryDTO}
	 *
	 * @param category entity to be mapped.
	 * @return resulted dto.
	 */
	public CategoryDTO mapEntityToDTO(Category category) {
		return modelMapper.map(category, CategoryDTO.class);
	}

	/**
	 * Method used to map {@link CategoryDTO} to {@link Category}
	 *
	 * @param categoryDTO DTO to be mapped.
	 * @return resulted entity.
	 */
	public Category mapDTOToEntity(CategoryDTO categoryDTO) {
		return modelMapper.map(categoryDTO, Category.class);
	}
}
