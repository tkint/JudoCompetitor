package fr.competitor.ws.judoka;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.competitor.ws.JsonManager;
import fr.competitor.ws.competition.CompetitionBDD;

@Path("/Judoka")
public class JudokaWS {
	public JudokaWS() {
		super();
	}
	
	public Boolean participate(int id_judoka, int id_competition) {
		for (int id : CompetitionBDD.getParticipants(id_competition)) {
			if (id == id_judoka) {
				return true;
			}
		}
		return false;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJudokaByIdJson(@PathParam("id") int id) {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(JudokaBDD.getJudoka(id, false)))
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJudokasJson() {
		return Response.ok()
				.entity(JsonManager.getInstance().toJson(JudokaBDD.getJudokas(false)))
				.build();
	}
}
