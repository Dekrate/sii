package pl.diakowski.mikolaj.sii.promocode;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.diakowski.mikolaj.sii.promocode.dto.NewPromoCodeDto;
import pl.diakowski.mikolaj.sii.promocode.dto.PromoCodeDto;
import pl.diakowski.mikolaj.sii.promocode.exception.*;

import java.util.List;

@RestController
public class PromoCodeControllerImpl implements PromoCodeController {
	private final PromoCodeService promoCodeService;

	public PromoCodeControllerImpl(PromoCodeService promoCodeService) {
		this.promoCodeService = promoCodeService;
	}

	@Override
	@PostMapping("/add-promo-code")
	public ResponseEntity<?> createPromoCode(@RequestBody NewPromoCodeDto promoCode) {
		try {
			promoCodeService.addPromoCode(promoCode);
		} catch (CodeIsNullException | InvalidMaxUsesException | CodeAlreadyExistsException |
		         InvalidCodeLengthException | CurrencyDoesNotExistException | InvalidDiscountException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			}
		return ResponseEntity.ok().build();
	}

	@Override
	@GetMapping("/promo-codes")
	public ResponseEntity<List<PromoCodeDto>> getPromoCodes() {
		return ResponseEntity.ok(promoCodeService.getPromoCodes());
	}

	@Override
	@GetMapping("/promo-code?code={code}")
	public ResponseEntity<PromoCodeDto> getPromoCode(@PathVariable String code) {
		try {
			return ResponseEntity.ok(promoCodeService.getPromoCode(code));
		} catch (PromoCodeNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}