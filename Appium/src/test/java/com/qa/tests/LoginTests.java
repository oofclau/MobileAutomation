package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.Homepage;
import com.qa.pages.LoginPage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static com.opti.hsoverview.Login.EMAIL;
import static com.opti.hsoverview.Login.PASSWORD;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    Homepage homepage;
    @BeforeClass
    public void beforeClass() {
    }
    @AfterClass
    public void afterClass() {
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        loginPage = new LoginPage();
        System.out.println("\n" + "********* Starting test: " + m.getName() + " *********" + "\n");
    }

    @AfterMethod
    public void afterMethod() {

    }

    @Test
    public void invalidEmail() {
        loginPage.pressMenu();
        loginPage.pressSignInBtn();
        loginPage.pressEmail();
        loginPage.enterEmail("invalidEmail");
        loginPage.pressPassword();
        loginPage.enterPassword(PASSWORD);
        loginPage.pressLoginBtn();


        String actualErrorPopup = loginPage.getErrorPopup();
        String expectedErrorPopup = "Wrong login credentials.";
        System.out.println("Actual error text: " + actualErrorPopup + "\n" + "Expected error text: " + expectedErrorPopup);

        Assert.assertEquals(actualErrorPopup, expectedErrorPopup);
        loginPage.pressClose();
    }
    @Test
    public void invalidPassword() {
        loginPage.pressMenu();
        loginPage.pressSignInBtn();
        loginPage.pressEmail();
        loginPage.enterEmail(EMAIL);
        loginPage.pressPassword();
        loginPage.enterPassword("invalidpassword");
        loginPage.pressLoginBtn();

        String actualErrorPopup = loginPage.getErrorPopup();
        String expectedErrorPopup = "Wrong login credentials.";
        System.out.println("Actual error text: " + actualErrorPopup + "\n" + "Expected error text: " + expectedErrorPopup);

        Assert.assertEquals(actualErrorPopup, expectedErrorPopup);
        loginPage.pressClose();
    }
    @Test
    public void validAccount() {
        loginPage.pressMenu();
        loginPage.pressSignInBtn();
        loginPage.pressEmail();
        loginPage.enterEmail(EMAIL);
        loginPage.pressPassword();
        loginPage.enterPassword(PASSWORD);
        homepage = loginPage.pressLoginBtn();

        String actualPageTitle = homepage.getTitle();
        String expectedPageTitle = "My home";
        System.out.println("Actual title: " + actualPageTitle + "\n" + "Expected page title: " + expectedPageTitle);

        Assert.assertEquals(actualPageTitle, expectedPageTitle);
    }


}
