package pl.diakowski.mikolaj.sii.promocode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.bind.DefaultValue;
import pl.diakowski.mikolaj.sii.basemodel.BaseModel;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.promocode.exception.InvalidDiscountException;
import pl.diakowski.mikolaj.sii.promocode.exception.InvalidMaxUsesException;
import pl.diakowski.mikolaj.sii.promocode.exception.PromoCodeExpiredException;

@Entity
public class PromoCode extends BaseModel {
	@NotNull
	@Size(min = 3, max = 24)
	@Column(unique = true)
	@Pattern(regexp = "^\\S+$")
	private String code;
	@Enumerated(EnumType.STRING)
	private CurrencyEnum currencyEnum;

	@NotNull
	@Range(min = 0)
	private Double discount;
	@NotNull
	@Range(min = 1)
	private Long maxUses;

	@Range(min = 0)
	private Long uses;

	public PromoCode() {

	}

	public Long getMaxUses() {
		return maxUses;
	}

	public void setMaxUses(Long maxUses) throws InvalidMaxUsesException {
		if (maxUses == null) {
			throw new InvalidMaxUsesException("Max uses cannot be null");
		}
		if (maxUses < 1) {
			throw new InvalidMaxUsesException("Max uses must be greater than 0");
		}
		this.maxUses = maxUses;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (code == null) {
			throw new NullPointerException("Code cannot be null");
		}
		if (code.length() < 3 || code.length() > 24) {
			throw new IllegalArgumentException("Code must be at least 3 characters long and at most 24 characters long");
		}
		if (!code.matches("^\\S+$")) {
			throw new IllegalArgumentException("Code must not contain whitespace characters");
		}
		this.code = code;
	}

	public CurrencyEnum getCurrency() {
		return currencyEnum;
	}

	public void setCurrency(CurrencyEnum currencyEnum) {
		if (currencyEnum == null) {
			throw new NullPointerException("Currency cannot be null");
		}
		this.currencyEnum = currencyEnum;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) throws InvalidDiscountException {
		if (discount == null) {
			throw new NullPointerException("Discount cannot be null");
		}
		if (discount < 0) {
			throw new InvalidDiscountException("Discount must be greater than or equal to 0");
		}
		this.discount = discount;
	}

	public Long getUses() {
		return uses;
	}

	public void setUses(Long uses) throws InvalidMaxUsesException, PromoCodeExpiredException {
		if (uses == null) {
			throw new NullPointerException("Uses cannot be null");
		}
		if (uses < 0) {
			throw new InvalidMaxUsesException("Uses must be greater than or equal to 0");
		}
		if (uses > maxUses) {
			throw new PromoCodeExpiredException("Uses cannot be greater than max uses");
		}
		this.uses = uses;
	}
}