package fr.competitor.ws.article;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.competitor.ws.JsonManager;

@Path("/Article")
public class ArticleWS {
	public ArticleWS() {
		super();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticleByIdJson(@PathParam("id") int id) {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(
						ArticleBDD.getArticle(id, true))).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticlesJson() {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(
						ArticleBDD.getArticles(true))).build();
	}

	@GET
	@Path("User/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticlesUserJson(@PathParam("id") int id) {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(
						ArticleBDD.getArticlesUser(id, false))).build();
	}
}
