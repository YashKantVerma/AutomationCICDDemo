package com.qa.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ThanksPage {
	

	WebDriver driver;
    WebDriverWait wait;
    
    public ThanksPage(WebDriver driver , WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver , this);
	}
    
    @FindBy(css = ".hero-primary")
    WebElement confirmationmessage;
    
    @FindBy(css = "[routerlink*='myorders']")
    WebElement ordersButton;
    
    public String getConfirmationMessage() {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
    	return confirmationmessage.getText();
    }
    

}
