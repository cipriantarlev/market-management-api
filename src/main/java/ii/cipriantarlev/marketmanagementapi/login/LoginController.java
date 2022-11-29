/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.login;

import java.time.LocalDateTime;

import ii.cipriantarlev.marketmanagementapi.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

/**
 * Class used to authenticate the {@link User}.
 */
@CrossOrigin(LOCAL_HOST)
@RestController
public class LoginController {

	/**
	 * Method used to authenticate the {@link User}.
	 *
	 * @return status 200 and login details if the authentication was successful,
	 * otherwise unauthorized message and code will be sent.
	 */
	@GetMapping(LOGIN_PATH)
	public ResponseEntity<Login> login() {
		var login = Login.builder()
				.timestamp(LocalDateTime.now().toString())
				.message("Successfully authenticated")
				.status(HttpStatus.OK.value())
				.path(API_LOGIN_PATH)
				.build();
		return new ResponseEntity<>(login, HttpStatus.OK);
	}
}