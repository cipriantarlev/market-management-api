package ii.cipriantarlev.marketmanagementapi.user;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1839843892420725805L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message) {
		super(message);
	}

}
