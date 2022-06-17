package com.mepv.resource;

import com.mepv.util.ApiError;
import com.mepv.model.User;
import com.mepv.service.GenerateTokenService;
import io.quarkus.elytron.security.common.BcryptUtil;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.mepv.util.StaticFunctions.loadUserByUsername;

@Path("api/v1/login")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    private static final int BAD_REQUEST = 400;

    @Inject
    GenerateTokenService generateTokenService;

    @GET
    @PermitAll
    public Response login(User userRequested) {
        String jwt;
        User user;
        Optional<User> optionalUser = loadUserByUsername(userRequested.getUsername());
        try {
            user = optionalUser.orElseThrow(NoSuchElementException::new);
            if (BcryptUtil.matches(userRequested.getPassword(), user.getPassword())) {
                jwt = generateTokenService.generateToken(user.getUsername());
                return Response.ok(jwt).build();
            } else {
                return response("The password is incorrect, please verify it and try again.");
            }
        } catch (NoSuchElementException e) {
            return response(String.format("User '%s' not found", userRequested.getUsername()));
        }
    }

    private Response response(String message) {
        return Response
                .status(BAD_REQUEST)
                .entity(new ApiError(message, BAD_REQUEST))
                .build();
    }
}
