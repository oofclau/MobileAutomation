package com.opti.hsoverview;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Login {
    public static final String EMAIL = "bogdan.andrei@opti.ro";
    public static final String PASSWORD = "par0la";

    public static void main(String[] args) throws Exception {
        AppiumDriver driver = CreateDriverSession.initializeDriver("Android");
        performLogin(driver);
    }
    public static void performLogin(AppiumDriver driver) {
        //Find the elements
        By menuIcon = AppiumBy.accessibilityId("Open navigation menu");
        By signInBtn = AppiumBy.xpath("//android.view.View[@content-desc=\"Sign in\"]");
        By emailField = AppiumBy.xpath("//android.widget.EditText[@hint='Email']");
        By passField = AppiumBy.xpath("//android.widget.EditText[@hint='Password']");
        By loginBtn = AppiumBy.accessibilityId("com.opti.hsoverview.Login");
        WebElement errorPopup = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Wrong login credentials.\"]"));

        //Perform actions
        driver.findElement(menuIcon).click();
        driver.findElement(signInBtn).click();
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(EMAIL);
        driver.findElement(passField).click();
        driver.findElement(passField).sendKeys(PASSWORD);
        driver.findElement(loginBtn).click();
    }
}
