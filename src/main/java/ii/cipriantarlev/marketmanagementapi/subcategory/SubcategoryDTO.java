/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.subcategory;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import ii.cipriantarlev.marketmanagementapi.category.CategoryDTO;
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
public class SubcategoryDTO {

	@Positive
	private Integer id;

	@NotBlank(message = "Subcategory name should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Subcategory name should contain only letters and numbers")
	@Size(min = 1, max = 50, message = "Subcategory name length should be between {min} and {max}")
	private String name;

	@Valid
	@NotNull(message = "Category DTO should not be null")
	private CategoryDTO category;
}

