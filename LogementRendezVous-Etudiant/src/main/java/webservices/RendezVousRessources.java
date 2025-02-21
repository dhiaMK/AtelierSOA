package webservices;

import entities.Logement;
import entities.RendezVous;
import metiers.LogementBusiness;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.client.Entity.entity;

@Path("/rdv")
public class RendezVousRessources {
    static RendezVousBusiness help = new RendezVousBusiness();
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response  getAll(){
        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity(help.getListeRendezVous()).
                build();
    }

    //get by id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getRendezVousById(@PathParam(value="id") int id){
        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity(help.getRendezVousById(id)).
                build();
    }

    //add
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rendezVous) {
        if (rendezVous == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid request: RendezVous data is missing")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }

        boolean isAdded = help.addRendezVous(rendezVous);

        if (isAdded) {
            return Response.status(Response.Status.CREATED)
                    .entity("rendezVous added successfully")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to add rendezVous")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }


    // UPDATE logement
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous rendezVous) {
        boolean isUpdated = help.updateRendezVous(id, rendezVous);
        if (isUpdated) {
            return Response.ok("rendezVous updated successfully")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("rendezVous not found or could not be updated")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }

    // DELETE rdv
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean isDeleted = help.deleteRendezVous(id);
        if (isDeleted) {
            return Response.ok("RendezVous deleted successfully")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("RendezVous not found or could not be deleted")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }
}
