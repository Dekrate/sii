package pl.diakowski.mikolaj.sii.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.diakowski.mikolaj.sii.order.dto.NewOrderDto;
import pl.diakowski.mikolaj.sii.order.dto.OrderDto;

import java.util.List;

@RestController
@Tag(name = "Order")
public class OrderController {
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/simulate-purchase")
	@Operation(summary = "Simulate purchase")
	public ResponseEntity<?> simulatePurchase(@RequestBody NewOrderDto newOrderDto) {
		try {
			OrderDto body = orderService.simulatePurchase(newOrderDto);
			return ResponseEntity.ok(body);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/purchase-details")
	@Operation(summary = "Get purchase details")
	public ResponseEntity<List<SalesReport>> getRawSalesReport() {
		return ResponseEntity.ok(orderService.getSalesReport());
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
