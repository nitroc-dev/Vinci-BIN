package be.vinci.api;

import be.vinci.api.filters.Authorize;
import be.vinci.domain.Film;
import be.vinci.domain.User;
import be.vinci.services.FilmDataService;
import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.server.ContainerRequest;

import java.util.List;

@Singleton
@Path("films")
public class FilmResource {

    private FilmDataService filmDataService = new FilmDataService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Film> getAll(@DefaultValue("-1") @QueryParam("minimum-duration") int minimumDuration) {
        return filmDataService.getAll(minimumDuration);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Film getOne(@PathParam("id") int id) {
        Film filmFound = filmDataService.getOne(id);
        if (filmFound == null)
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Ressource not found").type("text/plain").build());
        return filmFound;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Authorize
    public Film createOne(Film film, @Context ContainerRequest request) {
        User authenticatedUser = (User) request.getProperty("user");
        System.out.println("A new film is added by " + authenticatedUser.getLogin() );
        if (film == null || film.getTitle() == null || film.getTitle().isBlank())
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Lacks of mandatory info").type("text/plain").build());
        return filmDataService.createOne(film);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Authorize
    public Film updateOne(Film film, @PathParam("id") int id) {
        if (id == 0 || film == null || film.getTitle() == null || film.getTitle().isBlank())
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Lacks of mandatory info").type("text/plain").build());
        Film filmToUpdate = filmDataService.updateOne(film, id);
        if (filmToUpdate == null)
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Ressource not found").type("text/plain").build());
        return filmToUpdate;
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize
    public Film deleteOne(@PathParam("id") int id) {
        if (id == 0) // default value of an integer => has not been initialized
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Lacks of mandatory id info")
                    .type("text/plain").build());
        Film filmToDelete = filmDataService.deleteOne(id);
        if (filmToDelete == null)
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Ressource not found").type("text/plain").build());
        return filmToDelete;
    }
}

