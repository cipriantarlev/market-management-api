/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentTypeDTO;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTOOnlyName;
import ii.cipriantarlev.marketmanagementapi.validation.LocalDateFormat;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorDTOOnlyName;
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
public class InvoiceDTO {

	@Positive
	private Long id;

	@Valid
	@NotNull(message = "Document Type DTO should not be null")
	private DocumentTypeDTO documentType;

	@Valid
	@NotNull(message = "My Organization DTO should not be null")
	private MyOrganizationDTOOnlyName myOrganization;

	@Valid
	@NotNull(message = "Vendor DTO should not be null")
	private VendorDTOOnlyName vendor;

	@NotNull(message = "Created date should not be null")
	@LocalDateFormat(pattern = "yyyy-MM-dd", message = "Created date should be in the following format: {pattern}")
	private LocalDate dateCreated;

	@NotBlank(message = "Invoice number should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Invoice number should contain only letters and numbers")
	@Size(min = 1, max = 50, message = "Invoice number length should be between {min} and {max}")
	private String invoiceNumber;

	@NotNull(message = "Invoice date should not be null")
	@LocalDateFormat(pattern = "yyyy-MM-dd", message = "Invoice date should be in the following format: {pattern}")
	private LocalDate invoiceDate;

	@Size(min = 1, max = 250, message = "Note length should be between {min} and {max}")
	@Pattern(regexp = "^[a-zA-Z0-9\\s.,:;-]+$", message = "Note should contain alphanumeric character, space, dot, comma, colons, semicolons and dash")
	private String note;

	@DecimalMin(value = "0.0", message = "Total Discount Price min value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Total Discount Price fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal totalDiscountPrice;

	@DecimalMin(value = "0.0", message = "Total Retail Price min value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Total Retail Price fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal totalRetailPrice;

	@DecimalMin(value = "0.0", message = "Total Trade Margin min value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Total Trade Margin fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal totalTradeMargin;

	@DecimalMin(value = "0.0", message = "Trade Margin min value should be {value}")
	@Digits(integer = 4, fraction = 2, message = "Trade Margin fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal tradeMargin;

	@DecimalMin(value = "0.0", message = "Vat sum value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Vat sum fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal vatSum;

}
