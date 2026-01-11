package com.qa.framework.base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.framework.driver.BrowserManager;

public class BaseClass {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected BrowserManager bm;

    @BeforeMethod
    public void setUp() throws IOException {
        bm = new BrowserManager();
        driver = bm.initializeDriver();
        wait = bm.getWait();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
