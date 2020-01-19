package isa.rest.server;

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
import javax.ws.rs.core.MediaType;

@Path("")
public class UserApi {

	Logger log = LoggerFactory.getLogger(getClass());

	@Inject
	private UserService loginService;

	@Path("login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject login(@Context HttpServletResponse response, JsonObject input) {
		String login = input.getString("login");
		String password = input.getString("password");
		try {
			String token = loginService.login(login, password);
			response.addHeader("auth-token", token);
			return Response.ok();
		} catch (Exception e) {
			return Response.fail("Wrong login or password");
		}
	}

	@Path("register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject register(@Context HttpServletResponse response, JsonObject input) {
		log.debug("Register a new user {}", input);
		String login = input.getString("login");
		String password = input.getString("password");
		try {
			String token = loginService.createUser(login, password);
			response.addHeader("auth-token", token);
			return Response.ok();
		} catch (Exception e) {
			return Response.fail("Some problem. Maybe duplicated login?");
		}
	}

}
