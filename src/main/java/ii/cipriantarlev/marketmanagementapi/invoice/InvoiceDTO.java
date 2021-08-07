package ii.cipriantarlev.marketmanagementapi.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

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

	private DocumentTypeDTO documentType;

	private MyOrganizationDTOOnlyName myOrganization;

	private VendorDTOOnlyName vendor;

	private LocalDate dateCreated;

	private String invoiceNumber;

	private LocalDate invoiceDate;

	private String note;

	private BigDecimal totalDiscountPrice;

	private BigDecimal totalRetailPrice;

	private BigDecimal totalTradeMargin;

	private BigDecimal tradeMargin;

	private BigDecimal vatSum;

}
