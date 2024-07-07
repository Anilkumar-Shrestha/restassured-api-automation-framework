package com.api.listeners;

import com.api.utils.PropertiesManager;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer {

  private final int maxRetry = Integer.parseInt(PropertiesManager.getProperty("retry_count"));
  private int count = 0;

  @Override
  public boolean retry(ITestResult result) {
    boolean value = false;
    if (Boolean.parseBoolean(PropertiesManager.getProperty("retry_failed_tests"))) {
      value = count < maxRetry;
      count++;
    }
    return value;
  }
}
