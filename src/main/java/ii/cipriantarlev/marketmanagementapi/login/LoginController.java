package ii.cipriantarlev.marketmanagementapi.login;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@GetMapping("/login")
	public ResponseEntity<Login> login() {
		var login = new Login();
		login.setTimestamp(LocalDateTime.now().toString());
		login.setMessage("Successfuly authenticated");
		login.setStatus(HttpStatus.OK.value());
		login.setPath("/loing");
		return new ResponseEntity<>(login, HttpStatus.OK);
	}
}
