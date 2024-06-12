package pl.diakowski.mikolaj.sii.order;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.currency.exception.CurrencyDoesNotExistException;
import pl.diakowski.mikolaj.sii.order.dto.NewOrderDto;
import pl.diakowski.mikolaj.sii.order.dto.OrderDto;
import pl.diakowski.mikolaj.sii.order.dto.OrderDtoMapper;
import pl.diakowski.mikolaj.sii.order.exception.OrderIsNullException;
import pl.diakowski.mikolaj.sii.product.Product;
import pl.diakowski.mikolaj.sii.product.ProductRepository;
import pl.diakowski.mikolaj.sii.product.ProductService;
import pl.diakowski.mikolaj.sii.product.exception.ProductIsNullException;
import pl.diakowski.mikolaj.sii.promocode.PromoCode;
import pl.diakowski.mikolaj.sii.promocode.PromoCodeRepository;
import pl.diakowski.mikolaj.sii.promocode.exception.PromoCodeNotFoundException;

import java.util.Arrays;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final PromoCodeRepository promoCodeRepository;
	private final ProductRepository productRepository;
	private final ProductService productService;

	public OrderService(OrderRepository orderRepository, PromoCodeRepository promoCodeRepository, ProductRepository productRepository, ProductService productService) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.promoCodeRepository = promoCodeRepository;
		this.productService = productService;
	}

	@Transactional
	public OrderDto simulatePurchase(NewOrderDto newOrderDto)
			throws Exception {
		if (newOrderDto == null) {
			throw new OrderIsNullException("New order cannot be null");
		}

		PromoCode promoCode = promoCodeRepository
				.findByCode(newOrderDto.promoCode())
				.orElseThrow(() -> new PromoCodeNotFoundException("Promo code not found"));
		if (Arrays.stream(CurrencyEnum.values()).noneMatch(currencyEnum ->
				currencyEnum.name().equals(newOrderDto.currency()))) {
			throw new CurrencyDoesNotExistException("Currency does not exist");
		}
		CurrencyEnum currency = CurrencyEnum.valueOf(newOrderDto.currency());

		Product product = productRepository
				.findByName(newOrderDto.productName())
				.orElseThrow(() -> new ProductIsNullException("Product not found"));

		Order order = new Order();
		order.setCurrency(currency);
		order.setProduct(product);
		order.setRegularPrice(product.getPrice());
		promoCode.setUses(promoCode.getUses() + 1);

		order.setDiscountPrice(product.getPrice(), promoCode.getCurrency());
		Order saved = orderRepository.save(order);
		return OrderDtoMapper.mapToDto(saved);
	}
}

