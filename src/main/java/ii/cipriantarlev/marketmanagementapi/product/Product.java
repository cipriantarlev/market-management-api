package ii.cipriantarlev.marketmanagementapi.product;

import java.math.BigDecimal;
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

import ii.cipriantarlev.marketmanagementapi.barcode.Barcode;
import ii.cipriantarlev.marketmanagementapi.category.Category;
import ii.cipriantarlev.marketmanagementapi.measuringunit.MeasuringUnit;
import ii.cipriantarlev.marketmanagementapi.plu.Plu;
import ii.cipriantarlev.marketmanagementapi.productscode.ProductCode;
import ii.cipriantarlev.marketmanagementapi.subcategory.Subcategory;
import ii.cipriantarlev.marketmanagementapi.vat.Vat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name_rom")
	private String nameRom;

	@Column(name = "name_rus")
	private String nameRus;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "subcategory_id")
	private Subcategory subcategory;

	@Column(name = "discount_price")
	private BigDecimal discrountPrice;

	@Column(name = "retail_price")
	private BigDecimal retailPrice;

	@Column(name = "trade_margin")
	private BigDecimal tradeMargin;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "measuringUnit_id")
	private MeasuringUnit measuringUnit;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "vat_id")
	private Vat vat;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private List<Barcode> barCodes;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
	@JoinColumn(name = "plu_id")
	private Plu plu;

	@Column(name = "stock")
	private BigDecimal stock;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
	@JoinColumn(name = "product_code_id")
	private ProductCode productCode;

	public Product(String nameRom, String nameRus, Category category, Subcategory subcategory,
			BigDecimal discrountPrice, BigDecimal retailPrice, BigDecimal tradeMargin, MeasuringUnit measuringUnit,
			Vat vat, List<Barcode> barCodes, Plu plu, BigDecimal stock, ProductCode productCode) {
		this.nameRom = nameRom;
		this.nameRus = nameRus;
		this.category = category;
		this.subcategory = subcategory;
		this.discrountPrice = discrountPrice;
		this.retailPrice = retailPrice;
		this.tradeMargin = tradeMargin;
		this.measuringUnit = measuringUnit;
		this.vat = vat;
		this.barCodes = barCodes;
		this.plu = plu;
		this.stock = stock;
		this.productCode = productCode;
	}
}
