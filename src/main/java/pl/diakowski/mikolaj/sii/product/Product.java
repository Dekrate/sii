package pl.diakowski.mikolaj.sii.product;

import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;

public interface Product {
	public CurrencyEnum getCurrency();

	public void setCurrency(CurrencyEnum currency);

	public String getName();

	public void setName(String name);

	public Double getPrice();

	public void setPrice(Double price);

	public String getDescription();

	public void setDescription(String description);
}
