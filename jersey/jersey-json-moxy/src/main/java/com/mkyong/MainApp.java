package com.mkyong;

import com.mkyong.json.CustomExceptionMapper;
import com.mkyong.json.CustomMoxyConfig;
import com.mkyong.json.CustomNotFoundExceptionMapper;
import com.mkyong.json.JsonResource;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainApp {

    public static final URI BASE_URI = URI.create("http://localhost:8080");

    // For logging, setup to redirect J.U.L to logback
    static {
        // JUL Root logger to the lowest level, so that bridge can intercept all j.u.l. logs
        Logger.getLogger("").setLevel(Level.FINEST);

        // Optionally remove existing handlers attached to j.u.l root logger
        // (since SLF4J 1.6.5)
        SLF4JBridgeHandler.removeHandlersForRootLogger();

        // add SLF4JBridgeHandler to j.u.l's root logger, should be done once during
        // the initialization phase of your application
        SLF4JBridgeHandler.install();
    }

    // Starts Jetty HTTP server
    public static Server startHttpServer() {

        final ResourceConfig config = new ResourceConfig();

        // scan only one resource
        config.register(JsonResource.class);

        // only register if contains custom moxy config
        config.register(CustomMoxyConfig.class);

        // only register if contains custom exception mapper
        config.register(CustomNotFoundExceptionMapper.class);

        return JettyHttpContainerFactory.createServer(BASE_URI, config);

    }

    public static void main(String[] args) throws Exception {

        try {

            final Server server = startHttpServer();

            server.start();

            // shut down hook
            Runtime.getRuntime().addShutdownHook(
                    new Thread(() -> {
                        try {
                            server.stop();
                        } catch (Exception e) {
                            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, e);
                        }
                    })
            );

            System.out.println(String.format("Application started.%nStop the application using CTRL+C"));

            // block and wait shut down signal, like CTRL+C
            Thread.currentThread().join();

        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}