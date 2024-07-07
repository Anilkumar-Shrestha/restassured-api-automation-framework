package com.api.applications.authenticate;

import com.api.TestBase;
import com.api.models.builders.RequestBuilder;
import com.api.models.pojo.AuthBody;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.api.utils.helper.SetCredentialsForSuite.user1Email;
import static com.api.utils.helper.SetCredentialsForSuite.user1Password;

public final class AuthToken {

    private AuthToken() {
    }


    public static String get() {
        return  get (user1Email, user1Password);
    }

    public static String get( String user1Email, String user1Password) {
        AuthBody authBody = AuthBody.builder().setUsername(user1Email).setPassword(user1Password).build();
        RequestSpecification requestSpecification = RequestBuilder.buildRequest().body(authBody);
        Response response = requestSpecification
                .post(TestBase.baseSetup.getAppApiUrl() + "/auth");
        return response.jsonPath().get("token");
    }


}
