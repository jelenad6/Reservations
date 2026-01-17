package com.mycompany.rest;

import com.mycompany.data.Resource;
import com.mycompany.service.ResourceService;
import com.mycompany.exception.ReservationException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/resources")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResourceRest {

    private ResourceService resourceService = ResourceService.getInstance();

    // ---------------- Pregled svih resursa ----------------
    @GET
    public Response getAllResources() {
        try {
            List<Resource> resources = resourceService.getAllResources();
            return Response.ok(resources).build();
        } catch (ReservationException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    // ---------------- Pregled resursa po ID ----------------
    @GET
    @Path("/{id}")
    public Response getResource(@PathParam("id") int id) {
        try {
            Resource resource = resourceService.getResourceById(id);
            if (resource == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Resource not found")
                        .build();
            }
            return Response.ok(resource).build();
        } catch (ReservationException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    // ---------------- Dodavanje novog resursa ----------------
    @POST
    public Response addResource(Resource resource) {
        try {
            int id = resourceService.addResource(resource);
            resource.setId(id);
            return Response.ok(resource).build();
        } catch (ReservationException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    // ---------------- Izmena resursa ----------------
    @PUT
    @Path("/{id}")
    public Response updateResource(@PathParam("id") int id, Resource resource) {
        try {
            resource.setId(id);
            resourceService.updateResource(resource);
            return Response.ok(resource).build();
        } catch (ReservationException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    // ---------------- Brisanje resursa ----------------
    @DELETE
    @Path("/{id}")
    public Response deleteResource(@PathParam("id") int id) {
        try {
            resourceService.deleteResource(id);
            return Response.ok("Resource deleted").build();
        } catch (ReservationException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }
}
