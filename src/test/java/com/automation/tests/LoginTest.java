package com.automation.tests;

import com.automation.pages.LoginPage;
import com.automation.utilities.BrowserUtilities;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginTest(){
        extentTest = extentReports.createTest("login test");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        BrowserUtilities.wait(3);
        extentTest.pass("login successfully !");
    }
}
