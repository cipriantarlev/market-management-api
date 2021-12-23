/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.myorganization;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class MyOrganizationDTOOnlyName {

	@Positive
	private Integer id;

	@NotBlank(message = "My Organization name should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "My Organization name should contain only letters and numbers")
	@Size(min = 1, max = 150, message = "My Organization name length should be between {min} and {max}")
	private String name;
}
