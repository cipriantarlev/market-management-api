/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.region;

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
public class RegionDTO {

	private Integer id;

	@NotBlank(message = "Region name should not be blank")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Region name should contain only letters and numbers")
	@Size(min = 1, max = 100, message = "Region name length should be between {min} and {max}")
	private String name;

}
