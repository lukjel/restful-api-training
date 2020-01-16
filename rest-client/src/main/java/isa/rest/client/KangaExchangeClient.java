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

	static final String BASE_URL="";

	public String pingWithTargets() {
		//TODO
		return null;
	}
}
