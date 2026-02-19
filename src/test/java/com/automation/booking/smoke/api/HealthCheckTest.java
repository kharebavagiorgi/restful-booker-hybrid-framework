package com.automation.booking.smoke.api;

import com.automation.booking.base.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class HealthCheckTest extends ApiBaseTest {

    @Test(groups = {"smoke", "health"}, description = "API-01: Verify API System Health")
    public void verifyApiTest(){
        Response response = healthClient.getPing();
        assertEquals(response.getStatusCode(), 201,
                "API Health Check failed! Expected 201 but received: " + response.getStatusCode());
    }
}