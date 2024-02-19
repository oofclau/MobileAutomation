package com.opti.hsoverview;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Search {

    public static void main(String[] args) throws Exception {
        AppiumDriver driver = CreateDriverSession.initializeDriver("Android");
        new Search(driver);
    }
    public Search(AppiumDriver driver) {
        // Perform login
        Login.performLogin(driver);

        // Find elements for search
        By menuIcon = AppiumBy.accessibilityId("Open navigation menu");
        By contacts = AppiumBy.accessibilityId("Contacts");
        //By searchBtn = AppiumBy.xpath("//android.widget.Button[@bounds='[723,63][849,210]']");
        WebElement searchBtn = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.Button\").enabled(true).instance(1)"));
        By searchBox = AppiumBy.xpath("//android.widget.EditText");
        WebElement enterSearchBtn = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.Button\").enabled(true).instance(1)"));

        // Perform search actions
        driver.findElement(menuIcon).click();
        driver.findElement(contacts).click();
        searchBtn.click(); // Bring the textbox to focus
        driver.findElement(searchBox).click();
        driver.findElement(searchBox).sendKeys("Ana");
        enterSearchBtn.click();
    }
}
