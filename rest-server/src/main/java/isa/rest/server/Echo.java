package isa.rest.server;

import isa.hibernate.util.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.time.Instant;

@Path("/echo")
public class Echo {

	Logger log = LoggerFactory.getLogger(getClass());

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String baseCall() {
		return "ECHO... echo...";
	}

	@Path("/1")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject paramsFromBody(JsonObject body) {
		log.debug("Odczytane body: {}", body);
		return JsonHelper.toJson(
			"input", body
		);
	}

	@Path("/2")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject paramFromHead(@HeaderParam("isa") String isa) {
		return JsonHelper.toJson(
			"isa", isa,
			"timestamp", Instant.now().toString()
		);
	}

	@Path("/3")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject someUriInfo(@Context UriInfo uriInfo) {
		return JsonHelper.toJson(
			"absolutePath", uriInfo.getAbsolutePath().toASCIIString(),
			"path", uriInfo.getPath()
		);
	}
}
