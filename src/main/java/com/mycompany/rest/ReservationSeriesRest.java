package com.mycompany.rest;

import com.mycompany.data.ReservationSeries;
import com.mycompany.exception.ReservationException;
import com.mycompany.service.ReservationSeriesService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reservations/series")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservationSeriesRest {

    private final ReservationSeriesService service = ReservationSeriesService.getInstance();

   @POST
public Response createSeries(ReservationSeries series) {
    try {
        int seriesId = service.createSeriesAndReservations(series);
        return Response.status(Response.Status.CREATED)
                .entity("{\"seriesId\":" + seriesId + "}")
                .build();
    } catch (ReservationException ex) {
        ex.printStackTrace();
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ex.getMessage())  
                .build();
    }
}
}
