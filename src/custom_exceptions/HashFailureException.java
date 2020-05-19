package custom_exceptions;

public class HashFailureException extends RuntimeException {

	public HashFailureException(String message) {
		super(message);
	}
}
