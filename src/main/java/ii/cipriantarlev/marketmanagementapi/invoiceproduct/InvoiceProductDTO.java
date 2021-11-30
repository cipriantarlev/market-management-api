/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import ii.cipriantarlev.marketmanagementapi.invoice.InvoiceDTO;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTO;
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
public class InvoiceProductDTO {

	@Positive
	private Long id;

	@NotNull(message = "Invoice DTO should not be null")
	private InvoiceDTO invoice;

	@Valid
	@NotNull(message = "Product DTO should not be null")
	private ProductDTO product;

	@NotNull(message = "Quantity should not be null")
	@DecimalMin(value = "0.0", inclusive = false, message = "Quantity min value should be {value}")
	@Digits(integer = 6, fraction = 4, message = "Quantity fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal quantity;

	@NotNull(message = "Vat sum should not be null")
	@DecimalMin(value = "0.0", message = "Vat sum min value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Vat sum fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal vatSum;

	@NotNull(message = "Total Discount Price should not be null")
	@DecimalMin(value = "0.0", message = "Total Discount Price min value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Total Discount Price fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal totalDiscountPrice;

	@NotNull(message = "Total Retail Price should not be null")
	@DecimalMin(value = "0.0", message = "Total Retail Price min value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Total Retail Price fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal totalRetailPrice;
}
