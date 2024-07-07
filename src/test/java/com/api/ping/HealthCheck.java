package com.api.ping;

import com.api.TestBase;
import com.api.models.builders.RequestBuilder;
import com.api.utils.reporter.ExtentLogger;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public final class HealthCheck extends TestBase {

    RequestSpecification requestSpecification;

    @BeforeTest
    public void beforeTest(){
        requestSpecification = RequestBuilder.buildRequest();
    }

    @Test
    public void testHealthCheck(){
        Response response = requestSpecification
                .get(TestBase.baseSetup.getAppApiUrl()+"/ping");
        response.prettyPrint();

        ExtentLogger.logRequestInReport(TestBase.baseSetup.getAppApiUrl()+"/ping");
        ExtentLogger.logRequest(requestSpecification);
        ExtentLogger.logResponseInReport("API RESPONSE", response.prettyPrint());
        if (response.statusCode() != 201) {
            ExtentLogger.logFailureDetails(response.statusCode() + " actual status code is not equal to 201");
        } else {
            ExtentLogger.pass("API Status code is 201");
        }
        Assert.assertEquals(response.statusCode(), 201);
    }
}
