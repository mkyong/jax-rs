package com.mkyong.json;

import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;

@Provider
public class CustomMoxyConfig implements ContextResolver<MoxyJsonConfig> {

    final MoxyJsonConfig moxyJsonConfig;

    public CustomMoxyConfig() {
        moxyJsonConfig = new MoxyJsonConfig();
        // pretty print
        moxyJsonConfig.setFormattedOutput(true);
    }

    @Override
    public MoxyJsonConfig getContext(Class<?> type) {
        return moxyJsonConfig;
    }

}
