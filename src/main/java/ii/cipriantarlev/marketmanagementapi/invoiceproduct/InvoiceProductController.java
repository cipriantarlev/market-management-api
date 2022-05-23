/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

import java.util.List;
import java.util.Map;

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
@RequestMapping(INVOICE_PRODUCT_ROOT_PATH)
public class InvoiceProductController {

	@Autowired
	private InvoiceProductService invoiceProductService;

	@Autowired
	private RestControllerUtil restControllerUtil;

	@GetMapping(INVOICE_ID_PATH)
	public ResponseEntity<List<InvoiceProductDTO>> getInvoiceProductsByInvoiceId(@PathVariable Long invoiceId) {
		var invoiceProductsByInvoiceId = invoiceProductService.findAllByInvoiceId(invoiceId);
		return new ResponseEntity<>(invoiceProductsByInvoiceId, HttpStatus.OK);
	}

	@GetMapping(PRODUCT_PATH + PRODUCT_ID_PATH)
	public ResponseEntity<InvoiceProductDTO> getInvoiceProductById(@PathVariable Long productId) {
		var invoiceProduct = invoiceProductService.findById(productId);
		return new ResponseEntity<>(invoiceProduct, HttpStatus.OK);
	}

	@PostMapping(PRODUCT_PATH)
	public ResponseEntity<InvoiceProductDTO> createInvoiceProduct(
			@Valid @RequestBody InvoiceProductDTO invoiceProductDTO) {

		var invoiceProduct = invoiceProductService.save(invoiceProductDTO);
		var headers = restControllerUtil.setHttpsHeaderLocation(
				INVOICE_PRODUCT_ROOT_PATH.concat(PRODUCT_PATH).concat(PRODUCT_ID_PATH), invoiceProduct.getId());

		return new ResponseEntity<>(invoiceProduct, headers, HttpStatus.OK);
	}

	@PutMapping(PRODUCT_PATH)
	public ResponseEntity<InvoiceProductDTO> updateInvoiceProduct(
			@Valid @RequestBody InvoiceProductDTO invoiceProductDTO) {

		var savedInvoiceProduct = invoiceProductService.update(invoiceProductDTO);
		return new ResponseEntity<>(savedInvoiceProduct, HttpStatus.OK);
	}

	@PutMapping(IS_CHECKED_PRODUCT)
	public ResponseEntity<Integer> updateIsCheckedMarker(@RequestBody Map<Boolean, List<Long>> productsToUpdate) {
		var updatedRows = invoiceProductService.updateIsProductChecked(productsToUpdate);
		return new ResponseEntity<>(updatedRows, HttpStatus.OK);
	}

	@DeleteMapping(PRODUCT_PATH + PRODUCT_ID_PATH)
	public ResponseEntity<Void> deleteInvoiceProductById(@PathVariable Long productId) {
		invoiceProductService.deleteById(productId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
