package customExceptions;

public class BadDateFormat extends RuntimeException{

	public BadDateFormat() {
		super();
	}
	
	public BadDateFormat(String message){
		super(message);
	}
	
}
