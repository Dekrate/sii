package pl.diakowski.mikolaj.sii.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

public interface OrderController {
	ResponseEntity<?> simulatePurchase(String name, String promoCode);
}
