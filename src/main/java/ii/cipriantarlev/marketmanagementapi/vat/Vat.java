/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ii.cipriantarlev.marketmanagementapi.core.SuperEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vat")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Vat extends SuperEntity {

	@Column(name = "value")
	private Integer value;

	@Column(name = "name")
	private String name;

	public Vat(Integer value, String name) {
		this.value = value;
		this.name = name;
	}
}
