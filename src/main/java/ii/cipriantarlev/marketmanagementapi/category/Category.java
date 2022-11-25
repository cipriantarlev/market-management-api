/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.category;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ii.cipriantarlev.marketmanagementapi.core.SuperEntity;
import ii.cipriantarlev.marketmanagementapi.product.Product;
import ii.cipriantarlev.marketmanagementapi.subcategory.Subcategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class to hold values of {@link Product} category.
 */
@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category extends SuperEntity {

	/**
	 * Category name.
	 */
	@Column(name = "name")
	private String name;

	/**
	 * List with all category's subcategories.
	 */
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,
			   cascade = CascadeType.ALL,
			   mappedBy = "category")
	private List<Subcategory> subcategories;

	public Category(String name, List<Subcategory> subcategories) {
		this.name = name;
		this.subcategories = subcategories;
	}
}
