package com.qa.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
	
	WebDriver driver;
    WebDriverWait wait;
    
    public CheckoutPage(WebDriver driver , WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver , this);
	}
    
    @FindBy(css = "[placeholder = 'Select Country']")
    WebElement country;
    
    @FindBy(xpath = "//button[contains(@class , 'ta-item')][2]")
    WebElement selectcountry;
    
    @FindBy(css = ".action__submit")
    WebElement placeorder;
    
    public CheckoutPage selectCountry(String countryname ) {
    	
    	Actions a = new Actions(driver);
    	a.sendKeys(country, countryname).build().perform();
    	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
    	selectcountry.click();
    	return this;
    }
    
    public ThanksPage submitOrder() {
    	placeorder.click();
    	// return the next page object
    	return new  ThanksPage(driver , wait);
    	
    }
}
