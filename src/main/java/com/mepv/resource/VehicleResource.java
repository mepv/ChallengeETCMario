package com.mepv.resource;

import com.mepv.dto.UserDTO;
import com.mepv.dto.VehicleDTO;
import com.mepv.model.User;
import com.mepv.model.Vehicle;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/api/v1/vehicles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional(Transactional.TxType.SUPPORTS)
public class VehicleResource {

    @GET
    @RolesAllowed({"User", "Admin"})
    public Response listAllVehicles() {
        return Response.ok(vehicleList()).build();
    }

    @GET
    @RolesAllowed({"User", "Admin"})
    @Path("{uuid}")
    public Vehicle findVehicleById(@PathParam("uuid") UUID uuid) {
        return Vehicle.find("uuid", uuid).firstResult();
    }

    @POST
    @RolesAllowed({"User", "Admin"})
    @Transactional
    public Response persistVehicle(Vehicle vehicle, @Context UriInfo uriInfo) {
        Vehicle.persist(vehicle);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(vehicle.id));
        return Response.created(builder.build()).build();
    }

    @DELETE
    @RolesAllowed({"User", "Admin"})
    @Path("{uuid}")
    @Transactional
    public void deleteVehicle(@PathParam("uuid") UUID uuid) {
        Vehicle.delete("uuid", uuid);
    }

    @PUT
    @RolesAllowed({"User", "Admin"})
    @Path("{uuid}")
    @Transactional
    public Response updateVehicle(Vehicle v, @PathParam("uuid") UUID uuid) {
        Vehicle vehicle = Vehicle.find("uuid", uuid).firstResult();
        vehicle.setMake(v.getMake());
        vehicle.setModel(v.getModel());
        vehicle.setColor(v.getColor());
        vehicle.setYear(v.getYear());
        Vehicle.persist(vehicle);
        return Response.ok().build();
    }

    @GET
    @RolesAllowed("Admin")
    @Path("/users")
    public Response listAllUsers() {
        return Response.ok(userList()).build();
    }

    @GET
    @RolesAllowed("Admin")
    @Path("/users/{uuid}")
    public Response findUserByUUID(@PathParam("uuid") UUID uuid) {
        User user = User.find("uuid", uuid).firstResult();
        return Response.ok(new UserDTO(user.getUsername(), user.getRole(), user.getUuid())).build();
    }

    private List<VehicleDTO> vehicleList() {
        List<Vehicle> list = Vehicle.listAll();
        return list.
                stream()
                .map(v -> new VehicleDTO(
                        v.getMake(),
                        v.getModel(),
                        v.getColor(),
                        v.getYear(),
                        v.getUuid()
                ))
                .collect(Collectors.toList());
    }

    private List<UserDTO> userList() {
        List<User> list = User.listAll();
        return list
                .stream()
                .map(u -> new UserDTO(
                        u.getUsername(),
                        u.getRole(),
                        u.getUuid()
                )).collect(Collectors.toList());
    }
}