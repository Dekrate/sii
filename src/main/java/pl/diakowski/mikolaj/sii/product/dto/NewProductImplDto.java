package pl.diakowski.mikolaj.sii.product.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record NewProductImplDto(@NotNull String currency, @NotNull String name, String description,
                                @NotNull @Range(min = 0) Double price) {
}
