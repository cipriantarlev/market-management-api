/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.exceptions;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception to be thrown when the DTO was found during creation of a new entity
 * in database.
 * 
 * @author ciprian.tarlev
 */
public class DTOFoundWhenSaveException extends DTONotFoundException {

	@Serial
	private static final long serialVersionUID = -5344786827355057523L;

	/**
	 * Constructor used when object's id is long.
	 *
	 * @param message exception message.
	 * @param id object's id
	 */
	public DTOFoundWhenSaveException(String message, long id) {
		super(message, id);
	}

	/**
	 * Constructor used when object's id is {@link UUID}.
	 *
	 * @param message exception message.
	 * @param id object's id
	 */
	public DTOFoundWhenSaveException(String message, UUID id) {
		super(message, id);
	}
}
