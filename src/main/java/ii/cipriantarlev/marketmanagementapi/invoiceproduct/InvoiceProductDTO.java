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

import ii.cipriantarlev.marketmanagementapi.invoice.Invoice;
import ii.cipriantarlev.marketmanagementapi.invoice.InvoiceDTO;
import ii.cipriantarlev.marketmanagementapi.product.Product;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTO;
import lombok.*;

/**
 * DTO class of {@link InvoiceProduct}.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class InvoiceProductDTO {

	/**
	 * InvoiceProduct's id.
	 */
	@Positive
	private Long id;

	/**
	 * InvoiceProduct's {@link InvoiceDTO}.
	 */
	@NotNull(message = "Invoice DTO should not be null")
	private InvoiceDTO invoice;

	/**
	 * InvoiceProduct's {@link ProductDTO}.
	 */
	@Valid
	@NotNull(message = "Product DTO should not be null")
	private ProductDTO product;

	/**
	 * The quantity of the {@link Product} added in the {@link Invoice}.
	 */
	@NotNull(message = "Quantity should not be null")
	@DecimalMin(value = "0.0", inclusive = false, message = "Quantity min value should be {value}")
	@Digits(integer = 6, fraction = 4, message = "Quantity format should have {integer} integer digits and {fraction} digits")
	private BigDecimal quantity;

	/**
	 * Total vat's value of the {@link Product} added in the {@link Invoice}.
	 */
	@NotNull(message = "Vat sum should not be null")
	@DecimalMin(value = "0.0", message = "Vat sum min value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Vat sum format should have {integer} integer digits and {fraction} digits")
	private BigDecimal vatSum;

	/**
	 * Total discount price value of the {@link Product} added in the {@link Invoice}.
	 */
	@NotNull(message = "Total Discount Price should not be null")
	@DecimalMin(value = "0.0", message = "Total Discount Price min value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Total Discount Price format should have {integer} integer digits and {fraction} digits")
	private BigDecimal totalDiscountPrice;

	/**
	 * Total retail price value of the {@link Product} added in the {@link Invoice}.
	 */
	@NotNull(message = "Total Retail Price should not be null")
	@DecimalMin(value = "0.0", message = "Total Retail Price min value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Total Retail Price format should have {integer} integer digits and {fraction} digits")
	private BigDecimal totalRetailPrice;
}