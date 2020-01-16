package isa.rest.client.model;

import java.util.List;

public class MarketRatesListResponse {

	List<MarketRate> list;

	public List<MarketRate> getList() {
		return list;
	}

	public void setList(List<MarketRate> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "MarketRatesListResponse{" +
			"listSize=" + list.size() +
			'}';
	}
}
