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

@Entity
@Table(name = "invoice_products")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InvoiceProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "quantity")
	private BigDecimal quantity;

	@Column(name = "vat_sum")
	private BigDecimal vatSum;

	@Column(name = "total_discount_price")
	private BigDecimal totalDiscountPrice;

	@Column(name = "total_retail_price")
	private BigDecimal totalRetailPrice;

	public InvoiceProduct(Invoice invoice, Product product, BigDecimal quantity, BigDecimal vatSumt,
			BigDecimal totalDiscountPrice, BigDecimal totalRetailPrice) {
		this.invoice = invoice;
		this.product = product;
		this.quantity = quantity;
		this.vatSum = vatSumt;
		this.totalDiscountPrice = totalDiscountPrice;
		this.totalRetailPrice = totalRetailPrice;
	}
}
