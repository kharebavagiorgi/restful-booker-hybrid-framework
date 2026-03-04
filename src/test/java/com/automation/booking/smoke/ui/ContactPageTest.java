package com.automation.booking.smoke.ui;

import com.automation.booking.base.UIBaseTest;
import com.automation.booking.pages.ContactPage;
import com.automation.booking.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ContactPageTest extends UIBaseTest {

    private ContactPage contactPage;

    @BeforeMethod
    public void setUpPage(){
        driver.get(ConfigReader.getProperty("ui_url"));
        contactPage = new ContactPage(driver);
    }

    @DataProvider(name = "validContactData")
    public Object[][] getValidContactData(){
        return new Object[][]{
                { "George", "george@test.com", "123456789012", "Payment Issue", "Message from George regarding payment." },
                { "Anna", "anna@somewhere.com", "098765432109", "Booking Inquiry", "Anna wants to know about room availability." }
        };
    }

    @DataProvider(name = "invalidContactData")
    public Object[][] getInvalidContactData(){
        return new Object[][]{
                { "George", "george@", "123456789012", "Payment Issue",
                        "Message from George regarding payment.", "must be a well-formed email address" },
                { "Anna", "somewhere.com", "098765432109", "Booking Inquiry",
                        "Anna wants to know about room availability.", "must be a well-formed email address" }
        };
    }

    @Test(priority = 1,
            description = "UI-01: Submit form with valid data",
            dataProvider = "validContactData")
    public void verifyValidSubmission(String name, String email, String phone, String subject, String description){
        contactPage.fillForm(name, email, phone, subject, description);
        contactPage.clickSubmit();
        Assert.assertTrue(contactPage.isSuccessMessageDisplayed(), "Success message is not displayed.");
    }

    @Test(priority = 2,
            description = "UI-02: Submit with Invalid Email Format",
            dataProvider = "invalidContactData")
    public void verifyInvalidEmailFails(String name, String email, String phone,
                                        String subject, String description, String expectedErrorMessage){
        contactPage.fillForm(name, email, phone, subject, description);
        contactPage.clickSubmit();

        List<String> errors = contactPage.getErrors();
        Assert.assertTrue(errors.contains(expectedErrorMessage),
                "Expected error message not found for email: " + email);
    }
}