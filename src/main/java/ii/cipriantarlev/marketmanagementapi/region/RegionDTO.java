/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.region;

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
public class RegionDTO {

	@Positive
	private Long id;

	@NotBlank(message = "Region name should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Region name should contain only letters and numbers")
	@Size(min = 1, max = 100, message = "Region name length should be between {min} and {max}")
	private String name;

}
