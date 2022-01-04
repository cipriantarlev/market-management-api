/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.barcode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
  DTO class of {@link Barcode}.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BarcodeDTO {

	/**
	 * The value of id which should be always positive.
	 */
	@Positive
	private Long id;

	/**
	 * Barcode value.
	 */
	@NotBlank(message = "Barcode value should not be blank or null")
	@Size(min = 1, max = 13, message = "Barcode value length should be between {min} and {max}")
	@Pattern(regexp = "^[0-9]*$", message = "Barcode value should contain only numbers")
	private String value;

	public BarcodeDTO(String value) {
		this.value = value;
	}
}
