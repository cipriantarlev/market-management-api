/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.plu;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class PluDTO {

	@Positive
	private Integer id;

	@NotNull(message = "PLU value should not be null")
	@Max(value = 9, message = "PLU value max value is {value}")
	@Positive(message = "PLU value should be positive")
	private Integer value;
}
