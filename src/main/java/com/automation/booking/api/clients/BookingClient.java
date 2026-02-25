package com.automation.booking.api.clients;

import com.automation.booking.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BookingClient {

    private static final String BASE_URL = ConfigReader.getProperty("base_url");
    private static final String BOOKING_ENDPOINT = ("/booking");

    public Response getAllBookingIds(){
        return RestAssured
                .given()
                    .baseUri(BASE_URL)
                .when()
                    .get(BOOKING_ENDPOINT);
    }

    public Response getBookingById(int id){
        return RestAssured
                .given()
                    .baseUri(BASE_URL)
                .when()
                    .get(BOOKING_ENDPOINT + "/" + id);

    }

}
