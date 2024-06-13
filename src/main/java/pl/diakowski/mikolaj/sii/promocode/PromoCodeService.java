package pl.diakowski.mikolaj.sii.promocode;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.diakowski.mikolaj.sii.currency.CurrencyEnum;
import pl.diakowski.mikolaj.sii.currency.exception.CurrencyDoesNotExistException;
import pl.diakowski.mikolaj.sii.promocode.dto.NewPromoCodeDto;
import pl.diakowski.mikolaj.sii.promocode.dto.PromoCodeDto;
import pl.diakowski.mikolaj.sii.promocode.dto.PromoCodeDtoMapper;
import pl.diakowski.mikolaj.sii.promocode.exception.*;

import java.util.Arrays;
import java.util.List;

@Service
public class PromoCodeService {
	private final PromoCodeRepository promoCodeRepository;

	public PromoCodeService(PromoCodeRepository promoCodeRepository) {
		this.promoCodeRepository = promoCodeRepository;
	}

	@Transactional
	public void addPromoCode(NewPromoCodeDto promoCodeDto) throws
			CodeAlreadyExistsException, InvalidMaxUsesException, CurrencyDoesNotExistException, InvalidDiscountException, PromoCodeExpiredException, CodeIsNullException, CodeHasSpacesException, InvalidCodeLengthException {
		if (promoCodeRepository.findByCode(promoCodeDto.code()).isPresent()) {
			throw new CodeAlreadyExistsException("Code already exists");
		}
		if (Arrays.stream(CurrencyEnum.values()).noneMatch(currencyEnum -> currencyEnum.name().equals(promoCodeDto.currency()))) {
			throw new CurrencyDoesNotExistException("Currency does not exist");
		}
		PromoCode promoCode = new PromoCode();
		promoCode.setCode(promoCodeDto.code());
		promoCode.setDiscount(promoCodeDto.discount());
		promoCode.setMaxUses(promoCodeDto.maxUses());
		promoCode.setCurrency(CurrencyEnum.valueOf(promoCodeDto.currency()));
		promoCode.setUses(0L);
		promoCodeRepository.save(promoCode);
	}

	public List<PromoCodeDto> getPromoCodes() {
		return promoCodeRepository.findAll().stream().map(PromoCodeDtoMapper::mapToDto).toList();
	}

	public PromoCodeDto getPromoCode(String code) throws PromoCodeNotFoundException {
			return promoCodeRepository.findByCode(code).map(PromoCodeDtoMapper::mapToDto)
				.orElseThrow(() -> new PromoCodeNotFoundException("Promo code not found"));
	}
}
