package pl.diakowski.mikolaj.sii.order;

import org.springframework.stereotype.Service;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.currency.exception.CurrencyDoesNotExistException;
import pl.diakowski.mikolaj.sii.order.dto.NewOrderDto;
import pl.diakowski.mikolaj.sii.order.dto.OrderDto;
import pl.diakowski.mikolaj.sii.order.exception.OrderIsNullException;
import pl.diakowski.mikolaj.sii.product.ProductRepository;
import pl.diakowski.mikolaj.sii.product.dto.ProductDtoMapper;
import pl.diakowski.mikolaj.sii.product.dto.Product;
import pl.diakowski.mikolaj.sii.product.exception.ProductIsNullException;
import pl.diakowski.mikolaj.sii.promocode.PromoCodeRepository;
import pl.diakowski.mikolaj.sii.promocode.exception.PromoCodeNotFoundException;

import java.util.Arrays;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final PromoCodeRepository promoCodeRepository;
	private final ProductRepository productRepository;

	public OrderService(OrderRepository orderRepository, PromoCodeRepository promoCodeRepository, ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
	}

	@Override
	public OrderDto simulatePurchase(NewOrderDto newOrderDto) throws PromoCodeNotFoundException, OrderIsNullException, ProductIsNullException, CurrencyDoesNotExistException {
		if (newOrderDto == null) {
			throw new OrderIsNullException("New order cannot be null");
		}
		promoCodeRepository.findByCode(newOrderDto.promoCode()).orElseThrow(() -> new PromoCodeNotFoundException("Promo code not found"));
		if (Arrays.stream(CurrencyEnum.values()).noneMatch(currencyEnum ->
				currencyEnum.name().equals(newOrderDto.currency()))) {
			throw new CurrencyDoesNotExistException("Currency does not exist");
		}
		CurrencyEnum currency = CurrencyEnum.valueOf(newOrderDto.currency());

		if (newOrderDto.productName() == null) {
			throw new OrderIsNullException("Product name cannot be null");
		}

		Product productDto = productRepository.findByName(newOrderDto.productName()).map(ProductDtoMapper::mapToDto).orElseThrow(() -> new ProductIsNullException("Product not found"));
		Double regularPrice = productDto.price(); // price never null



	}
}
