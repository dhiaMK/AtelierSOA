package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.client.Entity.entity;

@Path("/logement")
public class LogementRessources {
    static LogementBusiness help = new LogementBusiness();
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response  getAll(){
        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity(help.getLogements()).
                build();
    }

    @GET
    @Path("/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    //getLogementsByReference
    public Response  getLogByRef(@PathParam(value="ref") int ref){
        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity(help.getLogementsByReference(ref)).
                build();
    }

    //add
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        if (logement == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid request: logement data is missing")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }

        boolean isAdded = help.addLogement(logement);

        if (isAdded) {
            return Response.status(Response.Status.CREATED)
                    .entity("Logement added successfully")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to add logement")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }


    // UPDATE logement
    @PUT
    @Path("/update/{ref}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("ref") int ref, Logement logement) {
        boolean isUpdated = help.updateLogement(ref, logement);
        if (isUpdated) {
            return Response.ok("Logement updated successfully")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Logement not found or could not be updated")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }

    // DELETE logement
    @DELETE
    @Path("/delete/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@PathParam("ref") int ref) {
        boolean isDeleted = help.deleteLogement(ref);
        if (isDeleted) {
            return Response.ok("Logement deleted successfully")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Logement not found or could not be deleted")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }
}
