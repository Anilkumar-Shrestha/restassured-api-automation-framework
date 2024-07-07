package com.api.deleteRequests;

import com.api.TestBase;
import com.api.applications.authenticate.AuthToken;
import com.api.models.builders.RequestBuilder;
import com.api.utils.reporter.ExtentLogger;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public final class DeleteBooking extends TestBase {
    String token;

    @BeforeTest
    public void beforeTest() {
        token = AuthToken.get();
    }

    @Test
    public void testDeleteBookingByCookie() {

        int bookingId = 3;
        RequestSpecification requestSpecification = RequestBuilder.buildRequest().header("Cookie", "token=" + token);
        Response response = requestSpecification
                .log().all()
                .delete(TestBase.baseSetup.getAppApiUrl() + "/booking/" + bookingId);
        response.prettyPrint();

        ExtentLogger.logRequest(requestSpecification);
        ExtentLogger.logResponse(response.prettyPrint());
        if(response.statusCode()!=201){
            ExtentLogger.logFailureDetails(response.statusCode()+" actual status code is not equal to 201");
        } else {
            ExtentLogger.pass("API Status code is 201");
        }
        Assert.assertEquals(response.statusCode(),201);
    }


}
