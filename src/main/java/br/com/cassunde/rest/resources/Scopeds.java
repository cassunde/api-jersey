package br.com.cassunde.rest.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cassunde.scopeds.ConversationService;
import br.com.cassunde.scopeds.RequestService;
import br.com.cassunde.scopeds.SessionService;

@Path("scopeds")
public class Scopeds {
	
	@Inject
	private SessionService sessionService;
	
	@Inject
	private RequestService requestService;
	
	@Inject
	private ConversationService conversationService;
	

	@GET
    @Path("/request/sum/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sum(@PathParam("value") final int value) {
		return Response.ok( requestService.sum(value) ).build();
    }
	
	@GET
    @Path("/session/sum/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sumSession(@PathParam("value") final int value) {
		return Response.ok( sessionService.sum(value) ).build();
    }
	
	
	@GET
    @Path("/conversation/sum/with/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response conversationSession(@PathParam("value") final int value) {
		return Response.ok( conversationService.sum(value, true) ).build();
    }
	
	@GET
    @Path("/conversation/sum/without/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response conversationWithOutSession(@PathParam("value") final int value) {
		return Response.ok( conversationService.sum(value, false) ).build();
    }
	
}
