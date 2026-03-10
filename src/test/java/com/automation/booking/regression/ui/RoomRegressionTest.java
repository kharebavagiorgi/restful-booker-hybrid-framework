package com.automation.booking.regression.ui;

import com.automation.booking.base.UIBaseTest;
import com.automation.booking.pages.AdminLoginPage;
import com.automation.booking.pages.RoomManagementPage;
import com.automation.booking.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

public class RoomRegressionTest extends UIBaseTest {

    @Override
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        super.setUp();
        driver.get(ConfigReader.getProperty("ui_url") + "/admin");
    }

    @Test(description = "REG-02: Create a new room and verify it appears in the management list")
    public void verifyNewRoomCreation(){
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        RoomManagementPage roomManagementPage = adminLoginPage.login(
                ConfigReader.getProperty("admin_user"),
                ConfigReader.getProperty("admin_password")
        );

        String roomNumber = "Room_" + System.currentTimeMillis();
        List<String> features = Arrays.asList("WiFi", "TV", "Safe");
        roomManagementPage.createRoom(roomNumber, "Double", "true", "150", features);

        boolean isCreated = roomManagementPage.isRoomNumberPresent(roomNumber);

        Assert.assertTrue(isCreated, "Assertion Failed: Room " + roomNumber + " was not found in the management list!");
    }
}
