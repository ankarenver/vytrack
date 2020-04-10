package com.automation.pages;

import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "prependedInput")
    private WebElement username;

    @FindBy(id = "prependedInput2")
    private WebElement password;

    @FindBy(id = "_submit")
    private WebElement login;

    @FindBy(linkText = "Forgot your password?")
    private WebElement forgetPassword;

    @FindBy(css = "[class='alert alert-error']")
    private WebElement warningMessage;


    public LoginPage(){
        // to connect our WebDriver, page class and page factory
        // PageFactory - used to use @FindBy annotations
        // PageFactory - helps to find elements easier
        PageFactory.initElements(Driver.getDriver(),this);
    }

    /**
     * method to login, version #1
     * login as a specific user
     * @param username
     * @param password
     */
    public void login(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password, Keys.ENTER);
    }

    /**
     * method to login version #2
     * login as a default user
     * Credentials will be retrieved from configuration.properties file
     */
    public void login(){
        this.username.sendKeys(ConfigurationReader.getProperty("store_manager"));
        this.password.sendKeys(ConfigurationReader.getProperty("password"),Keys.ENTER);
    }

    public String getWarningMessageText(){
        return warningMessage.getText();
    }

}
