package pl.diakowski.mikolaj.sii.promocode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import pl.diakowski.mikolaj.sii.basemodel.BaseModel;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;

import java.time.LocalDateTime;

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

	private Long uses;

	public PromoCode() {

	}

	public PromoCode(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String code, CurrencyEnum currencyEnum, Double discount, Long maxUses, Long uses) {
		super(id, createdAt, updatedAt);
		this.code = code;
		this.currencyEnum = currencyEnum;
		this.discount = discount;
		this.maxUses = maxUses;
		this.uses = uses;
	}

	public PromoCode(String code, CurrencyEnum currencyEnum, Double discount, Long maxUses, Long uses) {
		this.code = code;
		this.currencyEnum = currencyEnum;
		this.discount = discount;
		this.maxUses = maxUses;
		this.uses = uses;
	}

	public PromoCode(LocalDateTime updatedAt, String code, CurrencyEnum currencyEnum, Double discount, Long maxUses, Long uses) {
		super(updatedAt);
		this.code = code;
		this.currencyEnum = currencyEnum;
		this.discount = discount;
		this.maxUses = maxUses;
		this.uses = uses;
	}

	public Long getMaxUses() {
		return maxUses;
	}

	public void setMaxUses(Long maxUses) {
		this.maxUses = maxUses;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CurrencyEnum getCurrency() {
		return currencyEnum;
	}

	public void setCurrency(CurrencyEnum currencyEnum) {
		this.currencyEnum = currencyEnum;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Long getUses() {
		return uses;
	}

	public void setUses(Long uses) {
		this.uses = uses;
	}
}