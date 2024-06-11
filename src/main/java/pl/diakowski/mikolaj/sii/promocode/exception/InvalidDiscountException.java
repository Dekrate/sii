package pl.diakowski.mikolaj.sii.promocode.exception;

public class InvalidDiscountException extends Exception {
	public InvalidDiscountException() {
	}

	public InvalidDiscountException(String message) {
		super(message);
	}

	public InvalidDiscountException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDiscountException(Throwable cause) {
		super(cause);
	}

	public InvalidDiscountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
