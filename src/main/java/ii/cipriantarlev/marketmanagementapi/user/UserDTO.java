/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.user;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ii.cipriantarlev.marketmanagementapi.role.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {

	private Integer id;

	@NotBlank(message = "Username should not be blank")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username should contain only letters and numbers")
	@Size(min = 1, max = 100, message = "Username length should be between {min} and {max}")
	private String username;

	@NotBlank(message = "Password should not be blank")
	@Size(min = 1, max = 100, message = "Password length should be between {min} and {max}")
	private String password;

	@NotBlank(message = "Email should not be blank")
	@Email(message = "Email should be valid")
	@Pattern(regexp = "^(.+)@(.+)$", message = "Email should respect the patter: email@email.com")
	@Size(min = 1, max = 100, message = "Email length should be between {min} and {max}")
	private String email;

	@NotNull(message = "Document Type DTO should not be null")
	private List<RoleDTO> roles;

}
