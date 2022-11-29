/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ii.cipriantarlev.marketmanagementapi.invoice.Invoice;
import ii.cipriantarlev.marketmanagementapi.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class that holds {@link Product} related to one {@link Invoice}.
 */
@Entity
@Table(name = "invoice_products")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InvoiceProduct {

	/**
	 * InvoiceProduct's id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * {@link Invoice} related to this InvoiceProduct.
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "invoice_id")
	@ToString.Exclude
	private Invoice invoice;

	/**
	 * {@link Product} related to this InvoiceProduct.
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "product_id")
	@ToString.Exclude
	private Product product;

	/**
	 * The quantity of the {@link Product} added in the {@link Invoice}.
	 */
	@Column(name = "quantity")
	private BigDecimal quantity;

	/**
	 * Total vat's value of the {@link Product} added in the {@link Invoice}.
	 */
	@Column(name = "vat_sum")
	private BigDecimal vatSum;

	/**
	 * Total discount price value of the {@link Product} added in the {@link Invoice}.
	 */
	@Column(name = "total_discount_price")
	private BigDecimal totalDiscountPrice;

	/**
	 * Total retail price value of the {@link Product} added in the {@link Invoice}.
	 */
	@Column(name = "total_retail_price")
	private BigDecimal totalRetailPrice;

	public InvoiceProduct(Invoice invoice, Product product, BigDecimal quantity, BigDecimal vatSum,
			BigDecimal totalDiscountPrice, BigDecimal totalRetailPrice) {
		this.invoice = invoice;
		this.product = product;
		this.quantity = quantity;
		this.vatSum = vatSum;
		this.totalDiscountPrice = totalDiscountPrice;
		this.totalRetailPrice = totalRetailPrice;
	}
}