package customExceptions;

import domain.Multiton;

public class InstanceAlreadyExistsException extends Exception {
	
	public InstanceAlreadyExistsException(Multiton instance) {
		super("Instance of class " + instance.getClass().getName() + " with id = " + instance.getStringId() + " already exists. instance.toString = " + instance);
	}
	
}
