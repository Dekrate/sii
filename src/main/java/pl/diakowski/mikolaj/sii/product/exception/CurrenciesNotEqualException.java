package pl.diakowski.mikolaj.sii.product.exception;

public class CurrenciesNotEqualException extends Exception {
	private Double price;

	public CurrenciesNotEqualException() {
	}

	public CurrenciesNotEqualException(String message) {
		super(message);
	}

	public CurrenciesNotEqualException(String message, Throwable cause) {
		super(message, cause);
	}

	public CurrenciesNotEqualException(String message, Double price) {
		super(message);
		this.price = price;
	}

	public CurrenciesNotEqualException(Throwable cause) {
		super(cause);
	}

	public CurrenciesNotEqualException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public String getPrice() {
		return price.toString();
	}
}
