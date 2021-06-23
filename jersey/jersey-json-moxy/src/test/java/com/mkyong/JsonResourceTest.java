package com.mkyong;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.jetty.server.Server;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonResourceTest {

    private static Server server;
    private static WebTarget target;

    @BeforeAll
    public static void beforeAllTests() {
        server = MainApp.startHttpServer();
        Client c = ClientBuilder.newClient();
        target = c.target(MainApp.BASE_URI.toString());
    }

    @AfterAll
    public static void afterAllTests() throws Exception {
        server.stop();
    }

    @Test
    public void testJson() throws JSONException {

        String actual = target.path("json").request().get(String.class);
        String expected = "{\"result\":\"Jersey JSON example using MOXy\"}";

        JSONAssert.assertEquals(expected, actual, false);

    }

    @Test
    public void testJsonName() throws JSONException {

        String response = target.path("json/mkyong")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        // convert json string to JSONObject
        JSONObject actual = new JSONObject(response);

        String expected = "{\"id\":1,\"name\":\"mkyong\"}";
        JSONAssert.assertEquals(expected, actual, false);

    }

    @Test
    public void testJsonAll() throws JSONException {

        String response = target.path("json/all")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        // convert json string to JSONArray
        JSONArray actual = new JSONArray(response);

        String expected = "[{\"id\":1,\"name\":\"mkyong\"},{\"id\":2,\"name\":\"zilap\"}]";
        JSONAssert.assertEquals(expected, actual, false);

    }

    @Test
    public void testJsonCreateOk() throws JSONException {

        String json = "{\"id\":1,\"name\":\"mkyong\"}";

        Response response = target.path("json/create")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(json, MediaType.valueOf("application/json")));

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());

        // read response body
        String actual = response.readEntity(String.class);
        String expected = "{\"status\":\"ok\"}";
        JSONAssert.assertEquals(expected, actual, false);

    }

    @Test
    public void testJsonCustomNotFound() throws JSONException {

        Response response = target.path("json/all/error")
                .request(MediaType.APPLICATION_JSON).buildGet().invoke();

        // ensure 404 status code
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // test json response
        String actual = response.readEntity(String.class);
        String expected = "{\"error\":\"url not found!\"}";
        JSONAssert.assertEquals(expected, actual, false);

    }

}
