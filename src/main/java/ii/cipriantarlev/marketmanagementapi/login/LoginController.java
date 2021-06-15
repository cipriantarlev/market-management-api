package ii.cipriantarlev.marketmanagementapi.login;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RestController
public class LoginController {

	@GetMapping("/login")
	public ResponseEntity<Login> login() {
		var login = new Login();
		login.setTimestamp(LocalDateTime.now().toString());
		login.setMessage("Successfully authenticated");
		login.setStatus(HttpStatus.OK.value());
		login.setPath("/api/loing");
		return new ResponseEntity<>(login, HttpStatus.OK);
	}
}
