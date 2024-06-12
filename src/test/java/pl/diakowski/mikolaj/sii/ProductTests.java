package pl.diakowski.mikolaj.sii;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.diakowski.mikolaj.sii.product.ProductImplRepository;
import pl.diakowski.mikolaj.sii.product.ProductServiceImpl;
import pl.diakowski.mikolaj.sii.product.dto.NewProductImplDto;
import pl.diakowski.mikolaj.sii.product.exception.*;
import pl.diakowski.mikolaj.sii.currency.exception.CurrencyDoesNotExistException;
import pl.diakowski.mikolaj.sii.promocode.PromoCodeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductTests {

	@Mock
	private ProductImplRepository productRepository;

	@Mock
	private PromoCodeRepository promoCodeRepository;

	@InjectMocks
	private ProductServiceImpl productService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void createProduct_ThrowsProductIsNullException_WhenProductDtoIsNull() {
		assertThrows(ProductIsNullException.class, () -> productService.createProduct(null));
	}

	@Test
	public void createProduct_ThrowsCurrencyDoesNotExistException_WhenCurrencyDoesNotExist() {
		NewProductImplDto productDto = new NewProductImplDto("XYZ", "Product", "Description", 10.0);
		assertThrows(CurrencyDoesNotExistException.class, () -> productService.createProduct(productDto));
	}

	@Test
	public void createProduct_ThrowsPriceBelowOrEqualZeroException_WhenPriceIsNegative() {
		NewProductImplDto productDto = new NewProductImplDto("USD", "Product", "Description", -10.0);
		assertThrows(PriceBelowOrEqualZeroException.class, () -> productService.createProduct(productDto));
	}

	@Test
	public void updateProduct_ThrowsProductIsNullException_WhenProductDoesNotExist() {
		NewProductImplDto productDto = new NewProductImplDto("USD", "Product", "Description", 10.0);
		when(productRepository.findByName("Product")).thenReturn(Optional.empty());
		assertThrows(ProductIsNullException.class, () -> productService.updateProduct("Product", productDto));
	}

	@Test
	public void getDiscountPriceForProduct_ThrowsProductIsNullException_WhenProductDoesNotExist() {
		when(productRepository.findByName("Product")).thenReturn(Optional.empty());
		assertThrows(ProductIsNullException.class, () -> productService.getDiscountPriceForProduct("Product", "PromoCode"));
	}

}