package ii.cipriantarlev.marketmanagementapi.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	public ResponseEntity<List<RoleDTO>> getRoles() {
		List<RoleDTO> roles = roleService.findAll();

		if (roles == null || roles.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
}
