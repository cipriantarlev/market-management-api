/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.barcode;

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
public class BarcodeDTO {

	private Long id;

	@NotBlank(message = "Barcode value should not be blank")
	@Size(min = 1, max = 13, message = "Barcode value length should be between {min} and {max}")
	@Pattern(regexp = "^[0-9]*$", message = "Barcode value should contain only numbers")
	private String value;

	public BarcodeDTO(String value) {
		this.value = value;
	}
}
