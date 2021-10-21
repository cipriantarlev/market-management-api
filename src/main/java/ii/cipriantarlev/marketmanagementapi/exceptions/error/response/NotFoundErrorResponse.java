package ii.cipriantarlev.marketmanagementapi.exceptions.error.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotFoundErrorResponse extends ErrorResponse {

	private long id;
}
