package ii.cipriantarlev.marketmanagementapi.vendor;

import ii.cipriantarlev.marketmanagementapi.region.RegionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class VendorDTO {

	private Integer id;

	private String name;

	private String bank;

	private String fiscalCode;

	private String bankAccount;

	private String currency;

	private String vatCode;

	private String city;

	private RegionDTO region;

	private String phoneNumber;

	private String postalCode;

	private String businessAddress;

	private String vendorType;

	private String vendorLegalType;

	private String note;
}
