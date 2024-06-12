package pl.diakowski.mikolaj.sii.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import pl.diakowski.mikolaj.sii.basemodel.BaseModel;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.product.Product;
import pl.diakowski.mikolaj.sii.product.ProductImpl;

import java.time.LocalDateTime;

@Entity
public class OrderImpl extends BaseModel implements Order {
	@NotNull
	private LocalDateTime creationDate;
	@NotNull
	private Double regularPrice;
	@NotNull
	private Double discountPrice;
	@Enumerated(EnumType.STRING)
	@NotNull
	private CurrencyEnum currency;
	@ManyToOne(targetEntity = ProductImpl.class) // Spring Data JPA generally doesn't support interfaces as fields
	@NotNull
	private Product product;

	public OrderImpl() {
	}

	public OrderImpl(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime creationDate, Double regularPrice, Double discountPrice, CurrencyEnum currency, Product product) {
		super(id, createdAt, updatedAt);
		this.creationDate = creationDate;
		this.regularPrice = regularPrice;
		this.discountPrice = discountPrice;
		this.currency = currency;
		this.product = product;
	}

	public OrderImpl(LocalDateTime updatedAt, LocalDateTime creationDate, Double regularPrice, Double discountPrice, CurrencyEnum currency, Product product) {
		super(updatedAt);
		this.creationDate = creationDate;
		this.regularPrice = regularPrice;
		this.discountPrice = discountPrice;
		this.currency = currency;
		this.product = product;
	}

	public OrderImpl(LocalDateTime creationDate, Double regularPrice, Double discountPrice, CurrencyEnum currency, Product product) {
		this.creationDate = creationDate;
		this.regularPrice = regularPrice;
		this.discountPrice = discountPrice;
		this.currency = currency;
		this.product = product;
	}

	@Override
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	@Override
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public Double getRegularPrice() {
		return regularPrice;
	}

	@Override
	public void setRegularPrice(Double regularPrice) {
		this.regularPrice = regularPrice;
	}

	@Override
	public Double getDiscountPrice() {
		return discountPrice;
	}

	@Override
	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}

	@Override
	public Product getProduct() {
		return product;
	}

	@Override
	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public CurrencyEnum getCurrency() {
		return currency;
	}

	@Override
	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}
}
