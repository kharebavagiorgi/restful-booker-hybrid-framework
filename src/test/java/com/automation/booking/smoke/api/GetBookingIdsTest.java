package com.automation.booking.smoke.api;

import com.automation.booking.base.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class GetBookingIdsTest extends ApiBaseTest {

    @Test(groups = {"smoke", "booking"}, description = "API-02: Get All Booking IDs")
    public void verifyGettingAllBookingIds(){
        Response response = bookingClient.getAllBookingIds();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200,
                "Getting All Booking IDs failed, Expected 200 but received " + response.getStatusCode());
        List<Object> bookingIds = response.jsonPath().getList("");
        softAssert.assertNotNull(bookingIds,"Booking IDs list is null");

        if(bookingIds != null){
            softAssert.assertFalse(bookingIds.isEmpty(), "Booking IDs list is empty!");
        }
        softAssert.assertAll();
    }
}