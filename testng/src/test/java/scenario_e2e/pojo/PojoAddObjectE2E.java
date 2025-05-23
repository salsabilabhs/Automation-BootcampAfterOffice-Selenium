package scenario_e2e.pojo;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PojoAddObjectE2E {

    /*
     * SCENARIO ADD OBJECT :
     * 1. Add Object
     * 2. Get List By ID Object
     * 3. Get Single Object
     */

    public static int objectId;

    @Test(dependsOnGroups = "LoginGroup")
    public void addObject() {

        String bodyAddObject =   "{\n"
                            + "  \"name\": \"Samsung A53 5G\",\n"
                            + "  \"data\": {\n"
                            + "    \"year\": 2022,\n"
                            + "    \"price\": 3000,\n" 
                            + "    \"cpu_model\": \"Exynos\",\n" 
                            + "    \"hard_disk_size\": \"256 GB\",\n" 
                            + "    \"capacity\": \"123\",\n" 
                            + "    \"screen_size\": \"5 inch\",\n" 
                            + "    \"color\": \"Black\"\n" 
                            + "  }\n" 
                            + "}";

        Response resAddObject = RestAssured.given()
            .header("Content-Type", "application/json")
            // Passing token dari APILogin ke API Add Object
            .header("Authorization", "Bearer " + PojoRegisterUserE2E.tokenLogin)
            .body(bodyAddObject)
            .log().all()
            .when()
            .post("/webhook/api/objects");

        // Extract value id & simpan ke variable id
        objectId = resAddObject.jsonPath().getInt("[0].id");
        System.out.println("Object has been added successfully with ID: " + objectId);

        Assert.assertEquals(resAddObject.getStatusCode(), 200,
                "Expected status code 200, but got " + resAddObject.statusCode());
        Assert.assertNotNull(resAddObject.jsonPath().getInt("[0].id"), "'iD' is null");
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].name"), "Samsung A53 5G",
                "Expected name Samsung A53 5G, but got " + resAddObject.jsonPath().getString("[0].name"));
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].data.year"), "2022",
                "Expected year 2022, but got " + resAddObject.jsonPath().getString("[0].data.year"));
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].data.price"), "3000",
                "Expected price 3000, but got " + resAddObject.jsonPath().getString("[0].data.price"));
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].data.cpu_model"), "Exynos",
                "Expected cpu_model Exynos, but got " + resAddObject.jsonPath().getString("[0].data.cpu_model"));
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].data.hard_disk_size"), "256 GB",
                "Expected hard_disk_size 256 GB, but got " + resAddObject.jsonPath().getString("[0].data.hard_disk_size"));
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].data.capacity"), "123",
                "Expected capacity 123, but got " + resAddObject.jsonPath().getString("[0].data.capacity"));
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].data.screen_size"), "5 inch",
                "Expected screen_size 5 inch, but got " + resAddObject.jsonPath().getString("[0].data.screen_size"));
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].data.color"), "Black",
                "Expected color Black, but got " + resAddObject.jsonPath().getString("[0].data.color"));
                   
        System.out.println("Response: " + resAddObject.asPrettyString());
    }

        
    @Test(dependsOnMethods = "addObject", priority = 1)
    public void getListObjectbyId() {
        Response resGetListById = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + PojoRegisterUserE2E.tokenLogin)
            .log().all()
            .when()
            .get("/webhook/api/objectslistId?id=" + objectId);

        System.out.println("Validate Get List By ID: " + objectId);

        Assert.assertEquals(resGetListById.getStatusCode(), 200,
                "Expected status code 200, but got " + resGetListById.statusCode());
        Assert.assertNotNull(resGetListById.jsonPath().getInt("[0].id"), "'iD' is null");
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].name"), "Samsung A53 5G",
                "Expected name Samsung A53 5G, but got " + resGetListById.jsonPath().getString("[0].name"));
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].data.year"), "2022",
                "Expected year 2022, but got " + resGetListById.jsonPath().getString("[0].data.year"));
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].data.price"), "3000",
                "Expected price 3000, but got " + resGetListById.jsonPath().getString("[0].data.price"));
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].data.cpu_model"), "Exynos",
                "Expected cpu_model Exynos, but got " + resGetListById.jsonPath().getString("[0].data.cpu_model"));
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].data.hard_disk_size"), "256 GB",
                "Expected hard_disk_size 256 GB, but got " + resGetListById.jsonPath().getString("[0].data.hard_disk_size"));
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].data.capacity"), "123",
                "Expected capacity 123, but got " + resGetListById.jsonPath().getString("[0].data.capacity"));
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].data.screen_size"), "5",
                "Expected screen_size 5, but got " + resGetListById.jsonPath().getString("[0].data.screen_size"));
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].data.color"), "Black",
                "Expected color Black, but got " + resGetListById.jsonPath().getString("[0].data.color"));
                   
        System.out.println("Response: " + resGetListById.asPrettyString());

    }
        
    @Test(dependsOnMethods = "getListObjectbyId", priority = 2)
    public void getSingleObject() {
        Response resGetSingleObject = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + PojoRegisterUserE2E.tokenLogin)
            .log().all()
            .when()
            .get("/webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/" + objectId);

        System.out.println("Validate Get Single Object for ID: " + objectId);

        Assert.assertEquals(resGetSingleObject.getStatusCode(), 200,
                "Expected status code 200, but got " + resGetSingleObject.statusCode());
        Assert.assertNotNull(resGetSingleObject.jsonPath().getInt("id"), "'iD' is null");
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("name"), "Samsung A53 5G",
                "Expected name Samsung A53 5G, but got " + resGetSingleObject.jsonPath().getString("name"));
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("data.year"), "2022",
                "Expected year 2022, but got " + resGetSingleObject.jsonPath().getString("data.year"));
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("data.price"), "3000",
                "Expected price 3000, but got " + resGetSingleObject.jsonPath().getString("data.price"));
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("data.cpu_model"), "Exynos",  
                "Expected cpu_model Exynos, but got " + resGetSingleObject.jsonPath().getString("data.cpu_model"));
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("data.hard_disk_size"), "256 GB",
                "Expected hard_disk_size 256 GB, but got " + resGetSingleObject.jsonPath().getString("data.hard_disk_size"));
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("data.capacity"), "123",
                "Expected capacity 123, but got " + resGetSingleObject.jsonPath().getString("data.capacity"));
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("data.screen_size"), "5",
                "Expected screen_size 5, but got " + resGetSingleObject.jsonPath().getString("data.screen_size"));
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("data.color"), "Black",
                "Expected color Black, but got " + resGetSingleObject.jsonPath().getString("data.color"));

        System.out.println("Response: " + resGetSingleObject.asPrettyString());
    }
    
}