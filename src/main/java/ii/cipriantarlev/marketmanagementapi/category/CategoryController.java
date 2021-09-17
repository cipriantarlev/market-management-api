/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.category;

import java.util.List;

import javax.validation.Valid;

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
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryMapper categoryMapper;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		List<CategoryDTO> categories = categoryService.findAll();

		if (categories == null || categories.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer id) {
		var category = categoryService.findById(id);

		if (category == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		if (categoryDTO.getId() != null && categoryService.findById(categoryDTO.getId()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		var category = categoryMapper.mapEntityToDTO(categoryService.save(categoryDTO));
		var headers = new HttpHeaders();
		headers.setLocation(UriComponentsBuilder.fromPath("/categories/{id}").buildAndExpand(category.getId()).toUri());
		return new ResponseEntity<>(category, headers, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		var category = categoryService.findById(categoryDTO.getId());

		if (category == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		var savedCategory = categoryMapper.mapEntityToDTO(categoryService.save(categoryDTO));
		return new ResponseEntity<>(savedCategory, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
		var category = categoryService.findById(id);

		if (category == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		categoryService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
