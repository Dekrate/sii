package pl.diakowski.mikolaj.sii.promocode.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import pl.diakowski.mikolaj.sii.promocode.PromoCodeImpl;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link PromoCodeImpl}
 */
public record PromoCodeImplDto(Long id, @NotNull LocalDateTime createdAt, @NotNull LocalDateTime updatedAt,
                               @NotNull @Size(min = 3, max = 24) @Pattern(regexp = "^\\S+$") String code,
                               @NotNull String currency,
                               @NotNull @Range(min = 1) Long maxUses) implements Serializable {
}