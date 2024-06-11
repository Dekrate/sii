package pl.diakowski.mikolaj.sii.promocode.exception;

public class InvalidMaxUsesException extends Exception {
	public InvalidMaxUsesException() {
	}

	public InvalidMaxUsesException(String message) {
		super(message);
	}

	public InvalidMaxUsesException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidMaxUsesException(Throwable cause) {
		super(cause);
	}

	public InvalidMaxUsesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
