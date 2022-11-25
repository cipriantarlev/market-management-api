/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.documenttype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ii.cipriantarlev.marketmanagementapi.core.SuperEntity;
import ii.cipriantarlev.marketmanagementapi.invoice.Invoice;
import lombok.*;

/**
 * Class to hold values of {@link Invoice} document types.
 */
@Entity
@Table(name = "document_types")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class DocumentType extends SuperEntity {

	/**
	 * DocumentType name.
	 */
	@Column(name = "name")
	private String name;

	public DocumentType(long id) {
		super(id);
	}
}
