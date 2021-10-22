/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.productscode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ii.cipriantarlev.marketmanagementapi.util.Constants.*;

@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(PRODUCTS_CODE_ROOT_PATH)
public class ProductCodeController {

	@Autowired
	private ProductCodeService productCodeService;

	@GetMapping
	public ResponseEntity<List<ProductCodeDTO>> getProductsCode() {
		List<ProductCodeDTO> productsCode = productCodeService.findAll();
		return new ResponseEntity<>(productsCode, HttpStatus.OK);
	}

	@GetMapping(ID_PATH)
	public ResponseEntity<ProductCodeDTO> getProductCode(@PathVariable Long id) {
		var productCode = productCodeService.findById(id);
		return new ResponseEntity<>(productCode, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductCodeDTO> generateNewProductCode() {
		var generateNewProductCode = productCodeService.generateNewProductCode();
		return new ResponseEntity<>(generateNewProductCode, HttpStatus.OK);
	}

	@DeleteMapping(ID_PATH)
	public ResponseEntity<Void> deleteProductCode(@PathVariable Long id) {
		productCodeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
