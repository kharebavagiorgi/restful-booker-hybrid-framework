package com.automation.booking.api.clients;

import com.automation.booking.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HealthClient {

    private static final String BASE_URL = ConfigReader.getProperty("base_url");
    private static final String PING_ENDPOINT = "/ping";

    public Response getPing(){
        return RestAssured
                .given()
                    .baseUri(BASE_URL)
                .when()
                    .get(PING_ENDPOINT);
    }
}
