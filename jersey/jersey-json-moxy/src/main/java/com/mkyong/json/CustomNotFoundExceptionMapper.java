package com.mkyong.json;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.json.JSONObject;

@Provider
public class CustomNotFoundExceptionMapper
        implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {

        JSONObject json = new JSONObject();
        json.put("error", "url not found!");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(json.toString())
                .build();
    }

}
