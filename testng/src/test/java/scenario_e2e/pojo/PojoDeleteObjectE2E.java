package scenario_e2e.pojo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.testng.program.response_model.DeleteObjectResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PojoDeleteObjectE2E {
    
    /*
     * SCENARIO DELETE OBJECT :
     * 1. Delete Object
     * 2. Get List By ID Object
     * 3. Get Single Object
     */

    @Test(dependsOnGroups = "assertAddObject")
    public void deleteObject() throws Exception {

        Response resDeleteObject = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + StaticVar.tokenLogin)
            .log().all()
            .when()
            .delete(StaticVar.BASE_URL +"/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" + StaticVar.objectId);

        System.out.println("Validate Delete Object for ID: " + StaticVar.objectId);

        Assert.assertEquals(resDeleteObject.getStatusCode(), 200,
                "Expected status code 200, but got " + resDeleteObject.statusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        DeleteObjectResponse deleteObjectResponse = objectMapper.readValue(resDeleteObject.asString(), DeleteObjectResponse.class); 
        
        Assert.assertEquals(deleteObjectResponse.getStatus(), "deleted",
            "Expected status deleted, but got " + deleteObjectResponse.getStatus());
        Assert.assertEquals(deleteObjectResponse.getMessage(), "Object with id = " + StaticVar.objectId + ", has been deleted.",
            "Expected message Object with id = " + StaticVar.objectId + ", has been deleted., but got " + deleteObjectResponse.getMessage());

        System.out.println("Response: " + resDeleteObject.asPrettyString());

        /*
         * --- OLD WITHOUT POJO!! ---
         * 
         * Assert.assertEquals(resDeleteObject.jsonPath().getString("status"), "deleted",
                "Expected status deleted, but got " + resDeleteObject.jsonPath().getString("status"));
        Assert.assertEquals(resDeleteObject.jsonPath().getString("message"), "Object with id = " + StaticVar.objectId + ", has been deleted.",
                "Expected message Object with id = " + StaticVar.objectId + ", has been deleted., but got " + resDeleteObject.jsonPath().getString("message"));
         */

    }
      
        
    @Test(dependsOnMethods = "deleteObject")
    public void getListObjectbyId() throws Exception {
        
        Response resGetListById = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + StaticVar.tokenLogin)
            .log().all()
            .when()
            .get(StaticVar.BASE_URL +"/api/objectslistId?id=" + StaticVar.objectId);

        System.out.println("Validate Get List By the Deleted ID: " + StaticVar.objectId);

        Assert.assertEquals(resGetListById.getStatusCode(), 200,
                "Expected status code 200, but got " + resGetListById.statusCode());

        Assert.assertEquals(resGetListById.getBody().asString(), "", "Expected empty list of object, but got non-empty list");
    }


    @Test(dependsOnMethods = "deleteObject", priority = 1)
    public void getSingleObject() throws Exception {
        Response resGetSingleObject = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + StaticVar.tokenLogin)
            .log().all()
            .when()
            .get(StaticVar.BASE_URL + "/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/" + StaticVar.objectId);

        System.out.println("Validate Get Single Object for the Deleted ID: " + StaticVar.objectId);

        Assert.assertEquals(resGetSingleObject.getStatusCode(), 200,
                "Expected status code 200, but got " + resGetSingleObject.statusCode());

        Assert.assertTrue(resGetSingleObject.jsonPath().getMap("").isEmpty(), "'Single Object' is not empty");
        }

    }
