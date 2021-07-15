package ii.cipriantarlev.marketmanagementapi.barcode;

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
public class BarcodeDTO {

	private Long id;

	private String value;

	public BarcodeDTO(String value) {
		this.value = value;
	}
}
