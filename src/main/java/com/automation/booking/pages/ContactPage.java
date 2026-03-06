package com.automation.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class ContactPage extends BasePage{

    private final By name = By.id("name");
    private final By email = By.id("email");
    private final By phone = By.id("phone");
    private final By subject = By.id("subject");
    private final By description = By.id("description");
    private final By submit = By.xpath("//button[text() = 'Submit']");
    private final By errorList = By.cssSelector(".alert-danger p");
    private final By successMessage = By.xpath("//h3[contains(text(), 'Thanks for getting in touch')]");

    public ContactPage(WebDriver driver){
        super(driver);
    }

    public void fillForm(String name, String email, String phone, String subject, String description){
        WebElement nameField = driver.findElement(this.name);
        nameField.clear();
        nameField.sendKeys(name);
        WebElement emailField = driver.findElement(this.email);
        emailField.clear();
        emailField.sendKeys(email);
        WebElement phoneField = driver.findElement(this.phone);
        phoneField.clear();
        phoneField.sendKeys(phone);
        WebElement subjectField = driver.findElement(this.subject);
        subjectField.clear();
        subjectField.sendKeys(subject);
        WebElement descriptionField = driver.findElement(this.description);
        descriptionField.clear();
        descriptionField.sendKeys(description);
    }

    public void clickSubmit(){
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(submit));
        new Actions(driver)
                .scrollToElement(submitButton)
                .moveToElement(submitButton)
                .click()
                .perform();
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