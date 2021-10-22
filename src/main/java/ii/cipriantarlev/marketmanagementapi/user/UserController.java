/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.user;

import java.util.List;

import javax.validation.Valid;

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

import ii.cipriantarlev.marketmanagementapi.util.RestControllerUtil;

import static ii.cipriantarlev.marketmanagementapi.util.Constants.*;

@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(USERS_ROOT_PATH)
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RestControllerUtil restControllerUtil;

	@GetMapping
	public ResponseEntity<List<UserDTO>> getUsers() {
		List<UserDTO> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping(ID_PATH)
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
		var user = userService.findById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user) {
		var savedUser = userService.save(user);
		var headers = restControllerUtil.setHttpsHeaderLocation(USERS_ROOT_PATH.concat(ID_PATH),
				user.getId().longValue());
		return new ResponseEntity<>(savedUser, headers, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO user) {
		var savedUser = userService.update(user);
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}

	@DeleteMapping(ID_PATH)
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
