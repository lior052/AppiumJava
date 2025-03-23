package org.appium.framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

    private static ExtentReports report;


    public static ExtentReports configReport() {
        //extent report
        ExtentSparkReporter reporter = new ExtentSparkReporter(
                System.getProperty("user.dir")+"/src/test/java/org/appium/reports");
        reporter.config().setReportName("My report");
        reporter.config().setDocumentTitle("Tests");

        report = new ExtentReports();
        report.attachReporter(reporter);
        report.setSystemInfo("Tester", "Lior");

        return report;
    }
}
