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
public class BarcodeDTONoProduct {

	private Long id;

	private String value;
}
