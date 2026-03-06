package com.automation.booking.smoke.ui;

import com.automation.booking.base.UIBaseTest;
import com.automation.booking.model.ui.ContactModel;
import com.automation.booking.pages.ContactPage;
import com.automation.booking.utils.ConfigReader;
import com.automation.booking.utils.JsonReader;
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

    private void validateErrors(List<String> actual, List<String> expected, SoftAssert softAssert) {
        if (expected != null && !expected.isEmpty()) {
            softAssert.assertEquals(actual.size(), expected.size(), "Number of error messages does not match!");
            for (String error : expected) {
                softAssert.assertTrue(actual.contains(error), "Expected error NOT found: [" + error + "]");
            }
        } else {
            softAssert.fail("Test Data Error: 'expectedErrors' list is missing or empty in JSON.");
        }
    }

    private Object[][] mapToDataProvider(String sectionName){
        List<ContactModel> dataList = JsonReader.getContactData(sectionName);
        Object[][] data = new Object[dataList.size()][1];
        for(int i = 0; i < dataList.size(); i++){
            data[i][0] = dataList.get(i);
        }
        return data;
    }

    @DataProvider(name = "validContactJson")
    public Object[][] getValidContactJson(){
        return mapToDataProvider("validContactData");
    }

    @DataProvider(name = "invalidEmailContactData")
    public Object[][] getInvalidContactData(){
        return mapToDataProvider("invalidEmailData");
    }

    @DataProvider(name = "errorMessageData")
    public Object[][] getErrorMessageData(){
        return mapToDataProvider("emptyMandatoryData");
    }

    @DataProvider(name = "invalidNumberContactData")
    public Object[][] getInvalidNumberContactData(){
        return mapToDataProvider("invalidNumberData");
    }

    @Test(priority = 1,
            groups = "smoke",
            description = "UI-01: Submit form with valid data",
            dataProvider = "validContactJson")
    public void verifyValidSubmission(ContactModel user){
        contactPage.fillForm(user.getName(), user.getEmail(), user.getPhone(), user.getSubject(), user.getDescription());
        contactPage.clickSubmit();
        Assert.assertTrue(contactPage.isSuccessMessageDisplayed(), "Success message is not displayed.");
    }

    @Test(priority = 2,
            groups = "smoke",
            description = "UI-02: Submit with Invalid Email Format",
            dataProvider = "invalidEmailContactData")
    public void verifyInvalidEmailFails(ContactModel user){
        contactPage.fillForm(user.getName(), user.getEmail(), user.getPhone(), user.getSubject(),
                user.getDescription());
        contactPage.clickSubmit();

        List<String> actualErrorMessageList = contactPage.getErrors();
        List<String> expectedErrorMessageList = user.getExpectedErrors();

        SoftAssert softAssert = new SoftAssert();

        validateErrors(actualErrorMessageList, expectedErrorMessageList, softAssert);

        softAssert.assertAll();
    }

    @Test(priority = 3,
            groups = "smoke",
            description = "UI-03: Submit with empty mandatory fields",
            dataProvider = "errorMessageData")
    public void verifyEmptyFormValidation(ContactModel user){
        contactPage.clickSubmit();

        List<String> actualErrorMessageList = contactPage.getErrors();
        List<String> expectedErrorMessageList = user.getExpectedErrors();

        SoftAssert softAssert = new SoftAssert();
        validateErrors(actualErrorMessageList, expectedErrorMessageList, softAssert);

        softAssert.assertAll();
    }

    @Test(priority = 4,
            groups = "smoke",
            description = "UI-04: Sumbit with invalid number",
            dataProvider = "invalidNumberContactData")
    public void verifyInvalidNumberValidation(ContactModel user){
        contactPage.fillForm(user.getName(), user.getEmail(), user.getPhone(), user.getSubject(),
                user.getDescription());
        contactPage.clickSubmit();

        List<String> actualErrorMessageList = contactPage.getErrors();
        List<String> expectedErrorMessageList = user.getExpectedErrors();

        SoftAssert softAssert = new SoftAssert();

        validateErrors(actualErrorMessageList, expectedErrorMessageList, softAssert);

        softAssert.assertAll();
    }

    @Test(priority = 5,
            groups = "smoke",
            description = "UI-05: Cheking logo visibility")
    public void verifyLogoVisibility(){
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(contactPage.header().isLogoDisplayed(), "Logo is not visible");
        softAssert.assertEquals(contactPage.header().getLogoText(), "Shady Meadows B&B");

        softAssert.assertAll();
    }
}