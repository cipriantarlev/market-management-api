/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vendor;

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
public class VendorDTOOnlyName {

	private Integer id;

	@NotBlank(message = "Vendor name should not be blank")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Vendor nam should contain only letters and numbers")
	@Size(min = 1, max = 200, message = "Vendor nam length should be between {min} and {max}")
	private String name;
}
