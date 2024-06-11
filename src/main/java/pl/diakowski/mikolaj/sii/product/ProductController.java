package pl.diakowski.mikolaj.sii.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import pl.diakowski.mikolaj.sii.product.dto.NewProductImplDto;
import pl.diakowski.mikolaj.sii.product.dto.ProductImplDto;

import java.util.List;

public interface ProductController {
	ResponseEntity<?> createProduct(NewProductImplDto productDto);
	ResponseEntity<?> updateProduct(String name, NewProductImplDto productDto);
	ResponseEntity<?> getDiscountPriceForProduct(@PathVariable String name, @PathVariable String promoCode);
	ResponseEntity<List<ProductImplDto>> getProducts();
}
