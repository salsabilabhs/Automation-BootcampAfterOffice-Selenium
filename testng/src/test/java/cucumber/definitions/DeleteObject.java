package cucumber.definitions;

import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.Endpoints;

import com.demo.testng.program.response_model.DeleteObjectResponse;
import org.testng.Assert;

import io.restassured.response.Response;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

public class DeleteObject {
    
    Response response = AddObject.response;
    ObjectMapper objectMapper = new ObjectMapper();

    @Then("The delete response status code should be {int}")
    public void verifyDeleteResponseStatusCode(int statusCode) throws Exception {
        Assert.assertEquals(response.getStatusCode(), statusCode,
            "Expected status code " + statusCode + ", but got " + response.getStatusCode());
    }

    @And("The delete response status should be {string}")
    public void verifyDeleteResponseStatus(String status) throws Exception {
        DeleteObjectResponse deleteObjectResponse = objectMapper.readValue(response.asString(), DeleteObjectResponse.class); 
        Assert.assertEquals(deleteObjectResponse.getStatus(), status,
            "Expected status code " + status + ", but got " + deleteObjectResponse.getStatus());
    }

    @And ("The delete response message should be {string}")
    public void verifyDeleteResponseMessage(String message) throws Exception {
        Endpoints endpoints = new Endpoints();
        
        switch (message) {
            case "delete-message-positive" :
            message = "Object with id = " + endpoints.getFirstObjectId() + ", has been deleted.";
            break;

            case "delete-message-negative" :
            message = "object with id = "+ endpoints.getFirstObjectId() +" has been deleted or doesn't exist";
            break;
        }
        DeleteObjectResponse deleteObjectResponse = objectMapper.readValue(response.asString(), DeleteObjectResponse.class); 
        Assert.assertEquals(deleteObjectResponse.getMessage(), message,
            "Expected message " + message + ", but got " + deleteObjectResponse.getMessage());

    }
}
