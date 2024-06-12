package pl.diakowski.mikolaj.sii.order;

import pl.diakowski.mikolaj.sii.currency.exception.CurrencyDoesNotExistException;
import pl.diakowski.mikolaj.sii.order.dto.NewOrderImplDto;
import pl.diakowski.mikolaj.sii.order.dto.OrderImplDto;
import pl.diakowski.mikolaj.sii.order.exception.OrderIsNullException;
import pl.diakowski.mikolaj.sii.product.exception.ProductIsNullException;
import pl.diakowski.mikolaj.sii.promocode.exception.PromoCodeNotFoundException;

public interface OrderService {
	OrderImplDto simulatePurchase(NewOrderImplDto newOrderImplDto) throws PromoCodeNotFoundException, OrderIsNullException, ProductIsNullException, CurrencyDoesNotExistException;
}
