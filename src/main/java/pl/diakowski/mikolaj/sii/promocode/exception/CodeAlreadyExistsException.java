package pl.diakowski.mikolaj.sii.promocode.exception;

public class CodeAlreadyExistsException extends Exception {
	public CodeAlreadyExistsException() {
	}

	public CodeAlreadyExistsException(String message) {
		super(message);
	}

	public CodeAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public CodeAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public CodeAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

