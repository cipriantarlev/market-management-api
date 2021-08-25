/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vendor;

import java.util.List;

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

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/vendors")
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@Autowired
	private VendorMapper vendorMapper;

	@GetMapping
	public ResponseEntity<List<VendorDTONoRegions>> getVendors() {
		List<VendorDTONoRegions> vendors = vendorService.findAll();

		if (vendors == null || vendors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(vendors, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VendorDTO> getVendor(@PathVariable Integer id) {
		var vendor = vendorService.findById(id);

		if (vendor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(vendor, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<VendorDTO> createVendor(@RequestBody VendorDTO vendorDTO) {
		if (vendorDTO.getId() != null && vendorService.findById(vendorDTO.getId()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		var vendor = vendorMapper.mapVendorToVendorDTO(vendorService.save(vendorDTO));
		var headers = new HttpHeaders();
		headers.setLocation(UriComponentsBuilder.fromPath("/vendors/{id}").buildAndExpand(vendor.getId()).toUri());
		return new ResponseEntity<>(vendor, headers, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<VendorDTO> updateVendor(@RequestBody VendorDTO vendorDTO) {
		var vendor = vendorService.findById(vendorDTO.getId());

		if (vendor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		var savedVendor = vendorMapper.mapVendorToVendorDTO(vendorService.save(vendorDTO));
		return new ResponseEntity<>(savedVendor, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVendor(@PathVariable Integer id) {
		var vendor = vendorService.findById(id);

		if (vendor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		vendorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
