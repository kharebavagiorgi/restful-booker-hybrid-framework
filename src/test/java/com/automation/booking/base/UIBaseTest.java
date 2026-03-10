package com.automation.booking.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static com.automation.booking.constant.FrameworkConstants.EXPLICIT_WAIT_TIME;

public class UIBaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        String runMode = System.getProperty("runMode", "local");

        if(runMode.equalsIgnoreCase("grid")){
            ChromeOptions options = new ChromeOptions();
            driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
        }else{
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EXPLICIT_WAIT_TIME));
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
