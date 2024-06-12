package pl.diakowski.mikolaj.sii.promocode.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.promocode.PromoCode;

/**
 * DTO for {@link PromoCode}
 */
public record PromoCodeDto(@NotNull @Size(min = 3, max = 24) @Pattern(regexp = "^\\S+$") String code,
                           @NotNull CurrencyEnum currency,
                           @NotNull @Range(min = 1) Long maxUses,
                           @NotNull @Range(min = 0) Double discount,
                           Long uses) {
}