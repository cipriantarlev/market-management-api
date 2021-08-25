/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.subcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/subcategories")
public class SubcategoryController {

	@Autowired
	private SubcategoryService subcategoryService;

	@GetMapping
	public ResponseEntity<List<SubcategoryDTO>> getSubcategories() {
		List<SubcategoryDTO> subcategories = subcategoryService.findAll();

		if (subcategories == null || subcategories.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(subcategories, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<SubcategoryDTONoCategory>> getSubcategoriesByCategoryId(@PathVariable Integer categoryId) {
		List<SubcategoryDTONoCategory> subcategories = subcategoryService.findAllByCategoryId(categoryId);

		if (subcategories == null || subcategories.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(subcategories, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubcategoryDTO> getSubcategory(@PathVariable Integer id) {
		var subcategory = subcategoryService.findById(id);

		if (subcategory == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(subcategory, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<SubcategoryDTO> createSubcategory(@RequestBody SubcategoryDTO subcategoryDTO) {
		if (subcategoryDTO.getId() != null && subcategoryService.findById(subcategoryDTO.getId()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		var subcategory = subcategoryService.save(subcategoryDTO);
		var headers = new HttpHeaders();
		headers.setLocation(
				UriComponentsBuilder.fromPath("/subcategories/{id}").buildAndExpand(subcategory.getId()).toUri());
		return new ResponseEntity<>(subcategory, headers, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<SubcategoryDTO> updateSubcategory(@RequestBody SubcategoryDTO subcategoryDTO) {
		var subcategory = subcategoryService.findById(subcategoryDTO.getId());

		if (subcategory == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		var savedSubcategory = subcategoryService.save(subcategoryDTO);
		return new ResponseEntity<>(savedSubcategory, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSubcategory(@PathVariable Integer id) {
		var subcategory = subcategoryService.findById(id);

		if (subcategory == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		subcategoryService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
