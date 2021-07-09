package ii.cipriantarlev.marketmanagementapi.plu;

import javax.persistence.Column;

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
public class PluDTO {

	private Integer id;

	@Column(name = "value")
	private Integer value;
}
