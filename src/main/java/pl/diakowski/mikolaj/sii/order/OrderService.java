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
import pl.diakowski.mikolaj.sii.product.exception.ProductIsNullException;
import pl.diakowski.mikolaj.sii.promocode.PromoCode;
import pl.diakowski.mikolaj.sii.promocode.PromoCodeRepository;
import pl.diakowski.mikolaj.sii.promocode.exception.PromoCodeExpiredException;
import pl.diakowski.mikolaj.sii.promocode.exception.PromoCodeNotFoundException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final PromoCodeRepository promoCodeRepository;
	private final ProductRepository productRepository;

	public OrderService(OrderRepository orderRepository, PromoCodeRepository promoCodeRepository, ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.promoCodeRepository = promoCodeRepository;
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

		Product product = productRepository
				.findByName(newOrderDto.productName())
				.orElseThrow(() -> new ProductIsNullException("Product not found"));
		if (Arrays.stream(CurrencyEnum.values()).noneMatch(currencyEnum ->
				currencyEnum.name().equals(product.getCurrency().getName()))) {
			throw new CurrencyDoesNotExistException("Currency does not exist");
		}
		CurrencyEnum currency = CurrencyEnum.valueOf(product.getCurrency().getName());

		Order order = new Order();

		order.setCurrency(currency);
		order.setProduct(product);
		order.setRegularPrice(product.getPrice());
		String warning = "";
		order.setDiscountPrice(promoCode.getDiscount(), promoCode.getCurrency());
		Order saved = orderRepository.save(order);

		OrderDto orderDto = OrderDtoMapper.mapToDto(saved);
		if (orderDto.regularPrice().equals(orderDto.discountPrice())) {
			warning += "Currencies are not equal, discount price is the same as regular price.";
		}
		try {
			if (warning.isEmpty())
				promoCode.setUses(promoCode.getUses() + 1);
		} catch (PromoCodeExpiredException e) {
			order.setDiscountPrice(0.0, currency);
			warning = "Promo code expired, discount price is 0.0";
		orderDto.setWarning(warning);
		}

		return orderDto;
	}

	public List<SalesReport> getSalesReport() {
		List<Object[]> reportData = orderRepository.getSalesReport();
		return reportData.stream().map(this::mapToSalesReport).collect(Collectors.toList());
	}

	private SalesReport mapToSalesReport(Object[] data) {
		CurrencyEnum currency = CurrencyEnum.valueOf((String) data[0]);
		BigDecimal totalAmount = (BigDecimal) data[1];
		BigDecimal totalDiscount = (BigDecimal) data[2];
		Long numberOfPurchases = ((Number) data[3]).longValue();
		return new SalesReport(currency, totalAmount, totalDiscount, numberOfPurchases);
	}
}

