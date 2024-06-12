package pl.diakowski.mikolaj.sii.order.dto;

import jakarta.validation.constraints.NotNull;

public record NewOrderImplDto(@NotNull Double regularPrice,
                           @NotNull String currency,
						   @NotNull String promoCode,
                           @NotNull String productName) {
}