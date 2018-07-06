package fr.competitor.ws.rank;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.competitor.ws.JsonManager;

@Path("/Rank")
public class RankWS {
	public RankWS() {
		super();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRankByIdJson(@PathParam("id") int id) {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(RankBDD.getRank(id)))
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRanksJson() {
		return Response.ok()
				.entity(JsonManager.getInstance().toJson(RankBDD.getRanks()))
				.build();
	}
}
