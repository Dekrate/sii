package pl.diakowski.mikolaj.sii.promocode;

import java.util.Currency;

public interface PromoCode {
	String getCode();

	void setCode(String code);

	Currency getCurrency();

	void setCurrency(Currency currency);

	Long getMaxUses();

	void setMaxUses(Long maxUses);
}
