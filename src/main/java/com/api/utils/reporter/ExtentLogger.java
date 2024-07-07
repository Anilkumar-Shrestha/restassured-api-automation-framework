package com.api.utils.reporter;


import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Header;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentLogger {

    public static void pass(String message) {
        ExtentManager.getExtentTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
    }

    public static void logFailureDetails(String message) {
        ExtentManager.getExtentTest().fail(message);
    }

    public static void skip(String message) {
        ExtentManager.getExtentTest().skip(message);
    }

    public static void logInfo(Markup markup) {
        ExtentManager.getExtentTest().log(Status.INFO, markup);
    }

    // Overloaded method
    public static void logInfo(String message) {
        ExtentManager.getExtentTest().info(message);
    }

    public static void logResponse(String response) {
        logInfo(MarkupHelper.createLabel("API RESPONSE", ExtentColor.ORANGE));
        ExtentManager.getExtentTest().pass(MarkupHelper.createCodeBlock(response, CodeLanguage.JSON));
    }

    public static void logRequest(RequestSpecification requestSpecification) {
        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);
        logInfo(MarkupHelper.createLabel("API REQUEST SPECIFICATION", ExtentColor.ORANGE));
        for (Header header : query.getHeaders()) {
                logInfo(header.getName() + ":" + header.getValue());
        }
        if(!Objects.isNull(query.getBody())) {
            ExtentManager.getExtentTest().pass(MarkupHelper.createCodeBlock(query.getBody().toString(),
//        ExtentManager.getExtentTest().pass(MarkupHelper.createCodeBlock(String.valueOf(query.getBody()),
                    CodeLanguage.JSON));
        }
    }

    public static void logRequestInReport(String request) {
        logInfo(MarkupHelper.createLabel("API REQUEST", ExtentColor.ORANGE));
        logInfo(MarkupHelper.createCodeBlock(request));
    }

    public static void logResponseInReport(String label, String response) {
        logInfo(MarkupHelper.createLabel(label, ExtentColor.ORANGE));
        logInfo(MarkupHelper.createCodeBlock(response));
    }

    public static void logHeaders(List<Header> headerList) {
        String[][] headers = headerList.stream().map(header -> new String[]{header.getName(), header.getValue()})
                .toArray(String[][]::new);
        ExtentManager.getExtentTest().info(MarkupHelper.createTable(headers));
    }

}
