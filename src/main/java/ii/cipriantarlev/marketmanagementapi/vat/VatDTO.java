package ii.cipriantarlev.marketmanagementapi.vat;

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
public class VatDTO {

	private Integer id;

	private Integer value;

	private String name;
}
