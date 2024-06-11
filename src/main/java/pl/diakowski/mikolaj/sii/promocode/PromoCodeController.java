package pl.diakowski.mikolaj.sii.promocode;

import org.springframework.http.ResponseEntity;
import pl.diakowski.mikolaj.sii.promocode.dto.NewPromoCodeDto;
import pl.diakowski.mikolaj.sii.promocode.dto.PromoCodeDto;

import java.util.List;

public interface PromoCodeController {
	public ResponseEntity<?> createPromoCode(NewPromoCodeDto promoCode);

	public ResponseEntity<List<PromoCodeDto>> getPromoCodes();

	public ResponseEntity<?> getPromoCode(String code);
}
