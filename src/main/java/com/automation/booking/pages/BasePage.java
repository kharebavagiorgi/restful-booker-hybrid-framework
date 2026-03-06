package com.automation.booking.pages;

import com.automation.booking.pages.component.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private HeaderComponent header;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public HeaderComponent header(){
        if(header == null){
            header = new HeaderComponent(driver);
        }
        return header;
    }
}