package com.mycompany.rest;

import com.mycompany.data.Reservation;
import com.mycompany.exception.ReservationException;
import com.mycompany.service.ReservationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservationRest {

    private final ReservationService reservationService =
            ReservationService.getInstance();

    // ================== TEST ==================
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Reservation REST radi!";
    }

    // ================== GET: rezervacije korisnika ==================
    @GET
    @Path("/user/{userId}")
    public Response getReservationsByUser(@PathParam("userId") int userId) {
        try {
            List<Reservation> reservations =
                    reservationService.getReservationsByUserId(userId);
            return Response.ok(reservations).build();
        } catch (ReservationException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    // ================== POST: kreiranje rezervacije ==================
    @POST
    public Response createReservation(Reservation reservation) {
        try {
            int id = reservationService.createReservation(reservation);
            reservation.setId(id);

            return Response.status(Response.Status.CREATED)
                    .entity(reservation)
                    .build();
        } catch (ReservationException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }
    
    // ================== PUT: izmena rezervacije ==================
    @PUT
    public Response updateReservation(Reservation reservation) {
    try {
        reservationService.updateReservation(reservation);
        return Response.ok(reservation).build();
    } catch (ReservationException ex) {
        ex.printStackTrace();
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ex.getMessage())
                .build();
    }
}

    // ================== DELETE: otkazivanje ==================
    @DELETE
    @Path("/{id}")
    public Response deleteReservation(@PathParam("id") int id) {
        try {
            reservationService.deleteReservation(id);
            return Response.noContent().build();
        } catch (ReservationException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }
}
