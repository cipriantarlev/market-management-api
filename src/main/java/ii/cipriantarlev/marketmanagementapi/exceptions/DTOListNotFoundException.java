package ii.cipriantarlev.marketmanagementapi.exceptions;

import java.io.Serial;

/**
 * Exception to be thrown when the DTO list is not found.
 * 
 * @author ciprian.tarlev
 */
public class DTOListNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -5344786827355057523L;

	public DTOListNotFoundException(String message) {
		super(message);
	}
}
