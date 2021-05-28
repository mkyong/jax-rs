package com.mkyong;

import com.mkyong.service.MessageService;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class MyResource {

    @Inject
    @Named("aws")
    private MessageService awsService;

    @Inject
    @Named("azure")
    private MessageService azureService;

    @Path("/hk2/aws")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloAws() {
        return awsService.getHello();
    }

    @Path("/hk2/azure")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloAzure() {
        return azureService.getHello();
    }

}
