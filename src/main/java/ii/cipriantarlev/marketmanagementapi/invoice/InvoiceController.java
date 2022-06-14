/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentType;
import ii.cipriantarlev.marketmanagementapi.utils.MarketManagementFactory;
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

@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(INVOICES_ROOT_PATH)
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private MyOrganizationService myOrganizationService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private RestControllerUtil restControllerUtil;

	@Autowired
	private MarketManagementFactory factory;

	@GetMapping
	public ResponseEntity<List<InvoiceDTO>> getInvoices() {
		var invoices = invoiceService.findAll();
		return new ResponseEntity<>(invoices, HttpStatus.OK);
	}

	@GetMapping(INCOME_INVOICES)
	public ResponseEntity<List<InvoiceDTO>> getIncomeInvoices() {
		var invoices = invoiceService.findInvoicesByDocumentType(factory.getNewDocumentType(1L));
		return new ResponseEntity<>(invoices, HttpStatus.OK);
	}

	@GetMapping(OUTCOME_INVOICES)
	public ResponseEntity<List<InvoiceDTO>> getOutcomeInvoices() {
		var invoices = invoiceService.findInvoicesByDocumentType(factory.getNewDocumentType(2L));
		return new ResponseEntity<>(invoices, HttpStatus.OK);
	}

	@GetMapping(ID_PATH)
	public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable Long id) {
		var invoice = invoiceService.findById(id);
		return new ResponseEntity<>(invoice, HttpStatus.OK);
	}

	@GetMapping(MY_ORGANIZATIONS_ROOT_PATH)
	public ResponseEntity<List<MyOrganizationDTOOnlyName>> getMyOrganizations() {
		var myOrganizations = myOrganizationService.findAllMyOrganizationDTOOnlyName();
		return new ResponseEntity<>(myOrganizations, HttpStatus.OK);
	}

	@GetMapping(VENDORS_ROOT_PATH)
	public ResponseEntity<List<VendorDTOOnlyName>> getVendors() {
		var vendors = vendorService.findAllVendorDTOOnlyName();
		return new ResponseEntity<>(vendors, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InvoiceDTO> createInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
		var invoice = invoiceService.save(invoiceDTO);
		var headers = restControllerUtil.setHttpsHeaderLocation(INVOICES_ROOT_PATH, invoice.getId());
		return new ResponseEntity<>(invoice, headers, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<InvoiceDTO> updateInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
		var savedInvoice = invoiceService.update(invoiceDTO);
		return new ResponseEntity<>(savedInvoice, HttpStatus.OK);
	}

	@PutMapping(IS_APPROVED_INVOICE)
	public ResponseEntity<Integer> updateInvoiceIsApprovedMarker(@RequestBody Map<Boolean, List<Long>> invoicesToUpdate) {
		var updatedRows = invoiceService.updateIsApprovedMarker(invoicesToUpdate);
		return new ResponseEntity<>(updatedRows, HttpStatus.OK);
	}

	@DeleteMapping(ID_PATH)
	public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
		invoiceService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
