package br.com.cassunde.rest.resources;

import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cassunde.api.PeopleDAO;
import br.com.cassunde.cache.CacheHandler;
import br.com.cassunde.model.People;
import br.com.cassunde.model.Phone;
import br.com.cassunde.rest.websocket.ClientNotificationBasic;


@Path("peoples")
public class PeopleResource {

    private final PeopleDAO peopleDAO;
    private final CacheHandler<Integer, People> cacheHandler;

    @Inject
    public PeopleResource(PeopleDAO peopleDAO, CacheHandler<Integer, People> cacheHandler) {
        this.peopleDAO = peopleDAO;
        this.cacheHandler = cacheHandler;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleById(@PathParam("id") final int id) {
        People people = cacheHandler.getEntityFromCache("PeopleCache")
                .orFromSource(() -> peopleDAO.get(id))
                .usingCacheKey(id);
        return Response.ok(people).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPeoples() {
        return Response.ok(peopleDAO.list()).build();
    }
   
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPeople(People people) throws URISyntaxException {
    	people = peopleDAO.update(people);
    	return Response.ok(people).build();
    }
    
    @POST
    @Path("/{id}/phones")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPeoplePhone(@PathParam("id") final int id, Phone phone) throws URISyntaxException {
    	People people = peopleDAO.get(id);
    	phone.setPeople(people);
    	people.addPhones(phone);
    	
    	peopleDAO.update(people);
    	return Response.ok(phone).build();
    }
    
    @DELETE
    @Path("/{id}/phones/{idPhone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePeoplePhone(@PathParam("id") final int id, @PathParam("idPhone") final int idPhone ) {
    	People people = peopleDAO.get(id);
    	people.removePhonesById(idPhone);
    	    	
    	people = peopleDAO.update(people);
    	
    	return Response.ok(people).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePeople(People people) throws URISyntaxException {
    	people = peopleDAO.update(people);
        return Response.ok(people).build();
    }
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePeople(@PathParam("id") final int idPeople) {
    	peopleDAO.delete(idPeople);
    	return Response.ok("ok").build();
    }
    
    @GET
    @Path("/websocket/{message}")    
    public Response sendMenssage(@PathParam("message") final String message) {
        
    	new ClientNotificationBasic("localhost:8080/api-jersey/chat/1/1", message).call();
    	
        return Response.ok("ok").build();
    }
}
