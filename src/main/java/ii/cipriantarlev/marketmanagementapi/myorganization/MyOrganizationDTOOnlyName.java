package ii.cipriantarlev.marketmanagementapi.myorganization;

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
public class MyOrganizationDTOOnlyName {

	private Integer id;

	private String name;
}
