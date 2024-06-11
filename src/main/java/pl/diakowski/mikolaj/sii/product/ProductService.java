package pl.diakowski.mikolaj.sii.product;

import pl.diakowski.mikolaj.sii.product.dto.NewProductImplDto;
import pl.diakowski.mikolaj.sii.product.dto.ProductImplDto;
import pl.diakowski.mikolaj.sii.product.exception.*;
import pl.diakowski.mikolaj.sii.currency.exception.CurrencyDoesNotExistException;
import pl.diakowski.mikolaj.sii.promocode.exception.PromoCodeNotFoundException;

import java.util.List;

public interface ProductService {
	void createProduct(NewProductImplDto productDto) throws ProductIsNullException, CurrencyDoesNotExistException, ProductExistsException, PriceBelowOrEqualZeroException;

	void updateProduct(String name, NewProductImplDto productDto) throws ProductIsNullException, CurrencyDoesNotExistException;

	Double getDiscountPriceForProduct(String name, String promoCode) throws ProductIsNullException, PromoCodeNotFoundException, CurrenciesNotEqualException, DiscountTooHighException;

	List<ProductImplDto> getProducts();
}
