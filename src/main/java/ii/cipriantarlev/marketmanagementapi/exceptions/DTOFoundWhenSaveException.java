package ii.cipriantarlev.marketmanagementapi.exceptions;

import java.io.Serial;

/**
 * Exception to be thrown when the DTO was found during creation of a new entity
 * in database.
 * 
 * @author ciprian.tarlev
 */
public class DTOFoundWhenSaveException extends DTONotFoundException {

	@Serial
	private static final long serialVersionUID = -5344786827355057523L;

	public DTOFoundWhenSaveException(String message, long id) {
		super(message, id);
	}
}
