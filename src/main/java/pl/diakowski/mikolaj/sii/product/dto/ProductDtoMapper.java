package pl.diakowski.mikolaj.sii.product.dto;

public class ProductDtoMapper {

	public static Product mapToDto(pl.diakowski.mikolaj.sii.product.Product product) {
		return new Product(product.getCurrency(), product.getName(),
				product.getDescription(), product.getPrice());
	}

	public static pl.diakowski.mikolaj.sii.product.Product mapToProduct(Product productDto) {
		pl.diakowski.mikolaj.sii.product.Product product = new pl.diakowski.mikolaj.sii.product.Product();
		product.setCurrency(productDto.currency());
		product.setName(productDto.name());
		product.setDescription(productDto.description());
		product.setPrice(productDto.price());
		return product;
	}
}
