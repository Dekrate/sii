package pl.diakowski.mikolaj.sii.promocode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import pl.diakowski.mikolaj.sii.basemodel.BaseModel;

import java.time.LocalDateTime;
import java.util.Currency;

@Entity
public class PromoCodeImpl extends BaseModel implements PromoCode {
	@NotNull
	@Size(min = 3, max = 24)
	@Column(unique = true)
	@Pattern(regexp = "^\\S+$")
	private String code;

	@NotNull
	private String currency;

	@NotNull
	@Range(min = 0)
	private Double discount;
	@NotNull
	@Range(min = 1)
	private Long maxUses;

	public PromoCodeImpl() {

	}

	public PromoCodeImpl(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String code, String currency, Double discount, Long maxUses) {
		super(id, createdAt, updatedAt);
		this.code = code;
		this.currency = currency;
		this.discount = discount;
		this.maxUses = maxUses;
	}

	public PromoCodeImpl(String code, String currency, Double discount, Long maxUses) {
		this.code = code;
		this.currency = currency;
		this.discount = discount;
		this.maxUses = maxUses;
	}



	public void setCurrency(Currency currency) {
		this.currency = currency.getCurrencyCode(); //conversion to an actual currency
	}

	@Override
	public Long getMaxUses() {
		return maxUses;
	}

	@Override
	public void setMaxUses(Long maxUses) {
		this.maxUses = maxUses;
	}

	public Currency getCurrency() {
		return Currency.getInstance(currency); //conversion to an actual currency
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
}