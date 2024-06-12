package pl.diakowski.mikolaj.sii.order.dto;

import jakarta.validation.constraints.NotNull;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.product.Product;
import pl.diakowski.mikolaj.sii.product.dto.ProductImplDto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link pl.diakowski.mikolaj.sii.order.OrderImpl}
 */
public record OrderImplDto(@NotNull LocalDateTime creationDate, @NotNull Double regularPrice,
                           @NotNull Double discountPrice, @NotNull CurrencyEnum currency,
                           @NotNull ProductImplDto product) implements Serializable {
}