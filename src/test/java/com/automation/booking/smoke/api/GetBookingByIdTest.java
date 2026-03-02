package com.automation.booking.smoke.api;

import com.automation.booking.base.ApiBaseTest;
import com.automation.booking.model.Booking;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingByIdTest extends ApiBaseTest {

    @Test(groups = {"smoke", "booking"}, description = "API-03: Get Booking by ID")
    public void verifyGettingBookingById(){
        SoftAssert softAssert = new SoftAssert();
        Response response = bookingClient.getBookingById(1);
        Booking actualBooking = response.as(Booking.class);

        softAssert.assertEquals(response.getStatusCode(), 200, "Expected 200 OK");

        softAssert.assertNotNull(actualBooking.getFirstname(), "Firstname is missing!");
        softAssert.assertNotNull(actualBooking.getLastname(), "Lastname is missing!");
        softAssert.assertNotNull(actualBooking.getTotalprice(), "Total price is missing!");
        softAssert.assertNotNull(actualBooking.getDepositpaid(), "Deposit status is missing!");

        softAssert.assertNotNull(actualBooking.getBookingdates().getCheckin(), "Check-in is missing!");
        softAssert.assertNotNull(actualBooking.getBookingdates().getCheckout(), "Check-out is missing!");

        softAssert.assertAll();
    }

}
