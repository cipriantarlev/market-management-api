/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.exceptions.error.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * Class that holds necessary information to provide error response.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class ErrorResponse {

	/**
	 * Error Response status code. Usually using {@link HttpStatus} codes.
	 */
	private int statusCode;

	/**
	 * Error Response message.
	 */
	private String message;

	/**
	 * The date and time when Error Response has been generated.
	 */
	private LocalDateTime timeStamp;
}
