package com.mycompany.rest;

import com.mycompany.data.Slot;
import com.mycompany.exception.ReservationException;
import com.mycompany.service.SlotService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/slots")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SlotRest {

    private SlotService slotService = SlotService.getInstance();

    // Pregled slobodnih termina po datumu i resursu
    @GET
    @Path("/{resourceId}/{date}")
    public Response getSlots(@PathParam("resourceId") int resourceId,
                             @PathParam("date") String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            List<Slot> slots = slotService.getSlotsByResourceAndDate(resourceId, date);
            return Response.ok(slots).build();
        } catch (ReservationException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid date format, expected YYYY-MM-DD")
                    .build();
        }
    }
}
