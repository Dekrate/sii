package pl.diakowski.mikolaj.sii;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.order.OrderService;
import pl.diakowski.mikolaj.sii.order.dto.NewOrderDto;
import pl.diakowski.mikolaj.sii.order.exception.OrderIsNullException;
import pl.diakowski.mikolaj.sii.promocode.PromoCodeRepository;
import pl.diakowski.mikolaj.sii.promocode.exception.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderTests {

	@Mock
	private PromoCodeRepository promoCodeRepository;

	@InjectMocks
	private OrderService orderService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

	}

	@Test
	void simulatePurchaseThrowsExceptionWhenNewOrderDtoIsNull() {
		assertThrows(OrderIsNullException.class, () -> orderService.simulatePurchase(null));
	}

	@Test
	void simulatePurchaseThrowsExceptionWhenPromoCodeNotFound() {
		when(promoCodeRepository.findByCode(anyString())).thenReturn(Optional.empty());
		assertThrows(PromoCodeNotFoundException.class, () -> orderService.simulatePurchase(new NewOrderDto(CurrencyEnum.USD.name(), "Product", "PromoCode")));
	}
}