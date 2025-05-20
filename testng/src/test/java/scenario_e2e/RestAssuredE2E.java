package scenario_e2e;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredE2E {

    String base_url = "https://whitesmokehouse.com";
    String tokenLogin;
    int objectId;
    
    @Test
    public void registerUserScenario() {
        RestAssured.baseURI = base_url;

        String bodyRegisterUser = "{\n" +
                            "    \"email\": \"sulistyo_8@gmail.com\",\n" +
                            "    \"full_name\": \"Sulistyo Hamidar\",\n" +
                            "    \"password\": \"t@st12345\",\n" +
                            "    \"department\": \"Finance\",\n" +
                            "    \"phone_number\": \"08123456789\"\n" +
                            "}";

        Response responseRegisterUser = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(bodyRegisterUser)
            .log().all()
            .when()
            .post("/webhook/api/register");

        Assert.assertEquals(responseRegisterUser.getStatusCode(), 200, 
            "Expected status code 200, but got " + responseRegisterUser.statusCode());
        Assert.assertNotNull(responseRegisterUser.jsonPath().get("id"), "'id' is null");
        Assert.assertEquals(responseRegisterUser.jsonPath().getString("email"), "sulistyo_8@gmail.com",
            "Expected email sulistyo_8@gmail.com, but got " + responseRegisterUser.jsonPath().getString("email"));
        Assert.assertEquals(responseRegisterUser.jsonPath().getString("full_name"), "Sulistyo Hamidar", 
            "Expected full_name Sulistyo Hamidar, but got " + responseRegisterUser.jsonPath().getString("full_name"));
        Assert.assertEquals(responseRegisterUser.jsonPath().getString("department"), "Finance",
            "Expected department Finance, but got " + responseRegisterUser.jsonPath().getString("department"));
        Assert.assertEquals(responseRegisterUser.jsonPath().getString("phone_number"), "08123456789",
            "Expected phone_number 08123456789, but got " + responseRegisterUser.jsonPath().getString("phone_number"));
        Assert.assertNotNull(responseRegisterUser.jsonPath().get("create_at"), "'created_at' is null");
        Assert.assertNotNull(responseRegisterUser.jsonPath().get("update_at"), "'updated_at' is null"); 


        System.out.println("Response Body: " + responseRegisterUser.asPrettyString());


        Response responseLogin = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(bodyRegisterUser)
            .log().all()
            .when()
            .post("/webhook/api/login");

        tokenLogin = responseLogin.jsonPath().getString("token");
        System.out.println("Token Login : " + tokenLogin);

        Assert.assertEquals(responseLogin.getStatusCode(), 200, 
            "Expected status code 200, but got " + responseLogin.statusCode());
        Assert.assertNotNull(responseLogin.jsonPath().get("token"), "'token' is null");
        
        System.out.println("Response Body: " + responseLogin.asPrettyString());

        Response responseGetListAllObjects = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + tokenLogin)
            .log().all()
            .when()
            .get("/webhook/api/objects");

        Assert.assertEquals(responseGetListAllObjects.getStatusCode(), 200,
            "Expected status code 200, but got " + responseGetListAllObjects.statusCode());
        Assert.assertNotNull(responseGetListAllObjects.jsonPath().getList(""), "'List' is null");

        System.out.println("Response Body: " + responseGetListAllObjects.asPrettyString());           
    }
}