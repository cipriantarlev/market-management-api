/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.category;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

import java.util.List;

/**
 * {@link Category} service used to manage it.
 */
public interface CategoryService {

	/**
	 * Method to find all {@link CategoryDTO} in database.
	 *
	 * @return the list of found categories.
	 * If the list is empty the {@link DTOListNotFoundException}
	 * will be thrown.
	 */
	List<CategoryDTO> findAll();

	/**
	 * Method to find {@link CategoryDTO} by provided id.
	 *
	 * @param id of category to found.
	 * @return the found {@link CategoryDTO}.
	 * If the category is not found the {@link DTONotFoundException}
	 * will be thrown.
	 */
	CategoryDTO findById(Long id);

	/**
	 * Method to update the provided {@link CategoryDTO} as {@link Category}
	 * in the database.
	 *
	 * @param categoryDTO that should be updated.
	 * @return the updated category. If the category is not found the
	 * {@link DTONotFoundException} will be thrown.
	 */
	CategoryDTO update(CategoryDTO categoryDTO);

	/**
	 * Method to save the provided {@link CategoryDTO} as {@link Category}
	 * in the database.
	 *
	 * @param categoryDTO that should be saved in the database.
	 * @return the new created category. If the category is not found the
	 * {@link DTOFoundWhenSaveException} will be thrown.
	 */
	CategoryDTO save(CategoryDTO categoryDTO);

	/**
	 * Method to delete a {@link Category} based on provided id.
	 * If the category is not found the {@link DTONotFoundException}
	 * will be thrown.
	 *
	 * @param id of the {@link Category} to be deleted.
	 */
	void deleteById(Long id);
}
