package com.automation.pages;

import com.automation.utilities.BrowserUtilities;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageBase {

    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver,15);

    @FindBy(xpath = "//h1[@class='logo logo-text']")
    protected WebElement tabName;

    @FindBy(css = "#user-menu > a")
    protected WebElement currentUser;



    public PageBase(){
        PageFactory.initElements(driver,this);
    }

    /**
     * this method will return name of the blue tab
     * @return name of tab
     */
    public String getTabName(){
        return tabName.getText();
    }

    /**
     * this method will return current user's name
     * @return user name
     */
    public String getCurrentUserName(){
        BrowserUtilities.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(currentUser));
        return currentUser.getText().trim();
    }

    /**
     * Method for vytrack navigation. Provide tab name and module name to navigate
     * @param tabName like Dashboards, Fleet or Customers
     * @param moduleName like Vehicles, Vehicles Odometer and Vehicle Costs
     */
    public void navigateTo(String tabName, String moduleName){
        String tabNameXpath = "//span[@class='title title-level-1' and contains(text(),'"+tabName+"')]";
        String moduleNameXpath ="//span[@class='title title-level-2' and text() = '"+moduleName+"']";
        WebElement tabElements = driver.findElement(By.xpath(tabNameXpath));
        WebElement moduleElement = driver.findElement(By.xpath(moduleNameXpath));
        Actions actions = new Actions(driver);
        BrowserUtilities.wait(3);
        actions.moveToElement(tabElements).pause(2000).click(moduleElement).build().perform();
        BrowserUtilities.wait(4);


    }





}
