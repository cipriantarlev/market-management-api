/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.plu;

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
@Table(name = "plu")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Plu extends SuperEntity {

	@Column(name = "value")
	private Integer value;

	public Plu(Integer value) {
		this.value = value;
	}
}
