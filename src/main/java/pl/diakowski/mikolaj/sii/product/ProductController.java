package pl.diakowski.mikolaj.sii.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import pl.diakowski.mikolaj.sii.product.dto.NewProductDto;
import pl.diakowski.mikolaj.sii.product.dto.ProductDto;
import pl.diakowski.mikolaj.sii.currency.exception.CurrenciesNotEqualException;
import pl.diakowski.mikolaj.sii.product.exception.DiscountTooHighException;
import pl.diakowski.mikolaj.sii.product.exception.ProductIsNullException;
import pl.diakowski.mikolaj.sii.promocode.exception.PromoCodeNotFoundException;

import java.util.List;

@RestController
@Tag(name = "Product")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/create-product")
	@Operation(summary = "Create product")
	public ResponseEntity<?> createProduct(@RequestBody NewProductDto productDto) {
		try {
			productService.createProduct(productDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PatchMapping("/update-product")
	@Operation(summary = "Update product")
	public ResponseEntity<?> updateProduct(@RequestParam String name, @RequestBody NewProductDto productDto) {
		try {
			productService.updateProduct(name, productDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/get-discount-price")
	@Operation(summary = "Get discount price")
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
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (CurrenciesNotEqualException e) {
			return ResponseEntity.ok("Price: " + e.getPrice() + ". " + e.getMessage());
		}
	}

	@GetMapping("/get-products")
	@Operation(summary = "Get products")
	public ResponseEntity<List<ProductDto>> getProducts() {
		return ResponseEntity.ok(productService.getProducts());

	}

	@ExceptionHandler
	public ResponseEntity<String> handleInvalidInput(Exception ex) {
		if (ex instanceof HttpMessageNotReadableException exception) {
			String message = exception.getMessage();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
