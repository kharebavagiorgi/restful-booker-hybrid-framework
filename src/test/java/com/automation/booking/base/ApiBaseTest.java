package com.automation.booking.base;

import com.automation.booking.api.clients.BookingClient;
import com.automation.booking.api.clients.HealthClient;
import org.testng.annotations.BeforeClass;

public class ApiBaseTest {

    protected HealthClient healthClient;
    protected BookingClient bookingClient;

    @BeforeClass(alwaysRun = true)
    public void apiSetup(){
        healthClient = new HealthClient();
        bookingClient = new BookingClient();
    }
}
