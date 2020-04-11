package com.automation.tests;

import com.automation.pages.Activities.CalendarEvents;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests extends TestBase{

    @Test
    public void test_01(){
        extentTest = extentReports.createTest("first test");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEvents a = new CalendarEvents();
        a.navigateTo("Activities","Calendar Events");
        a.hoverOverThreeDots();
        Assert.assertTrue(a.isIconVisible("View"));
        Assert.assertTrue(a.isIconVisible("Edit"));
        Assert.assertTrue(a.isIconVisible("Delete"));
        extentTest.pass("first test pass!!");
    }

    @Test
    public void test_02(){
        extentTest = extentReports.createTest("second test");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEvents a = new CalendarEvents();
        a.navigateTo("Activities","Calendar Events");
        a.deselectAllOptionsInGrid();
        Assert.assertEquals(a.getTableHeaderName(),"TITLE");
        extentTest.pass("first test pass!!");
    }

    @Test
    public void test_03(){

    }
}