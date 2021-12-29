/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vendor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import ii.cipriantarlev.marketmanagementapi.region.RegionDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class VendorDTO {

	@Positive
	private Long id;

	@NotBlank(message = "Vendor name should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Vendor name should contain only letters and numbers")
	@Size(min = 1, max = 200, message = "Vendor name length should be between {min} and {max}")
	private String name;

	@NotBlank(message = "Bank should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9-.\\s]+$", message = "Bank should contain only letters and numbers")
	@Size(min = 1, max = 250, message = "Bank length should be between {min} and {max}")
	private String bank;

	@NotBlank(message = "Fiscal code should not be blank or null")
	@Pattern(regexp = "^[0-9]+$", message = "Fiscal code should contain only numbers")
	@Size(min = 1, max = 100, message = "Fiscal code length should be between {min} and {max}")
	private String fiscalCode;

	@NotBlank(message = "Bank account should not be blank or null")
	@Pattern(regexp = "^[0-9]+$", message = "Bank account should contain only numbers")
	@Size(min = 1, max = 100, message = "Bank account length should be between {min} and {max}")
	private String bankAccount;

	@NotBlank(message = "Currency should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Currency should contain only letters")
	@Size(min = 1, max = 10, message = "Currency length should be between {min} and {max}")
	private String currency;

	@NotBlank(message = "Vat code should not be blank or null")
	@Pattern(regexp = "^[0-9]+$", message = "Vat code should contain only numbers")
	@Size(min = 1, max = 50, message = "Vat code length should be between {min} and {max}")
	private String vatCode;

	@NotBlank(message = "City should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9-ăâîșşțţÂĂÎȘŞȚŢ\\s]+$", message = "City should contain only letters and numbers")
	@Size(min = 1, max = 100, message = "City length should be between {min} and {max}")
	private String city;

	@Valid
	@NotNull(message = "Region DTO should not be null")
	private RegionDTO region;

	@NotBlank(message = "Phone number should not be blank or null")
	@Pattern(regexp = "^[0-9-]+$", message = "Phone number should contain only numbers and dash")
	@Size(min = 1, max = 50, message = "Phone number length should be between {min} and {max}")
	private String phoneNumber;

	@NotBlank(message = "Postal code should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9-]+$", message = "Postal code should contain only letters and numbers")
	@Size(min = 1, max = 10, message = "Postal code length should be between {min} and {max}")
	private String postalCode;

	@NotBlank(message = "Business address should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9\\s:\"\'\\-ăâîșşțţÂĂÎȘŞȚŢ,]+$", message = "Business address should contain only letters, numbers, \', \", -, :")
	@Size(min = 1, max = 250, message = "Business address length should be between {min} and {max}")
	private String businessAddress;

	@NotBlank(message = "Vendor Type should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Vendor Type should contain only letters and numbers")
	@Size(min = 1, max = 100, message = "Vendor Type length should be between {min} and {max}")
	private String vendorType;

	@NotBlank(message = "Vendor Legal Type should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Vendor Legal Type should contain only letters and numbers")
	@Size(min = 1, max = 150, message = "Vendor Legal Type length should be between {min} and {max}")
	private String vendorLegalType;

	@Pattern(regexp = "^[a-zA-Z0-9\\s.,:;-]+$", message = "Note should contain alphanumeric character, space, dot, comma, colons, semicolons and dash")
	@Size(min = 1, max = 250, message = "Note length should be between {min} and {max}")
	private String note;
}
