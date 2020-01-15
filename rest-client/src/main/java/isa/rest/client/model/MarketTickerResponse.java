package isa.rest.client.model;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class MarketTickerResponse {

	@JsonbProperty("markets")
	List<MarketTicker> markets;

	public List<MarketTicker> getMarkets() {
		return markets;
	}

	public void setMarkets(List<MarketTicker> markets) {
		this.markets = markets;
	}
}
