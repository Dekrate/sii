package pl.diakowski.mikolaj.sii.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<OrderImplDto> simulatePurchase(@RequestBody NewOrderImplDto newOrderImplDto) {
		try {
		return ResponseEntity.ok(orderService.simulatePurchase(newOrderImplDto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
