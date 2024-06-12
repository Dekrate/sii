package pl.diakowski.mikolaj.sii.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.diakowski.mikolaj.sii.order.dto.NewOrderImplDto;
import pl.diakowski.mikolaj.sii.order.dto.OrderImplDto;

public interface OrderController {
	ResponseEntity<OrderImplDto> simulatePurchase(@RequestBody NewOrderImplDto newOrderImplDto);
}
