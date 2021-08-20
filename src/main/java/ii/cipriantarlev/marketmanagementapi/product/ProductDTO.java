package ii.cipriantarlev.marketmanagementapi.product;

import java.math.BigDecimal;
import java.util.List;

import ii.cipriantarlev.marketmanagementapi.barcode.BarcodeDTO;
import ii.cipriantarlev.marketmanagementapi.category.CategoryDTO;
import ii.cipriantarlev.marketmanagementapi.measuringunit.MeasuringUnitDTO;
import ii.cipriantarlev.marketmanagementapi.plu.PluDTO;
import ii.cipriantarlev.marketmanagementapi.productscode.ProductCodeDTO;
import ii.cipriantarlev.marketmanagementapi.subcategory.SubcategoryDTONoCategory;
import ii.cipriantarlev.marketmanagementapi.vat.VatDTO;
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
public class ProductDTO {

	private Long id;

	private String nameRom;

	private String nameRus;

	private CategoryDTO category;

	private SubcategoryDTONoCategory subcategory;

	private BigDecimal discountPrice;

	private BigDecimal retailPrice;

	private BigDecimal tradeMargin;

	private MeasuringUnitDTO measuringUnit;

	private VatDTO vat;

	private List<BarcodeDTO> barcodes;

	private PluDTO plu;

	private BigDecimal stock;

	private ProductCodeDTO productCode;
}
