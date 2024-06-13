package pl.diakowski.mikolaj.sii.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;
import pl.diakowski.mikolaj.sii.basemodel.BaseModel;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.currency.exception.CurrenciesNotEqualException;
import pl.diakowski.mikolaj.sii.order.exception.InvalidPriceException;
import pl.diakowski.mikolaj.sii.product.Product;
import pl.diakowski.mikolaj.sii.product.exception.ProductIsNullException;

import java.time.LocalDateTime;
import java.util.Arrays;

@Entity(name = "purchase")
public class Order extends BaseModel {
	@CreationTimestamp
	private LocalDateTime creationDate;
	@NotNull
	@Range(min = 0)
	private Double regularPrice;
	@Range(min = 0)
	private Double discountPrice;
	@Enumerated(EnumType.STRING)
	@NotNull
	private CurrencyEnum currency;
	@ManyToOne
	@NotNull
	private Product product;

	public Order() {
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public Double getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(Double regularPrice) throws InvalidPriceException {
		if (regularPrice == null) {
			throw new InvalidPriceException("Regular price cannot be null");
		}
		if (regularPrice <= 0) {
			throw new InvalidPriceException("Regular price cannot be negative");
		}
		this.regularPrice = regularPrice;
	}

	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice, CurrencyEnum promoCodeCurrency) throws Exception {
		if (discountPrice == null) {
			throw new InvalidPriceException("Discount price cannot be null");
		}
		if (discountPrice < 0) {
			this.discountPrice = 0.0;
		}
		if (promoCodeCurrency == null) {
			throw new InvalidPriceException("Promo code currency cannot be null");
		}
		if (currency != promoCodeCurrency) {
			this.discountPrice = regularPrice;
			// daj warning
		} else {
			double discountedPrice = regularPrice - discountPrice;
			if (discountedPrice < 0) {
				throw new InvalidPriceException("Discount price cannot be greater than regular price");
			}
			this.discountPrice = discountedPrice;
		}
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) throws ProductIsNullException {
		if (product == null) {
			throw new ProductIsNullException("Product cannot be null");
		}
		this.product = product;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) throws CurrenciesNotEqualException {
		if (currency == null) {
			throw new NullPointerException("Currency cannot be null");
		}
		if (Arrays.stream(CurrencyEnum.values()).noneMatch(currencyEnum -> currencyEnum.equals(currency))) {
			throw new CurrenciesNotEqualException("Currency not supported");
		}
		this.currency = currency;
	}
}
