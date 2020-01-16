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
		String firstName = json.getString("firstName");
		String lastName = json.getString("lastName");
		String city = json.getString("city");
		long id = developerService.addDeveloper(firstName, lastName, city);
		return JsonHelper.toJson(
			"id", id
		);
	}

	@POST
	@Path("new/dto")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject createNewDeveloperFromDto(DeveloperShortDTO dto) {
		//TODO
		return null;
	}

}
