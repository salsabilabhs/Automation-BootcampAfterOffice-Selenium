package cucumber.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.List;

import org.testng.Assert;

import com.demo.testng.program.response_model.AddObjectResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.Endpoints;
import cucumber.TestContext;

public class AddObject extends Endpoints {

    public static Response response;
    public static String addedObjectId;
    ObjectMapper objectMapper = new ObjectMapper();
    private final TestContext context;

    public AddObject(TestContext context) {
        this.context = context;
    }
    
    @When("I send {string} request to {string} with body:")
    public void sendHttpRequest(String method, String url, String body) {

        response = sendRequest(method, url, body);
        context.setResponse(response);

        /* 
        * ===== OLD WITHOUT CONTEXT & ENDPOINTS =====
        switch (url) {
            case "/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/":
                url = url + SharedData.addedObjectId; //with ID that have been added
                break;

            case "/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/":
                url = url + SharedData.firstObjectId;
                SharedData.deletedObjectId = SharedData.firstObjectId;
                break;
        
            default:
                break;
        }

        String resolvedUrl = Endpoints.resolver(url);

        response = RestAssured
                    .given()
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + SharedData.tokenAuthentication)
                    .body(body)
                    .when()
                    .request(method, SharedData.getBaseUrl() + resolvedUrl);

        System.out.println("Sending " + method + " request to: " + SharedData.getBaseUrl() + resolvedUrl);
        System.out.println("Token: " + SharedData.tokenAuthentication);
        System.out.println("Body:" + body);
        System.out.println("Response: " + response.body().asString());
        */
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
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertNotNull(addObjectResponse.get(0).getId(), 
            "'ID' is null");
    }

    @And ("I save the object ID")
    public void saveObjectId() throws Exception {
        // Code to check for a valid authentication token
        // This could involve checking a stored token or making an API call
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        addedObjectId = addObjectResponse.get(0).getId();
    }
    
    @And("The add response name should be {string}")
    public void verifyResponseName(String name) throws Exception {
        // Code to verify the response name
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getName(), name,
                "Expected name "+ name +", but got " + addObjectResponse.get(0).getName());
    }

    @And("The add response year should be {string}")
    public void verifyResponseYear(String year) throws Exception {
        // Code to verify the response year
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getYear(), year,
                "Expected year "+ year +", but got " + addObjectResponse.get(0).getData().getYear());
    }
    @And("The add response price should be {int}")
    public void verifyResponsePrice(int price) throws Exception {
        // Code to verify the response price
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getPrice(), price,
                "Expected price "+ price +", but got " + addObjectResponse.get(0).getData().getPrice());
    }

    @And("The add response cpu model should be {string}")
    public void verifyResponseCpuModel(String cpuModel) throws Exception {
        // Code to verify the response cpu model
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getCpuModel(), cpuModel,
                "Expected CPU model "+ cpuModel +", but got " + addObjectResponse.get(0).getData().getCpuModel());
    }

    @And("The add response hard disk size should be {string}")
    public void verifyResponseHardDiskSize(String hardDiskSize) throws Exception {
        // Code to verify the response hard disk size
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getHardDiskSize(), hardDiskSize,
                "Expected hard disk size "+ hardDiskSize +", but got " + addObjectResponse.get(0).getData().getHardDiskSize());
    }

    @And("The add response capacity should be {string}")
    public void verifyResponseCapacity(String capacity) throws Exception {
        // Code to verify the response capacity
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getCapacity(), capacity,
                "Expected capacity "+ capacity +", but got " + addObjectResponse.get(0).getData().getCapacity());
    }

    @And("The add response screen size should be {string}")
    public void verifyResponseScreenSize(String screenSize) throws Exception {
        // Code to verify the response screen size
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getScreenSize(), screenSize,
                "Expected screen size "+ screenSize +", but got " + addObjectResponse.get(0).getData().getScreenSize());
    }
    @And("The add response color should be {string}")
    public void verifyResponseColor(String color) throws Exception {
        // Code to verify the response color
        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponse.get(0).getData().getColor(), color,
                "Expected color "+ color +", but got " + addObjectResponse.get(0).getData().getColor());
    }

}
