package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseTest {
    @AndroidFindBy(accessibility = "Open navigation menu")
    private WebElement menuIcon;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Sign in\"]")
    private WebElement signInBtn;
    @AndroidFindBy(xpath = "//android.widget.EditText[@hint='Email']")
    private WebElement emailField;
    @AndroidFindBy(xpath = "//android.widget.EditText[@hint='Password']")
    private WebElement passField;
    @AndroidFindBy(accessibility = "Login")
    private WebElement loginBtn;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Wrong login credentials.\"]")
    private WebElement errorPopup;
    @AndroidFindBy(accessibility = "Close")
    private WebElement closeButton;

    public LoginPage enterEmail(String email) {
        sendKeys(emailField, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(passField, password);
        return this;
    }

    public LoginPage pressEmail() {
        click(emailField);
        return this;
    }

    public LoginPage pressPassword() {
        click(passField);
        return this;
    }

    public LoginPage pressMenu() {
        click(menuIcon);
        return this;
    }

    public LoginPage pressSignInBtn() {
        click(signInBtn);
        return this;
    }

    public LoginPage pressClose() {
        click(closeButton);
        return this;
    }

    public Homepage pressLoginBtn() {
        click(loginBtn);
        return new Homepage();
    }

    public String getErrorPopup() {
        return getAttribute(errorPopup, "content-desc");
    }
}