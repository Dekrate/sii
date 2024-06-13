package pl.diakowski.mikolaj.sii.order;

import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;

import java.math.BigDecimal;

public class SalesReport {

	private CurrencyEnum currency;
	private BigDecimal totalAmount;
	private BigDecimal totalDiscount;
	private Long numberOfPurchases;

	public SalesReport(CurrencyEnum currency, BigDecimal totalAmount, BigDecimal totalDiscount, Long numberOfPurchases) {
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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Long getNumberOfPurchases() {
		return numberOfPurchases;
	}

	public void setNumberOfPurchases(Long numberOfPurchases) {
		this.numberOfPurchases = numberOfPurchases;
	}
}