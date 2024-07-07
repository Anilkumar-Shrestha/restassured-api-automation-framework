package com.api.putRequests;

import com.api.TestBase;
import com.api.applications.authenticate.AuthToken;
import com.api.models.builders.RequestBuilder;
import com.api.models.pojo.booking.BookingBody;
import com.api.models.pojo.booking.BookingDates;
import com.api.utils.faker.FakerUtils;
import com.api.utils.reporter.ExtentLogger;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public final class UpdateBooking extends TestBase {

    private RequestSpecification requestSpecification;
    private String token;
    private int bookingId = 10;

    @BeforeTest
    public void beforeTest() {
        requestSpecification = RequestBuilder.buildRequest();
        token = AuthToken.get();
    }

    @Test
    public void testUpdateBooking() {

        BookingBody putBody = BookingBody.builder()
                .setFirstname(FakerUtils.generateFirstname())
                .setLastname(FakerUtils.generateLastname())
                .setTotalprice(FakerUtils.generateNumber(10, 200))
                .setDepositpaid(true)
                .setBookingdates(BookingDates.builder().setCheckin("2025-11-01").setCheckout("2026-12-01").build())
                .setAdditionalneeds("Lunch")
                .build();

        RequestSpecification rs = requestSpecification.accept(ContentType.JSON).cookie("token", token).body(putBody);
        Response response = rs
                .put(TestBase.baseSetup.getAppApiUrl() + "/booking/" + bookingId);
        response.prettyPrint();

        ExtentLogger.logRequest(rs);
        ExtentLogger.logResponse(response.prettyPrint());
        if (response.statusCode() != 200) {
            ExtentLogger.logFailureDetails(response.statusCode() + " actual status code is not equal to 200");
        } else {
            ExtentLogger.pass("API Status code is 200");
        }
        Assert.assertEquals(response.statusCode(), 200);

    }
}
