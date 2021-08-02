package ii.cipriantarlev.marketmanagementapi.subcategory;

import java.util.List;

public interface SubcategoryService {

	List<SubcategoryDTO> findAll();

	List<SubcategoryDTONoCategory> findAllByCategoryId(Integer id);

	SubcategoryDTO findById(Integer id);

	SubcategoryDTO save(SubcategoryDTO subcategoryDTO);

	void deleteById(Integer id);
}
