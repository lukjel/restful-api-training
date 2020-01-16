package isa.rest.client.model;

import javax.json.bind.annotation.JsonbProperty;

public class MarketRate {

	String fromCurrency;

	String toCurrency;

	String rate;

	String change;

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}
}
