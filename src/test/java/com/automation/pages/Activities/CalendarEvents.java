package com.automation.pages.Activities;

import com.automation.pages.PageBase;
import com.automation.utilities.BrowserUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CalendarEvents extends PageBase {


    private Actions actions = new Actions(driver);
    @FindBy(xpath = "//td[@class='string-cell grid-cell grid-body-cell grid-body-cell-title' and contains(text(),\"Testers Meeting\")]/..")
    private WebElement TestersMeetingAll;

    @FindBy(xpath = "//td[@class='string-cell grid-cell grid-body-cell grid-body-cell-title' and contains(text(),\"Testers Meeting\")]/..//td[@class='action-cell grid-cell grid-body-cell']")
    private WebElement threeDot;

    @FindBy(partialLinkText = "Create Calendar")
    private WebElement createCalendarBtn;

    @FindBy(xpath = "//a[@href='#']//i[@class='fa-cog hide-text']")
    private WebElement gridBtn;

    @FindBy(xpath = "//thead[@class='grid-header']//th[2]")
    private WebElement tableHeaderName;


    public void hoverOverThreeDots(){
        BrowserUtilities.waitForPageToLoad(10);
        actions.moveToElement(TestersMeetingAll).pause(2000).moveToElement(threeDot).build().perform();
    }

    public Boolean isIconVisible(String icon){
        return driver.findElement(By.xpath("//li[@class='launcher-item']//a[@title='"+icon+"']")).isDisplayed();
    }

    public void deselectAllOptionsInGrid(){
        BrowserUtilities.waitForPageToLoad(10);
        gridBtn.click();

        List<WebElement> allNames = driver.findElements(By.xpath("//tbody[@class='ui-sortable']//tr"));
        List<WebElement> allClick = driver.findElements(By.xpath("//tbody[@class='ui-sortable']//tr//td[3]//input"));

        for (int i = 0; i <allNames.size() ; i++) {
            if ((!allNames.get(i).getText().equals("Title"))&&allClick.get(i).isSelected()){
                allClick.get(i).click(); // un_click it
            }
        }
    }

    public String getTableHeaderName(){
        return tableHeaderName.getText();
    }



}
