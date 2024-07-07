package com.api.patchRequests;

import com.api.TestBase;
import com.api.applications.authenticate.AuthToken;
import com.api.models.builders.RequestBuilder;
import com.api.utils.faker.FakerUtils;
import com.api.utils.reporter.ExtentLogger;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public final class PartialUpdateBooking extends TestBase {

    private RequestSpecification requestSpecification ;
    private String token;
    private int bookingId = 15;

    @BeforeTest
    public void beforeTest() {
        requestSpecification = RequestBuilder.buildRequest();
        token = AuthToken.get();
    }


    @Test
    public void testPartialUpdateBooking() {

        String putBody = """
                {
                    "firstname" : "%s",
                    "lastname" : "%s"
                }
                """;
        putBody = String.format(putBody, FakerUtils.generateFirstname(), FakerUtils.generateLastname());

        RequestSpecification rs = requestSpecification.body(putBody).cookie("token", token);
        Response response = rs
                .log().all()
                .patch(TestBase.baseSetup.getAppApiUrl() + "/booking/" + bookingId);
        response.prettyPrint();

        ExtentLogger.logRequestInReport(TestBase.baseSetup.getAppApiUrl() + "/booking/" + bookingId);
        ExtentLogger.logRequest(requestSpecification);
        ExtentLogger.logResponse(response.prettyPrint());
        if (response.statusCode() != 200) {
            ExtentLogger.logFailureDetails(response.statusCode() + " actual status code is not equal to 200");
        } else {
            ExtentLogger.pass("API Status code is 200");
        }
        Assert.assertEquals(response.statusCode(), 200);

    }
}
