package pl.diakowski.mikolaj.sii.promocode.exception;

public class CodeIsNullException extends Exception {
	public CodeIsNullException(String message) {
		super(message);
	}

	public CodeIsNullException(String message, Throwable cause) {
		super(message, cause);
	}

	public CodeIsNullException(Throwable cause) {
		super(cause);
	}

	public CodeIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CodeIsNullException() {
	}
}
