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
		//TODO
		return null;
	}
}
