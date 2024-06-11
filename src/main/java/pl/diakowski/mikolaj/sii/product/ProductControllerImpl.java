package pl.diakowski.mikolaj.sii.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.diakowski.mikolaj.sii.product.dto.NewProductImplDto;
import pl.diakowski.mikolaj.sii.product.dto.ProductImplDto;
import pl.diakowski.mikolaj.sii.product.exception.CurrenciesNotEqualException;
import pl.diakowski.mikolaj.sii.product.exception.ProductIsNullException;
import pl.diakowski.mikolaj.sii.promocode.exception.PromoCodeNotFoundException;

import java.util.List;

@RestController
public class ProductControllerImpl implements ProductController {
	private final ProductService productService;

	public ProductControllerImpl(ProductService productService) {
		this.productService = productService;
	}

	@Override
	@PostMapping("/create-product") // tutaj uwaga na adnotację, ale chyba już nie jest wymagana
	public ResponseEntity<?> createProduct(NewProductImplDto productDto) {
		try {
			productService.createProduct(productDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@Override
	@PatchMapping("/update-product")
	public ResponseEntity<?> updateProduct(String name, NewProductImplDto productDto) {
		try {
			productService.updateProduct(name, productDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@Override
	@GetMapping("/get-discount-price?name={name}&promoCode={promoCode}")
	public ResponseEntity<?> getDiscountPriceForProduct(@PathVariable(required = false) String name,
	                                                    @PathVariable(required = false) String promoCode) {
		try {
			if (name == null) {
				return ResponseEntity.badRequest().build();
			}
			if (promoCode == null) {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.ok(productService.getDiscountPriceForProduct(name, promoCode));
		} catch (ProductIsNullException | PromoCodeNotFoundException e) {
			return ResponseEntity.badRequest().build();
		} catch (CurrenciesNotEqualException e) {
			return ResponseEntity.ok("Price: " + e.getPrice() + ". " + e.getMessage());
		}

	}

	@Override
	public void simulatePurchase(ProductImplDto productDto) {

	}

	@Override
	@GetMapping("/get-products")
	public ResponseEntity<List<ProductImplDto>> getProducts() {
		return ResponseEntity.ok(productService.getProducts());

	}
}
