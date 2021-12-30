/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.subcategory;

import java.util.List;

public interface SubcategoryService {

	List<SubcategoryDTO> findAll();

	List<SubcategoryDTONoCategory> findAllByCategoryId(Long id);

	SubcategoryDTO findById(Long id);

	SubcategoryDTO save(SubcategoryDTO subcategoryDTO);

	SubcategoryDTO update(SubcategoryDTO subcategoryDTO);

	void deleteById(Long id);
}
