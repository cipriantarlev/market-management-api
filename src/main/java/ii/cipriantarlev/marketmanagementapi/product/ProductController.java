/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.product;

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
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDTOForList>> getProducts() {
		var products = productService.findAll();

		if (products == null || products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
		var product = productService.findById(id);

		if (product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/barcodes/{barcodeValue}")
	public ResponseEntity<ProductDTO> getProductByBarcodeValue(@PathVariable String barcodeValue) {
		var product = productService.findByBarcodeValue(barcodeValue);

		if (product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
		if (productDTO.getId() != null && productService.findById(productDTO.getId()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		var product = productService.save(productDTO);
		var headers = new HttpHeaders();
		headers.setLocation(UriComponentsBuilder.fromPath("/products/{id}").buildAndExpand(product.getId()).toUri());
		return new ResponseEntity<>(product, headers, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO) {
		var product = productService.findById(productDTO.getId());

		if (product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		var savedProduct = productService.save(productDTO);
		return new ResponseEntity<>(savedProduct, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		var product = productService.findById(id);

		if (product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		productService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
