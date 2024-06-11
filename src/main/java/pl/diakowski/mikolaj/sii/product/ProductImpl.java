package pl.diakowski.mikolaj.sii.product;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import pl.diakowski.mikolaj.sii.basemodel.BaseModel;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;

import java.time.LocalDateTime;

@Entity
public class ProductImpl extends BaseModel implements Product {
	@NotNull
	CurrencyEnum currency;
	@NotNull
	private String name;
	private String description;
	@NotNull
	@Range(min = 0)
	private Double price;

	public ProductImpl(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, CurrencyEnum currency, String name, String description, Double price) {
		super(id, createdAt, updatedAt);
		this.currency = currency;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public ProductImpl(CurrencyEnum currency, String name, String description, Double price) {
		this.currency = currency;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public ProductImpl() {
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
