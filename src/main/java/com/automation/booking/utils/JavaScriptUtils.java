package com.automation.booking.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

    private final JavascriptExecutor js;

    public JavaScriptUtils(WebDriver driver){
        this.js = (JavascriptExecutor) driver;
    }

    public void clickWithJS(WebElement element){
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void setValueWithJS(WebElement element, String value){
        js.executeScript("arguments[0].value = arguments[1];", element, value);
    }
}
