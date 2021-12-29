/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.category;

import java.util.List;

public interface CategoryService {

	List<CategoryDTO> findAll();

	CategoryDTO findById(Long id);

	CategoryDTO update(CategoryDTO categoryDTO);

	CategoryDTO save(CategoryDTO categoryDTO);

	void deleteById(Long id);
}
