package com.qa.framework.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	
	WebDriver driver;
    WebDriverWait wait;
    
    public CartPage(WebDriver driver , WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver , this);
	}
    
    @FindBy(css = ".totalRow button")
    WebElement checkoutbutton;
    
    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProductsNames;
    
    public Boolean verifyCartProductDisplay(String productName) {
    	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cartSection h3")));
    	System.out.println("You are on Cart Page!");
    	Boolean match = cartProductsNames.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
    	return match;
    }
    
    public CheckoutPage checkOutPage() {
    	System.out.println("Checking Out from the Cart Page!");
    	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".totalRow button")));
    	checkoutbutton.click();
    	// return the next page object
    	return new CheckoutPage(driver , wait);
    }
}
