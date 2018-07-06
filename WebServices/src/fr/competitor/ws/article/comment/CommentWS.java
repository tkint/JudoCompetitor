package fr.competitor.ws.article.comment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.competitor.model.article.Comment;
import fr.competitor.ws.JsonManager;

@Path("/Comment")
public class CommentWS {
	public CommentWS() {
		super();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommentByIdJson(@PathParam("id") int id) {
		Comment comment = CommentBDD.getComment(id);
		return Response.ok().entity(JsonManager.getInstance().toJson(comment))
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCommentsJson() {
		String result = "";
		for (Comment comment : CommentBDD.getComments()) {
			result += JsonManager.getInstance().toJson(comment);
		}
		return result;
	}

	@GET
	@Path("Article/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCommentsArticleJson(@PathParam("id") int id) {
		String result = "";
		for (Comment comment : CommentBDD.getCommentsArticle(id)) {
			result += JsonManager.getInstance().toJson(comment);
		}
		return result;
	}

	@GET
	@Path("User/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCommentsUserJson(@PathParam("id") int id) {
		String result = "";
		for (Comment comment : CommentBDD.getCommentsUser(id)) {
			result += JsonManager.getInstance().toJson(comment);
		}
		return result;
	}
}
