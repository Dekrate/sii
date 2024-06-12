package pl.diakowski.mikolaj.sii.currency.exception;

import pl.diakowski.mikolaj.sii.order.dto.OrderDto;

public class CurrenciesNotEqualException extends Exception {
	private Double price;
	private OrderDto orderDto;
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

	public CurrenciesNotEqualException(String currencyDoesNotExist, OrderDto orderDto) {
		super(currencyDoesNotExist);
		this.orderDto = orderDto;
	}

	public String getPrice() {
		return price.toString();
	}
}
