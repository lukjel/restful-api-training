package isa.rest.client.model;


import javax.json.bind.annotation.JsonbNillable;
import javax.json.bind.annotation.JsonbProperty;

public class Market {

	String id;

	//	@JsonbProperty("buyingCurrency")
	String buyingCurrency;

	//	@JsonbProperty("payingCurrency")
	String payingCurrency;

	//	@JsonbProperty("buyingCurrency")
	String minAmount;

	//	@JsonbProperty("buyingCurrency")
	String type;

	//	@JsonbProperty("buyingCurrency")
	String opening;

	//	@JsonbProperty("buyingCurrency")
	String ecoOpenning;

	//	@JsonbProperty("pricePrecision")
	int pricePrecision;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuyingCurrency() {
		return buyingCurrency;
	}

	public void setBuyingCurrency(String buyingCurrency) {
		this.buyingCurrency = buyingCurrency;
	}

	public String getPayingCurrency() {
		return payingCurrency;
	}

	public void setPayingCurrency(String payingCurrency) {
		this.payingCurrency = payingCurrency;
	}

	public String getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(String minAmount) {
		this.minAmount = minAmount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOpening() {
		return opening;
	}

	public void setOpening(String opening) {
		this.opening = opening;
	}

	public String getEcoOpenning() {
		return ecoOpenning;
	}

	public void setEcoOpenning(String ecoOpenning) {
		this.ecoOpenning = ecoOpenning;
	}

	public int getPricePrecision() {
		return pricePrecision;
	}

	public void setPricePrecision(int pricePrecision) {
		this.pricePrecision = pricePrecision;
	}

	@Override
	public String toString() {
		return "Market{" +
			"id='" + id + '\'' +
			", buyingCurrency='" + buyingCurrency + '\'' +
			", payingCurrency='" + payingCurrency + '\'' +
			", minAmount='" + minAmount + '\'' +
			", type='" + type + '\'' +
			", opening='" + opening + '\'' +
			", ecoOpenning='" + ecoOpenning + '\'' +
			", pricePrecision=" + pricePrecision +
			'}';
	}
}
