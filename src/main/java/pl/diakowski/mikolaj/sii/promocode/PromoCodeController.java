package pl.diakowski.mikolaj.sii.promocode;

import org.springframework.http.ResponseEntity;

public interface PromoCodeController {

	public ResponseEntity<?> createPromoCode(PromoCode promoCode);
}
