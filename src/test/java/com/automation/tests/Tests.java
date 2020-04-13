package com.automation.tests;

import com.automation.pages.Activities.CalendarEvents;
import com.automation.pages.LoginPage;
import com.automation.utilities.BrowserUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests extends TestBase{

    @Test
    public void test_01(){
        extentTest = extentReports.createTest("test#1");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEvents a = new CalendarEvents();
        a.navigateTo("Activities","Calendar Events");
        a.hoverOverThreeDots();
        Assert.assertTrue(a.isIconVisible("View"));
        Assert.assertTrue(a.isIconVisible("Edit"));
        Assert.assertTrue(a.isIconVisible("Delete"));
        extentTest.pass("test#1 pass!!");
    }

    @Test
    public void test_02(){
        extentTest = extentReports.createTest("test#2 test");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEvents a = new CalendarEvents();
        a.navigateTo("Activities","Calendar Events");
        a.deselectAllOptionsInGrid();
        Assert.assertEquals(a.getTableHeaderName(),"TITLE");
        extentTest.pass("test#2 pass!!");
    }

    @Test(description = "not done yet")
    public void test_03(){
        extentTest = extentReports.createTest("test#3 test");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEvents a = new CalendarEvents();
        a.navigateTo("Activities","Calendar Events");
        a.clickOnCreateCalendarEventBtn();
        a.getNameOfTheDropdown();
        extentTest.pass("test#3 pass!!");
    }

    @Test
    public void test_04(){
        extentTest = extentReports.createTest("test#4 test");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEvents a = new CalendarEvents();
        a.navigateTo("Activities","Calendar Events");
        a.clickOnCreateCalendarEventBtn();
        a.clickOnCancelBtn();
        Assert.assertTrue(a.isAllCalendarEventsDisplayed());
        extentTest.pass("test#4 pass!!");
    }

    @Test
    public void test_05(){
        extentTest = extentReports.createTest("test#5 test");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEvents a = new CalendarEvents();
        a.navigateTo("Activities","Calendar Events");
        a.clickOnCreateCalendarEventBtn();
        Assert.assertEquals(a.getTimeDifferences(),1);
        extentTest.pass("test#5 pass!!");
    }

    @Test
    public void test_06(){
        extentTest = extentReports.createTest("test#6 test");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEvents a = new CalendarEvents();
        a.navigateTo("Activities","Calendar Events");
        a.clickOnCreateCalendarEventBtn();
        a.selectTime("9:00 PM");
        Assert.assertEquals(a.getTime2(),"10:00 PM");
        extentTest.pass("test#6 pass!!");
    }

    @Test
    public void test_07(){
        extentTest = extentReports.createTest("test#7 test");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEvents a = new CalendarEvents();
        a.navigateTo("Activities","Calendar Events");
        a.clickOnCreateCalendarEventBtn();
        a.clickOnAllDayEvent();
        Assert.assertTrue(a.allDay.isSelected());
        Assert.assertFalse(a.isTimesAreDisplayed());
        Assert.assertTrue(a.isDatesAreDisplayed());
        extentTest.pass("test#7 pass!!");
    }

    @Test
    public void test_08(){
        extentTest = extentReports.createTest("test#8 test");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEvents a = new CalendarEvents();
        a.navigateTo("Activities","Calendar Events");
        a.clickOnCreateCalendarEventBtn();
        a.clickOnRepeatBtn();
        Assert.assertTrue(a.repeatBtn.isSelected());
        Assert.assertEquals(a.getDefaultOption(),"Daily");
        Assert.assertNotEquals(a.getAllDropdown(),null);
        extentTest.pass("test#8 pass!!");
    }

    @Test
    public void test_09(){
        extentTest = extentReports.createTest("test#9");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEvents a = new CalendarEvents();
        a.navigateTo("Activities","Calendar Events");
        a.clickOnCreateCalendarEventBtn();
        a.clickOnRepeatBtn();
        Assert.assertTrue(a.repeatBtn.isSelected());
        Assert.assertTrue(a.repeatEveryRadio.isSelected());
        Assert.assertTrue(a.endsRadio.isSelected());
        Assert.assertTrue(a.messageUnderSummary.getText().contains("Daily every 1 day"));
        extentTest.pass("test#9 pass!!");
    }

    @Test
    public void test_10(){
        extentTest = extentReports.createTest("test#10");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEvents a = new CalendarEvents();
        a.navigateTo("Activities","Calendar Events");
        a.clickOnCreateCalendarEventBtn();
        a.clickOnRepeatBtn();
        a.clickAfterAndEnterValue("10");
        Assert.assertTrue(a.messageUnderSummary.getText().contains("Daily every 1 day, end after 10 occurrences"));
        extentTest.pass("test#10 pass!!");
    }

    @Test
    public void test_11(){
        extentTest = extentReports.createTest("test#11");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEvents a = new CalendarEvents();
        a.navigateTo("Activities","Calendar Events");
        a.clickOnCreateCalendarEventBtn();
        a.clickOnRepeatBtn();
        driver.findElement(By.xpath("//span[contains(text(),'By')]/..//input")).click();
        BrowserUtilities.wait(2);
        driver.findElement(By.xpath("//span[text()='By']/../..//div//input[2]")).sendKeys("Nov 21, 2021", Keys.ENTER);
        Assert.assertTrue(a.messageUnderSummary.getText().contains("Daily every 1 day, end by Nov 21, 2021"));
        extentTest.pass("test#11 pass!!");
    }
    @Test
    public void test_12(){
        extentTest = extentReports.createTest("test#12");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEvents a = new CalendarEvents();
        a.navigateTo("Activities","Calendar Events");
        a.clickOnCreateCalendarEventBtn();
        a.clickOnRepeatBtn();
        Select repeatsDropdown = new Select(driver.findElement(By.xpath("//select[@data-name=\"recurrence-repeats\"]")));
        repeatsDropdown.selectByVisibleText("Weekly");
        a.Monday.click();
        a.Friday.click();
        Assert.assertTrue(a.Monday.isSelected()&&a.Friday.isSelected());
        Assert.assertTrue(a.messageUnderSummary.getText().contains("Weekly every 1 week on Monday, Friday"));
        extentTest.pass("test#12 pass!!");
    }




}
