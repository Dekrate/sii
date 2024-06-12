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
	public void addPromoCode(NewPromoCodeDto promoCode) throws CodeIsNullException, InvalidCodeLengthException,
			CodeAlreadyExistsException, InvalidMaxUsesException, CurrencyDoesNotExistException, InvalidDiscountException {
		if (promoCode.code() == null) {
			throw new CodeIsNullException("Code cannot be null");
		}
		if (promoCode.code().isBlank()) {
			throw new CodeIsNullException("Discount code cannot be blank");
		}
		if (promoCode.currency() == null) {
			throw new CurrencyDoesNotExistException("Currency cannot be null");
		}
		if (promoCode.currency().isBlank()) {
			throw new CurrencyDoesNotExistException("Currency cannot be blank");
		}
		if (promoCode.maxUses() == null) {
			throw new InvalidMaxUsesException("Max uses cannot be null");
		}
		if (promoCode.discount() == null) {
			throw new InvalidDiscountException("Discount cannot be null");
		}
		if (promoCode.code().length() > 24 || promoCode.code().length() < 3) {
			throw new InvalidCodeLengthException("Code must be between 8 and 24 characters");
		}
		if (promoCodeRepository.findByCode(promoCode.code()).isPresent()) {
			throw new CodeAlreadyExistsException("Code already exists");
		}
		if (promoCode.maxUses() < 1) {
			throw new InvalidMaxUsesException("Max uses must be greater than 0");
		}
		if (Arrays.stream(CurrencyEnum.values()).noneMatch(currencyEnum -> currencyEnum.name().equals(promoCode.currency()))) {
			throw new CurrencyDoesNotExistException("Currency does not exist");
		}
		promoCodeRepository.save(new PromoCode(promoCode.code(), CurrencyEnum.valueOf(promoCode.currency()),
				promoCode.discount() < 0.0 ? 0.0 : promoCode.discount(), promoCode.maxUses(), 0L));
	}

	public List<PromoCodeDto> getPromoCodes() {
		return promoCodeRepository.findAll().stream().map(PromoCodeDtoMapper::mapToDto).toList();
	}

	public PromoCodeDto getPromoCode(String code) throws PromoCodeNotFoundException {
		return promoCodeRepository.findByCode(code).map(PromoCodeDtoMapper::mapToDto)
				.orElseThrow(() -> new PromoCodeNotFoundException("Promo code not found"));
	}
}
