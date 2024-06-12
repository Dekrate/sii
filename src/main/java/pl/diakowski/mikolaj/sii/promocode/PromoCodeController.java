package pl.diakowski.mikolaj.sii.promocode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import pl.diakowski.mikolaj.sii.currency.exception.CurrencyDoesNotExistException;
import pl.diakowski.mikolaj.sii.promocode.dto.NewPromoCodeDto;
import pl.diakowski.mikolaj.sii.promocode.dto.PromoCodeDto;
import pl.diakowski.mikolaj.sii.promocode.exception.*;

import java.util.List;

@RestController
public class PromoCodeController {
	private final PromoCodeService promoCodeService;

	public PromoCodeController(PromoCodeService promoCodeService) {
		this.promoCodeService = promoCodeService;
	}

	@PostMapping("/add-promo-code")
	public ResponseEntity<?> createPromoCode(@RequestBody NewPromoCodeDto promoCode) {
		try {
			promoCodeService.addPromoCode(promoCode);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/promo-codes")
	public ResponseEntity<List<PromoCodeDto>> getPromoCodes() {
		return ResponseEntity.ok(promoCodeService.getPromoCodes());
	}

	@GetMapping("/promo-code?code={code}")
	public ResponseEntity<PromoCodeDto> getPromoCode(@PathVariable String code) {
		try {
			return ResponseEntity.ok(promoCodeService.getPromoCode(code));
		} catch (PromoCodeNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@ExceptionHandler
	public ResponseEntity<String> handleInvalidInput(Exception ex) {
		if (ex instanceof HttpMessageNotReadableException exception) {
			String message = exception.getMessage();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
