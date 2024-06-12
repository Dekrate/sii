package pl.diakowski.mikolaj.sii.product.dto;

import pl.diakowski.mikolaj.sii.product.Product;
import pl.diakowski.mikolaj.sii.product.exception.PriceBelowOrEqualZeroException;

public class ProductDtoMapper {

	public static ProductDto mapToDto(Product product) {
		return new ProductDto(product.getCurrency(), product.getName(),
				product.getDescription(), product.getPrice());
	}

	public static Product mapToProduct(ProductDto productDto) throws PriceBelowOrEqualZeroException {
		Product product = new Product();
		product.setCurrency(productDto.currency());
		product.setName(productDto.name());
		product.setDescription(productDto.description());
		product.setPrice(productDto.price());
		return product;
	}
}
