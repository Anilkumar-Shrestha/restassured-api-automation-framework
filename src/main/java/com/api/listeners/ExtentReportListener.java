package com.api.listeners;

import com.api.utils.reporter.ExtentLogger;
import com.api.utils.reporter.ExtentReport;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener, ISuiteListener {
    private static final String MESSAGE = "Test - <b>";


    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.flushExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getMethod().getMethodName());
        ExtentLogger.pass(MESSAGE + result.getMethod().getMethodName() + "</b>  is started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(MESSAGE + result.getMethod().getMethodName() + "</b> is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.logFailureDetails(MESSAGE + result.getMethod().getMethodName() + "</b> is failed");
        ExtentLogger.logFailureDetails(result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(MESSAGE + result.getMethod().getMethodName() + "</b> is skipped");
    }
}
