package com.qa.framework.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserManager {
	
	private WebDriver driver;
    private WebDriverWait wait;
    private Properties prop;
    
    //Load properties file once when object is created
    public BrowserManager() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("D:\\Java_Workspace_Udemy_TestNG_Practice\\src\\test\\resources\\GlobalData.properties");
        prop.load(fis);
    }
    
    //Launch browser based on property
    public WebDriver initializeDriver() {
        String browserName = prop.getProperty("browser");
        System.out.println("Browser selected: " + browserName);

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Invalid browser name in GlobalData.properties");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        return driver;
    }
    
    public WebDriverWait getWait() {
        return wait;
    }

    public String getWebUrl() {
        return prop.getProperty("url");
    }

}

