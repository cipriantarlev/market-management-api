/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentType;
import ii.cipriantarlev.marketmanagementapi.invoiceproduct.InvoiceProduct;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganization;
import ii.cipriantarlev.marketmanagementapi.vendor.Vendor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoices")
@NoArgsConstructor
@Getter
@Setter
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "document_type_id")
	private DocumentType documentType;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "my_organization_id")
	private MyOrganization myOrganization;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;

	@Column(name = "date_created")
	private LocalDate dateCreated;

	@Column(name = "invoice_number")
	private String invoiceNumber;

	@Column(name = "invoice_date")
	private LocalDate invoiceDate;

	@Column(name = "note")
	private String note;

	@Column(name = "total_discount_price")
	private BigDecimal totalDiscountPrice;

	@Column(name = "total_retail_price")
	private BigDecimal totalRetailPrice;

	@Column(name = "total_trade_margin")
	private BigDecimal totalTradeMargin;

	@Column(name = "trade_margin")
	private BigDecimal tradeMargin;

	@Column(name = "vat_sum")
	private BigDecimal vatSum;

	@OneToMany(fetch = FetchType.LAZY, 
				cascade = CascadeType.ALL, 
				mappedBy = "invoice")
	private List<InvoiceProduct> invoiceProducts;

	public Invoice(DocumentType documentType, MyOrganization myOrganization, Vendor vendor, LocalDate dateCreated,
			String invoiceNumber, LocalDate invoiceDate, String note, BigDecimal totalDiscountPrice,
			BigDecimal totalRetailPrice, BigDecimal totalTradeMargin, BigDecimal tradeMargin, BigDecimal vatSum) {
		this.documentType = documentType;
		this.myOrganization = myOrganization;
		this.vendor = vendor;
		this.dateCreated = dateCreated;
		this.invoiceNumber = invoiceNumber;
		this.invoiceDate = invoiceDate;
		this.note = note;
		this.totalDiscountPrice = totalDiscountPrice;
		this.totalRetailPrice = totalRetailPrice;
		this.totalTradeMargin = totalTradeMargin;
		this.tradeMargin = tradeMargin;
		this.vatSum = vatSum;
	}
}
