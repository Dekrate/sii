package pl.diakowski.mikolaj.sii.order;

import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;

public class SalesReport {

	private CurrencyEnum currency;
	private Double totalAmount;
	private Double totalDiscount;
	private Long numberOfPurchases;

	public SalesReport(CurrencyEnum currency, Double totalAmount, Double totalDiscount, Long numberOfPurchases) {
		this.currency = currency;
		this.totalAmount = totalAmount;
		this.totalDiscount = totalDiscount;
		this.numberOfPurchases = numberOfPurchases;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Long getNumberOfPurchases() {
		return numberOfPurchases;
	}

	public void setNumberOfPurchases(Long numberOfPurchases) {
		this.numberOfPurchases = numberOfPurchases;
	}
}