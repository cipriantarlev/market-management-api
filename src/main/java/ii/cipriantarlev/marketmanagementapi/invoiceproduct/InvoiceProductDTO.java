package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import java.math.BigDecimal;

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

	private Long id;

	private InvoiceDTO invoice;

	private ProductDTO product;

	private Double quantity;

	private BigDecimal vatSum;

	private BigDecimal totalDiscountPrice;

	private BigDecimal totalRetailPrice;
}
