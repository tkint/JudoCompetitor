package fr.competitor.ws.user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.competitor.model.user.User;
import fr.competitor.ws.JsonManager;

@Path("/User")
public class UserWS {
	public UserWS() {
		super();
	}

	/**
	 * Fonction qui retourne l'utilisateur qui se connecte
	 * 
	 * @param login
	 * @param password
	 * @return UTILISATEUR se connectant, NULL sinon
	 */
	protected User connectUser(String login, String password) {
		if (login != null && password != null) {
			Integer id = existUser(login);
			if (id != null) {
				User user = UserBDD.getUser(id, false);
				if (user.getPassword().equals(password)) {
					return user;
				}
			}
		}
		return null;
	}

	/**
	 * Fonction qui vérifie si l'utilisateur spécifié existe
	 * 
	 * @param login
	 * @return ID de l'utilisateur, NULL sinon
	 */
	private Integer existUser(String login) {
		for (User user : UserBDD.getUsers(false)) {
			if (user.getLogin().equals(login)) {
				return user.getId();
			}
		}
		return null;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByIdJson(@PathParam("id") int id) {
		return Response.ok()
				.entity(JsonManager.getInstance().toJson(UserBDD.getUser(id, false)))
				.build();
	}

	@GET
	@Path("{login}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByIdJson(@PathParam("login") String login,
			@PathParam("password") String password) {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(
						connectUser(login, password))).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsersJson() {
		return Response.ok()
				.entity(JsonManager.getInstance().toJson(UserBDD.getUsers(false)))
				.build();
	}
}
