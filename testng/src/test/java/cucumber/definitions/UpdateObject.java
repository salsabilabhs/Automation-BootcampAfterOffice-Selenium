package cucumber.definitions;

import java.util.List;

import org.testng.Assert;

import com.demo.testng.program.response_model.UpdateObjectResponse;
import com.demo.testng.program.response_model.GetListAllObjectResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class UpdateObject {

    // Panggil AddObject untuk mendapatkan response
    Response response = AddObject.response;
    public static String firstObjectId;
    ObjectMapper objectMapper = new ObjectMapper();
    
    @And ("I save the first id of existing Object")
    public void saveFirstIdOfExistingObject() throws Exception {
        
        System.out.println("Saving the first id of an existing object.");
        List<GetListAllObjectResponse> resGetListAll = objectMapper.readValue(AddObject.response.body().asString(),new TypeReference<List<GetListAllObjectResponse>>() {});

        firstObjectId = resGetListAll.get(0).getId();
    }

    @Then("The update response status code should be {int}")
    public void verifyUpdateResponseStatusCode(int statusCode) {
        // Code to verify the response status code
        Assert.assertEquals(response.getStatusCode(), statusCode,
            "Expected status code: " + statusCode + ", but got: " + response.getStatusCode());
    }

    @And("The update response id should not be null")
    public void verifyUpdateResponseIdNotNull() throws Exception {
        // Code to verify the response id is not null
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(),new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertNotNull(updateObjectResponse.get(0).getId(), 
            "'ID' is null");

        System.out.println("Update response ID: " + updateObjectResponse.get(0).getId());
    }
    
    @And("The update response name should be {string}")
    public void verifyUpdateResponseName(String name) throws Exception {
        // Code to verify the update response name
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getName(), name,
                "Expected name " + name + ", but got " + updateObjectResponse.get(0).getName());
    }

    @And("The update response year should be {string}")
    public void verifyUpdateResponseYear(String year) throws Exception {
        // Code to verify the update response year
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getData().getYear(), year,
                "Expected year " + year + ", but got " + updateObjectResponse.get(0).getData().getYear());
    }

    @And("The update response price should be {double}")
    public void verifyUpdateResponsePrice(Double price) throws Exception {
        // Code to verify the update response price
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getData().getPrice(), price,
                "Expected price " + price + ", but got " + updateObjectResponse.get(0).getData().getPrice());
    }

    @And("The update response cpu model should be {string}")
    public void verifyUpdateResponseCpuModel(String cpuModel) throws Exception {
        // Code to verify the update response cpu model
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getData().getCpuModel(), cpuModel,
                "Expected CPU model " + cpuModel + ", but got " + updateObjectResponse.get(0).getData().getCpuModel());
    }

    @And("The update response hard disk size should be {string}")
    public void verifyUpdateResponseHardDiskSize(String hardDiskSize) throws Exception {
        // Code to verify the update response hard disk size
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getData().getHardDiskSize(), hardDiskSize,
                "Expected hard disk size " + hardDiskSize + ", but got " + updateObjectResponse.get(0).getData().getHardDiskSize());
    }

    @And("The update response capacity should be {string}")
    public void verifyUpdateResponseCapacity(String capacity) throws Exception {
        // Code to verify the update response capacity
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getData().getCapacity(), capacity,
                "Expected capacity " + capacity + ", but got " + updateObjectResponse.get(0).getData().getCapacity());
    }

    @And("The update response screen size should be {string}")
    public void verifyUpdateResponseScreenSize(String screenSize) throws Exception {
        // Code to verify the update response screen size
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getData().getScreenSize(), screenSize,
                "Expected screen size " + screenSize + ", but got " + updateObjectResponse.get(0).getData().getScreenSize());
    }

    @And("The update response color should be {string}")
    public void verifyUpdateResponseColor(String color) throws Exception {
        // Code to verify the update response color
        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<UpdateObjectResponse>>() {});
        Assert.assertEquals(updateObjectResponse.get(0).getData().getColor(), color,
                "Expected color " + color + ", but got " + updateObjectResponse.get(0).getData().getColor());
    }

    
}
