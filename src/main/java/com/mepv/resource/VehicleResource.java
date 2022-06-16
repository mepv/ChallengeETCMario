package com.mepv.resource;

import com.mepv.model.Vehicle;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/api/v1/vehicles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional(Transactional.TxType.SUPPORTS)
public class VehicleResource {

    @GET
    public List<Vehicle> listAllVehicles() {
        return Vehicle.listAll();
    }

    @GET
    @Path("{id}")
    public Vehicle findVehicleById(@PathParam("id") Long id) {
        return (Vehicle) Vehicle.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @POST
    @Transactional
    public Response persistVehicle(Vehicle vehicle, @Context UriInfo uriInfo) {
        Vehicle.persist(vehicle);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(vehicle.id));
        return Response.created(builder.build()).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deleteVehicle(@PathParam("id") Long id) {
        Vehicle.deleteById(id);
    }
}