/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vendor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class VendorDTOOnlyName {

	@Positive
	private Long id;

	@NotBlank(message = "Vendor name should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Vendor name should contain only letters and numbers")
	@Size(min = 1, max = 200, message = "Vendor name length should be between {min} and {max}")
	private String name;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VendorDTOOnlyName that = (VendorDTOOnlyName) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
