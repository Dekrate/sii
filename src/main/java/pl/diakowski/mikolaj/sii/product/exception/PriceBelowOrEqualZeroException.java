package pl.diakowski.mikolaj.sii.product.exception;

public class PriceBelowOrEqualZeroException extends Exception {
	public PriceBelowOrEqualZeroException(String message) {
		super(message);
	}
}
