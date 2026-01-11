package com.qa.listeners;

import java.io.IOException;
import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.framework.utils.ScreenshotUtil;
import com.qa.reports.ExtentReporterNG;

public class Listeners implements ITestListener {

	private static ExtentReports extent = ExtentReporterNG.getReportObject();

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (extentTest.get() != null) {
            extentTest.get().log(Status.PASS, "Test Passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

    	if (extentTest.get() == null) {
            ExtentTest test =
                extent.createTest(result.getMethod().getMethodName());
            extentTest.set(test);
        }

        extentTest.get().log(Status.FAIL, result.getThrowable());

        WebDriver driver = null;

        try {
            Object testInstance = result.getInstance();
            Class<?> testClass = testInstance.getClass();

            while (testClass != null) {
                try {
                    Field driverField = testClass.getDeclaredField("driver");
                    driverField.setAccessible(true);
                    driver = (WebDriver) driverField.get(testInstance);
                    break;
                } catch (NoSuchFieldException e) {
                    testClass = testClass.getSuperclass();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (driver != null) {
            try {
                String path = ScreenshotUtil.captureScreenshot(
                        result.getMethod().getMethodName(), driver);
                extentTest.get().addScreenCaptureFromPath(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
