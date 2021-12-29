/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.subcategory;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

import java.util.List;

import javax.validation.Valid;

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

@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(SUBCATEGORIES_ROOT_PATH)
public class SubcategoryController {

	@Autowired
	private SubcategoryService subcategoryService;

	@Autowired
	private RestControllerUtil restControllerUtil;

	@GetMapping
	public ResponseEntity<List<SubcategoryDTO>> getSubcategories() {
		List<SubcategoryDTO> subcategories = subcategoryService.findAll();
		return new ResponseEntity<>(subcategories, HttpStatus.OK);
	}

	@GetMapping(CATEGORY_CATEGORY_ID)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<SubcategoryDTONoCategory>> getSubcategoriesByCategoryId(@PathVariable Long categoryId) {
		List<SubcategoryDTONoCategory> subcategories = subcategoryService.findAllByCategoryId(categoryId);
		return new ResponseEntity<>(subcategories, HttpStatus.OK);
	}

	@GetMapping(ID_PATH)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<SubcategoryDTO> getSubcategory(@PathVariable Long id) {
		var subcategory = subcategoryService.findById(id);
		return new ResponseEntity<>(subcategory, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<SubcategoryDTO> createSubcategory(@Valid @RequestBody SubcategoryDTO subcategoryDTO) {
		var subcategory = subcategoryService.save(subcategoryDTO);
		var headers = restControllerUtil.setHttpsHeaderLocation(SUBCATEGORIES_ROOT_PATH.concat(ID_PATH),
				subcategory.getId().longValue());
		return new ResponseEntity<>(subcategory, headers, HttpStatus.OK);
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<SubcategoryDTO> updateSubcategory(@Valid @RequestBody SubcategoryDTO subcategoryDTO) {
		var savedSubcategory = subcategoryService.update(subcategoryDTO);
		return new ResponseEntity<>(savedSubcategory, HttpStatus.OK);
	}

	@DeleteMapping(ID_PATH)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteSubcategory(@PathVariable Long id) {
		subcategoryService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
