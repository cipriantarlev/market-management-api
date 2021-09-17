/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

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

import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTOOnlyName;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationService;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorDTOOnlyName;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private MyOrganizationService myOrganizationService;

	@Autowired
	private VendorService vendorService;

	@GetMapping
	public ResponseEntity<List<InvoiceDTO>> getInvoices() {
		var invoices = invoiceService.findAll();

		if (invoices == null || invoices.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(invoices, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable Long id) {
		var invoice = invoiceService.findById(id);

		if (invoice == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(invoice, HttpStatus.OK);
	}

	@GetMapping("/my-organizations")
	public ResponseEntity<List<MyOrganizationDTOOnlyName>> getMyOrganizations() {
		var myOrganizations = myOrganizationService.findAllMyOrganizationDTOOnlyName();

		if (myOrganizations == null || myOrganizations.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(myOrganizations, HttpStatus.OK);
	}

	@GetMapping("/vendors")
	public ResponseEntity<List<VendorDTOOnlyName>> getVendors() {
		var vendors = vendorService.findAllVendorDTOOnlyName();

		if (vendors == null || vendors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(vendors, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InvoiceDTO> createInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
		if (invoiceDTO.getId() != null && invoiceService.findById(invoiceDTO.getId()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		var invoice = invoiceService.save(invoiceDTO);
		var headers = new HttpHeaders();
		headers.setLocation(UriComponentsBuilder.fromPath("/invoices/{id}").buildAndExpand(invoice.getId()).toUri());
		return new ResponseEntity<>(invoice, headers, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<InvoiceDTO> updateInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
		var invoice = invoiceService.findById(invoiceDTO.getId());

		if (invoice == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		var savedInvoice = invoiceService.save(invoiceDTO);
		return new ResponseEntity<>(savedInvoice, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
		var invoice = invoiceService.findById(id);

		if (invoice == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		invoiceService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
