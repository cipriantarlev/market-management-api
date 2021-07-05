package ii.cipriantarlev.marketmanagementapi.vendor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ii.cipriantarlev.marketmanagementapi.region.Region;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vendors")
@NoArgsConstructor
@Getter
@Setter
public class Vendor {

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

	@Column(name = "currency")
	private String currency;

	@Column(name = "vat_code")
	private String vatCode;

	@Column(name = "city")
	private String city;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "region_id")
	private Region region;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "business_address")
	private String businessAddress;

	@Column(name = "vendor_type")
	private String vendorType;

	@Column(name = "vendor_legal_type")
	private String vendorLegalType;

	@Column(name = "note")
	private String note;

	public Vendor(String name, String bank, String fiscalCode, String bankAccount, String currency, String vatCode,
			String city, Region region, String phoneNumber, String postalCode, String businessAddress,
			String vendorType, String vendorLegalType, String note) {
		this.name = name;
		this.bank = bank;
		this.fiscalCode = fiscalCode;
		this.bankAccount = bankAccount;
		this.currency = currency;
		this.vatCode = vatCode;
		this.city = city;
		this.region = region;
		this.phoneNumber = phoneNumber;
		this.postalCode = postalCode;
		this.businessAddress = businessAddress;
		this.vendorType = vendorType;
		this.vendorLegalType = vendorLegalType;
		this.note = note;
	}
}
