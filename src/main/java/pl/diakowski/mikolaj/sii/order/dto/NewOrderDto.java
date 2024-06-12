package pl.diakowski.mikolaj.sii.order.dto;

import jakarta.validation.constraints.NotNull;

public record NewOrderDto(@NotNull String currency,
                          @NotNull String promoCode,
                          @NotNull String productName) {
}