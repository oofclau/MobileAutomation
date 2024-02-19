package com.qa.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class Homepage {
    @AndroidFindBy(accessibility = "My home")
    private WebElement pageTitle;

    @AndroidFindBy(accessibility = "Open navigation menu")
    private WebElement menuIcon;

    public String getTitle() {
        return pageTitle.getAttribute("content-desc");
    }
}
