/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class VatDTO {

	@Positive
	private Long id;

	@NotNull(message = "Vat value should not be null")
	@Max(value = 99, message = "Vat value max value is {value}")
	@Min(value = 0, message = "Vat value min value is {value}")
	private Integer value;

	@NotBlank(message = "Vat name should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9()%\\s]+$", message = "Vat name should contain only letters, numbers, () or %")
	@Size(min = 1, max = 100, message = "Vat name length should be between {min} and {max}")
	private String name;
}