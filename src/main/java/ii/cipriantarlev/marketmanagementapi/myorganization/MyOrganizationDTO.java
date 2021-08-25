/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.myorganization;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
public class MyOrganizationDTO {

	private Integer id;

	@NotBlank(message = "My Organization name should not be blank")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "My Organization name should contain only letters and numbers")
	@Size(min = 1, max = 150, message = "My Organization name length should be between {min} and {max}")
	private String name;

	@NotBlank(message = "Bank should not be blank")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Bank should contain only letters and numbers")
	@Size(min = 1, max = 200, message = "Bank length should be between {min} and {max}")
	private String bank;

	@NotBlank(message = "Fiscal Code should not be blank")
	@Pattern(regexp = "^[0-9]+$", message = "Fiscal Code should contain only numbers")
	@Size(min = 1, max = 20, message = "Fiscal Code length should be between {min} and {max}")
	private String fiscalCode;

	@NotBlank(message = "Bank Account should not be blank")
	@Pattern(regexp = "^[0-9]+$", message = "Bank Account should contain only numbers")
	@Size(min = 1, max = 50, message = "Bank Account length should be between {min} and {max}")
	private String bankAccount;

	@NotBlank(message = "Vat Code should not be blank")
	@Pattern(regexp = "^[0-9]+$", message = "Vat Code should contain only numbers")
	@Size(min = 1, max = 50, message = "Vat Code length should be between {min} and {max}")
	private String vatCode;

	@NotBlank(message = "City should not be blank")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "City should contain only letters and numbers")
	@Size(min = 1, max = 150, message = "City length should be between {min} and {max}")
	private String city;

	@Pattern(regexp = "^[0-9-]+$", message = "Phone number should contain only dash and numbers")
	@Size(min = 1, max = 100, message = "Phone number length should be between {min} and {max}")
	private String phoneNumber;

	@Email(message = "Email should be valid")
	@Pattern(regexp = "^(.+)@(.+)$", message = "Email should respect the patter: email@email.com")
	@Size(min = 5, max = 150, message = "Email length should be between {min} and {max}")
	private String email;

	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Note should contain only letters and numbers")
	@Size(min = 1, max = 500, message = "Note length should be between {min} and {max}")
	private String note;
}
