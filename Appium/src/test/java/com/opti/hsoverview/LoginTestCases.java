package com.opti.hsoverview;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.time.Duration;

import static com.opti.hsoverview.Login.EMAIL;
import static com.opti.hsoverview.Login.PASSWORD;

public class LoginTestCases {
    AppiumDriver driver;
    @Test
    public void invalidEmail() {
        By menuIcon = AppiumBy.accessibilityId("Open navigation menu");
        By signInBtn = AppiumBy.xpath("//android.view.View[@content-desc=\"Sign in\"]");
        By emailField = AppiumBy.xpath("//android.widget.EditText[@hint='Email']");
        By passField = AppiumBy.xpath("//android.widget.EditText[@hint='Password']");
        By loginBtn =  AppiumBy.accessibilityId("Login");

        driver.findElement(menuIcon).click();
        driver.findElement(signInBtn).click();
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys("invalidEmail");
        driver.findElement(passField).click();
        //closing keyboard
        driver.findElement(passField).sendKeys(PASSWORD);
        driver.findElement(loginBtn).click();

        WebElement errorPopup = driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Wrong login credentials.\"]"));

        String actualErrorPopup = errorPopup.getAttribute("content-desc");
        String expectedErrorPopup = "Wrong login credentials.";

        Assert.assertEquals(actualErrorPopup, expectedErrorPopup);

        WebElement closeButton = driver.findElement(AppiumBy.accessibilityId("Close"));
        closeButton.click();
    }
    @Test
    public void invalidPassword() {
        By menuIcon = AppiumBy.accessibilityId("Open navigation menu");
        By signInBtn = AppiumBy.xpath("//android.view.View[@content-desc=\"Sign in\"]");
        By emailField = AppiumBy.xpath("//android.widget.EditText[@hint='Email']");
        By passField = AppiumBy.xpath("//android.widget.EditText[@hint='Password']");
        By loginBtn =  AppiumBy.accessibilityId("Login");

        driver.findElement(menuIcon).click();
        driver.findElement(signInBtn).click();
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(EMAIL);
        driver.findElement(passField).click();
        driver.findElement(passField).sendKeys("wrongpassword");
        driver.findElement(loginBtn).click();

        WebElement errorPopup = driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Wrong login credentials.\"]"));

        String actualErrorPopup = errorPopup.getAttribute("content-desc");
        String expectedErrorPopup = "Wrong login credentials.";

        Assert.assertEquals(actualErrorPopup, expectedErrorPopup);

        WebElement closeButton = driver.findElement(AppiumBy.accessibilityId("Close"));
        closeButton.click();
    }
    @Test
    public void validAccount() {
        By menuIcon = AppiumBy.accessibilityId("Open navigation menu");
        By signInBtn = AppiumBy.xpath("//android.view.View[@content-desc=\"Sign in\"]");
        By emailField = AppiumBy.xpath("//android.widget.EditText[@hint='Email']");
        By passField = AppiumBy.xpath("//android.widget.EditText[@hint='Password']");
        By loginBtn = AppiumBy.accessibilityId("Login");

        driver.findElement(menuIcon).click();
        driver.findElement(signInBtn).click();
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(EMAIL);
        driver.findElement(passField).click();
        driver.findElement(passField).sendKeys(PASSWORD);
        driver.findElement(loginBtn).click();

        WebElement pageTitle = driver.findElement(AppiumBy.accessibilityId("My home"));

        String actualPageTitle = pageTitle.getAttribute("content-desc");
        String expectedPageTitle = "My home";

        Assert.assertEquals(actualPageTitle, expectedPageTitle);
    }
    @BeforeTest
    public void beforeClass() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Pixel7");
        caps.setCapability("automationName", "UIAutomator2");
        caps.setCapability("udid", "emulator-5554");
        String appUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator
                + "main" + File.separator + "resources" + File.separator + "app/app-release.apk";
        caps.setCapability("appPackage", "com.opti.hsoverview");
        caps.setCapability("appActivity", "com.example.hsoverview.MainActivity");
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("autoAcceptAlerts", true);

        URL url = new URL("http://0.0.0.0:4723");

        driver = new AndroidDriver(url, caps);
        String sessionId = driver.getSessionId().toString();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }
}
