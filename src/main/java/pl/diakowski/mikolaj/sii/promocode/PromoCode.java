package pl.diakowski.mikolaj.sii.promocode;


import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;

public interface PromoCode {
	String getCode();

	void setCode(String code);

	CurrencyEnum getCurrency();

	void setCurrency(CurrencyEnum currencyEnum);

	Long getMaxUses();

	void setMaxUses(Long maxUses);

	Double getDiscount();

	void setDiscount(Double discount);

	Long getUses();

	void setUses(Long uses);
}
