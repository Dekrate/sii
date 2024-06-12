package pl.diakowski.mikolaj.sii.product.dto;

public record NewProductDto(String currency, String name, String description,
                            Double price) {
}
