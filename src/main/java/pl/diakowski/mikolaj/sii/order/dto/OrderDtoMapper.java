package pl.diakowski.mikolaj.sii.order.dto;

import pl.diakowski.mikolaj.sii.order.Order;
import pl.diakowski.mikolaj.sii.product.dto.ProductDtoMapper;

public class OrderDtoMapper {
	public static OrderDto mapToDto(Order order) {
		return new OrderDto(order.getCreationDate(), order.getRegularPrice(), order.getDiscountPrice(),
				order.getCurrency(), ProductDtoMapper.mapToDto(order.getProduct()));
	}
}
