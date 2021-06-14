package ii.cipriantarlev.marketmanagementapi.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@GetMapping("/list")
	public String getUser() {
		return "Hello World";
	}

	@PostMapping("/list")
	public String postUser() {
		return "Hello World";
	}

}
