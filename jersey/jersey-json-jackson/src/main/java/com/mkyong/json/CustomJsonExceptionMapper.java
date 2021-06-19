package com.mkyong.json;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomJsonExceptionMapper
        implements ExceptionMapper<JsonMappingException> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Response toResponse(JsonMappingException exception) {

        ObjectNode json = mapper.createObjectNode();
        //json.put("error", exception.getMessage());
        json.put("error", "json mapping error");
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(json.toPrettyString())
                .build();
    }

}
