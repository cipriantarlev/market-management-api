/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.product;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;

@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(PRODUCTS_ROOT_PATH)
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private RestControllerUtil restControllerUtil;

	@GetMapping
	public ResponseEntity<List<ProductDTOForList>> getProducts() {
		var products = productService.findAll();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping(ID_PATH)
	public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
		var product = productService.findById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping(BARCODES_ROOT_PATH + BARCODE_VALUE)
	public ResponseEntity<ProductDTO> getProductByBarcodeValue(@PathVariable String barcodeValue) {
		var product = productService.findByBarcodeValue(barcodeValue);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
		var product = productService.save(productDTO);
		var headers = restControllerUtil.setHttpsHeaderLocation(PRODUCTS_ROOT_PATH.concat(ID_PATH), product.getId());
		return new ResponseEntity<>(product, headers, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO) {
		var savedProduct = productService.update(productDTO);
		return new ResponseEntity<>(savedProduct, HttpStatus.OK);
	}

	@DeleteMapping(ID_PATH)
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(PRODUCT_BY_NAME_ROM)
	public ResponseEntity<Boolean> checkIfNameRomExists(@PathVariable String value) {
		return new ResponseEntity<>(productService.checkIfNameRomExists(value), HttpStatus.OK);
	}
	
	@GetMapping(PRODUCT_BY_NAME_RUS)
	public ResponseEntity<Boolean> checkIfNameRusExists(@PathVariable String value) {
		return new ResponseEntity<>(productService.checkIfNameRusExists(value), HttpStatus.OK);
	}
}
