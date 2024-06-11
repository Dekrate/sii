package pl.diakowski.mikolaj.sii.promocode.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import pl.diakowski.mikolaj.sii.promocode.PromoCodeImpl;

/**
 * DTO for {@link PromoCodeImpl}
 */
public record NewPromoCodeDto(@NotNull @Size(min = 3, max = 24) @Pattern(regexp = "^\\S+$") String code,
                              String currency,
                              @NotNull @Range(min = 1) Long maxUses,
                              @NotNull @Range(min = 0) Double discount) {
}