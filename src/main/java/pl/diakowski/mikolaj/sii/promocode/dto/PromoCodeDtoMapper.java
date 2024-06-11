package pl.diakowski.mikolaj.sii.promocode.dto;

import pl.diakowski.mikolaj.sii.promocode.PromoCode;
import pl.diakowski.mikolaj.sii.promocode.PromoCodeImpl;

public class PromoCodeDtoMapper {
	public static PromoCodeDto mapToDto(PromoCode promoCode) {
		return new PromoCodeDto(promoCode.getCode(), promoCode.getCurrency(), promoCode.getMaxUses(),
				promoCode.getDiscount(), promoCode.getUses());
	}

	public static PromoCode mapToEntity(PromoCodeDto promoCodeDto) {
		return new PromoCodeImpl(promoCodeDto.code(), promoCodeDto.currency(), promoCodeDto.discount(),
				promoCodeDto.maxUses(), promoCodeDto.uses());
	}
}
