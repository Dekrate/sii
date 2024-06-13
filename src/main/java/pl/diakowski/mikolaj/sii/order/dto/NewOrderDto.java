package pl.diakowski.mikolaj.sii.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record NewOrderDto(@Null String currency,
                          @NotNull String promoCode,
                          @NotNull String productName) {
}