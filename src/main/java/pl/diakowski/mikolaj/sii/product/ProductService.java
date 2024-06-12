package pl.diakowski.mikolaj.sii.product;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.currency.exception.CurrenciesNotEqualException;
import pl.diakowski.mikolaj.sii.product.dto.NewProductDto;
import pl.diakowski.mikolaj.sii.product.dto.ProductDtoMapper;
import pl.diakowski.mikolaj.sii.product.dto.ProductDto;
import pl.diakowski.mikolaj.sii.product.exception.*;
import pl.diakowski.mikolaj.sii.currency.exception.CurrencyDoesNotExistException;
import pl.diakowski.mikolaj.sii.promocode.PromoCode;
import pl.diakowski.mikolaj.sii.promocode.PromoCodeRepository;
import pl.diakowski.mikolaj.sii.promocode.exception.PromoCodeNotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final PromoCodeRepository promoCodeRepository;

	public ProductService(ProductRepository productRepository, PromoCodeRepository promoCodeRepository) {
		this.productRepository = productRepository;
		this.promoCodeRepository = promoCodeRepository;
	}

	@Transactional
	public void createProduct(NewProductDto productDto) throws ProductIsNullException, CurrencyDoesNotExistException, ProductExistsException, PriceBelowOrEqualZeroException {
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
		if (productRepository.findByName(productDto.name()).isPresent()) {
			throw new ProductExistsException("Product already exists");
		}
		if (productDto.price() <= 0) {
			throw new PriceBelowOrEqualZeroException("Price cannot be negative");
		}
		productRepository.save(new pl.diakowski.mikolaj.sii.product.Product(CurrencyEnum.valueOf(productDto.currency()), productDto.name(),
				productDto.description(), productDto.price()));
	}

	@Transactional
	public void updateProduct(@RequestParam String name, NewProductDto productDto) throws ProductIsNullException, CurrencyDoesNotExistException, PriceBelowOrEqualZeroException {

		if (productDto == null) {
			throw new ProductIsNullException("ProductDto cannot be null");
		}

		pl.diakowski.mikolaj.sii.product.Product product = productRepository.findByName(name)
				.orElseThrow(() -> new ProductIsNullException("Product does not exist"));
		if (productDto.name() != null) {
			product.setName(productDto.name());
		}
		if (productDto.price() != null) {
			if (productDto.price() <= 0.0) {
				throw new PriceBelowOrEqualZeroException("Price cannot be negative");
			}
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
		product.setUpdatedAt(LocalDateTime.now());
		productRepository.save(product);

	}

	public Double getDiscountPriceForProduct(String name, String promoCode) throws ProductIsNullException,
			PromoCodeNotFoundException, CurrenciesNotEqualException, DiscountTooHighException {
		pl.diakowski.mikolaj.sii.product.Product product = productRepository.findByName(name)
				.orElseThrow(() -> new ProductIsNullException("Product does not exist"));
		PromoCode promoCodeObject = promoCodeRepository.findByCode(promoCode)
				.orElseThrow(() -> new PromoCodeNotFoundException("Promo code does not exist"));
		if (!product.getCurrency().equals(promoCodeObject.getCurrency())) {
			throw new CurrenciesNotEqualException(String.format("Promo code does not match product currency. Product's currency: %s, promo code's currency: %s",
					product.getCurrency(), promoCodeObject.getCurrency()), product.getPrice());
		}
		if (product.getPrice() - promoCodeObject.getDiscount() <= 0)
			throw new DiscountTooHighException("Discount is too high");
		return product.getPrice() - promoCodeObject.getDiscount();
	}

	public List<ProductDto> getProducts() {
		return productRepository.findAll().stream()
				.map(ProductDtoMapper::mapToDto)
				.collect(Collectors.toList());
	}
}
