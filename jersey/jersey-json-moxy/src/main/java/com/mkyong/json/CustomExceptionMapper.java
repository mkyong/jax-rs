package com.mkyong.json;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.json.JSONObject;

@Provider
public class CustomExceptionMapper
        implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException exception) {

        JSONObject json = new JSONObject();
        json.put("error", exception.getMessage());
        //json.put("error", "some error");

        // 400
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(json.toString())
                .build();
    }

}
