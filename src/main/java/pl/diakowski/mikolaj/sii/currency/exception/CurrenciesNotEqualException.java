package pl.diakowski.mikolaj.sii.currency.exception;

public class CurrenciesNotEqualException extends Exception {
	private Double price;

	public CurrenciesNotEqualException(String message) {
		super(message);
	}

	public CurrenciesNotEqualException(String message, Double price) {
		super(message);
		this.price = price;
	}

	public String getPrice() {
		return price.toString();
	}
}
