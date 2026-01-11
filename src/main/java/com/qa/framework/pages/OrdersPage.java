package com.qa.framework.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrdersPage {
	
	WebDriver driver;
    WebDriverWait wait;
    
    public OrdersPage(WebDriver driver , WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver , this);
	}
    
        
    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> ordersname; 
    
    
    public Boolean verifyOrderDisplay(String productname) {
    	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".table-responsive h1")));
    	System.out.println("You are on Orders Page!");
    	Boolean match = ordersname.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productname));
    	return match;
    }
}
