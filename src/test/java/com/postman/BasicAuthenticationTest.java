package com.postman;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicAuthenticationTest {
    String baseEndPoint ="https://postman-echo.com/basic-auth\n";
    String userName="postman";
    String passWord ="password";

    @Test
    public void testBasicAuth() {
        given()
                .header("accept","application/jason")
                .auth().preemptive().basic(userName,passWord)
                .get(baseEndPoint)
                .then().log().body()
                .statusCode(200)
                .body(
                        "authenticated",equalTo(true)

                );


    }
}
