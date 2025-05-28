package cucumber.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;

import org.testng.Assert;

import com.demo.testng.program.response_model.AddObjectResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AddObject {

    public static Response response;
    
    @When("I send {string} request to {string} with body:")
    public void sendRequest(String method, String url, String body) throws Exception {

        switch (url) {
            case "/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/":
                url = url + "78"; //Input ID
                break;

            case "/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/":
                url = url + SharedData.firstObjectId;
                SharedData.deletedObjectId = SharedData.firstObjectId;
                break;
        
            default:
                break;
        }

        response = RestAssured
                    .given()
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + SharedData.tokenAuthentication)
                    .body(body)
                    .when()
                    .request(method, SharedData.baseUrl + url);

        System.out.println("Sending " + method + " request to: " + SharedData.baseUrl + url);
        System.out.println("Token: " + SharedData.tokenAuthentication);
        System.out.println("Body:" + body);
        System.out.println("Response: " + response.body().asString());
    }

    @Then("The add response status code should be {int}")
    public void verifyResponseStatusCode(int statusCode) {
        // Code to verify the response status code
        Assert.assertEquals(response.getStatusCode(), statusCode,
            "Expected status code: " + statusCode + ", but got: " + response.getStatusCode());
    }

    @And("The add response id should not be null")
    public void verifyResponseIdNotNull() throws Exception {
        // Code to verify the response id is not null
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertNotNull(addObjectResponse.get(0).getId(), 
            "'ID' is null");
    }

    @And ("I save the object ID")
    public void saveObjectId() throws Exception {
        // Code to check for a valid authentication token
        // This could involve checking a stored token or making an API call
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        SharedData.objectId = addObjectResponse.get(0).getId();
    }
    
    @And("The add response name should be {string}")
    public void verifyResponseName(String name) throws Exception {
        // Code to verify the response name
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getName(), name,
                "Expected name "+ name +", but got " + addObjectResponse.get(0).getName());
    }

    @And("The add response year should be {string}")
    public void verifyResponseYear(String year) throws Exception {
        // Code to verify the response year
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getYear(), year,
                "Expected year "+ year +", but got " + addObjectResponse.get(0).getData().getYear());
    }
    @And("The add response price should be {int}")
    public void verifyResponsePrice(int price) throws Exception {
        // Code to verify the response price
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getPrice(), price,
                "Expected price "+ price +", but got " + addObjectResponse.get(0).getData().getPrice());
    }

    @And("The add response cpu model should be {string}")
    public void verifyResponseCpuModel(String cpuModel) throws Exception {
        // Code to verify the response cpu model
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getCpuModel(), cpuModel,
                "Expected CPU model "+ cpuModel +", but got " + addObjectResponse.get(0).getData().getCpuModel());
    }

    @And("The add response hard disk size should be {string}")
    public void verifyResponseHardDiskSize(String hardDiskSize) throws Exception {
        // Code to verify the response hard disk size
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getHardDiskSize(), hardDiskSize,
                "Expected hard disk size "+ hardDiskSize +", but got " + addObjectResponse.get(0).getData().getHardDiskSize());
    }

    @And("The add response capacity should be {string}")
    public void verifyResponseCapacity(String capacity) throws Exception {
        // Code to verify the response capacity
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getCapacity(), capacity,
                "Expected capacity "+ capacity +", but got " + addObjectResponse.get(0).getData().getCapacity());
    }

    @And("The add response screen size should be {string}")
    public void verifyResponseScreenSize(String screenSize) throws Exception {
        // Code to verify the response screen size
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getScreenSize(), screenSize,
                "Expected screen size "+ screenSize +", but got " + addObjectResponse.get(0).getData().getScreenSize());
    }
    @And("The add response color should be {string}")
    public void verifyResponseColor(String color) throws Exception {
        // Code to verify the response color
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getColor(), color,
                "Expected color "+ color +", but got " + addObjectResponse.get(0).getData().getColor());
    }

}
