package isa.rest.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;


public class KangaExchangeBaseClient {

	Logger log = LoggerFactory.getLogger(getClass());

	public String pingString() {
		//TODO
		return null;
	}
}
