package com.automation.booking.smoke.ui;

import com.automation.booking.base.UIBaseTest;
import com.automation.booking.pages.ContactPage;
import com.automation.booking.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactPageTest extends UIBaseTest {

    private ContactPage contactPage;

    @BeforeMethod
    public void setUpPage(){
        driver.get(ConfigReader.getProperty("ui_url"));
        contactPage = new ContactPage(driver);
    }

    @Test(priority = 1, description = "UI-01: Submit form with valid data")
    public void verifyValidSubmission(){
        contactPage.fillForm("George", "george@somewhere.com", "555555555555",
                "Payment issue", "please check payment details from your side...");
        contactPage.clickSubmit();

        Assert.assertTrue(contactPage.isSuccessMessageDisplayed(), "Success message is not displayed.");
    }
}
