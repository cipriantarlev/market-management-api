/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.exceptions.error.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * Class that holds necessary information to provide error response
 * when the object has been not found.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotFoundErrorResponse extends ErrorResponse {

	/**
	 * The id of the not found object.
	 */
	private String id;
}