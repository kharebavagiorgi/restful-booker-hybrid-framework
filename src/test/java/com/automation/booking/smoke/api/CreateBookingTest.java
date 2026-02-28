package com.automation.booking.smoke.api;

import com.automation.booking.base.ApiBaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

public class CreateBookingTest extends ApiBaseTest {

    @Test(groups = {"smoke", "booking"}, description = "API-04: Create New Booking")
    public void verifyCreateBooking(){

        SoftAssert softAssert = new SoftAssert();

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2026-01-01");
        bookingDates.put("checkout", "2026-01-06");

        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Giorgi");
        body.put("lastname", "Kharebava");
        body.put("totalprice", 200);
        body.put("depositpaid", true);
        body.put("bookingdates", bookingDates);
        body.put("additionalneeds", "breakfast");

        Response response = bookingClient.createBooking(body);
        JsonPath jsonPath = response.jsonPath();

        softAssert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

        softAssert.assertNotNull(jsonPath.get("bookingid"), "The 'bookingid' must not be null");
        softAssert.assertEquals(jsonPath.get("booking.firstname"), "Giorgi", "Firstname mismatch");
        softAssert.assertEquals(jsonPath.get("booking.lastname"), "Kharebava", "Lastname mismatch");
        softAssert.assertEquals(jsonPath.getInt("booking.totalprice"), 200, "Price mismatch");
        softAssert.assertTrue(jsonPath.getBoolean("booking.depositpaid"), "Deposit status should be true");
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"), "2026-01-01", "Check-in date mismatch");
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"), "2026-01-06", "Check-out date mismatch");

        softAssert.assertAll();
    }
}
