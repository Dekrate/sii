package pl.diakowski.mikolaj.sii.product.dto;

import pl.diakowski.mikolaj.sii.product.Product;

public class ProductDtoMapper {

	public static ProductDto mapToDto(Product product) {
		return new ProductDto(product.getCurrency(), product.getName(),
				product.getDescription(), product.getPrice());
	}

}
