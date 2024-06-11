package pl.diakowski.mikolaj.sii.product;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.product.dto.NewProductImplDto;
import pl.diakowski.mikolaj.sii.product.dto.ProductDtoMapper;
import pl.diakowski.mikolaj.sii.product.dto.ProductImplDto;
import pl.diakowski.mikolaj.sii.product.exception.CurrenciesNotEqualException;
import pl.diakowski.mikolaj.sii.product.exception.ProductIsNullException;
import pl.diakowski.mikolaj.sii.currency.exception.CurrencyDoesNotExistException;
import pl.diakowski.mikolaj.sii.promocode.PromoCode;
import pl.diakowski.mikolaj.sii.promocode.PromoCodeRepository;
import pl.diakowski.mikolaj.sii.promocode.PromoCodeService;
import pl.diakowski.mikolaj.sii.promocode.exception.PromoCodeNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductImplRepository productRepository;
	private final PromoCodeRepository promoCodeRepository;

	public ProductServiceImpl(ProductImplRepository productRepository, PromoCodeRepository promoCodeRepository) {
		this.productRepository = productRepository;
		this.promoCodeRepository = promoCodeRepository;
	}

	@Override
	@Transactional
	public void createProduct(NewProductImplDto productDto) throws ProductIsNullException, CurrencyDoesNotExistException {
		if (productDto == null) {
			throw new ProductIsNullException("OrderDto cannot be null");
		}
		if (productDto.name() == null) {
			throw new ProductIsNullException("OrderDto.name() cannot be null");
		}
		if (productDto.price() == null) {
			throw new ProductIsNullException("OrderDto.price() cannot be null");
		}
		if (Arrays.stream(CurrencyEnum.values()).noneMatch(currencyEnum ->
				currencyEnum.name().equals(productDto.currency()))) {
			throw new CurrencyDoesNotExistException("Currency does not exist");
		}
		productRepository.save(new ProductImpl(CurrencyEnum.valueOf(productDto.currency()), productDto.name(),
				productDto.description(), productDto.price()));
	}

	@Override
	@Transactional
	public void updateProduct(String name, NewProductImplDto productDto) throws ProductIsNullException, CurrencyDoesNotExistException {
		if (productDto == null) {
			throw new ProductIsNullException("ProductDto cannot be null");
		}

		ProductImpl product = productRepository.findByName(name)
				.orElseThrow(() -> new ProductIsNullException("Product does not exist"));
		if (productDto.name() != null) {
			product.setName(productDto.name());
		}
		if (productDto.price() != null) {
			product.setPrice(productDto.price());
		}
		if (productDto.description() != null) {
			product.setDescription(productDto.description());
		}
		if (productDto.currency() != null) {
			if (Arrays.stream(CurrencyEnum.values()).noneMatch(currencyEnum ->
					currencyEnum.name().equals(productDto.currency()))) {
				throw new CurrencyDoesNotExistException("Currency does not exist");
			}
			product.setCurrency(CurrencyEnum.valueOf(productDto.currency()));
		}

		productRepository.save(product); // TODO check

	}

	@Override
	public Double getDiscountPriceForProduct(String name, String promoCode) throws ProductIsNullException,
			PromoCodeNotFoundException, CurrenciesNotEqualException {
		ProductImpl product = productRepository.findByName(name)
				.orElseThrow(() -> new ProductIsNullException("Product does not exist"));
		PromoCode promoCodeObject = promoCodeRepository.findByCode(promoCode)
				.orElseThrow(() -> new PromoCodeNotFoundException("Promo code does not exist"));
		if (!product.getCurrency().equals(promoCodeObject.getCurrency())) {
			throw new CurrenciesNotEqualException(String.format("Promo code does not match product currency. Product's currency: %s, promo code's currency: %s",
					product.getCurrency(), promoCodeObject.getCurrency()), product.getPrice());
		}
		return product.getPrice();
	}

	@Override
	public List<ProductImplDto> getProducts() {
		return productRepository.findAll().stream()
				.map(ProductDtoMapper::mapToDto)
				.collect(Collectors.toList());
	}
}
