package ii.cipriantarlev.marketmanagementapi.measuringunit;

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
public class MeasuringUnitDTO {

	private Integer id;

	private String name;
}
