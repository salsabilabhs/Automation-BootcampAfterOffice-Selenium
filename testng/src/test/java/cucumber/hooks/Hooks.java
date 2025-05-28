package cucumber.hooks;

import io.restassured.response.Response;

import cucumber.definitions.SharedData;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {
    
    @Before
    public void login() throws Exception {

        String body = "{\n" +
            "  \"email\": \"sulistyo_2@gmail.com\",\n" +
            "  \"password\": \"t@st12345\"\n" +
            "}";

        Response resLogin = RestAssured.given()
        .contentType("application/json")
            .body(body)
            .when()
            .post(SharedData.baseUrl + "/api/login");
        
        SharedData.tokenAuthentication = resLogin.jsonPath().getString("token");

        System.out.println("Login Response body: " + resLogin.getBody().asPrettyString());
    }
}
