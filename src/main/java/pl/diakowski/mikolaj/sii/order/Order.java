package pl.diakowski.mikolaj.sii.order;

import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.product.Product;

import java.time.LocalDateTime;

public interface Order {
	LocalDateTime getCreationDate();

	void setCreationDate(LocalDateTime creationDate);

	Double getRegularPrice();

	void setRegularPrice(Double regularPrice);

	Double getDiscountPrice();

	void setDiscountPrice(Double discountPrice);


	Product getProduct();

	void setProduct(Product product);

	CurrencyEnum getCurrency();

	void setCurrency(CurrencyEnum currency);
}
