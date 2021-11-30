/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.role;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(ROLES_ROOT_PATH)
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<RoleDTO>> getRoles() {
		List<RoleDTO> roles = roleService.findAll();
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
}
