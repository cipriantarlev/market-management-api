/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentTypeDTO;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTOOnlyName;
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

	private Long id;

	@NotNull(message = "Document Type DTO should not be null")
	private DocumentTypeDTO documentType;

	@NotNull(message = "My Organization DTO should not be null")
	private MyOrganizationDTOOnlyName myOrganization;

	@NotNull(message = "Vendor DTO should not be null")
	private VendorDTOOnlyName vendor;

	@NotBlank(message = "Created date should not be blank")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dateCreated;

	@NotBlank(message = "Invoice number should not be blank")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Invoice number should contain only letters and numbers")
	@Size(min = 1, max = 50, message = "Invoice number length should be between {min} and {max}")
	private String invoiceNumber;

	@NotBlank(message = "Invoice date should not be blank")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate invoiceDate;

	@Size(min = 1, max = 250, message = "Note length should be between {min} and {max}")
	private String note;

	@DecimalMin(value = "0.0", inclusive = false, message = "Total Discount Price min value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Total Discount Price fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal totalDiscountPrice;

	@DecimalMin(value = "0.0", inclusive = false, message = "Total Retail Price min value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Total Retail Price fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal totalRetailPrice;

	@DecimalMin(value = "0.0", inclusive = false, message = "Total Trade Margin min value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Total Trade Margin fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal totalTradeMargin;

	@DecimalMin(value = "0.0", inclusive = false, message = "Trade Margin min value should be {value}")
	@Digits(integer = 4, fraction = 2, message = "Trade Margin fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal tradeMargin;

	@DecimalMin(value = "0.0", inclusive = false, message = "Vat sum value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Vat sum fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal vatSum;

}
