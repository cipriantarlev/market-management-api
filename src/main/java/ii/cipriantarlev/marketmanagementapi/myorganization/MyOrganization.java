/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.myorganization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "my_organizations")
@NoArgsConstructor
@Getter
@Setter
public class MyOrganization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

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

	public MyOrganization(String name, String bank, String fiscalCode, String bankAccount, String vatCode, String city,
			String phoneNumber, String email, String note) {
		this.name = name;
		this.bank = bank;
		this.fiscalCode = fiscalCode;
		this.bankAccount = bankAccount;
		this.vatCode = vatCode;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.note = note;
	}
}
