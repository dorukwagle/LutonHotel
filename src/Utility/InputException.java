package Utility;

public class InputException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InputException() {
		super("The data you entered is not valid. Please correct it.");
	}

	public InputException(String message) {
		super(message);
	}
}
