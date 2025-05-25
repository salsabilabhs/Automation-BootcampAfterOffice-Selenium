package cucumber.definitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;

import org.testng.Assert;

import com.demo.testng.program.response_model.AddObjectResponse;
import com.demo.testng.program.response_model.DeleteObjectResponse;
import com.demo.testng.program.response_model.UpdateObjectResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.And;

public class ObjectDefinition {

    public static String baseUrl;
    public static String authenticationToken;
    public static Response resLogin;
    public static Response response;
    public static String id;

    /*
     * ===================== Login Preconditions =====================
     */

    @Given("I have set the base url {string}")
    public void setBaseUrl(String baseUrl) {
        // Code to set the base URL
        ObjectDefinition.baseUrl = baseUrl;
    }

    @And("Send a http {string} request to login {string} with body:") 
    public void sendHttpRequestLogin(String method, String url, String body) {
        // Code to send an HTTP request
        resLogin = RestAssured.given()
            .contentType("application/json")
            .body(body)
            .when()
            .request(method, ObjectDefinition.baseUrl + url);

        System.out.println("Login Response body: " + resLogin.getBody().asPrettyString());
        }

    @And("I save a valid authentication token")
    public void saveValidAuthenticationToken() {
        // Code to check for a valid authentication token
        // This could involve checking a stored token or making an API call
        ObjectDefinition.authenticationToken = resLogin.jsonPath().getString("token");
    }

    /*
     * ===================== Request API =====================
     */

    @When("I send a http {string} request to {string} with body:")
    public void sendHttpRequest(String method, String url, String body) {

        /*
         * if (url.equals("update-object-url")) {
            url = "/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/" + ObjectDefinition.id;
        }
        else if (url.equals("delete-object-url")) {
            url = "/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" + ObjectDefinition.id;
        }
         */

        switch (url) {
            case "update-object-url" :
                url = "/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/" + ObjectDefinition.id;
                break;

            case "delete-object-url" :
                url = "/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" + ObjectDefinition.id;

            default:
                break;
        }
        
        // Code to send an HTTP request
        response = RestAssured.given()
            .contentType("application/json")
            .header("Authorization", "Bearer " + ObjectDefinition.authenticationToken)
            .body(body)
            .when()
            .request(method, ObjectDefinition.baseUrl + url); 

        System.out.println("Response body: " + response.getBody().asPrettyString());
    }

    /*
     * ===================== Add Object =====================
     */

    @Then("The response status code should be {int}")
    public void verifyResponseStatusCode(int statusCode) {
        // Code to verify the response status code
        Assert.assertEquals(response.getStatusCode(), statusCode,
            "Expected status code: " + statusCode + ", but got: " + response.getStatusCode());
    }

    @And("The response id should not be null")
    public void verifyResponseIdNotNull() throws Exception {
        // Code to verify the response id is not null
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertNotNull(addObjectResponse.get(0).getId(), 
            "'ID' is null");
    }

    @And ("I save an object id")
    public void saveObjectId() throws Exception {
        // Code to check for a valid authentication token
        // This could involve checking a stored token or making an API call
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        ObjectDefinition.id = addObjectResponse.get(0).getId();
    }
    
    @And("The response name should be {string}")
    public void verifyResponseName(String name) throws Exception {
        // Code to verify the response name
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getName(), name,
                "Expected name "+ name +", but got " + addObjectResponse.get(0).getName());
    }

    @And("The response year should be {string}")
    public void verifyResponseYear(String year) throws Exception {
        // Code to verify the response year
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getYear(), year,
                "Expected year "+ year +", but got " + addObjectResponse.get(0).getData().getYear());
    }
    @And("The response price should be {int}")
    public void verifyResponsePrice(int price) throws Exception {
        // Code to verify the response price
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getPrice(), price,
                "Expected price "+ price +", but got " + addObjectResponse.get(0).getData().getPrice());
    }

    @And("The response cpu model should be {string}")
    public void verifyResponseCpuModel(String cpuModel) throws Exception {
        // Code to verify the response cpu model
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getCpuModel(), cpuModel,
                "Expected CPU model "+ cpuModel +", but got " + addObjectResponse.get(0).getData().getCpuModel());
    }

    @And("The response hard disk size should be {string}")
    public void verifyResponseHardDiskSize(String hardDiskSize) throws Exception {
        // Code to verify the response hard disk size
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getHardDiskSize(), hardDiskSize,
                "Expected hard disk size "+ hardDiskSize +", but got " + addObjectResponse.get(0).getData().getHardDiskSize());
    }

    @And("The response capacity should be {string}")
    public void verifyResponseCapacity(String capacity) throws Exception {
        // Code to verify the response capacity
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getCapacity(), capacity,
                "Expected capacity "+ capacity +", but got " + addObjectResponse.get(0).getData().getCapacity());
    }

    @And("The response screen size should be {string}")
    public void verifyResponseScreenSize(String screenSize) throws Exception {
        // Code to verify the response screen size
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getScreenSize(), screenSize,
                "Expected screen size "+ screenSize +", but got " + addObjectResponse.get(0).getData().getScreenSize());
    }
    @And("The response color should be {string}")
    public void verifyResponseColor(String color) throws Exception {
        // Code to verify the response color
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getColor(), color,
                "Expected color "+ color +", but got " + addObjectResponse.get(0).getData().getColor());
    }
