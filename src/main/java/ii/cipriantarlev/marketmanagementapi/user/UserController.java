package ii.cipriantarlev.marketmanagementapi.user;

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
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@GetMapping
	public ResponseEntity<List<UserDTO>> getUsers() {
		List<UserDTO> users = userService.findAll();

		if (users == null || users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
		var user = userService.findById(id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
		if (user.getId() != null && userService.findById(user.getId()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		if (user.getUsername() != null && userService.findByUsername(user.getUsername()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		var savedUser = userMapper.mapUserToUserDTO(userService.save(user));
		var headers = new HttpHeaders();
		headers.setLocation(UriComponentsBuilder.fromPath("/users/{id}").buildAndExpand(savedUser.getId()).toUri());
		return new ResponseEntity<>(savedUser, headers, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user) {
		var userDTO = userService.findById(user.getId());

		if (userDTO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (user.getUsername() != null && userService.findByUsername(user.getUsername()) != null
				&& !user.getUsername().equalsIgnoreCase(userDTO.getUsername())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		var savedUser = userMapper.mapUserToUserDTO(userService.save(user));
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		var user = userService.findById(id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
