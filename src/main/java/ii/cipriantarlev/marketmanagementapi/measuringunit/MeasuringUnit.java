/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.measuringunit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ii.cipriantarlev.marketmanagementapi.core.SuperEntity;
import lombok.*;

@Entity
@Table(name = "measuring_units")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MeasuringUnit extends SuperEntity {

	@Column(name = "name")
	private String name;

	public MeasuringUnit(String name) {
		this.name = name;
	}
}
