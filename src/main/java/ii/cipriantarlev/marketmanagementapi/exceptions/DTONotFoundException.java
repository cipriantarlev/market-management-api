package ii.cipriantarlev.marketmanagementapi.exceptions;

import java.io.Serial;

/**
 * Exception to be thrown when the DTO is not found with given id.
 * 
 * @author ciprian.tarlev
 */
public class DTONotFoundException extends RuntimeException {

	private final long id;

	@Serial
	private static final long serialVersionUID = -5344786827355057523L;

	public DTONotFoundException(String message, long id) {
		super(message);
		this.id = id;
	}

	public DTONotFoundException(String message) {
		super(message);
		id = 0;
	}

	public long getDtoId() {
		return id;
	}
}
