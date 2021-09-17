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
public class ProductDTOForList {

	@Positive
	private Long id;

	@NotBlank(message = "Romanian Name should not be blank or null")
	@Pattern(regexp = "^[a-zA-Z0-9-\\s]+$", message = "Romanian Name should contain only letters, numbers and dash")
	@Size(min = 1, max = 300, message = "Romanian Name length should be between {min} and {max}")
	private String nameRom;

	@DecimalMin(value = "0.0", inclusive = false, message = "Discount Price min value should be {value}")
	@Digits(integer = 5, fraction = 2, message = "Discount Price fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal discountPrice;

	@DecimalMin(value = "0.0", inclusive = false, message = "Retail Price min value should be {value}")
	@Digits(integer = 5, fraction = 2, message = "Retail Price fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal retailPrice;

	@Valid
	@NotNull(message = "Barcode list should not be null")
	private List<BarcodeDTO> barcodes;

	@DecimalMin(value = "0.0", inclusive = false, message = "Stock min value should be {value}")
	@Digits(integer = 6, fraction = 2, message = "Stock fromat should have {integer} integer digits and {fraction} digits")
	private BigDecimal stock;
}
