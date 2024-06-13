package pl.diakowski.mikolaj.sii.order;

import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;

import java.math.BigDecimal;

public class SalesReport {

	private final CurrencyEnum currency;
	private final BigDecimal totalAmount;
	private final BigDecimal totalDiscount;
	private final Long numberOfPurchases;

	public SalesReport(CurrencyEnum currency, BigDecimal totalAmount, BigDecimal totalDiscount, Long numberOfPurchases) {
		this.currency = currency;
		this.totalAmount = totalAmount;
		this.totalDiscount = totalDiscount;
		this.numberOfPurchases = numberOfPurchases;
	}

}