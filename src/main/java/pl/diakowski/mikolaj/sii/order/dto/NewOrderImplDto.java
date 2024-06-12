package pl.diakowski.mikolaj.sii.order.dto;

import jakarta.validation.constraints.NotNull;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.product.Product;

import java.io.Serializable;
import java.time.LocalDateTime;

public record NewOrderImplDto(@NotNull Double regularPrice,
                           @NotNull Double discountPrice, @NotNull String currency,
                           @NotNull String productId) implements Serializable {
}