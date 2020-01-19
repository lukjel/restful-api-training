package isa.rest.server;

import isa.util.JsonHelper;

import javax.json.JsonObject;
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
