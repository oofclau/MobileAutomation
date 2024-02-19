package com.qa;

import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public class BaseTest {
    protected AppiumDriver driver;
    protected Properties props;
    InputStream inputStream;
    FileInputStream fileInputStream;
    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @Parameters({"platformName", "platformVersion", "deviceName"})
    @BeforeTest
    public void beforeTest(String platformName, String platformVersion, String deviceName) throws Exception {
        try {
            props = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", platformName);
            caps.setCapability("platformVersion", platformVersion);
            caps.setCapability("deviceName", deviceName);
            caps.setCapability("automationName", props.getProperty("androidAutomationName"));
            caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
            caps.setCapability("appActivity", props.getProperty("androidAppActivity"));

            URL appUrl = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
            System.out.println("URL is " + (appUrl != null ? "not null: " + appUrl : "null"));

            caps.setCapability("app", appUrl);
            caps.setCapability("autoGrantPermissions", true);
            caps.setCapability("autoAcceptAlerts", true);

            URL url = new URL(props.getProperty("appiumURL"));

            driver = new AndroidDriver(url, caps);
            String sessionId = driver.getSessionId().toString();

        }
        catch (Exception e) {
            e.printStackTrace();
        throw e;
        }
    }
    public void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void click(WebElement e) {
        waitForVisibility(e);
        e.click();
    }

    public void sendKeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }
    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
