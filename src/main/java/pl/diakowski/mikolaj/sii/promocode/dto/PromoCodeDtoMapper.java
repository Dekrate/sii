package pl.diakowski.mikolaj.sii.promocode.dto;

import pl.diakowski.mikolaj.sii.promocode.PromoCode;
import pl.diakowski.mikolaj.sii.promocode.exception.InvalidDiscountException;
import pl.diakowski.mikolaj.sii.promocode.exception.InvalidMaxUsesException;

public class PromoCodeDtoMapper {
	public static PromoCodeDto mapToDto(PromoCode promoCode) {
		return new PromoCodeDto(promoCode.getCode(), promoCode.getCurrency(), promoCode.getMaxUses(),
				promoCode.getDiscount(), promoCode.getUses());
	}

	public static PromoCode mapToEntity(PromoCodeDto promoCodeDto) throws Exception {
		PromoCode promoCode = new PromoCode();
		promoCode.setCode(promoCodeDto.code());
		promoCode.setCurrency(promoCodeDto.currency());
		promoCode.setMaxUses(promoCodeDto.maxUses());
		promoCode.setDiscount(promoCodeDto.discount());
		return promoCode;
	}
}
