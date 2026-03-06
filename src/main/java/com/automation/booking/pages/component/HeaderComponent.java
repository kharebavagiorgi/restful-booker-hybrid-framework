package com.automation.booking.pages.component;

import com.automation.booking.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderComponent extends BasePage {

    private final By logo = By.xpath("//a[contains(@class, 'navbar-brand')]");

    public HeaderComponent(WebDriver driver){
        super(driver);
    }

    public boolean isLogoDisplayed(){
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(logo)).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public String getLogoText(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logo)).getText();
    }

}
