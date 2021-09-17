/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

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

import ii.cipriantarlev.marketmanagementapi.product.ProductService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/invoice-products")
public class InvoiceProductController {

	@Autowired
	private InvoiceProductService invoiceProductService;

	@Autowired
	private ProductService productService;

	@GetMapping("/{invoiceId}")
	public ResponseEntity<List<InvoiceProductDTO>> getInvoiceProductsByInvoiceId(@PathVariable Long invoiceId) {
		var invoiceProductsByInvoiceId = invoiceProductService.findAllByInvoiceId(invoiceId);

		if (invoiceProductsByInvoiceId == null || invoiceProductsByInvoiceId.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(invoiceProductsByInvoiceId, HttpStatus.OK);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<InvoiceProductDTO> getInvoiceProductById(@PathVariable Long productId) {
		var invoiceProduct = invoiceProductService.findById(productId);

		if (invoiceProduct == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(invoiceProduct, HttpStatus.OK);
	}

	@PostMapping("/product")
	public ResponseEntity<InvoiceProductDTO> createInvoiceProduct(
			@Valid @RequestBody InvoiceProductDTO invoiceProductDTO) {
		if (invoiceProductDTO.getId() != null && invoiceProductService.findById(invoiceProductDTO.getId()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		var invoiceProduct = invoiceProductService.save(invoiceProductDTO);

		productService.save(invoiceProductDTO.getProduct());

		var headers = new HttpHeaders();
		headers.setLocation(UriComponentsBuilder.fromPath("/invoice-products/product/{productId}")
				.buildAndExpand(invoiceProduct.getId()).toUri());
		return new ResponseEntity<>(invoiceProduct, headers, HttpStatus.OK);
	}

	@PutMapping("/product")
	public ResponseEntity<InvoiceProductDTO> updateInvoiceProduct(
			@Valid @RequestBody InvoiceProductDTO invoiceProductDTO) {
		var invoiceProduct = invoiceProductService.findAllByInvoiceId(invoiceProductDTO.getId());

		if (invoiceProduct == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		var savedInvoiceProduct = invoiceProductService.save(invoiceProductDTO);
		return new ResponseEntity<>(savedInvoiceProduct, HttpStatus.OK);
	}

	@DeleteMapping("/product/{productId}")
	public ResponseEntity<Void> deleteInvoiceProductById(@PathVariable Long productId) {
		var invoiceProduct = invoiceProductService.findById(productId);

		if (invoiceProduct == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		var product = invoiceProduct.getProduct();
		product.setStock(product.getStock().subtract(invoiceProduct.getQuantity()));
		productService.save(product);

		invoiceProductService.deleteById(productId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
