package com.api.constants;

import com.api.TestBase;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.api.TestBase.testConfig;

public final class FrameworkConstants {

    private static final String PROJECT_PATH =  System.getProperty("user.dir");
    public static final String RESOURCES_FOLDER_PATH = PROJECT_PATH + File.separator + "src" + File.separator
            + "test" + File.separator + "resources";

    public static final String FIlE_DOWNLOAD_PATH = PROJECT_PATH + File.separator + "src" +File.separator+"test"+File.separator+"downloads"+File.separator+"common";
    public static final String JSON_SCHEMA_PATH = RESOURCES_FOLDER_PATH + File.separator + "json" + File.separator + "schema";

    private static final String extentReportPath = PROJECT_PATH + File.separator + "extent-test-report";
    public static String getExtentReportPath() {
        return testConfig.overrideReports() ?
                extentReportPath + File.separator + "TestReport_"+TestBase.baseSetup.getEnvironment().toUpperCase()+".html" :
                extentReportPath + File.separator + getCurrentDateTime() + File.separator + "TestReport_"+TestBase.baseSetup.getEnvironment().toUpperCase()+".html";
    }

    private static String getCurrentDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
        return dateTimeFormatter.format(LocalDateTime.now());
    }

}
