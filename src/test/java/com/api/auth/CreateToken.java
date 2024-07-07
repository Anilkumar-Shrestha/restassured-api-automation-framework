package com.api.auth;

import com.api.TestBase;
import com.api.models.BaseSetup;
import com.api.models.builders.RequestBuilder;
import com.api.models.pojo.AuthBody;
import com.api.utils.helper.SetCredentialsForSuite;
import com.api.utils.reporter.ExtentLogger;
import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.api.utils.helper.SetCredentialsForSuite.user1Email;
import static com.api.utils.helper.SetCredentialsForSuite.user1Password;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateToken extends TestBase {


    private RequestSpecification rs;
    @BeforeTest
    public void beforeTest(){
        rs =  RequestBuilder.buildRequest(); // this is added before test since we don't to have repeat in each test method
    }

    @Test(description = "Validate Basic Auth and token")
    public void testBasicAuth(){
        AuthBody authBody = AuthBody.builder().setUsername(user1Email).setPassword(user1Password).build();
        RequestSpecification requestSpecification =  rs.body(authBody);
        Response response =requestSpecification
                .post("/auth")
                .then().extract().response();

        ExtentLogger.logRequest(requestSpecification);
        ExtentLogger.logResponse(response.prettyPrint());

        if(response.statusCode()!=200){
            ExtentLogger.logFailureDetails(response.statusCode()+" actual status code is not equal to 200");
        } else {
            ExtentLogger.pass("API Status code is 200");
        }
        Assert.assertEquals(response.statusCode(),200);
    }

}
