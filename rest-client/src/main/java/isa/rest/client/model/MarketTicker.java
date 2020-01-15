package isa.rest.client.model;

import javax.json.bind.annotation.JsonbProperty;

public class MarketTicker {

	@JsonbProperty("marketId")
	String marketId;

	@JsonbProperty("price")
	String price;

	@JsonbProperty("volume24")
	String volume24;

	@JsonbProperty("change")
	String change;

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getVolume24() {
		return volume24;
	}

	public void setVolume24(String volume24) {
		this.volume24 = volume24;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}
}
