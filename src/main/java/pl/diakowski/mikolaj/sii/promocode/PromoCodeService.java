package pl.diakowski.mikolaj.sii.promocode;

import org.springframework.stereotype.Service;
import pl.diakowski.mikolaj.sii.currency.exception.CurrencyDoesNotExistException;
import pl.diakowski.mikolaj.sii.promocode.dto.NewPromoCodeDto;
import pl.diakowski.mikolaj.sii.promocode.dto.PromoCodeDto;
import pl.diakowski.mikolaj.sii.promocode.exception.*;

import java.util.List;
@Service
public interface PromoCodeService {
	void addPromoCode(NewPromoCodeDto promoCode) throws CodeIsNullException, InvalidCodeLengthException, CodeAlreadyExistsException, InvalidMaxUsesException, CurrencyDoesNotExistException, InvalidDiscountException;

	List<PromoCodeDto> getPromoCodes();

	PromoCodeDto getPromoCode(String code) throws PromoCodeNotFoundException;
}
