package fr.competitor.ws.region;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.competitor.ws.JsonManager;

@Path("/Region")
public class RegionWS {
	public RegionWS() {
		super();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegionByIdJson(@PathParam("id") int id) {
		return Response
				.ok()
				.entity(JsonManager.getInstance().toJson(RegionBDD.getRegion(id)))
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegionsJson() {
		return Response.ok()
				.entity(JsonManager.getInstance().toJson(RegionBDD.getRegions()))
				.build();
	}
}
