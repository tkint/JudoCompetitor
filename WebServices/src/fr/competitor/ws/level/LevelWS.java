package fr.competitor.ws.level;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.competitor.ws.JsonManager;

@Path("/Level")
public class LevelWS {
	public LevelWS() {
		super();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLevelByIdJson(@PathParam("id") int id) {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(LevelBDD.getLevel(id)))
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLevelsJson() {
		return Response.ok()
				.entity(JsonManager.getInstance().toJson(LevelBDD.getLevels()))
				.build();
	}
}
