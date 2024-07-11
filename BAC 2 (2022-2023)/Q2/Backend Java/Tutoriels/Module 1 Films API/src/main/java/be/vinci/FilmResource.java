package be.vinci;

import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.text.StringEscapeUtils;

import java.util.List;

@Singleton
@Path("films")
public class FilmResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Film> getAll(@DefaultValue("-1") @QueryParam("minimum-duration") int minimumDuration) {
        var films = Json.parse();
        if (minimumDuration != -1) {
            List<Film> filmsFiltered = films.stream().filter(film -> film.getDuration() >= minimumDuration)
                    .toList();
            return filmsFiltered;
        }
        return films;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Film getOne(@PathParam("id") int id) {
        var films = Json.parse();
        Film filmFound = films.stream().filter(film -> film.getId() == id).findAny().orElse(null);
        if (filmFound == null)
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Ressource not found").type("text/plain").build());
        return filmFound;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Film createOne(Film film) {
        if (film == null || film.getTitle() == null || film.getTitle().isBlank())
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Lacks of mandatory info").type("text/plain").build());
        var films = Json.parse();
        film.setId(films.size() + 1);
        film.setTitle(StringEscapeUtils.escapeHtml4(film.getTitle()));
        film.setLink(StringEscapeUtils.escapeHtml4(film.getLink()));
        films.add(film);
        Json.serialize(films);
        return film;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Film updateOne(Film film, @PathParam("id") int id) {
        if (id == 0 || film == null || film.getTitle() == null || film.getTitle().isBlank())
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Lacks of mandatory info").type("text/plain").build());
        var films = Json.parse();
        Film filmToUpdate = films.stream().filter(f -> f.getId() == id).findAny().orElse(null);
        if (filmToUpdate == null)
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Ressource not found").type("text/plain").build());
        film.setId(id);
        film.setTitle(StringEscapeUtils.escapeHtml4(film.getTitle()));
        film.setLink(StringEscapeUtils.escapeHtml4(film.getLink()));
        films.remove(film); // thanks to equals(), films is found via its id
        films.add(film);
        Json.serialize(films);
        return film;
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Film deleteOne(@PathParam("id") int id) {
        if (id == 0) // default value of an integer => has not been initialized
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Lacks of mandatory id info")
                    .type("text/plain").build());
        var films = Json.parse();
        Film filmToDelete = films.stream().filter(film -> film.getId() == id).findAny().orElse(null);
        if (filmToDelete == null)
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Ressource not found").type("text/plain").build());
        films.remove(filmToDelete);
        Json.serialize(films);
        return filmToDelete;
    }
}

