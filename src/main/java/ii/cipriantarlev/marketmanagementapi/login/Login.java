/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.login;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that holds login details sent to UI.
 */
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
public class Login {

	/**
	 * Time stamp when the user was successfully authenticated.
	 */
	private String timestamp;

	/**
	 * Status used when the user was successfully authenticated.
	 */
	private Integer status;

	/**
	 * The message used when the user was successfully authenticated.
	 */
	private String message;

	/**
	 * The path used when the user was successfully authenticated.
	 */
	private String path;
}