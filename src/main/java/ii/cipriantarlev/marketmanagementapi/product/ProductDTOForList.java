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
import ii.cipriantarlev.marketmanagementapi.plu.PluDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ProductDTOForList {

	@Positive
	private Long id;

	@NotBlank(message = "Romanian Name should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9-\\s]+$", message = "Romanian Name should contain only letters, numbers and dash")
	@Size(min = 1, max = 300, message = "Romanian Name length should be between {min} and {max}")
	private String nameRom;

	@NotBlank(message = "Russian Name should not be blank or null")
	@Pattern(regexp = "^[a-zA-ZА-Яа-я0-9-\\s.]+$", message = "Russian Name should contain only letters, numbers, dash and dot")
	@Size(min = 1, max = 300, message = "Russian Name length should be between {min} and {max}")
	private String nameRus;

	@DecimalMin(value = "0.0", inclusive = false, message = "Discount Price min value should be {value}")
	@Digits(integer = 5, fraction = 2, message = "Discount Price format should have {integer} integer digits and {fraction} digits")
	private BigDecimal discountPrice;

	@DecimalMin(value = "0.0", inclusive = false, message = "Retail Price min value should be {value}")
	@Digits(integer = 5, fraction = 2, message = "Retail Price format should have {integer} integer digits and {fraction} digits")
	private BigDecimal retailPrice;

	@DecimalMin(value = "0.0", message = "Trade margin min value should be {value}")
	@Digits(integer = 3, fraction = 2, message = "Trade margin format should have {integer} integer digits and {fraction} digits")
	private BigDecimal tradeMargin;

	@Valid
	@NotNull(message = "Barcode list should not be null")
	private List<BarcodeDTO> barcodes;

	@DecimalMin(value = "0.0", inclusive = false, message = "Stock min value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Stock format should have {integer} integer digits and {fraction} digits")
	private BigDecimal stock;

	private boolean isChecked;

	private PluDTO plu;

	private boolean isRetailPriceChanged;

	@DecimalMin(value = "0.0", inclusive = false, message = "Old Retail Price min value should be {value}")
	@Digits(integer = 5, fraction = 2, message = "Old Retail Price format should have {integer} integer digits and {fraction} digits")
	private BigDecimal oldRetailPrice;
}
