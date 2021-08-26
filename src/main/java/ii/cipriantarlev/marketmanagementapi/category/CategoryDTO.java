/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.category;

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
public class CategoryDTO {

	private Integer id;

	@NotBlank(message = "Category name should not be blank or null")
	@Size(min = 1, max = 150, message = "Category name length should be between {min} and {max}")
	@Pattern(regexp = "^[A-Za-z\\s]*$", message = "Category name should contain only letters")
	private String name;
}
