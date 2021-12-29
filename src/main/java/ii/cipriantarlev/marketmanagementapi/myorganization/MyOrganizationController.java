/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.myorganization;

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
@RequestMapping(MY_ORGANIZATIONS_ROOT_PATH)
public class MyOrganizationController {

	@Autowired
	private MyOrganizationService myOrganizationService;

	@Autowired
	private RestControllerUtil restControllerUtil;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<MyOrganizationDTO>> getMyOrganizations() {
		List<MyOrganizationDTO> myOrganizations = myOrganizationService.findAll();
		return new ResponseEntity<>(myOrganizations, HttpStatus.OK);
	}

	@GetMapping(ID_PATH)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MyOrganizationDTO> getMyOrganizationDTO(@PathVariable Long id) {
		var myOrganization = myOrganizationService.findById(id);
		return new ResponseEntity<>(myOrganization, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MyOrganizationDTO> createMyOrganization(
			@Valid @RequestBody MyOrganizationDTO myOrganizationDTO) {
		var savedMyOrganization = myOrganizationService.save(myOrganizationDTO);
		var headers = restControllerUtil
				.setHttpsHeaderLocation(MY_ORGANIZATIONS_ROOT_PATH.concat(ID_PATH), savedMyOrganization.getId().longValue());
		return new ResponseEntity<>(savedMyOrganization, headers, HttpStatus.CREATED);
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MyOrganizationDTO> updateUser(@Valid @RequestBody MyOrganizationDTO myOrganizationDTO) {
		var savedMyOrganization = myOrganizationService.update(myOrganizationDTO);
		return new ResponseEntity<>(savedMyOrganization, HttpStatus.OK);
	}

	@DeleteMapping(ID_PATH)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deletedUser(@PathVariable Long id) {
		myOrganizationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
