package scenario_e2e;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteObjectE2E {
    
    /*
     * SCENARIO DELETE OBJECT :
     * 1. Delete Object
     * 2. Get List By ID Object
     * 3. Get Single Object
     */

    @Test(dependsOnGroups = "assertAddObject")
    public void deleteObject() {

        Response resDeleteObject = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + RegisterUserE2E.tokenLogin)
            .log().all()
            .when()
            .delete("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" + AddObjectE2E.objectId);

        System.out.println("Validate Delete Object for ID: " + AddObjectE2E.objectId);
        Assert.assertEquals(resDeleteObject.getStatusCode(), 200,
                "Expected status code 200, but got " + resDeleteObject.statusCode());
        Assert.assertEquals(resDeleteObject.jsonPath().getString("status"), "deleted",
                "Expected status deleted, but got " + resDeleteObject.jsonPath().getString("status"));
        Assert.assertEquals(resDeleteObject.jsonPath().getString("message"), "Object with id = " + AddObjectE2E.objectId + ", has been deleted.",
                "Expected message Object with id = " + AddObjectE2E.objectId + ", has been deleted., but got " + resDeleteObject.jsonPath().getString("message"));

        System.out.println("Response: " + resDeleteObject.asPrettyString());

    }
      
        
    @Test(dependsOnMethods = "deleteObject", priority = 1)
    public void getListObjectbyId() {
        Response resGetListById = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + RegisterUserE2E.tokenLogin)
            .log().all()
            .when()
            .get("/webhook/api/objectslistId?id=" + AddObjectE2E.objectId);

        System.out.println("Validate Get List By the Deleted ID: " + AddObjectE2E.objectId);

        Assert.assertEquals(resGetListById.getStatusCode(), 200,
                "Expected status code 200, but got " + resGetListById.statusCode());
        Assert.assertEquals(resGetListById.getBody().asString(), "", "Expected empty list of object, but got non-empty list");
    }


    @Test(dependsOnMethods = "deleteObject", priority = 2)
    public void getSingleObject() {
        Response resGetSingleObject = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + RegisterUserE2E.tokenLogin)
            .log().all()
            .when()
            .get("/webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/" + AddObjectE2E.objectId);

        System.out.println("Validate Get Single Object for the Deleted ID: " + AddObjectE2E.objectId);
        Assert.assertEquals(resGetSingleObject.getStatusCode(), 200,
                "Expected status code 200, but got " + resGetSingleObject.statusCode());
        Assert.assertTrue(resGetSingleObject.jsonPath().getMap("").isEmpty(), "'Single Object' is not empty");
        }

    }
