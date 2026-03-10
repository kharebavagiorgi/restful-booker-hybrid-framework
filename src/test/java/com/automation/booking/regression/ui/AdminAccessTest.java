package com.automation.booking.regression.ui;

import com.automation.booking.base.UIBaseTest;
import com.automation.booking.constant.FrameworkConstants;
import com.automation.booking.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class AdminAccessTest extends UIBaseTest {

    @Override
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        super.setUp();
        driver.get(ConfigReader.getProperty("ui_url"));
    }

    @Test(description = "REG-01: Verify navigation to Admin Login via footer bypass")
    public void verifyAdminPanelIsAccessibleViaFooter(){
        WebElement adminLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(
                FrameworkConstants.ADMIN_PANEL_LINK_TEXT
        )));

        jsUtils.scrollToElement(adminLink);
        jsUtils.clickWithJS(adminLink);

        boolean isOnAdminPage = wait.until(ExpectedConditions.urlContains("/admin"));

        Assert.assertTrue(isOnAdminPage, "Verification Failed: Navigation to Admin Login page was unsuccessful.");
    }
}
