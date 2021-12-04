package io.swagger.petstore;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetCreationTest {
    String BaseUrl ="https://petstore.swagger.io/v2";
    String payload ="{\n" +
            "  \"id\": 9965,\n" +
            "  \"category\": {\n" +
            "    \"id\": 10,\n" +
            "    \"name\": \"Cats\"\n" +
            "  },\n" +
            "  \"name\": \"Somadasa\",\n" +
            "  \"photoUrls\": [\n" +
            "    \"string\"\n" +
            "  ],\n" +
            "  \"tags\": [\n" +
            "    {\n" +
            "      \"id\": 0,\n" +
            "      \"name\": \"string\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"available\"\n" +
            "}";

            @Test
           public void testCreateNewPet(){
             given().log().headers()
                     .header("accept","application/json")
                     .header("Content-Type","application/json")
                     .body(payload)
                     .when()
                     .post(BaseUrl+"/pet")
                     .then().log().all()
                     .statusCode(200)
                     .body(
                             "id",equalTo(9965),
                             "name",equalTo("Somadasa"),
                             "category.id",equalTo(10),
                             "category.name",equalTo("Cats")
                     );

            }

    @Test (dependsOnMethods = {"testCreateNewPet"})
    public void testGetPetDetails() {
      given()
              .header("accept","application/json")
              .when()
              .get(BaseUrl+"/pet/9965")
              .then()
              .statusCode(200)
              .body(
                      "id",equalTo(9965),
                      "name",equalTo("Somadasa"),
                      "category.id",equalTo(10),
                      "category.name",equalTo("Cats")
              );
    }
}
