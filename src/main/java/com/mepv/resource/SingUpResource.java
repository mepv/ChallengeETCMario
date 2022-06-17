package com.mepv.resource;

import com.mepv.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path("/api/v1/signup")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class SingUpResource {

    private static final String URI_TEMPLATE = "/api/v1/vehicles/users";

    @POST
    @Path("/user")
    @Transactional
    public Response persistUser(User userRequested, @Context UriInfo uriInfo) {
        User user = User.addUser(userRequested.getUsername(), userRequested.getPassword());
        UriBuilder builder = UriBuilder.fromUri(URI_TEMPLATE).path(user.getUuid().toString());
        return Response.created(builder.build()).build();
    }

    @POST
    @Path("/admin")
    @Transactional
    public Response persistAdmin(User userRequested, @Context UriInfo uriInfo) {
        User user = User.addAdmin(userRequested.getUsername(), userRequested.getPassword());
        UriBuilder builder = UriBuilder.fromUri(URI_TEMPLATE).path(user.getUuid().toString());
        return Response.created(builder.build()).build();
    }
}
