package com.automation.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class ContactPage extends BasePage{

    private By name = By.id("name");
    private By email = By.id("email");
    private By phone = By.id("phone");
    private By subject = By.id("subject");
    private By description = By.id("description");
    private By submit = By.xpath("//button[text() = 'Submit']");
    private By errorList = By.cssSelector(".alert-danger p");
    private By successMessage = By.xpath("//h3[contains(text(), 'Thanks for getting in touch')]");

    public ContactPage(WebDriver driver){
        super(driver);
    }

    public void fillForm(String name, String email, String phone, String subject, String description){
        driver.findElement(this.name).sendKeys(name);
        driver.findElement(this.email).sendKeys(email);
        driver.findElement(this.phone).sendKeys(phone);
        driver.findElement(this.subject).sendKeys(subject);
        driver.findElement(this.description).sendKeys(description);
    }

    public void clickSubmit(){
        WebElement submitButton = driver.findElement(submit);
        Actions actions = new Actions(driver);
        actions.moveToElement(submitButton).click().perform();
    }

    public List<String> getErrors(){
        return driver.findElements(errorList)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}