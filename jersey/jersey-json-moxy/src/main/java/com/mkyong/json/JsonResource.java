package com.mkyong.json;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

@Path("/json")
public class JsonResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {

        // create a JSON string
        JSONObject json = new JSONObject();
        json.put("result", "Jersey JSON example using MOXy");

        // MUST json.toString(), else empty result or Unconsumed content
        // return Response.status(Response.Status.OK).entity(json).build();
        return Response.status(Response.Status.OK).entity(json.toString()).build();

    }

    // Object to JSON
    @Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User hello(@PathParam("name") String name) {
        return new User(1, name);
    }

    // A list of objects to JSON
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> helloList() {
        return Arrays.asList(
                new User(1, "mkyong"),
                new User(2, "zilap")
        );
    }

    // POST, accepts JSON
    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(User user) {

        JSONObject json = new JSONObject();
        json.put("status", "ok");
        return Response.status(Response.Status.CREATED).entity(json.toString()).build();

    }

}