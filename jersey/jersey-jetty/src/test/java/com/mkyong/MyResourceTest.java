package com.mkyong;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.jetty.server.Server;
import jakarta.ws.rs.client.WebTarget;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyResourceTest {

    private static Server server;
    private static WebTarget target;

    @BeforeAll
    public static void beforeAllTests() {
        server = MainApp.startServer();
        Client c = ClientBuilder.newClient();
        target = c.target(MainApp.BASE_URI);
    }

    @AfterAll
    public static void afterAllTests() throws Exception {
        server.stop();
    }

    @Test
    public void testHello() {
        String response = target.path("hello").request().get(String.class);
        assertEquals("Jersey Jetty example.", response);
    }

    @Test
    public void testHelloName() throws JSONException {

        String response = target.path("hello/mkyong")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        // convert json string to JSONObject
        JSONObject actual = new JSONObject(response);

        String expected = "{\"id\":0,\"name\":\"mkyong\"}";
        JSONAssert.assertEquals(expected, actual, false);

    }

    @Test
    public void testHelloAll() throws JSONException {

        String response = target.path("hello/all")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        // convert json string to JSONArray
        JSONArray actual = new JSONArray(response);

        String expected = "[{\"id\":1,\"name\":\"mkyong\"},{\"id\":2,\"name\":\"zilap\"}]";
        JSONAssert.assertEquals(expected, actual, false);

    }

}
