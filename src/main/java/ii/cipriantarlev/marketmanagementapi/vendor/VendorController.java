/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vendor;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping(VENDORS_ROOT_PATH)
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@Autowired
	private RestControllerUtil restControllerUtil;

	@GetMapping
	public ResponseEntity<List<VendorDTONoRegions>> getVendors() {
		List<VendorDTONoRegions> vendors = vendorService.findAll();
		return new ResponseEntity<>(vendors, HttpStatus.OK);
	}

	@GetMapping(ID_PATH)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<VendorDTO> getVendor(@PathVariable Long id) {
		var vendor = vendorService.findById(id);
		return new ResponseEntity<>(vendor, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<VendorDTO> createVendor(@Valid @RequestBody VendorDTO vendorDTO) {
		var vendor = vendorService.save(vendorDTO);
		var headers = restControllerUtil.setHttpsHeaderLocation(VENDORS_ROOT_PATH.concat(ID_PATH),
				vendor.getId().longValue());
		return new ResponseEntity<>(vendor, headers, HttpStatus.OK);
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<VendorDTO> updateVendor(@Valid @RequestBody VendorDTO vendorDTO) {
		var savedVendor = vendorService.update(vendorDTO);
		return new ResponseEntity<>(savedVendor, HttpStatus.OK);
	}

	@DeleteMapping(ID_PATH)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
		vendorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
