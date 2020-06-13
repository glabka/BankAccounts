package custom_exceptions;

public class UnsupportedClassException extends RuntimeException {

	public UnsupportedClassException(String message) {
		super(message);
	}
	
	public UnsupportedClassException(Class<?> unsupportedClass) {
		super("Class " + unsupportedClass.getName() + " is not supported.");
	}
	
}
