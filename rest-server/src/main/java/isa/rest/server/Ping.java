package isa.rest.server;

import isa.hibernate.util.JsonHelper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Instant;

@Path("/ping")
public class Ping {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject getPing() {
		JsonObject json = JsonHelper.toJson(
			"pong", true,
			"datetime", Instant.now().toString()
		);
		return json;
	}

}
