/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.product.Product;
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

/**
 * RestController class for {@link InvoiceProduct}.
 */
@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(INVOICE_PRODUCT_ROOT_PATH)
public class InvoiceProductController {

	/**
	 * {@link InvoiceProductService} used to manage {@link InvoiceProduct}.
	 */
	@Autowired
	private InvoiceProductService invoiceProductService;

	/**
	 * Utility class used to create headers for Post method.
	 */
	@Autowired
	private RestControllerUtil restControllerUtil;

	/**
	 * Method to expose all {@link InvoiceProduct} related to provided invoice id.
	 *
	 * @param invoiceId the is of the invoice related to {@link InvoiceProduct}.
	 * @return list of found {@link InvoiceProduct} related to provided invoice id
	 * and 200 status code. If the list is empty the 404 status code will be sent.
	 */
	@GetMapping(INVOICE_ID_PATH)
	public ResponseEntity<List<InvoiceProductDTO>> getInvoiceProductsByInvoiceId(@PathVariable Long invoiceId) {
		var invoiceProductsByInvoiceId = invoiceProductService.findAllByInvoiceId(invoiceId);
		return new ResponseEntity<>(invoiceProductsByInvoiceId, HttpStatus.OK);
	}

	/**
	 * Method to expose {@link InvoiceProduct} for provided id.
	 *
	 * @param productId the id of {@link InvoiceProduct} to be found.
	 * @return the found {@link InvoiceProduct} and 200 status code.
	 * If {@link InvoiceProduct} was not found the 404 status code will be sent.
	 */
	@GetMapping(PRODUCT_PATH + PRODUCT_ID_PATH)
	public ResponseEntity<InvoiceProductDTO> getInvoiceProductById(@PathVariable Long productId) {
		var invoiceProduct = invoiceProductService.findById(productId);
		return new ResponseEntity<>(invoiceProduct, HttpStatus.OK);
	}

	/**
	 * Method to save the provided {@link InvoiceProductDTO} in the database.
	 *
	 * @param invoiceProductDTO {@link InvoiceProductDTO} to be saved in the database.
	 * @return the saved {@link InvoiceProductDTO} and 200 status code.
	 * If in database already exists an {@link InvoiceProductDTO} with the same id,
	 * the {@link DTOFoundWhenSaveException} will be thrown and http code 400 bad request will be sent.
	 */
	@PostMapping(PRODUCT_PATH)
	public ResponseEntity<InvoiceProductDTO> createInvoiceProduct(
			@Valid @RequestBody InvoiceProductDTO invoiceProductDTO) {

		var invoiceProduct = invoiceProductService.save(invoiceProductDTO);
		var headers = restControllerUtil.setHttpsHeaderLocation(
				INVOICE_PRODUCT_ROOT_PATH.concat(PRODUCT_PATH).concat(PRODUCT_ID_PATH), invoiceProduct.getId());

		return new ResponseEntity<>(invoiceProduct, headers, HttpStatus.OK);
	}

	/**
	 * Method to updated the provided {@link InvoiceProductDTO} in the database.
	 *
	 * @param invoiceProductDTO {@link InvoiceProductDTO} to be updated in the database.
	 * @return the updated {@link InvoiceProductDTO} and 200 status code.
	 * If in database already exists an {@link InvoiceProductDTO} with the same id,
	 * the {@link DTOFoundWhenSaveException} will be thrown and http code 400 bad request will be sent.
	 */
	@PutMapping(PRODUCT_PATH)
	public ResponseEntity<InvoiceProductDTO> updateInvoiceProduct(
			@Valid @RequestBody InvoiceProductDTO invoiceProductDTO) {

		var savedInvoiceProduct = invoiceProductService.update(invoiceProductDTO);
		return new ResponseEntity<>(savedInvoiceProduct, HttpStatus.OK);
	}

	/**
	 * Method to update {@link Product#isChecked()} marker.
	 *
	 * @param productsToUpdate {@link Map} that consists of keys that hold that value for
	 * {@link Product#isChecked()} marker and values that hold the id of {@link Product}
	 * that should be updated.
	 * @return the number of updated rows.
	 */
	@PutMapping(IS_CHECKED)
	public ResponseEntity<Integer> updateIsCheckedMarker(@RequestBody Map<Boolean, List<Long>> productsToUpdate) {
		var updatedRows = invoiceProductService.updateIsProductChecked(productsToUpdate);
		return new ResponseEntity<>(updatedRows, HttpStatus.OK);
	}

	/**
	 * Method to delete {@link InvoiceProductDTO} with provided id.
	 *
	 * @param productId id of {@link InvoiceProductDTO} to be deleted.
	 * @return 200 status code if the {@link InvoiceProductDTO} was successfully deleted.
	 * If the provided {@link InvoiceProductDTO} was not found in the database,
	 * the {@link DTONotFoundException} will be thrown and http code 404 not found will be sent.
	 */
	@DeleteMapping(PRODUCT_PATH + PRODUCT_ID_PATH)
	public ResponseEntity<Void> deleteInvoiceProductById(@PathVariable Long productId) {
		invoiceProductService.deleteById(productId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
