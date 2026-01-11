package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.framework.base.BaseClass;
import com.qa.framework.pages.LoginPage;


public class LoginTest extends BaseClass{
	
	@Test
	public void invalidLoginTest() {
		LoginPage lp = new LoginPage(driver , wait);  // Initialize LoginPage using existing driver/wait from BaseClass
		lp.openUrl(bm.getWebUrl()); // Open URL
		lp.loginpageActions("yashkantverma1@gmail.com", "wrongpassword");// Perform invalid login
		Assert.assertEquals(lp.errorMessage(), "Incorrect email or password.");
	}
	
	@Test
	public void invalidLoginIDFormat() {
		LoginPage lp = new LoginPage(driver , wait);  // Initialize LoginPage using existing driver/wait from BaseClass
		lp.openUrl(bm.getWebUrl()); // Open URL
		lp.loginpageActions("yashkantverma1", "Hello@123");// Perform invalid login
		Assert.assertEquals(lp.invalidEmailWarning(), "*Enter Valid Email");
	}
	
	@Test
	public void requireLoginID() {
		LoginPage lp = new LoginPage(driver , wait);  // Initialize LoginPage using existing driver/wait from BaseClass
		lp.openUrl(bm.getWebUrl()); // Open URL
		lp.loginpageActions("", "Hello@123");// Perform invalid login
		Assert.assertEquals(lp.requiredEmailWarning(), "*Email is required");
	}
	
	@Test
	public void requirePassword() {
		LoginPage lp = new LoginPage(driver , wait);  // Initialize LoginPage using existing driver/wait from BaseClass
		lp.openUrl(bm.getWebUrl()); // Open URL
		lp.loginpageActions("yashkantverma1@gmail.com", "111111");// Perform invalid login
		Assert.assertEquals(lp.requiredPasswordWarning(), "*Password is required");
	}
}
