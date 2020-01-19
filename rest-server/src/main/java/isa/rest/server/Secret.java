package isa.rest.server;

import isa.dto.UserDTO;
import isa.exceptions.AuthorizationException;
import isa.service.UserService;
import isa.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.net.http.HttpResponse;

@Path("secret")
public class Secret {

	Logger log = LoggerFactory.getLogger(getClass());

	@Inject
	private UserService loginService;

	@Path("action")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject action(@Context HttpHeaders headers, @Context HttpServletResponse response,  JsonObject input) {
		//FIXME
		return null;
	}

}
