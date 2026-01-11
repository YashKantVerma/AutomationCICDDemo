package com.qa.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public LoginPage(WebDriver driver , WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver , this);
	}
	//declaring the WebElements
	
	@FindBy(id = "userEmail")
	WebElement loginid;
	
	@FindBy(id = "userPassword")
	WebElement loginpassword;
	
	@FindBy(id = "login")
	WebElement loginbutton;
	
	@FindBy(css = ".invalid-feedback")
	WebElement emailwarning;
	
	@FindBy(css = ".invalid-feedback")
	WebElement passwordwarning;
	
	//accessing loginpage url
	public LoginPage openUrl(String webUrl) {
		driver.get(webUrl);
		return this;
	}
	
	//declaring the actions for login page
	public DashboardPage loginpageActions(String lid , String lpassword) {
		loginid.sendKeys(lid);
		loginpassword.sendKeys(lpassword);
		loginbutton.click();
		System.out.println("Successfully Login!");
		
		// return the next page object
		return new DashboardPage(driver, wait);
	}
	
	public String errorMessage() {
	    String message = "";
	    By toastLocator = By.cssSelector("#toast-container");

	    try {
	        // Wait for toast to appear and be visible
	        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));

	        // Capture the text immediately
	        Thread.sleep(200); // wait for animation to complete
	        message = toast.getText();
	        System.out.println("Captured message: " + message);

	        // Wait for it to disappear (optional, avoids flaky future steps)
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
	    } 
	    catch (Exception e) {
	        System.out.println("Error message not found or disappeared too quickly.");
	    }

	    return message;
	}
	
	public String invalidEmailWarning() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".invalid-feedback")));
		String message = emailwarning.getText();
		return message;
	}
	
	public String requiredEmailWarning() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".invalid-feedback")));
		String message = emailwarning.getText();
		return message;
	}
	
	public String requiredPasswordWarning() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".invalid-feedback")));
		String message = passwordwarning.getText();
		return message;
	}
}


/*In Java, the keyword this refers to the current object —
that is, the object on which the method is being called.

inside openUrl(),
this refers to the same object as lp.

So, if you return this;, you are literally returning the same LoginPage object back to the caller.

By returning this, you allow method chaining —
that means you can call multiple methods on the same object in a single line.

openUrl() returns the same LoginPage object,
so the compiler allows you to call another method (loginpageActions()) immediately after it.

This is known as a fluent interface or method chaining.

But remember: if the element disappears from the DOM, even @FindBy WebElements can become stale, 
so the first approach (using By locator) is more reliable for transient toast messages.
[
@FindBy(css = "#toast-container")
WebElement errormessage;

public String errorMessage() {
    try {
        wait.until(ExpectedConditions.visibilityOf(errormessage));
        return errormessage.getText();
    } catch (Exception e) {
        System.out.println("Error message not found or disappeared too quickly.");
        return "";
    }
}

]

*/
