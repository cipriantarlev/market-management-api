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

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/products-code")
public class ProductCodeController {

	@Autowired
	private ProductCodeService productCodeService;

	@GetMapping
	public ResponseEntity<List<ProductCodeDTO>> getProductsCode() {
		List<ProductCodeDTO> productsCode = productCodeService.findAll();

		if (productsCode == null || productsCode.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(productsCode, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductCodeDTO> getProductCode(@PathVariable Long id) {
		var productCode = productCodeService.findById(id);

		if (productCode == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(productCode, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductCodeDTO> generateNewProductCode() {
		var generateNewProductCode = productCodeService.generateNewProductCode();
		return new ResponseEntity<>(generateNewProductCode, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductCode(@PathVariable Long id) {
		var productCode = productCodeService.findById(id);

		if (productCode == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		productCodeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
