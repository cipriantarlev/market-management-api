/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<CategoryDTO> findAll() {
		return categoryRepository.findAll().stream().map(category -> categoryMapper.mapEntityToDTO(category))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO findById(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);

		if (category.isPresent()) {
			return categoryMapper.mapEntityToDTO(category.get());
		}

		return null;
	}

	@Override
	public Category save(CategoryDTO categoryDTO) {

		var category = categoryMapper.mapDTOToEntity(categoryDTO);
		return categoryRepository.save(category);
	}

	@Override
	public void deleteById(Integer id) {
		categoryRepository.deleteById(id);
	}

}
