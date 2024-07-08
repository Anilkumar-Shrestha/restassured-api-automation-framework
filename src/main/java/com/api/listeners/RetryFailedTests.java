package com.api.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.api.TestBase.testConfig;

public class RetryFailedTests implements IRetryAnalyzer {

  private final int maxRetry = testConfig.retryCount();
  private int count = 0;

  @Override
  public boolean retry(ITestResult result) {
    boolean value = false;
    if (testConfig.retryFailedTests()) {
      value = count < maxRetry;
      count++;
    }
    return value;
  }
}
