package pl.diakowski.mikolaj.sii.promocode.exception;

public class PromoCodeNotFoundException extends Exception {
	public PromoCodeNotFoundException() {
	}

	public PromoCodeNotFoundException(String message) {
		super(message);
	}

	public PromoCodeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PromoCodeNotFoundException(Throwable cause) {
		super(cause);
	}

	public PromoCodeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
