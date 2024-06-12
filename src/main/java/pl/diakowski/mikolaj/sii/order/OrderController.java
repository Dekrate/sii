package pl.diakowski.mikolaj.sii.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.diakowski.mikolaj.sii.order.dto.NewOrderDto;
import pl.diakowski.mikolaj.sii.order.dto.OrderDto;

@RestController
public class OrderController {
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/simulate-purchase")
	public ResponseEntity<OrderDto> simulatePurchase(@RequestBody NewOrderDto newOrderDto) {
		try {
		return ResponseEntity.ok(orderService.simulatePurchase(newOrderDto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
