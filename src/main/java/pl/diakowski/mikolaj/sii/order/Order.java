package pl.diakowski.mikolaj.sii.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import pl.diakowski.mikolaj.sii.basemodel.BaseModel;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.product.Product;

import java.time.LocalDateTime;

@Entity
public class Order extends BaseModel {
	@NotNull
	private LocalDateTime creationDate;
	@NotNull
	private Double regularPrice;
	private Double discountPrice;
	@Enumerated(EnumType.STRING)
	@NotNull
	private CurrencyEnum currency;
	@ManyToOne
	@NotNull
	private Product product;

	public Order() {
	}

	public Order(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime creationDate, Double regularPrice, Double discountPrice, CurrencyEnum currency, Product product) {
		super(id, createdAt, updatedAt);
		this.creationDate = creationDate;
		this.regularPrice = regularPrice;
		this.discountPrice = discountPrice;
		this.currency = currency;
		this.product = product;
	}

	public Order(LocalDateTime updatedAt, LocalDateTime creationDate, Double regularPrice, Double discountPrice, CurrencyEnum currency, Product product) {
		super(updatedAt);
		this.creationDate = creationDate;
		this.regularPrice = regularPrice;
		this.discountPrice = discountPrice;
		this.currency = currency;
		this.product = product;
	}

	public Order(LocalDateTime creationDate, Double regularPrice, Double discountPrice, CurrencyEnum currency, Product product) {
		this.creationDate = creationDate;
		this.regularPrice = regularPrice;
		this.discountPrice = discountPrice;
		this.currency = currency;
		this.product = product;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Double getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(Double regularPrice) {
		this.regularPrice = regularPrice;
	}

	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}
}
