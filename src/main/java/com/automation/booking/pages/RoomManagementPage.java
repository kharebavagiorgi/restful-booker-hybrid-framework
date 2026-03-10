package com.automation.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class RoomManagementPage extends BasePage {

    private final By roomNumberField = By.id("roomName");
    private final By typeDropDown = By.id("type");
    private final By accessibleDropDown = By.id("accessible");
    private final By roomPriceField = By.id("roomPrice");
    private final By createButton = By.id("createRoom");

    private final String featureCheckboxXPath = "//input[@name='featureCheck' and @value='%s']";

    public RoomManagementPage(WebDriver driver){
        super(driver);
    }

    public RoomManagementPage createRoom(String roomNo, String type, String accessible, String price,
                                         List<String> features){
        wait.until(ExpectedConditions.visibilityOfElementLocated(roomNumberField)).sendKeys(roomNo);

        selectFromDropDown(typeDropDown, type);
        selectFromDropDown(accessibleDropDown, accessible);

        wait.until(ExpectedConditions.visibilityOfElementLocated(roomPriceField)).sendKeys(price);

        for(String feature : features){
            By checkboxLocator = By.xpath(String.format(featureCheckboxXPath, feature));
            WebElement checkbox = driver.findElement(checkboxLocator);
            if(!checkbox.isSelected()){
                checkbox.click();
            }
        }

        driver.findElement(createButton).click();
        return this;
    }

    private void selectFromDropDown(By locator, String value){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    public boolean isRoomNumberPresent(String roomNumber) {
        By roomLocator = By.xpath("//div[@data-testid='roomlisting']//p[text()='" + roomNumber + "']");

        return wait.until(ExpectedConditions.visibilityOfElementLocated(roomLocator)).isDisplayed();
    }
}
