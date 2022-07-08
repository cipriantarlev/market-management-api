/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.myorganization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import ii.cipriantarlev.marketmanagementapi.core.SuperEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "my_organizations")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MyOrganization extends SuperEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "bank")
	private String bank;

	@Column(name = "fiscal_code")
	private String fiscalCode;

	@Column(name = "bank_account")
	private String bankAccount;

	@Column(name = "vat_code")
	private String vatCode;

	@Column(name = "city")
	private String city;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "note")
	private String note;

	@Column(name = "is_default")
	private boolean isDefault;
}
