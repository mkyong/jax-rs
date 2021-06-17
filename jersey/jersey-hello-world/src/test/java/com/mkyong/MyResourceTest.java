package com.mkyong;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyResourceTest {

    private static HttpServer httpServer;
    private static WebTarget target;

    @BeforeAll
    public static void beforeAllTests() {
        httpServer = MainApp.startServer();
        Client c = ClientBuilder.newClient();
        target = c.target(MainApp.BASE_URI);
    }

    @AfterAll
    public static void afterAllTests() {
        httpServer.stop();
    }

    @Test
    public void testHello() {
        String response = target.path("hello").request().get(String.class);
        assertEquals("Jersey hello world example.", response);
    }

    // add param test
    @Test
    public void testHelloName() {
        String response = target.path("hello/mkyong").request().get(String.class);
        assertEquals("Jersey: hello mkyong", response);
    }

    @Test
    public void testHelloHK2() {
        String response = target.path("hello/hk2").request().get(String.class);
        assertEquals("Hello World Jersey from HK2", response);
    }

}
