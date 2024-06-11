package pl.diakowski.mikolaj.sii.promocode.exception;

public class CurrencyDoesNotExistException extends Exception {
	public CurrencyDoesNotExistException() {
	}

	public CurrencyDoesNotExistException(String message) {
		super(message);
	}

	public CurrencyDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public CurrencyDoesNotExistException(Throwable cause) {
		super(cause);
	}

	public CurrencyDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
