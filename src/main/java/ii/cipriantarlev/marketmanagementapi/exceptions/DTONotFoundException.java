package ii.cipriantarlev.marketmanagementapi.exceptions;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception to be thrown when the DTO is not found with given id.
 * 
 * @author ciprian.tarlev
 */
public class DTONotFoundException extends RuntimeException {

	private final long longId;

	private final UUID uuidId;

	@Serial
	private static final long serialVersionUID = -5344786827355057523L;

	public DTONotFoundException(String message, long id) {
		super(message);
		this.longId = id;
		uuidId = UUID.fromString("00000000-0000-0000-0000-000000000000");
	}

	public DTONotFoundException(String message, UUID id) {
		super(message);
		this.uuidId = id;
		longId = 0;
	}

	public DTONotFoundException(String message) {
		super(message);
		longId = 0;
		uuidId = UUID.fromString("00000000-0000-0000-0000-000000000000");
	}

	public long getLongId() {
		return longId;
	}

	public UUID getUUIDId() {
		return uuidId;
	}
}
