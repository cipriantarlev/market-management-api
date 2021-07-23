package ii.cipriantarlev.marketmanagementapi.product;

import java.math.BigDecimal;
import java.util.List;

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

	private Long id;

	private String nameRom;

	private BigDecimal discrountPrice;

	private BigDecimal retailPrice;

	private List<BarcodeDTO> barcodes;

	private BigDecimal stock;
}
