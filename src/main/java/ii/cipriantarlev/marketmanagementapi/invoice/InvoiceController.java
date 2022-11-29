/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganization;
import ii.cipriantarlev.marketmanagementapi.utils.MarketManagementFactory;
import ii.cipriantarlev.marketmanagementapi.vendor.Vendor;
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

import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTOOnlyName;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationService;
import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorDTOOnlyName;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorService;

/**
 * RestController class for {@link Invoice}.
 */
@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(INVOICES_ROOT_PATH)
public class InvoiceController {

	/**
	 * {@link InvoiceService} used to manage {@link Invoice}.
	 */
	@Autowired
	private InvoiceService invoiceService;

	/**
	 * {@link MyOrganizationService} used to manage {@link MyOrganization}
	 */
	@Autowired
	private MyOrganizationService myOrganizationService;

	/**
	 * {@link VendorService} used to manage {@link Vendor}
	 */
	@Autowired
	private VendorService vendorService;

	/**
	 * Utility class used to create headers for Post method.
	 */
	@Autowired
	private RestControllerUtil restControllerUtil;

	/**
	 * Factory class used to create new instances based on input.
	 */
	@Autowired
	private MarketManagementFactory factory;

	/**
	 * Method to expose all {@link Invoice} found in the database.
	 *
	 * @return list of found invoices and 200 status code.
	 * If the list is empty the 404 status code will be sent.
	 */
	@GetMapping
	public ResponseEntity<List<InvoiceDTO>> getInvoices() {
		var invoices = invoiceService.findAll();
		return new ResponseEntity<>(invoices, HttpStatus.OK);
	}

	/**
	 * Method to expose income {@link Invoice} found in the database.
	 *
	 * @return list of found income invoices and 200 status code.
	 * If the list is empty the 404 status code will be sent.
	 */
	@GetMapping(INCOME_INVOICES)
	public ResponseEntity<List<InvoiceDTO>> getIncomeInvoices() {
		var invoices = invoiceService.findInvoicesByDocumentType(factory.getNewDocumentType(1L));
		return new ResponseEntity<>(invoices, HttpStatus.OK);
	}

	/**
	 * Method to expose outcome {@link Invoice} found in the database.
	 *
	 * @return list of found outcome invoices and 200 status code.
	 * If the list is empty the 404 status code will be sent.
	 */
	@GetMapping(OUTCOME_INVOICES)
	public ResponseEntity<List<InvoiceDTO>> getOutcomeInvoices() {
		var invoices = invoiceService.findInvoicesByDocumentType(factory.getNewDocumentType(2L));
		return new ResponseEntity<>(invoices, HttpStatus.OK);
	}

	/**
	 * Method to expose the {@link Invoice} for provided id.
	 *
	 * @param id the id of invoice to be found.
	 * @return the found invoice and 200 status code.
	 * If invoice was not found the 404 status code will be sent.
	 */
	@GetMapping(ID_PATH)
	public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable Long id) {
		var invoice = invoiceService.findById(id);
		return new ResponseEntity<>(invoice, HttpStatus.OK);
	}

	/**
	 * Method to expose {@link MyOrganization} found in the database
	 * that will be used to create a new {@link Invoice}.
	 *
	 * @return list of found {@link MyOrganization} and 200 status code.
	 * If the list is empty the 404 status code will be sent.
	 */
	@GetMapping(MY_ORGANIZATIONS_ROOT_PATH)
	public ResponseEntity<List<MyOrganizationDTOOnlyName>> getMyOrganizations() {
		var myOrganizations = myOrganizationService.findAllMyOrganizationDTOOnlyName();
		return new ResponseEntity<>(myOrganizations, HttpStatus.OK);
	}

	/**
	 * Method to expose {@link Vendor} found in the database
	 * that will be used to create a new {@link Invoice}.
	 *
	 * @return list of found {@link Vendor} and 200 status code.
	 * If the list is empty the 404 status code will be sent.
	 */
	@GetMapping(VENDORS_ROOT_PATH)
	public ResponseEntity<List<VendorDTOOnlyName>> getVendors() {
		var vendors = vendorService.findAllVendorDTOOnlyName();
		return new ResponseEntity<>(vendors, HttpStatus.OK);
	}

	/**
	 * Method to save the provided {@link InvoiceDTO} in the database.
	 *
	 * @param invoiceDTO {@link InvoiceDTO} to be saved in database.
	 * @return the saved {@link InvoiceDTO } and 200 status code.
	 * If in database already exists an invoice with the same id, the {@link DTOFoundWhenSaveException}
	 * will be thrown and http code 400 bad request will be sent.
	 */
	@PostMapping
	public ResponseEntity<InvoiceDTO> createInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
		var invoice = invoiceService.save(invoiceDTO);
		var headers = restControllerUtil.setHttpsHeaderLocation(INVOICES_ROOT_PATH, invoice.getId());
		return new ResponseEntity<>(invoice, headers, HttpStatus.OK);
	}

	/**
	 * Method to update the provided {@link InvoiceDTO} in the database.
	 *
	 * @param invoiceDTO {@link InvoiceDTO} to be updated in database.
	 * @return the updated {@link InvoiceDTO} and 200 status code.
	 * If the provided document type was not found in the database, the {@link DTONotFoundException}
	 * will be thrown and http code 404 not found will be sent.
	 */
	@PutMapping
	public ResponseEntity<InvoiceDTO> updateInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
		var savedInvoice = invoiceService.update(invoiceDTO);
		return new ResponseEntity<>(savedInvoice, HttpStatus.OK);
	}

	/**
	 * Method to update {@link Invoice#isApproved()} marker.
	 *
	 * @param invoicesToUpdate {@link Map} that consists of keys that hold that value for
	 * {@link Invoice#isApproved()} marker and values that hold the id of {@link Invoice}
	 * that should be updated.
	 * @return the number of updated rows.
	 */
	@PutMapping(IS_APPROVED)
	public ResponseEntity<Integer> updateInvoiceIsApprovedMarker(@RequestBody Map<Boolean, List<Long>> invoicesToUpdate) {
		var updatedRows = invoiceService.updateIsApprovedMarker(invoicesToUpdate);
		return new ResponseEntity<>(updatedRows, HttpStatus.OK);
	}

	/**
	 * Method to delete the {@link Invoice} with provided id.
	 *
	 * @param id id of {@link Invoice} to be deleted.
	 * @return 200 status code if the {@link Invoice} was successfully deleted.
	 * 	If the provided invoice was not found in the database, the {@link DTONotFoundException}
	 * 	will be thrown and http code 404 not found will be sent.
	 */
	@DeleteMapping(ID_PATH)
	public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
		invoiceService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
