package com.automation.pages.Activities;

import com.automation.pages.PageBase;
import com.automation.utilities.BrowserUtilities;
import com.automation.utilities.DateTimeUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

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

    @FindBy(xpath = "//a[@class='btn-success btn dropdown-toggle']//span")
    private WebElement expandOfSaveAndClose;

    @FindBy(xpath = "//a[@title='Cancel']")
    private WebElement cancelBtn;

    @FindBy(xpath = "(//input[@placeholder ='time'])[1]")
    private WebElement time1;

    @FindBy(xpath = "(//input[@placeholder ='time'])[2]")
    private WebElement time2;

    @FindBy(name = "oro_calendar_event_form[allDay]")
    public WebElement allDay;

    @FindBy(xpath = "(//input[@placeholder='Choose a date'])[1]")
    private WebElement startDate;

    @FindBy(xpath = "(//input[@placeholder='Choose a date'])[2]")
    private WebElement endDate;

    @FindBy(xpath = "//input[@data-name=\"recurrence-repeat\"]")
    public WebElement repeatBtn;

    @FindBy(xpath = "//label[@class='fields-row']//input[@type='radio'and @checked='checked']")
    public WebElement repeatEveryRadio;

    @FindBy(xpath = "//span[contains(text(),'Never')]/..//input")
    public WebElement endsRadio;

    @FindBy(xpath = "//div[@class=\"control-group recurrence-summary alert-info\"]")
    public WebElement messageUnderSummary;

    @FindBy(xpath = "//span[contains(text(),'After')]/..//input[@type='radio']")
    public WebElement afterRadio;

    @FindBy(xpath = "//span[contains(text(),'After')]/..//input[@type='text']")
    public WebElement afterText;

    @FindBy(xpath = "//span[text()='M']/..//input")
    public WebElement Monday;

    @FindBy(xpath = "//span[text()='F']/..//input")
    public WebElement Friday;

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

    public void clickOnCreateCalendarEventBtn(){
        createCalendarBtn.click();
    }

    public void getNameOfTheDropdown(){
        BrowserUtilities.waitForPageToLoad(10);
        BrowserUtilities.wait(5);
        driver.findElement(By.xpath("(//div[@class='controls']//input[@type='text'])[3]")).sendKeys("sadasad");
        BrowserUtilities.wait(1);
        List<WebElement> allDropdownElements = driver.findElements(By.xpath("//li//button[@type='submit']"));
//        return BrowserUtilities.getTextFromWebElements(allDropdownElements);
        allDropdownElements.forEach(each-> System.out.println(each.getText().trim()));
    }

    public void clickOnCancelBtn(){
        BrowserUtilities.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn));
        cancelBtn.click();
    }
    public boolean isAllCalendarEventsDisplayed(){
        return driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).isDisplayed();
    }

    public long getTimeDifferences(){
        BrowserUtilities.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(time1));
        String time1Full = time1.getAttribute("value");
        String time2Full = time2.getAttribute("value");
        return DateTimeUtilities.getTimeDifference(time1Full, time2Full, "h:m a");
    }

    public void selectTime(String time){
        BrowserUtilities.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(time1));
        time1.clear();
        time1.sendKeys(time, Keys.ENTER);
    }
    public String getTime1(){
        return time1.getAttribute("value");
    }
    public String getTime2(){
        return time2.getAttribute("value");
    }

    public void clickOnAllDayEvent(){
        BrowserUtilities.waitForPageToLoad(10);
        BrowserUtilities.wait(5);
        allDay.click();
        BrowserUtilities.wait(2);
    }

    public boolean isTimesAreDisplayed(){
        return time1.isDisplayed()&&time2.isDisplayed();
    }

    public boolean isDatesAreDisplayed(){
        return  startDate.isDisplayed()&&endDate.isDisplayed();
    }

    public void clickOnRepeatBtn(){
        BrowserUtilities.waitForPageToLoad(10);
        BrowserUtilities.wait(3);
        repeatBtn.click();
        BrowserUtilities.wait(2);
    }

    public String getDefaultOption(){
        Select a = new Select(driver.findElement(By.xpath("//select[@data-name=\"recurrence-repeats\"]")));
        return a.getFirstSelectedOption().getText();
    }

    public List<String> getAllDropdown(){
        Select a = new Select(driver.findElement(By.xpath("//select[@data-name=\"recurrence-repeats\"]")));
        return BrowserUtilities.getTextFromWebElements(a.getOptions());
    }

    public void clickAfterAndEnterValue(String str){
        afterRadio.click();
        afterText.sendKeys(str,Keys.ENTER);
    }







}
