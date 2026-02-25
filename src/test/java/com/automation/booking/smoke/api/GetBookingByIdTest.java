package com.automation.booking.smoke.api;

import com.automation.booking.base.ApiBaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingByIdTest extends ApiBaseTest {

    @Test(groups = {"smoke", "booking"}, description = "API-03: Get Booking by ID")
    public void verifyGettingBookingById(){
        Response response = bookingClient.getBookingById(1);
        JsonPath jsonPath = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.getStatusCode(), 200, "Expected 200 OK");

        softAssert.assertNotNull(jsonPath.get("firstname"), "Firstname is missing!");
        softAssert.assertNotNull(jsonPath.get("lastname"), "Lastname is missing!");
        softAssert.assertNotNull(jsonPath.get("totalprice"), "Total price is missing!");
        softAssert.assertNotNull(jsonPath.get("depositpaid"), "Deposit status is missing!");

        softAssert.assertNotNull(jsonPath.get("bookingdates.checkin"), "Check-in is missing!");
        softAssert.assertNotNull(jsonPath.get("bookingdates.checkout"), "Check-out is missing!");

        softAssert.assertAll();
    }

}
