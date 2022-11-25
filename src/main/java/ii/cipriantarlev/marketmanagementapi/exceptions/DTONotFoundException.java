/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.exceptions;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception to be thrown when the DTO is not found with given id.
 * 
 * @author ciprian.tarlev
 */
public class DTONotFoundException extends RuntimeException {

	/**
	 * Object's long id.
	 */
	private final long longId;

	/**
	 * Object's uuid id.
	 */
	private final UUID uuidId;

	@Serial
	private static final long serialVersionUID = -5344786827355057523L;

	/**
	 * Constructor used when object has a long id.
	 *
	 * @param message exception's message.
	 * @param id object's long id.
	 */
	public DTONotFoundException(String message, long id) {
		super(message);
		this.longId = id;
		uuidId = UUID.fromString("00000000-0000-0000-0000-000000000000");
	}

	/**
	 * Constructor used when object has a {@link UUID} id.
	 *
	 * @param message exception's message.
	 * @param id object's {@link UUID} id.
	 */
	public DTONotFoundException(String message, UUID id) {
		super(message);
		this.uuidId = id;
		longId = 0;
	}

	/**
	 * Constructor used when there is only the message.
	 *
	 * @param message exception's message.
	 */
	public DTONotFoundException(String message) {
		super(message);
		longId = 0;
		uuidId = UUID.fromString("00000000-0000-0000-0000-000000000000");
	}

	/**
	 * Method to get the value of long id.
	 *
	 * @return long id value.
	 */
	public long getLongId() {
		return longId;
	}

	/**
	 * Method to get the value of {@link UUID} id.
	 *
	 * @return {@link UUID} id value.
	 */
	public UUID getUUIDId() {
		return uuidId;
	}
}