package pl.diakowski.mikolaj.sii.order.dto;

import jakarta.validation.constraints.NotNull;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.order.Order;
import pl.diakowski.mikolaj.sii.product.dto.ProductDto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Order}
 */
public record OrderDto(@NotNull LocalDateTime creationDate, @NotNull Double regularPrice,
                       @NotNull Double discountPrice, @NotNull CurrencyEnum currency,
                       @NotNull ProductDto productDto) implements Serializable {
}