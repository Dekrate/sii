package pl.diakowski.mikolaj.sii.currency;

public enum CurrencyEnum {
	EUR("EUR", 4.3), USD("USD", 3.8), PLN("PLN", 1.0);
	private final String name;
	private final Double exchangeRate;


	CurrencyEnum(String name, double exchangeRate) {
		this.name = name;
		this.exchangeRate = exchangeRate;
	}

	public String getName() {
		return name;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}


}
