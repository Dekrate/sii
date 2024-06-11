package pl.diakowski.mikolaj.sii.product.exception;

public class ProductIsNullException extends Exception {
	public ProductIsNullException() {
	}

	public ProductIsNullException(String message) {
		super(message);
	}

	public ProductIsNullException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductIsNullException(Throwable cause) {
		super(cause);
	}

	public ProductIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
