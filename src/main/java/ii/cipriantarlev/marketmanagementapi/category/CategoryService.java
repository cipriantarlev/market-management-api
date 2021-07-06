package ii.cipriantarlev.marketmanagementapi.category;

import java.util.List;

public interface CategoryService {

	List<CategoryDTO> findAll();

	CategoryDTO findById(Integer id);

	Category save(CategoryDTO categoryDTO);

	void deleteById(Integer id);
}
