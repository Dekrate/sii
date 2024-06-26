package pl.diakowski.mikolaj.sii.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.order.Order;
import pl.diakowski.mikolaj.sii.product.dto.ProductDto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Order}
 */
public class OrderDto implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	@JsonProperty
	private final @NotNull LocalDateTime creationDate;
	@JsonProperty
	private final @NotNull Double regularPrice;
	@JsonProperty
	private final @NotNull Double discountPrice;
	@JsonProperty
	private final @NotNull CurrencyEnum currency;
	@JsonProperty
	private final @NotNull ProductDto productDto;
	@JsonProperty
	private String warning;

	public OrderDto(@NotNull LocalDateTime creationDate, @NotNull Double regularPrice,
	                @NotNull Double discountPrice, @NotNull CurrencyEnum currency,
	                @NotNull ProductDto productDto) {
		this.creationDate = creationDate;
		this.regularPrice = regularPrice;
		this.discountPrice = discountPrice;
		this.currency = currency;
		this.productDto = productDto;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public @NotNull Double regularPrice() {
		return regularPrice;
	}

	public @NotNull Double discountPrice() {
		return discountPrice;
	}

}