package pl.diakowski.mikolaj.sii.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import pl.diakowski.mikolaj.sii.basemodel.BaseModel;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.product.exception.PriceBelowOrEqualZeroException;

import java.time.LocalDateTime;

@Entity
public class Product extends BaseModel {
	@NotNull
	@Enumerated(EnumType.STRING)
	CurrencyEnum currency;
	@NotNull
	@Column(unique = true)
	private String name;
	private String description;
	@NotNull
	@Range(min = 0)
	private Double price;

	public Product() {
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) throws NullPointerException {
		if (currency == null) {
			throw new NullPointerException("Currency cannot be null");
		}
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new NullPointerException("Name cannot be null");
		}
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) throws PriceBelowOrEqualZeroException {
		if (price == null) {
			throw new NullPointerException("Price cannot be null");
		}
		if (price <= 0.0) {
			throw new PriceBelowOrEqualZeroException("Price cannot be negative");
		}
		this.price = price;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
