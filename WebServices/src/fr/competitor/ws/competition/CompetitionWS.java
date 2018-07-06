package fr.competitor.ws.competition;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.competitor.ws.JsonManager;

/**
 * Web Services des compétitions
 * @author Thomas
 *
 */
@Path("/Competition")
public class CompetitionWS {
	public CompetitionWS() {
		super();
	}
	
	/**
	 * Retourne la compétition désirée en JSON
	 * @param id de la compétition désirée
	 * @return Competition sérialisée en JSON
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompetitionByIdJson(@PathParam("id") int id) {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(
						CompetitionBDD.getCompetition(id, true))).build();
	}

	/**
	 * Retourne l'ensemble des compétitions en JSON
	 * @return ArrayList<Competition> sérialisé en JSON
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompetitionsJson() {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(
						CompetitionBDD.getCompetitions(true))).build();
	}
}
