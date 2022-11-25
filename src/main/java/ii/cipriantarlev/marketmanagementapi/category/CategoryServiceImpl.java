/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.category;

import java.util.List;
import java.util.stream.Collectors;

import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistory;
import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

/**
 * Class to implement {@link CategoryService} interface.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	/**
	 * {@link CategoryRepository} used to connect with database.
	 */
	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * {@link CategoryMapper} used to map entity to dto and vice-versa.
	 */
	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * {@link EntitiesHistoryService} used to create {@link EntitiesHistory}
	 * records in database based on action performed on {@link Category}.
	 */
	@Autowired
	private EntitiesHistoryService entitiesHistoryService;

	@Override
	public List<CategoryDTO> findAll() {
		List<CategoryDTO> categories = categoryRepository.findAll().stream()
				.map(category -> categoryMapper.mapEntityToDTO(category))
				.collect(Collectors.toList());

		if (categories.isEmpty()) {
			throw new DTOListNotFoundException("Category list not found");
		}

		return categories;
	}

	@Override
	public CategoryDTO findById(Long id) {
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
		var savedCategory = categoryRepository.save(category);
		entitiesHistoryService.createEntityHistoryRecord(savedCategory, null, HistoryAction.CREATE);
		return categoryMapper.mapEntityToDTO(savedCategory);
	}

	@Override
	public CategoryDTO update(CategoryDTO categoryDTO) {
		var foundCategory = categoryMapper.mapDTOToEntity(this.findById(categoryDTO.getId()));
		var savedCategory = categoryRepository.save(categoryMapper.mapDTOToEntity(categoryDTO));
		entitiesHistoryService.createEntityHistoryRecord(savedCategory, foundCategory, HistoryAction.UPDATE);
		return categoryMapper.mapEntityToDTO(savedCategory);
	}

	@Override
	public void deleteById(Long id) {
		entitiesHistoryService.createEntityHistoryRecord(categoryMapper.mapDTOToEntity(this.findById(id)), null, HistoryAction.DELETE);
		categoryRepository.deleteById(id);
	}
}