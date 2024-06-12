package pl.diakowski.mikolaj.sii;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.diakowski.mikolaj.sii.currency.exception.CurrencyDoesNotExistException;
import pl.diakowski.mikolaj.sii.promocode.PromoCodeRepository;
import pl.diakowski.mikolaj.sii.promocode.PromoCodeService;
import pl.diakowski.mikolaj.sii.promocode.dto.NewPromoCodeDto;
import pl.diakowski.mikolaj.sii.promocode.exception.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class PromoCodeTests {

	@Mock
	private PromoCodeRepository promoCodeRepository;

	@InjectMocks
	private PromoCodeService promoCodeService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void addPromoCode_ThrowsCodeIsNullException_WhenCodeIsNull() {
		NewPromoCodeDto promoCode = new NewPromoCodeDto(null, "USD", 10L, 5.0);
		assertThrows(CodeIsNullException.class, () -> promoCodeService.addPromoCode(promoCode));
	}

	@Test
	public void addPromoCode_ThrowsInvalidCodeLengthException_WhenCodeLengthIsInvalid() {
		NewPromoCodeDto promoCode = new NewPromoCodeDto("AB", "USD", 10L, 5.0);
		assertThrows(InvalidCodeLengthException.class, () -> promoCodeService.addPromoCode(promoCode));
	}


	@Test
	public void addPromoCode_ThrowsInvalidMaxUsesException_WhenMaxUsesIsLessThanOne() {
		NewPromoCodeDto promoCode = new NewPromoCodeDto("ABCD", "USD", -10L, 0.0);
		assertThrows(InvalidMaxUsesException.class, () -> promoCodeService.addPromoCode(promoCode));
	}

	@Test
	public void addPromoCode_ThrowsCurrencyDoesNotExistException_WhenCurrencyDoesNotExist() {
		NewPromoCodeDto promoCode = new NewPromoCodeDto("ABCD", "XYZ", 10L, 5.0);
		assertThrows(CurrencyDoesNotExistException.class, () -> promoCodeService.addPromoCode(promoCode));
	}

	@Test
	public void getPromoCode_ThrowsPromoCodeNotFoundException_WhenCodeDoesNotExist() {
		when(promoCodeRepository.findByCode("ABCD")).thenReturn(Optional.empty());
		assertThrows(PromoCodeNotFoundException.class, () -> promoCodeService.getPromoCode("ABCD"));
	}
}
