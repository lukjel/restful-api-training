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


public class KangaExchangeBaseClient {

	Logger log = LoggerFactory.getLogger(getClass());

	public String pingString() {
		String address = "https://kanga.exchange/api/ping";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(address);

		try (Response response = webTarget.request().get()) {
			if (Response.Status.OK.equals(response.getStatusInfo())) {
				String strResponse = response.readEntity(String.class);
				return strResponse;
			}
			int status = response.getStatus();
			throw new IllegalStateException("Error: " + status);
		}
	}

	public JsonObject pingJson() {
		String address = "https://kanga.exchange/api/ping";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(address);

		try (Response response = webTarget.request().get()) {
			if (Response.Status.OK.equals(response.getStatusInfo())) {
				String str = response.readEntity(String.class);
				return JsonHelper.convert(str);
			}
			int status = response.getStatus();
			throw new IllegalStateException("Error: " + status);
		}
	}

	public PingResponse pingModel() {
		String address = "https://kanga.exchange/api/ping";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(address);

		try (Response response = webTarget.request().get()) {
			if (Response.Status.OK.equals(response.getStatusInfo())) {
				PingResponse model = response.readEntity(PingResponse.class);
				return model;
			}
			int status = response.getStatus();
			throw new IllegalStateException("Error: " + status);
		}
	}
}
