/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.productscode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
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
public class ProductCodeDTO {

	@Positive
	private Long id;

	@NotBlank(message = "Product Code value should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Product Code value should contain only letters and numbers")
	@Size(min = 1, max = 50, message = "Product Code value length should be between {min} and {max}")
	private String value;
}
