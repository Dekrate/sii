package pl.diakowski.mikolaj.sii.product.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;

import java.io.Serializable;


public record ProductImplDto(@NotNull CurrencyEnum currency, @NotNull String name, String description,
                             @NotNull @Range(min = 0) Double price) {
}