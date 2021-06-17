package com.mkyong.resource;

import com.mkyong.service.MessageService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class MyResource {

    // DI via HK2
    @Inject
    private MessageService messageService;

    // output text
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Jersey hello world example.";
    }

    // output text with argument
    @Path("/{name}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name") String name) {
        return "Jersey: hello " + name;
    }

    // for dependency injection
    @Path("/hk2")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloHK2() {
        return messageService.getHello();
    }

}
