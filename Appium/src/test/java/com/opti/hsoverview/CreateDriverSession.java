package com.opti.hsoverview;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class CreateDriverSession {

    public static void main(String[] args) {
    }
    public static AppiumDriver initializeDriver(String platformName) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", platformName);
        caps.setCapability("newConnectionTimeout", 300);

        URL url = new URL("http://0.0.0.0:4723");
        switch(platformName){
            case "Android":
                caps.setCapability("deviceName", "Pixel7");
                caps.setCapability("automationName", "UIAutomator2");
                caps.setCapability("udid", "emulator-5554");
                String appUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator
                        + "main" + File.separator + "resources" + File.separator + "app/app-release.apk";
                caps.setCapability("appPackage", "com.opti.hsoverview");
                caps.setCapability("appActivity", "com.example.hsoverview.MainActivity");
                caps.setCapability("autoGrantPermissions", true);
                caps.setCapability("autoAcceptAlerts", true);
                return new AndroidDriver(url, caps);
            case "iOS":
                caps.setCapability("deviceName", "iPhone 13 Pro");
                caps.setCapability("automationName", "XCUITest");
                caps.setCapability("udid", "13CBBA1C-AC61-4BE8-8419-E89879DD2BA1");
                String iOSAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator
                        + "main" + File.separator + "resources" + File.separator + "UIKitCatalog-iphonesimulator.app";
                caps.setCapability("simulatorStartupTimeout", 180000);
                caps.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");
                return new IOSDriver(url, caps);
            default:
                throw new Exception("Invalid Platform");
        }
    }
}
