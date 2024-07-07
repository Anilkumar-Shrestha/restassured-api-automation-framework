package com.api.getRequests;

import com.api.TestBase;
import com.api.models.builders.RequestBuilder;
import com.api.utils.reporter.ExtentLogger;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public final class GetBookingIds extends TestBase {

    private RequestSpecification requestSpecification;
    private int bookingId = 10;
    private String firstname = "Sally";
    private String lastname = "Jackson";
    private String checkin = "2024-03-13";
    private String checkout = "2024-05-21";


    @BeforeTest
    public void beforeTest() {
        requestSpecification = RequestBuilder.buildRequest().log().all();
    }

    @Test
    public void testGetBookings() {
        Response response = given().spec(requestSpecification)
                .get(TestBase.baseSetup.getAppApiUrl() + "/booking");
        response.prettyPrint();

        ExtentLogger.logRequestInReport(TestBase.baseSetup.getAppApiUrl() + "/booking");
        ExtentLogger.logRequest(requestSpecification);
        ExtentLogger.logResponse(response.prettyPrint());
        if (response.statusCode() != 200) {
            ExtentLogger.logFailureDetails(response.statusCode() + " actual status code is not equal to 200");
        } else {
            ExtentLogger.pass("API Status code is 200");
        }
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void testGetBookingByIds() {
        Response response = requestSpecification
                .get(TestBase.baseSetup.getAppApiUrl() + "/booking/" + bookingId);
        response.prettyPrint();
        ExtentLogger.logRequest(requestSpecification);
        ExtentLogger.logResponse(response.prettyPrint());
        if (response.statusCode() != 200) {
            ExtentLogger.logFailureDetails(response.statusCode() + " actual status code is not equal to 200");
        } else {
            ExtentLogger.pass("API Status code is 200");
        }
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testGetBookingByName() {
        Response response = requestSpecification
                .queryParam("firstname", firstname)
                .queryParam("lastname", lastname)
                .get(TestBase.baseSetup.getAppApiUrl() + "/booking");
        response.prettyPrint();
        ExtentLogger.logRequest(requestSpecification);
        ExtentLogger.logResponse(response.prettyPrint());
        if (response.statusCode() != 200) {
            ExtentLogger.logFailureDetails(response.statusCode() + " actual status code is not equal to 200");
        } else {
            ExtentLogger.pass("API Status code is 200");
        }
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void testGetBookingByCheckInOutDate() {
        Response response = requestSpecification
                .queryParam("checkin", checkin)
                .queryParam("checkout", checkout)
                .get(TestBase.baseSetup.getAppApiUrl() + "/booking");
        response.prettyPrint();
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
