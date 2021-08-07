package ii.cipriantarlev.marketmanagementapi.vendor;

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
public class VendorDTOOnlyName {

	private Integer id;

	private String name;
}
