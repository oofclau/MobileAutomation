package com.opti.hsoverview;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SendKeysTest {
    public static void main(String[] args) throws Exception{
        AppiumDriver driver = CreateDriverSession.initializeDriver("iOS");
        By textFields = AppiumBy.accessibilityId("Text Fields");
        By editText = AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == \"Placeholder text\"`][1]");

        driver.findElement(textFields).click();
        driver.findElement(editText).sendKeys("aaaaa");
        Thread.sleep(100000);
    }
}
