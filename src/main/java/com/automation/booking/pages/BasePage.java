package com.automation.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private final By logo = By.xpath("//a[contains(@class, 'navbar-brand')]");

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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