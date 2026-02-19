package com.automation.booking.smoke.api;

import com.automation.booking.base.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GetBookingIdsTest extends ApiBaseTest {

    @Test(groups = {"smoke", "booking"}, description = "API-02: Get All Booking IDs")
    public void verifyGettingAllBookingIds(){
        Response response = bookingClient.getAllBookingIds();
        assertEquals(response.getStatusCode(), 200,
                "Getting All Booking IDs failed, Expected 200 but received " + response.getStatusCode());
        assertFalse(response.jsonPath().getList("").isEmpty(), "Booking IDs List is empty!");
    }
}
