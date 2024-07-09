package com.api.postRequests;

import com.api.TestBase;
import com.api.constants.FrameworkConstants;
import com.api.models.builders.RequestBuilder;
import com.api.models.pojo.booking.BookingBody;
import com.api.models.pojo.booking.BookingDates;
import com.api.models.pojo.booking.ResponseBookingCreated;
import com.api.utils.faker.FakerUtils;
import com.api.utils.reporter.ExtentLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public final class CreateBooking extends TestBase {

    RequestSpecification requestSpecification;
    Response response;
    BookingBody postBody;

    @BeforeTest
    public void beforeTest() {
        requestSpecification = RequestBuilder.buildRequest();
    }

    @Test (priority = 1)
    public  void testCreateBooking() {

        File schemaJsonFile = new File(FrameworkConstants.JSON_SCHEMA_PATH + File.separator + "createBookingJsonSchema.json");
//        String postBody = """
//                {
//                    "firstname" : "Jim",
//                    "lastname" : "Brown",
//                    "totalprice" : 111,
//                    "depositpaid" : true,
//                    "bookingdates" : {
//                        "checkin" : "2018-01-01",
//                        "checkout" : "2019-01-01"
//                    },
//                    "additionalneeds" : "Breakfast"
//                }
//                """;
        postBody = BookingBody.builder()
                .setFirstname(FakerUtils.generateFirstname())
                .setLastname(FakerUtils.generateLastname())
                .setTotalprice(FakerUtils.generateNumber(10, 200))
                .setDepositpaid(true)
                .setBookingdates(BookingDates.builder().setCheckin("2024-11-01").setCheckout("2025-12-01").build())
                .setAdditionalneeds("Breakfast")
                .build();

        RequestSpecification rs = requestSpecification.body(postBody);
        response = rs
                .post(TestBase.baseSetup.getAppApiUrl() + "/booking")
                .then()
//                .body(matchesJsonSchema(schemaJsonFile))
                .extract().response();
        response.prettyPrint();

        ExtentLogger.logRequest(rs);
        ExtentLogger.logResponse(response.prettyPrint());


        if (response.statusCode() != 200) {
            ExtentLogger.logFailureDetails(response.statusCode() + " actual status code is not equal to 200");
        } else {
            ExtentLogger.pass("API Status code is 200");
        }
        Assert.assertEquals(response.statusCode(), 200);


        // json schema validation
        try {
            response.then().body(matchesJsonSchema(schemaJsonFile)).extract().response();
            ExtentLogger.pass("Schema validation passed");
        } catch (AssertionError e) {
            ExtentLogger.logFailureDetails("Schema validation failed: " + e.getMessage());
            Assert.fail("Schema validation failed: " + e.getMessage());

        }

    }


    @Test (priority = 2)
    public void testJsonResponse() throws IOException {
       if( response.jsonPath().get("booking.firstname").equals(postBody.getFirstname()))
       {
           ExtentLogger.pass(response.jsonPath().get("booking.firstname") + " response firstname matches with the request first name");
       } else {
           ExtentLogger.logFailureDetails(response.jsonPath().get("booking.firstname") + " response firstname does not matches with the request first name");
           Assert.fail();
       }



    }

    @Test (priority = 3)
    public void testJsonResponseUsingDeserializeResponseUsingPojo() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseBookingCreated responseBookingCreated;
        responseBookingCreated = objectMapper.readValue(response.getBody().asString(),ResponseBookingCreated.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(postBody.getFirstname(),responseBookingCreated.getBooking().getFirstname());
        softAssert.assertEquals(postBody.getLastname(),responseBookingCreated.getBooking().getLastname());
        softAssert.assertEquals(postBody.getTotalprice(),responseBookingCreated.getBooking().getTotalprice());
        softAssert.assertEquals(postBody.getAdditionalneeds(),responseBookingCreated.getBooking().getAdditionalneeds());
        softAssert.assertEquals(postBody.getBookingdates(),responseBookingCreated.getBooking().getBookingdates());
        softAssert.assertAll();
    }




}
