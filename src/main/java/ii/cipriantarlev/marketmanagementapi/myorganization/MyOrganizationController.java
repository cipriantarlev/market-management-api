/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.myorganization;

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

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/my-organizations")
public class MyOrganizationController {

	@Autowired
	private MyOrganizationService myOrganizationService;

	@Autowired
	private MyOrganizationMapper myOrganizationMapper;

	@GetMapping
	public ResponseEntity<List<MyOrganizationDTO>> getMyOrganizations() {
		List<MyOrganizationDTO> myOrganizations = myOrganizationService.findAll();
		
		if(myOrganizations == null || myOrganizations.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(myOrganizations, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MyOrganizationDTO> getMyOrganizationDTO(@PathVariable Integer id) {
		var myOrganization = myOrganizationService.findById(id);

		if (myOrganization == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(myOrganization, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<MyOrganizationDTO> createMyOrganization(
			@Valid @RequestBody MyOrganizationDTO myOrganizationDTO) {
		if (myOrganizationDTO.getId() != null && myOrganizationService.findById(myOrganizationDTO.getId()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		var savedMyOrganization = myOrganizationMapper.mapEntityToDTO(myOrganizationService.save(myOrganizationDTO));
		var headers = new HttpHeaders();
		headers.setLocation(UriComponentsBuilder.fromPath("/my-organizations/{id}")
				.buildAndExpand(savedMyOrganization.getId()).toUri());
		return new ResponseEntity<>(savedMyOrganization, headers, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<MyOrganizationDTO> updateUser(@Valid @RequestBody MyOrganizationDTO myOrganizationDTO) {
		var myOrganization = myOrganizationService.findById(myOrganizationDTO.getId());

		if (myOrganization == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		var savedMyOrganization = myOrganizationMapper.mapEntityToDTO(myOrganizationService.save(myOrganizationDTO));
		return new ResponseEntity<>(savedMyOrganization, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletedUser(@PathVariable Integer id) {
		var myOrganization = myOrganizationService.findById(id);

		if (myOrganization == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		myOrganizationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
