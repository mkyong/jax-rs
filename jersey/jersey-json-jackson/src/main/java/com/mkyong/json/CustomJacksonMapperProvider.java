package com.mkyong.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

// enable Jackson pretty print
@Provider
public class CustomJacksonMapperProvider implements ContextResolver<ObjectMapper> {

    final ObjectMapper mapper;

    public CustomJacksonMapperProvider() {
        // enable pretty print
        mapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }

}
