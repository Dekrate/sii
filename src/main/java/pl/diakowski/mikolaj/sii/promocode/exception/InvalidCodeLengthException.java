package pl.diakowski.mikolaj.sii.promocode.exception;

public class InvalidCodeLengthException extends Exception {
	public InvalidCodeLengthException() {
	}

	public InvalidCodeLengthException(String message) {
		super(message);
	}

	public InvalidCodeLengthException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidCodeLengthException(Throwable cause) {
		super(cause);
	}

	public InvalidCodeLengthException(String message, Throwable cause,
	                                  boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
