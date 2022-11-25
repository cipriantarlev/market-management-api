/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.exceptions.error.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class that holds necessary information to provide error response
 * when the DTO has been found.
 */
@Getter
@Setter
@ToString
public class DTOFoundErrorResponse extends NotFoundErrorResponse {
}