/*
 * ===================== Update ObjectDefinition.java =====================
 */

 @Then("The update response status code should be {int}")
    public void verifyUpdateResponseStatusCode(int statusCode) {
        // Code to verify the response status code
        Assert.assertEquals(response.getStatusCode(), statusCode,
            "Expected status code: " + statusCode + ", but got: " + response.getStatusCode());
    }

    @And("The update response id should not be null")
    public void verifyUpdateResponseIdNotNull() throws Exception {
        // Code to verify the response id is not null
        ObjectMapper objectMapper = new ObjectMapper();
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertNotNull(updateObjectResponse.get(0).getId(), 
            "'ID' is null");
    }
    
    @And("The update response name should be {string}")
    public void verifyUpdateResponseName(String name) throws Exception {
        // Code to verify the update response name
        ObjectMapper objectMapper = new ObjectMapper();
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getName(), name,
                "Expected name " + name + ", but got " + updateObjectResponse.get(0).getName());
    }

    @And("The update response year should be {string}")
    public void verifyUpdateResponseYear(String year) throws Exception {
        // Code to verify the update response year
        ObjectMapper objectMapper = new ObjectMapper();
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getData().getYear(), year,
                "Expected year " + year + ", but got " + updateObjectResponse.get(0).getData().getYear());
    }

    @And("The update response price should be {int}")
    public void verifyUpdateResponsePrice(int price) throws Exception {
        // Code to verify the update response price
        ObjectMapper objectMapper = new ObjectMapper();
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getData().getPrice(), price,
                "Expected price " + price + ", but got " + updateObjectResponse.get(0).getData().getPrice());
    }

    @And("The update response cpu model should be {string}")
    public void verifyUpdateResponseCpuModel(String cpuModel) throws Exception {
        // Code to verify the update response cpu model
        ObjectMapper objectMapper = new ObjectMapper();
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getData().getCpuModel(), cpuModel,
                "Expected CPU model " + cpuModel + ", but got " + updateObjectResponse.get(0).getData().getCpuModel());
    }

    @And("The update response hard disk size should be {string}")
    public void verifyUpdateResponseHardDiskSize(String hardDiskSize) throws Exception {
        // Code to verify the update response hard disk size
        ObjectMapper objectMapper = new ObjectMapper();
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getData().getHardDiskSize(), hardDiskSize,
                "Expected hard disk size " + hardDiskSize + ", but got " + updateObjectResponse.get(0).getData().getHardDiskSize());
    }

    @And("The update response capacity should be {string}")
    public void verifyUpdateResponseCapacity(String capacity) throws Exception {
        // Code to verify the update response capacity
        ObjectMapper objectMapper = new ObjectMapper();
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getData().getCapacity(), capacity,
                "Expected capacity " + capacity + ", but got " + updateObjectResponse.get(0).getData().getCapacity());
    }

    @And("The update response screen size should be {string}")
    public void verifyUpdateResponseScreenSize(String screenSize) throws Exception {
        // Code to verify the update response screen size
        ObjectMapper objectMapper = new ObjectMapper();
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getData().getScreenSize(), screenSize,
                "Expected screen size " + screenSize + ", but got " + updateObjectResponse.get(0).getData().getScreenSize());
    }

    @And("The update response color should be {string}")
    public void verifyUpdateResponseColor(String color) throws Exception {
        // Code to verify the update response color
        ObjectMapper objectMapper = new ObjectMapper();
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getData().getColor(), color,
                "Expected color " + color + ", but got " + updateObjectResponse.get(0).getData().getColor());
    }

    /*
     * ===================== DELETE Object =====================
     */



    @Then("The delete response status code should be {int}")
    public void verifyDeleteResponseStatusCode(int statusCode) throws Exception {
        Assert.assertEquals(response.getStatusCode(), statusCode,
            "Expected status code " + statusCode + ", but got " + response.getStatusCode());
    }

    @And("The delete response status should be {string}")
    public void verifyDeleteResponseStatus(String status) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        DeleteObjectResponse deleteObjectResponse = objectMapper.readValue(response.asString(), DeleteObjectResponse.class); 
        Assert.assertEquals(deleteObjectResponse.getStatus(), status,
            "Expected status code " + status + ", but got " + deleteObjectResponse.getStatus());
    }

    @And ("The delete response message should be {string}")
    public void verifyDeleteResponseMessage(String message) throws Exception {
        switch (message) {
            case "delete-message-positive" :
            message = "Object with id = " + ObjectDefinition.id + ", has been deleted.";
            break;

            case "delete-message-negative" :
            message = "object with id = "+ ObjectDefinition.id +" has been deleted or doesn't exist";
            break;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        DeleteObjectResponse deleteObjectResponse = objectMapper.readValue(response.asString(), DeleteObjectResponse.class); 
        Assert.assertEquals(deleteObjectResponse.getMessage(), message,
            "Expected message " + message + ", but got " + deleteObjectResponse.getMessage());

    }

}
