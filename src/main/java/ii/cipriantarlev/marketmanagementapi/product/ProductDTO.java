/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.product;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import ii.cipriantarlev.marketmanagementapi.barcode.BarcodeDTO;
import ii.cipriantarlev.marketmanagementapi.category.CategoryDTO;
import ii.cipriantarlev.marketmanagementapi.measuringunit.MeasuringUnitDTO;
import ii.cipriantarlev.marketmanagementapi.plu.PluDTO;
import ii.cipriantarlev.marketmanagementapi.productscode.ProductCodeDTO;
import ii.cipriantarlev.marketmanagementapi.subcategory.SubcategoryDTONoCategory;
import ii.cipriantarlev.marketmanagementapi.vat.VatDTO;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorDTOOnlyName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ProductDTO {

	@Positive
	private Long id;

	@NotBlank(message = "Romanian Name should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9-\\s.]+$", message = "Romanian Name should contain only letters, numbers, dash and dot")
	@Size(min = 1, max = 300, message = "Romanian Name length should be between {min} and {max}")
	private String nameRom;

	@NotBlank(message = "Russian Name should not be blank or null")
	@Pattern(regexp = "^[a-zA-ZА-Яа-я0-9-\\s.]+$", message = "Russian Name should contain only letters, numbers, dash and dot")
	@Size(min = 1, max = 300, message = "Russian Name length should be between {min} and {max}")
	private String nameRus;

	@Valid
	@NotNull(message = "CategoryDTO should not be null")
	private CategoryDTO category;

	@Valid
	@NotNull(message = "SubcategoryDTO should not be null")
	private SubcategoryDTONoCategory subcategory;

	@DecimalMin(value = "0.0", message = "Discount Price min value should be {value}")
	@Digits(integer = 5, fraction = 2, message = "Discount Price format should have {integer} integer digits and {fraction} digits")
	private BigDecimal discountPrice;

	@DecimalMin(value = "0.0", message = "Retail Price min value should be {value}")
	@Digits(integer = 5, fraction = 2, message = "Retail Price format should have {integer} integer digits and {fraction} digits")
	private BigDecimal retailPrice;

	@DecimalMin(value = "0.0", message = "Trade margin min value should be {value}")
	@Digits(integer = 3, fraction = 2, message = "Trade margin format should have {integer} integer digits and {fraction} digits")
	private BigDecimal tradeMargin;

	@Valid
	@NotNull(message = "MeasuringUnitDTO should not be null")
	private MeasuringUnitDTO measuringUnit;

	@Valid
	@NotNull(message = "VatDTO should not be null")
	private VatDTO vat;

	@Valid
	@NotNull(message = "Barcode list should not be null")
	private List<BarcodeDTO> barcodes;

	private PluDTO plu;

	@DecimalMin(value = "0.0", message = "Stock min value should be {value}")
	@Digits(integer = 6, fraction = 4, message = "Stock format should have {integer} integer digits and {fraction} digits")
	private BigDecimal stock;

	@Valid
	@NotNull(message = "ProductCodeDTO should not be null")
	private ProductCodeDTO productCode;

	@Positive
	private Long defaultVendorId;

	private List<VendorDTOOnlyName> vendors;

	private boolean isChecked;

	private boolean isRetailPriceChanged;

	@DecimalMin(value = "0.0", message = "Old Retail Price min value should be {value}")
	@Digits(integer = 5, fraction = 2, message = "Old Retail Price format should have {integer} integer digits and {fraction} digits")
	private BigDecimal oldRetailPrice;
}
