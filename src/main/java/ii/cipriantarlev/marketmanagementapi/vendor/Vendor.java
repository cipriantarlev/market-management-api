/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vendor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ii.cipriantarlev.marketmanagementapi.core.SuperEntity;
import ii.cipriantarlev.marketmanagementapi.product.Product;
import ii.cipriantarlev.marketmanagementapi.region.Region;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "vendors")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Vendor extends SuperEntity {

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

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinTable(name = "vendors_products",
			joinColumns=@JoinColumn(name="vendor_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;
}
