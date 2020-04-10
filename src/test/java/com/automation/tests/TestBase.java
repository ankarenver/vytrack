package com.automation.tests;

import com.automation.utilities.BrowserUtilities;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public abstract class TestBase {

    protected WebDriver driver;
    protected static ExtentReports extentReports;
    protected static ExtentHtmlReporter extentHtmlReporter;
    protected static ExtentTest extentTest;

    @BeforeTest
    public void beforeTest(){
        extentReports = new ExtentReports();
        String reportPath = "";
        if (System.getProperty("os.name").toLowerCase().contains("win")){
            reportPath = System.getProperty("user.dir") + "\\test-output\\report.html";
        }else {
            reportPath = System.getProperty("user.dir") + "/test-output/report.html";
        }
        extentHtmlReporter = new ExtentHtmlReporter(reportPath);
        extentReports.attachReporter(extentHtmlReporter);
        extentHtmlReporter.config().setReportName("VyTrack");
    }

    @AfterTest
    public void afterTest() {
        extentReports.flush();
    }



    @BeforeMethod
    public void setup(){
        driver = Driver.getDriver();
        driver.get(ConfigurationReader.getProperty("qa2"));
        driver.manage().window().maximize();
        BrowserUtilities.waitForPageToLoad(10);
    }

    @AfterMethod
    public void teardown(ITestResult testResult){
        if(testResult.getStatus()==ITestResult.FAILURE){
            String screenShotLocation = BrowserUtilities.getScreenshot(testResult.getName());
            try {
                extentTest.fail(testResult.getName());
                extentTest.addScreenCaptureFromPath(screenShotLocation);
                extentTest.fail(testResult.getThrowable());
            }catch (IOException e){
                e.printStackTrace();
                throw new RuntimeException("failed to attach screenshot");
            }
        }else if (testResult.getStatus()==ITestResult.SUCCESS){
            extentTest.pass(testResult.getName());
        }else if (testResult.getStatus()==ITestResult.SKIP){
            extentTest.skip(testResult.getName());
        }
        BrowserUtilities.wait(3);
        Driver.closeDriver();
    }



}
