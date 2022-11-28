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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ii.cipriantarlev.marketmanagementapi.core.SuperEntity;
import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentType;
import ii.cipriantarlev.marketmanagementapi.invoiceproduct.InvoiceProduct;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganization;
import ii.cipriantarlev.marketmanagementapi.vendor.Vendor;
import lombok.*;

/**
 * Class that holds value invoice values.
 */
@Entity
@Table(name = "invoices")
@NoArgsConstructor
@Getter
@Setter
public class Invoice extends SuperEntity {

	/**
	 * Invoice's {@link DocumentType}
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "document_type_id")
	private DocumentType documentType;

	/**
	 * {@link MyOrganization} for which invoice has been created.
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "my_organization_id")
	private MyOrganization myOrganization;

	/**
	 * {@link Vendor} for which invoice has been created.
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;

	/**
	 * The date when invoice has been created.
	 */
	@Column(name = "date_created")
	private LocalDate dateCreated;

	/**
	 * Value for invoice number.
	 */
	@Column(name = "invoice_number")
	private String invoiceNumber;

	/**
	 * The invoice's date.
	 */
	@Column(name = "invoice_date")
	private LocalDate invoiceDate;

	/**
	 * Note to hold additional details about invoice
	 */
	@Column(name = "note")
	private String note;

	/**
	 * Total value for discount price on the invoice.
	 */
	@Column(name = "total_discount_price")
	private BigDecimal totalDiscountPrice;

	/**
	 * Total value for retail price on the invoice.
	 */
	@Column(name = "total_retail_price")
	private BigDecimal totalRetailPrice;

	/**
	 * Total value for trade margin on the invoice.
	 */
	@Column(name = "total_trade_margin")
	private BigDecimal totalTradeMargin;

	/**
	 * Trade margin value in (%) percentage.
	 */
	@Column(name = "trade_margin")
	private BigDecimal tradeMargin;

	/**
	 * Total value for vat on the invoice.
	 */
	@Column(name = "vat_sum")
	private BigDecimal vatSum;

	/**
	 * List of {@link InvoiceProduct} associated with invoice.
	 */
	@OneToMany(fetch = FetchType.LAZY, 
				cascade = CascadeType.ALL, 
				mappedBy = "invoice")
	private List<InvoiceProduct> invoiceProducts;

	/**
	 * Value used to determine whether the invoice is approved or not.
	 */
	@Column(name = "is_approved")
	private boolean isApproved;

	/**
	 * Constructor to make a deep copy of invoice {@link Invoice}.
	 *
	 * @param invoice to be copied.
	 */
	public Invoice(Invoice invoice) {
		this(invoice.getId(), invoice.getDocumentType(), invoice.getMyOrganization(), invoice.getVendor(),
				invoice.getDateCreated(), invoice.getInvoiceNumber(), invoice.getInvoiceDate(),
				invoice.getNote(), invoice.getTotalDiscountPrice(), invoice.getTotalRetailPrice(),
				invoice.getTotalTradeMargin(), invoice.getTradeMargin(), invoice.getVatSum(),
				invoice.getInvoiceProducts(), invoice.isApproved());
	}

	public Invoice(Long id, DocumentType documentType, MyOrganization myOrganization, Vendor vendor,
				   LocalDate dateCreated, String invoiceNumber, LocalDate invoiceDate, String note,
				   BigDecimal totalDiscountPrice, BigDecimal totalRetailPrice, BigDecimal totalTradeMargin,
				   BigDecimal tradeMargin, BigDecimal vatSum, List<InvoiceProduct> invoiceProducts, boolean isApproved) {
		super(id);
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
		this.invoiceProducts = invoiceProducts;
		this.isApproved = isApproved;
	}
}
