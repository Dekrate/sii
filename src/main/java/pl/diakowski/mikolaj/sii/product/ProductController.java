package pl.diakowski.mikolaj.sii.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.diakowski.mikolaj.sii.product.dto.NewProductDto;
import pl.diakowski.mikolaj.sii.product.dto.ProductDto;
import pl.diakowski.mikolaj.sii.currency.exception.CurrenciesNotEqualException;
import pl.diakowski.mikolaj.sii.product.exception.DiscountTooHighException;
import pl.diakowski.mikolaj.sii.product.exception.ProductIsNullException;
import pl.diakowski.mikolaj.sii.promocode.exception.PromoCodeNotFoundException;

import java.util.List;

@RestController
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/create-product") // tutaj uwaga na adnotację, ale chyba już nie jest wymagana
	public ResponseEntity<?> createProduct(@RequestBody NewProductDto productDto) {
		try {
			productService.createProduct(productDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PatchMapping("/update-product")
	public ResponseEntity<?> updateProduct(@RequestParam String name, @RequestBody NewProductDto productDto) {
		try {
			productService.updateProduct(name, productDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/get-discount-price")
	public ResponseEntity<?> getDiscountPriceForProduct(@RequestParam(required = false) String name,
	                                                    @RequestParam(required = false) String promoCode) {
		try {
			if (name == null) {
				return ResponseEntity.badRequest().body("Name cannot be null");
			}
			if (promoCode == null) {
				return ResponseEntity.badRequest().body("Promo code cannot be null");
			}
			return ResponseEntity.ok(productService.getDiscountPriceForProduct(name, promoCode));
		} catch (ProductIsNullException | PromoCodeNotFoundException | DiscountTooHighException e) {
			return ResponseEntity.badRequest().build();
		} catch (CurrenciesNotEqualException e) {
			return ResponseEntity.ok("Price: " + e.getPrice() + ". " + e.getMessage());
		}
	}

	@GetMapping("/get-products")
	public ResponseEntity<List<ProductDto>> getProducts() {
		return ResponseEntity.ok(productService.getProducts());

	}
}
