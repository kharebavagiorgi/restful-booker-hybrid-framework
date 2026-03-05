package com.automation.booking.smoke.ui;

import com.automation.booking.base.UIBaseTest;
import com.automation.booking.pages.ContactPage;
import com.automation.booking.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

    @DataProvider(name = "invalidEmailContactData")
    public Object[][] getInvalidContactData(){
        return new Object[][]{
                { "George", "george@", "123456789012", "Payment Issue",
                        "Message from George regarding payment.", "must be a well-formed email address" },
                { "Anna", "somewhere.com", "098765432109", "Booking Inquiry",
                        "Anna wants to know about room availability.", "must be a well-formed email address" }
        };
    }

    @DataProvider(name = "invalidNumberContactData")
    public Object[][] getInvalidNumberContactData(){
        return new Object[][]{
                { "George", "george@test.com", "11", "Payment Issue", "Message from George regarding payment." },
                { "Anna", "anna@somewhere.com", "8888888888888888888888", "Booking Inquiry", "Anna wants to know about room availability." }
        };
    }

    @DataProvider(name = "errorMessageData")
    public Object[][] getErrorMessageData(){
        String[] expectedErrors =
                {"Message must be between 20 and 2000 characters.",
                "Phone may not be blank",
                "Email may not be blank",
                "Phone must be between 11 and 21 characters.",
                "Subject must be between 5 and 100 characters.",
                "Message may not be blank",
                "Name may not be blank",
                "Subject may not be blank"};

        return new Object[][]{{expectedErrors}};
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
            dataProvider = "invalidEmailContactData")
    public void verifyInvalidEmailFails(String name, String email, String phone,
                                        String subject, String description, String expectedErrorMessage){
        contactPage.fillForm(name, email, phone, subject, description);
        contactPage.clickSubmit();

        List<String> errors = contactPage.getErrors();
        Assert.assertTrue(errors.contains(expectedErrorMessage),
                "Expected error message not found for email: " + email);
    }

    @Test(priority = 3,
            description = "UI-03: Submit with empty mandatory fields",
            dataProvider = "errorMessageData")
    public void verifyEmptyFormValidation(String[] expectedErrors){
        contactPage.clickSubmit();

        List<String> errorMessages = contactPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(errorMessages.size(), expectedErrors.length,
                "Number of error messages does not match");
        for(String error : expectedErrors){
            softAssert.assertTrue(errorMessages.contains(error), error + " was not found");
        }
        softAssert.assertAll();
    }

    @Test(priority = 4,
            description = "UI-04: Sumbit with invalid number",
            dataProvider = "invalidNumberContactData")
    public void verifyInvalidNumberValidation(String name, String email, String phone, String subject,
                                              String description){
        contactPage.fillForm(name, email, phone, subject, description);
        contactPage.clickSubmit();

        List<String> errorMessages = contactPage.getErrors();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(errorMessages.size(), 1,
                "Expected exactly 1 error for phone length, but found: " + errorMessages.size());

        softAssert.assertTrue(errorMessages.contains("Phone must be between 11 and 21 characters."),
                "The specific phone length validation message was missing or incorrect!");
        softAssert.assertAll();
    }
}