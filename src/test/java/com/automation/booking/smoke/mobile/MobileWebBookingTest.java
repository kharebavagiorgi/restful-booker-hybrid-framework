package com.automation.booking.smoke.mobile;

import com.automation.booking.base.MobileBaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class MobileWebBookingTest extends MobileBaseTest {

    @Test(description = "Verify that the booking portal loads correctly on a mobile browser")
    public void verifyMobileWebsiteLoad(){
        driver.get("https://automationintesting.online/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement bookButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//a[contains(text(), 'Book Now')]")
        ));

        Assert.assertTrue(bookButton.isDisplayed(), "The 'Book this room' button was not visible on the mobile view!");
    }
}
