package com.automation.booking.smoke.api;

import com.automation.booking.base.ApiBaseTest;
import com.automation.booking.model.Booking;
import com.automation.booking.model.BookingDates;
import com.automation.booking.model.BookingResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateBookingTest extends ApiBaseTest {

    @Test(groups = {"smoke", "booking"}, description = "API-04: Create New Booking")
    public void verifyCreateBooking(){

        SoftAssert softAssert = new SoftAssert();

        BookingDates bookingDates = BookingDates.builder()
                .checkin("2026-01-01")
                .checkout("2026-01-06")
                .build();

        Booking bookingRequest = Booking.builder()
                .firstname("Giorgi")
                .lastname("Kharebava")
                .totalprice(200)
                .depositpaid(true)
                .bookingdates(bookingDates)
                .additionalneeds("breakfast")
                .build();

        Response response = bookingClient.createBooking(bookingRequest);
        BookingResponse actualResponse = response.as(BookingResponse.class);

        softAssert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

        softAssert.assertNotNull(actualResponse.getBookingid(),
                "The 'bookingid' must not be null");
        softAssert.assertEquals(actualResponse.getBooking().getFirstname(), bookingRequest.getFirstname(), "Firstname mismatch");
        softAssert.assertEquals(actualResponse.getBooking().getLastname(), bookingRequest.getLastname(), "Lastname mismatch");
        softAssert.assertEquals(actualResponse.getBooking().getTotalprice(), bookingRequest.getTotalprice(), "Price mismatch");
        softAssert.assertTrue(actualResponse.getBooking().getDepositpaid(), "Deposit status should be true");
        softAssert.assertEquals(actualResponse.getBooking().getBookingdates().getCheckin(), bookingRequest.getBookingdates().getCheckin(), "Check-in date mismatch");
        softAssert.assertEquals(actualResponse.getBooking().getBookingdates().getCheckout(), bookingRequest.getBookingdates().getCheckout(), "Check-out date mismatch");

        softAssert.assertAll();
    }
}
