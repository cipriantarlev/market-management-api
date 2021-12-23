/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.category;

import java.util.List;
import java.util.stream.Collectors;

import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
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

	@Autowired
	private EntitiesHistoryService entitiesHistoryService;

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
		var savedCategoryDTO = categoryMapper.mapEntityToDTO(categoryRepository.save(category));
		entitiesHistoryService.createEntityHistoryRecord(savedCategoryDTO, null, HistoryAction.CREATE);
		return savedCategoryDTO;
	}

	@Override
	public CategoryDTO update(CategoryDTO categoryDTO) {
		var foundCategoryDTO = this.findById(categoryDTO.getId());
		var category = categoryMapper.mapDTOToEntity(categoryDTO);
		var savedSubcategoryDTO = categoryMapper.mapEntityToDTO(categoryRepository.save(category));
		entitiesHistoryService.createEntityHistoryRecord(savedSubcategoryDTO, foundCategoryDTO, HistoryAction.UPDATE);
		return savedSubcategoryDTO;
	}

	@Override
	public void deleteById(Integer id) {
		entitiesHistoryService.createEntityHistoryRecord(this.findById(id), null, HistoryAction.DELETE);
		categoryRepository.deleteById(id);
	}
}
