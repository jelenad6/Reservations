package com.mycompany.rest;

import com.mycompany.data.User;
import com.mycompany.service.AuthService;
import com.mycompany.exception.ReservationException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthRest {

    private AuthService authService = AuthService.getInstance();

    // Login korisnika
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        try {
            // poziv servisa za login
            User loggedUser = authService.login(user.getUsername(), user.getPassword());

            if (loggedUser == null) {
                // korisnik nije pronađen ili lozinka je pogrešna
                return Response.status(Response.Status.UNAUTHORIZED)
                               .entity("Neispravno korisničko ime ili lozinka")
                               .build();
            }

            // uspešan login
            // vraćamo samo osnovne podatke, ne lozinku
            User responseUser = new User(
                    loggedUser.getId(),
                    loggedUser.getUsername(),
                    null, // lozinka se ne vraća
                    loggedUser.getFullName()
            );

            return Response.ok(responseUser).build();

        } catch (Exception ex) {
                    ex.printStackTrace(); 
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                   .entity(ex.getMessage())
                   .build();
}

    }
    
  
}

