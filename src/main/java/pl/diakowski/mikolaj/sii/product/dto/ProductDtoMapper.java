package pl.diakowski.mikolaj.sii.product.dto;

public class ProductDtoMapper {

	public static ProductDto mapToDto(pl.diakowski.mikolaj.sii.product.Product product) {
		return new ProductDto(product.getCurrency(), product.getName(),
				product.getDescription(), product.getPrice());
	}

	public static pl.diakowski.mikolaj.sii.product.Product mapToProduct(ProductDto productDto) {
		pl.diakowski.mikolaj.sii.product.Product product = new pl.diakowski.mikolaj.sii.product.Product();
		product.setCurrency(productDto.currency());
		product.setName(productDto.name());
		product.setDescription(productDto.description());
		product.setPrice(productDto.price());
		return product;
	}
}
