/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class VatDTO {

	private Integer id;

	@NotNull(message = "Vat value value should not be null")
	@Max(value = 99, message = "Vat value max value is {value}.")
	@Positive(message = "Vat value should be positive.")
	private Integer value;

	@NotBlank(message = "Vat name should not be blank")
	@Pattern(regexp = "^[a-zA-Z0-9()%]+$", message = "Vat name should contain only letters, numbers, () or %")
	@Size(min = 1, max = 100, message = "Vat name length should be between {min} and {max}")
	private String name;
}
