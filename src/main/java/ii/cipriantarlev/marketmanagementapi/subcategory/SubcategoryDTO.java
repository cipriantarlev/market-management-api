package ii.cipriantarlev.marketmanagementapi.subcategory;

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

	private Integer id;

	private String name;

	private CategoryDTO category;
}

