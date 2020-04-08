package com.automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
    private static WebDriver driver;

    private Driver(){}


    public static WebDriver getDriver(){
        if(driver==null){
            //specify browser type in configuration.properties file
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().version("79").setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "chromeheadless":
                    //to run chrome without interface (headless mode)
                    WebDriverManager.chromedriver().version("79").setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        return driver;
    }

    public static WebDriver getDriver(String driverName){
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("windows")&&driverName.equalsIgnoreCase("safari")  ||  os.contains("mac")&&driverName.equalsIgnoreCase("edg")){
            return null;
        }
        switch (driverName){
            case "chrome":{
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case "firefox":{
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            case "edge":{
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
            case "safari":{
                return new SafariDriver();
            }
            default:{
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }

        }

    }


    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
