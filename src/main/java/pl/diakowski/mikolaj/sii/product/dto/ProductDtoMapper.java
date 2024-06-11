package pl.diakowski.mikolaj.sii.product.dto;

import pl.diakowski.mikolaj.sii.product.Product;
import pl.diakowski.mikolaj.sii.product.ProductImpl;

public class ProductDtoMapper {

	public static ProductImplDto mapToDto(Product product) {
		return new ProductImplDto(product.getCurrency(), product.getName(),
				product.getDescription(), product.getPrice());
	}

	public static Product mapToProduct(ProductImplDto productDto) {
		Product product = new ProductImpl();
		product.setCurrency(productDto.currency());
		product.setName(productDto.name());
		product.setDescription(productDto.description());
		product.setPrice(productDto.price());
		return product;
	}
}
