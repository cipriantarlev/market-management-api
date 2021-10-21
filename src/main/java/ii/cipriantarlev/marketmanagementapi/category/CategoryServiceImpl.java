/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<CategoryDTO> findAll() {
		List<CategoryDTO> categories = categoryRepository.findAll().stream()
				.map(category -> categoryMapper.mapEntityToDTO(category))
				.collect(Collectors.toList());

		if (categories == null || categories.isEmpty()) {
			throw new DTOListNotFoundException("Category list not found");
		}

		return categories;
	}

	@Override
	public CategoryDTO findById(Integer id) {
		var category = categoryRepository.findById(id);

		if (category.isPresent()) {
			return categoryMapper.mapEntityToDTO(category.get());
		}

		throw new DTONotFoundException(String.format("Category with %d not found", id), id);
	}

	@Override
	public CategoryDTO save(CategoryDTO categoryDTO) {
		if (categoryDTO.getId() != null && 
				categoryRepository.findById(categoryDTO.getId()).isPresent()) {
			throw new DTOFoundWhenSaveException(
					String.format(
							"Category with id: '%d' already exists in database. "
							+ "Please use update in order to save the changes in database", 
							categoryDTO.getId()), categoryDTO.getId());
		}
		var category = categoryMapper.mapDTOToEntity(categoryDTO);
		return categoryMapper.mapEntityToDTO(categoryRepository.save(category));
	}

	@Override
	public CategoryDTO update(CategoryDTO categoryDTO) {
		this.findById(categoryDTO.getId());
		var category = categoryMapper.mapDTOToEntity(categoryDTO);
		return categoryMapper.mapEntityToDTO(categoryRepository.save(category));
	}

	@Override
	public void deleteById(Integer id) {
		this.findById(id);
		categoryRepository.deleteById(id);
	}
}
