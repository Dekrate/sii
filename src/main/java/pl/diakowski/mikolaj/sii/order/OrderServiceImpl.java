package pl.diakowski.mikolaj.sii.order;

import org.springframework.stereotype.Service;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.currency.exception.CurrencyDoesNotExistException;
import pl.diakowski.mikolaj.sii.order.dto.NewOrderImplDto;
import pl.diakowski.mikolaj.sii.order.dto.OrderImplDto;
import pl.diakowski.mikolaj.sii.order.exception.OrderIsNullException;
import pl.diakowski.mikolaj.sii.product.ProductImplRepository;
import pl.diakowski.mikolaj.sii.product.dto.ProductDtoMapper;
import pl.diakowski.mikolaj.sii.product.dto.ProductImplDto;
import pl.diakowski.mikolaj.sii.product.exception.ProductIsNullException;
import pl.diakowski.mikolaj.sii.promocode.PromoCodeRepository;
import pl.diakowski.mikolaj.sii.promocode.exception.PromoCodeNotFoundException;

import java.util.Arrays;

@Service
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;
	private final PromoCodeRepository promoCodeRepository;
	private final ProductImplRepository productImplRepository;

	public OrderServiceImpl(OrderRepository orderRepository, PromoCodeRepository promoCodeRepository, ProductImplRepository productImplRepository) {
		this.orderRepository = orderRepository;
		this.productImplRepository = productImplRepository;
	}

	@Override
	public OrderImplDto simulatePurchase(NewOrderImplDto newOrderImplDto) throws PromoCodeNotFoundException, OrderIsNullException, ProductIsNullException, CurrencyDoesNotExistException {
		if (newOrderImplDto == null) {
			throw new OrderIsNullException("New order cannot be null");
		}
		promoCodeRepository.findByCode(newOrderImplDto.promoCode()).orElseThrow(() -> new PromoCodeNotFoundException("Promo code not found"));
		if (Arrays.stream(CurrencyEnum.values()).noneMatch(currencyEnum ->
				currencyEnum.name().equals(newOrderImplDto.currency()))) {
			throw new CurrencyDoesNotExistException("Currency does not exist");
		}
		CurrencyEnum currency = CurrencyEnum.valueOf(newOrderImplDto.currency());

		if (newOrderImplDto.productName() == null) {
			throw new OrderIsNullException("Product name cannot be null");
		}

		if ()

		ProductImplDto productDto = productImplRepository.findByName(newOrderImplDto.productName()).map(ProductDtoMapper::mapToDto).orElseThrow(() -> new ProductIsNullException("Product not found"));
		Double regularPrice = productDto.price(); // price never null
		i


	}
}
