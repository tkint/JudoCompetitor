package fr.competitor.ws.competition.phase.fight.mark.move;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.competitor.ws.JsonManager;

/**
 * Web Services pour récupérer les données relatives aux mouvements
 * @author Thomas
 *
 */
@Path("/Move")
public class MoveWS {
	public MoveWS() {
		super();
	}

	/**
	 * Retourne le mouvement spécifié en JSON
	 * @param id du mouvement demandé
	 * @return Chaine sérialisée en JSON du mouvement
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMoveByIdJson(@PathParam("id") int id) {
		return Response.ok()
				.entity(JsonManager.getInstance().toJson(MoveBDD.getMove(id)))
				.build();
	}

	/**
	 * Retourne tous les mouvements en JSON
	 * @return Chaine sérialisée en JSON de tous les mouvements
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovesJson() {
		return Response.ok()
				.entity(JsonManager.getInstance().toJson(MoveBDD.getMoves()))
				.build();
	}
}
