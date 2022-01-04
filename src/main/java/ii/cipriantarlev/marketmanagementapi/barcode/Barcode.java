/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.barcode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ii.cipriantarlev.marketmanagementapi.core.SuperEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class to hold value of product's barcode(s).
 */
@Entity
@Table(name = "barcodes")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Barcode extends SuperEntity {

	/**
	 * Barcode value.
	 */
	@Column(name = "value")
	private String value;

	public Barcode(String value) {
		this.value = value;
	}
}
