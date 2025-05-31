package cucumber.hooks;

import io.restassured.response.Response;
import io.cucumber.java.Before;
import cucumber.Endpoints;
import io.restassured.RestAssured;

public class Hooks {

    public static String token;
    
    @Before("@withLogin")
    public void login() throws Exception {


        String body = "{\n" +
            "  \"email\": \"sulistyo_2@gmail.com\",\n" +
            "  \"password\": \"t@st12345\"\n" +
            "}";

        Endpoints endpoints = new Endpoints();
        Response resLogin = RestAssured.given()
        .contentType("application/json")
            .body(body)
            .when()
            .post(endpoints.getBaseUrl() + "/api/login");
        
        token = resLogin.jsonPath().getString("token");

        System.out.println("Login Response body: " + resLogin.getBody().asPrettyString());
    }
}
