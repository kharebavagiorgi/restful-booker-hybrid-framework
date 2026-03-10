package com.automation.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminLoginPage extends BasePage {

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginButton = By.id("doLogin");

    public AdminLoginPage(WebDriver driver){
        super(driver);
    }

    public RoomManagementPage login(String userVal, String passVal){
        WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        user.clear();
        user.sendKeys(userVal);
        WebElement pass = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        pass.clear();
        pass.sendKeys(passVal);
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(this.loginButton));
        login.click();

        return new RoomManagementPage(driver);
    }
}
