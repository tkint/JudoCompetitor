package fr.competitor.ws.article.category;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.competitor.ws.JsonManager;

@Path("/Category")
public class CategoryWS {
	public CategoryWS() {
		super();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryByIdJson(@PathParam("id") int id) {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(
						CategoryBDD.getCategory(id, false))).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoriesJson() {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(
						CategoryBDD.getCategories(false))).build();
	}

	@GET
	@Path("Article/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoriesArticleJson(@PathParam("id") int id) {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(
						CategoryBDD.getCategoriesArticle(id, false))).build();
	}
}
