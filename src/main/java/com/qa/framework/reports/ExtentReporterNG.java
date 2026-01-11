package com.qa.framework.reports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    private static ExtentReports extent;

    private ExtentReporterNG() {
        // prevent object creation
    }

    public static synchronized ExtentReports getReportObject() {

        if (extent == null) {
            String path = System.getProperty("user.dir") + "/reports/index.html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            reporter.config().setReportName("Website Testing Results");
            reporter.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Yash");
        }

        return extent;
    }
}
