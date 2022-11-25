/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.category;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

import java.util.List;

import javax.validation.Valid;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.role.Role;
import ii.cipriantarlev.marketmanagementapi.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;

/**
 * RestController class for {@link Category}.
 */
@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(CATEGORIES_ROOT_PATH)
public class CategoryController {

	/**
	 * {@link CategoryService} used to manage {@link Category}
	 */
	@Autowired
	private CategoryService categoryService;

	/**
	 * Utility class used to create headers for Post method
	 */
	@Autowired
	private RestControllerUtil restControllerUtil;

	/**
	 * Method to expose all categories found in the database.
	 *
	 * @return list of found categories and 200 status code.
	 * If the list is empty the 404 status code will be sent.
	 */
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		List<CategoryDTO> categories = categoryService.findAll();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	/**
	 * Method to expose the {@link Category} for provided id.
	 * Only {@link User}
	 * with ADMIN {@link Role}
	 * has access to this method.
	 *
	 * @param id of category to be found.
	 * @return the found category and 200 status code.
	 * If category was not found the 404 status code will be sent.
	 * If user doesn't have ADMIN {@link Role}
	 * he will receive Http code 401 Unauthorized request.
	 */
	@GetMapping(ID_PATH)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
		var category = categoryService.findById(id);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	/**
	 * Method to save the provided {@link CategoryDTO} in database.
	 * Only {@link User} with ADMIN {@link Role} has access to this method.
	 *
	 * @param categoryDTO to be saved in database.
	 * @return the saved {@link Category} and 200 status code.
	 * If in database already exists a category with the same id, the {@link DTOFoundWhenSaveException}
	 * will be thrown and http code 400 bad request will be sent.
	 * If user doesn't have ADMIN {@link Role}
	 * he will receive Http code 401 Unauthorized request.
	 */
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		var category = categoryService.save(categoryDTO);
		var headers = restControllerUtil
				.setHttpsHeaderLocation(CATEGORIES_ROOT_PATH, category.getId());
		return new ResponseEntity<>(category, headers, HttpStatus.OK);
	}

	/**
	 * Method to update the provided {@link CategoryDTO} in database.
	 * Only {@link User} with ADMIN {@link Role} has access to this method.
	 *
	 * @param categoryDTO to be updated.
	 * @return the updated {@link Category} and 200 status code.
	 * If the provided category was not found in the database, the {@link DTONotFoundException}
	 * will be thrown and http code 404 not found will be sent.
	 * If user doesn't have ADMIN {@link Role}
	 * he will receive Http code 401 Unauthorized request.
	 */
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		var savedCategory = categoryService.update(categoryDTO);
		return new ResponseEntity<>(savedCategory, HttpStatus.OK);
	}

	/**
	 * Method to delete the {@link Category} with provided id.
	 *
	 * @param id of category to be deleted.
	 * @return 200 status code if the {@link Category} was successfully deleted.
	 * If the provided category was not found in the database, the {@link DTONotFoundException}
	 * will be thrown and http code 404 not found will be sent.
	 * If user doesn't have ADMIN {@link Role}
	 * he will receive Http code 401 Unauthorized request.
	 */
	@DeleteMapping(ID_PATH)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
