package be.vinci.api;

import be.vinci.domain.User;
import be.vinci.services.UserDataService;
import be.vinci.services.UserDataServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Singleton
@Path("/auths")
public class AuthsResource {

    @Inject
    private UserDataService myUserDataService = new UserDataServiceImpl();

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectNode login(JsonNode json) {
        if (!json.hasNonNull("login") || !json.hasNonNull("password")) {
            throw new WebApplicationException("login or password required", Response.Status.BAD_REQUEST);
        }
        String login = json.get("login").asText();
        String password = json.get("password").asText();
        ObjectNode publicUser = myUserDataService.login(login, password);
        if (publicUser == null) {
            throw new WebApplicationException("login or password required", Response.Status.BAD_REQUEST);
        }
        return publicUser;
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectNode register(User user) {
        if (user == null || user.getPassword() == null || user.getPassword().isBlank()
                || user.getLogin() == null || user.getLogin().isBlank()) {
            throw new WebApplicationException("login or password required", Response.Status.BAD_REQUEST);
        }
        ObjectNode publicUser = myUserDataService.register(user);
        if (publicUser == null) {
            throw new WebApplicationException("this resource already exists", Response.Status.CONFLICT);
        }
        return publicUser;
    }
}
