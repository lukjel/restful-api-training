package isa.rest.client;

import isa.rest.client.model.*;
import isa.rest.client.util.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.util.List;


public class KangaExchangeClient {

	Logger log = LoggerFactory.getLogger(getClass());

	static final String BASE_URL="https://kanga.exchange/api";

	public String pingWithTargets() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
			.target(BASE_URL)
			.path("ping")
			;

		try (Response response = webTarget.request().get()) {
			if (Response.Status.OK.equals(response.getStatusInfo())) {
				String market = response.readEntity(String.class);
				log.debug("Ping: {}", market);
				return market;
			}
			log.debug("Response: {} :: {}", response.getStatus(), response.getEntity());
			int status = response.getStatus();
			throw new IllegalStateException("Error: " + status);
		}
	}

	public String marketDetailsStringWithString(String marketId) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
			.target(BASE_URL)
			.path("market")
			.path("get");

		String param = "{\"id\":\"ETH-BTC\"}";

		try (Response response = webTarget.request().post(Entity.json(param))) {
			if (Response.Status.OK.equals(response.getStatusInfo())) {
				String market = response.readEntity(String.class);
				log.debug("Market: {}", market);
				return market;
			}
			log.debug("Response: {} :: {}", response.getStatus(), response.getEntity());
			int status = response.getStatus();
			throw new IllegalStateException("Error: " + status);
		}
	}

	public String marketDetailsStringWithJson(String marketId) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
			.target(BASE_URL)
			.path("market")
			.path("get");

		JsonObject param = JsonHelper.toJson("id", marketId);

		try (Response response = webTarget.request().post(Entity.json(param))) {
			if (Response.Status.OK.equals(response.getStatusInfo())) {
				String market = response.readEntity(String.class);
				log.debug("Market: {}", market);
				return market;
			}
			log.debug("Response: {} :: {}", response.getStatus(), response.getEntity());
			int status = response.getStatus();
			throw new IllegalStateException("Error: " + status);
		}
	}

	public String marketDetailsStringWithModel(String marketId) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
			.target(BASE_URL)
			.path("market")
			.path("get");


		IdModel id = new IdModel("ETH-BTC");

		try (Response response = webTarget.request().post(Entity.json(id))) {
			if (Response.Status.OK.equals(response.getStatusInfo())) {
				String market = response.readEntity(String.class);
				log.debug("Market: {}", market);
				return market;
			}
			log.debug("Response: {} :: {}", response.getStatus(), response.readEntity(String.class));
			int status = response.getStatus();
			throw new IllegalStateException("Error: " + status);
		}
	}

	public String ratesString() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
			.target(BASE_URL)
			.path("market")
			.path("rates")
			.path("list");

		try (Response response = webTarget.request().post(Entity.json("{}"))) {
			if (Response.Status.OK.equals(response.getStatusInfo())) {
				String rates = response.readEntity(String.class);
				return rates;
			}
			int status = response.getStatus();
			throw new IllegalStateException("Error: " + status);
		}
	}

	public JsonObject ratesJson() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
			.target(BASE_URL)
			.path("market")
			.path("rates")
			.path("list");

		try (Response response = webTarget.request().post(Entity.json("{}"))) {
			if (Response.Status.OK.equals(response.getStatusInfo())) {
				String rates = response.readEntity(String.class);
				return JsonHelper.convert(rates);
			}
			int status = response.getStatus();
			throw new IllegalStateException("Error: " + status);
		}
	}

	public MarketRatesListResponse ratesModel() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
			.target(BASE_URL)
			.path("market")
			.path("rates")
			.path("list");

		try (Response response = webTarget.request().post(Entity.json("{}"))) {
			if (Response.Status.OK.equals(response.getStatusInfo())) {
				return response.readEntity(MarketRatesListResponse.class);
			}
			int status = response.getStatus();
			throw new IllegalStateException("Error: " + status);
		}
	}

	public List<MarketTicker> marketTickers() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
			.target(BASE_URL)
			.path("market")
			.path("ticker");

		try (Response response = webTarget.request().get()) {
			if (Response.Status.OK.equals(response.getStatusInfo())) {
				MarketTickerResponse rates = response.readEntity(MarketTickerResponse.class);
				return rates.getMarkets();
			}
			int status = response.getStatus();
			throw new IllegalStateException("Error: " + status);
		}
	}


}
