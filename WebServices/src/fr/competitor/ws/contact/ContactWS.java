package fr.competitor.ws.contact;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.competitor.ws.JsonManager;

@Path("/Contact")
public class ContactWS {
	public ContactWS() {
		super();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContactByIdJson(@PathParam("id") int id) {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(
						ContactBDD.getContact(id))).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContactsJson() {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(
						ContactBDD.getContacts())).build();
	}
}
