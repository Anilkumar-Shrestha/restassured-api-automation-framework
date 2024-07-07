package com.api.utils.reporter;

import com.api.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

import static com.api.constants.FrameworkConstants.getExtentReportPath;
import static java.net.InetAddress.getLocalHost;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentReport extends TestBase {

    private static final ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(getExtentReportPath());
    private static ExtentReports extentReports;

    /**
     * This method is to initialize the Extent Report
     */

    static {
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        InetAddress ip = null;
        try {
            ip = getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String hostname = ip.getHostName();
        extentReports.setSystemInfo("Host Name", hostname);
        extentReports.setSystemInfo("Project", "RestAssured-API Automation");
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("os", System.getProperty("os.name"));
        extentSparkReporter.config().setDocumentTitle("HTML Report");
        extentSparkReporter.config().setReportName("API Automation Test");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.viewConfigurer().viewOrder()
                .as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY})
                .apply();
    }


    public static void createTest(String testCaseName) {
        ExtentManager.setExtentTest(extentReports.createTest(testCaseName));
    }

    public static void flushExtentReport() {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
        ExtentManager.unload();
        try {
            Desktop.getDesktop().browse(new File(getExtentReportPath()).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
