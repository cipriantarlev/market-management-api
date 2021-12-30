/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.subcategory;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ii.cipriantarlev.marketmanagementapi.category.Category;
import ii.cipriantarlev.marketmanagementapi.core.SuperEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "subcategories")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Subcategory extends SuperEntity {
	
	@Column(name="name")
	private String name;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,
			   cascade=CascadeType.REFRESH)
	@JoinColumn(name = "category_id")
	private Category category;

}
