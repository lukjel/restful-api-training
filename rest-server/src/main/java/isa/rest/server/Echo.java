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

	public String baseCall() {
		//TODO
		return null;
	}

}
