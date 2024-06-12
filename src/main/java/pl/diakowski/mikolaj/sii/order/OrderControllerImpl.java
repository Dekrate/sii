package pl.diakowski.mikolaj.sii.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.diakowski.mikolaj.sii.order.dto.NewOrderImplDto;
import pl.diakowski.mikolaj.sii.order.dto.OrderImplDto;

@RestController
public class OrderControllerImpl implements OrderController {
	private final OrderService orderService;

	public OrderControllerImpl(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	@GetMapping("/simulate-purchase")
	public ResponseEntity<NewOrderImplDto> simulatePurchase(@RequestParam String name,
	                                                        @RequestParam String promoCode) {

	}
}
