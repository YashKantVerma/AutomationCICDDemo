package com.qa.framework.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String captureScreenshot(String testCaseName, WebDriver driver)
            throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destinationPath =
                System.getProperty("user.dir")
                + "/reports/screenshots/"
                + testCaseName
                + ".png";

        File destination = new File(destinationPath);
        FileUtils.copyFile(source, destination);

        return destinationPath;
    }
}
