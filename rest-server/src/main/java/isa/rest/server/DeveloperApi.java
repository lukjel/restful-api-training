package isa.rest.server;

import isa.hibernate.dto.DeveloperShortDTO;
import isa.hibernate.service.DeveloperService;
import isa.hibernate.util.JsonHelper;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("dev")
public class DeveloperApi {

	@Inject
	private DeveloperService developerService;

	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject createNewDeveloperFromJson(JsonObject json) {
		//TODO
		return null;
	}
}
