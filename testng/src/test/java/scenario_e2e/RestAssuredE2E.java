package scenario_e2e;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredE2E {

    String base_url = "https://whitesmokehouse.com";
    String tokenLogin;
    int objectId;

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = base_url;

        String jsonLogin = "{\n"
                    + "  \"email\": \"sulistyo_11@gmail.com\",\n"
                    + "  \"password\": \"t@st12345\"\n"
                    + "}";

        Response responseLogin = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(jsonLogin)
            .log().all()
            .when()
            .post("/webhook/api/login");

        tokenLogin = responseLogin.jsonPath().getString("token");

        Assert.assertEquals(responseLogin.getStatusCode(), 200,
            "Expected status code 200, but got " + responseLogin.statusCode());
    }

   /*
    * SCENARIO REGISTER USER :
    * 1. Register User
    * 2. Login User
    * 3. Get List All Objects
    */

    @Test
    public void registerUserScenario() {
        RestAssured.baseURI = base_url;

        String bodyRegisterUser = "{\n" +
                            "    \"email\": \"sulistyo_13@gmail.com\",\n" +
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
        Assert.assertEquals(responseRegisterUser.jsonPath().getString("email"), "sulistyo_13@gmail.com",
            "Expected email sulistyo_13@gmail.com, but got " + responseRegisterUser.jsonPath().getString("email"));
        Assert.assertEquals(responseRegisterUser.jsonPath().getString("full_name"), "Sulistyo Hamidar", 
            "Expected full_name Sulistyo Hamidar, but got " + responseRegisterUser.jsonPath().getString("full_name"));
        Assert.assertEquals(responseRegisterUser.jsonPath().getString("department"), "Finance",
            "Expected department Finance, but got " + responseRegisterUser.jsonPath().getString("department"));
        Assert.assertEquals(responseRegisterUser.jsonPath().getString("phone_number"), "08123456789",
            "Expected phone_number 08123456789, but got " + responseRegisterUser.jsonPath().getString("phone_number"));
        Assert.assertNotNull(responseRegisterUser.jsonPath().get("create_at"), "'created_at' is null");
        Assert.assertNotNull(responseRegisterUser.jsonPath().get("update_at"), "'updated_at' is null"); 


        System.out.println("Response Body: " + responseRegisterUser.asPrettyString());


        String jsonLogin = "{\n"
                            + "  \"email\": \"sulistyo_13@gmail.com\",\n"
                            + "  \"password\": \"t@st12345\"\n"
                            + "}";
        
        Response responseLogin = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(jsonLogin)
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
    
    /*
     * SCENARIO ADD OBJECT :
     * 1. Add Object
     * 2. Get List By ID Object
     * 3. Get Single Object
     */

    @Test
    public void addObjectScenario() {
        RestAssured.baseURI = base_url;

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

        Response responseAddObject = RestAssured.given()
            .header("Content-Type", "application/json")
            // Passing token dari APILogin ke API Add Object
            .header("Authorization", "Bearer " + tokenLogin)
            .body(bodyAddObject)
            .log().all()
            .when()
            .post("/webhook/api/objects");

        // Extract value id & simpan ke variable id
        objectId = responseAddObject.jsonPath().getInt("[0].id");
        System.out.println("Object has been added successfully with ID: " + objectId);

        Assert.assertEquals(responseAddObject.getStatusCode(), 200,
                "Expected status code 200, but got " + responseAddObject.statusCode());
        Assert.assertNotNull(responseAddObject.jsonPath().getInt("[0].id"), "'iD' is null");
        Assert.assertEquals(responseAddObject.jsonPath().getString("[0].name"), "Samsung A53 5G",
                "Expected name Samsung A53 5G, but got " + responseAddObject.jsonPath().getString("[0].name"));
        Assert.assertEquals(responseAddObject.jsonPath().getString("[0].data.year"), "2022",
                "Expected year 2022, but got " + responseAddObject.jsonPath().getString("[0].data.year"));
        Assert.assertEquals(responseAddObject.jsonPath().getString("[0].data.price"), "3000",
                "Expected price 3000, but got " + responseAddObject.jsonPath().getString("[0].data.price"));
        Assert.assertEquals(responseAddObject.jsonPath().getString("[0].data.cpu_model"), "Exynos",
                "Expected cpu_model Exynos, but got " + responseAddObject.jsonPath().getString("[0].data.cpu_model"));
        Assert.assertEquals(responseAddObject.jsonPath().getString("[0].data.hard_disk_size"), "256 GB",
                "Expected hard_disk_size 256 GB, but got " + responseAddObject.jsonPath().getString("[0].data.hard_disk_size"));
        Assert.assertEquals(responseAddObject.jsonPath().getString("[0].data.capacity"), "123",
                "Expected capacity 123, but got " + responseAddObject.jsonPath().getString("[0].data.capacity"));
        Assert.assertEquals(responseAddObject.jsonPath().getString("[0].data.screen_size"), "5 inch",
                "Expected screen_size 5 inch, but got " + responseAddObject.jsonPath().getString("[0].data.screen_size"));
        Assert.assertEquals(responseAddObject.jsonPath().getString("[0].data.color"), "Black",
                "Expected color Black, but got " + responseAddObject.jsonPath().getString("[0].data.color"));
                   
        System.out.println("Response: " + responseAddObject.asPrettyString());

        Response responseGetResponseById = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + tokenLogin)
            .log().all()
            .when()
            .get("/webhook/api/objectslistId?id=" + objectId);

        System.out.println("Validate Get List By ID: " + objectId);

        Assert.assertEquals(responseGetResponseById.getStatusCode(), 200,
                "Expected status code 200, but got " + responseGetResponseById.statusCode());
        Assert.assertNotNull(responseGetResponseById.jsonPath().getInt("[0].id"), "'iD' is null");
        Assert.assertEquals(responseGetResponseById.jsonPath().getString("[0].name"), "Samsung A53 5G",
                "Expected name Samsung A53 5G, but got " + responseGetResponseById.jsonPath().getString("[0].name"));
        Assert.assertEquals(responseGetResponseById.jsonPath().getString("[0].data.year"), "2022",
                "Expected year 2022, but got " + responseGetResponseById.jsonPath().getString("[0].data.year"));
        Assert.assertEquals(responseGetResponseById.jsonPath().getString("[0].data.price"), "3000",
                "Expected price 3000, but got " + responseGetResponseById.jsonPath().getString("[0].data.price"));
        Assert.assertEquals(responseGetResponseById.jsonPath().getString("[0].data.cpu_model"), "Exynos",
                "Expected cpu_model Exynos, but got " + responseGetResponseById.jsonPath().getString("[0].data.cpu_model"));
        Assert.assertEquals(responseGetResponseById.jsonPath().getString("[0].data.hard_disk_size"), "256 GB",
                "Expected hard_disk_size 256 GB, but got " + responseGetResponseById.jsonPath().getString("[0].data.hard_disk_size"));
        Assert.assertEquals(responseGetResponseById.jsonPath().getString("[0].data.capacity"), "123",
                "Expected capacity 123, but got " + responseGetResponseById.jsonPath().getString("[0].data.capacity"));
        Assert.assertEquals(responseGetResponseById.jsonPath().getString("[0].data.screen_size"), "5",
                "Expected screen_size 5, but got " + responseGetResponseById.jsonPath().getString("[0].data.screen_size"));
        Assert.assertEquals(responseGetResponseById.jsonPath().getString("[0].data.color"), "Black",
                "Expected color Black, but got " + responseGetResponseById.jsonPath().getString("[0].data.color"));
                   
        System.out.println("Response: " + responseGetResponseById.asPrettyString());
        

        Response responseGetSingleObject = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + tokenLogin)
            .log().all()
            .when()
            .get("/webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/" + objectId);

        System.out.println("Validate Get Single Object for ID: " + objectId);

        Assert.assertEquals(responseGetSingleObject.getStatusCode(), 200,
                "Expected status code 200, but got " + responseGetSingleObject.statusCode());
        Assert.assertNotNull(responseGetSingleObject.jsonPath().getInt("id"), "'iD' is null");
        Assert.assertEquals(responseGetSingleObject.jsonPath().getString("name"), "Samsung A53 5G",
                "Expected name Samsung A53 5G, but got " + responseGetSingleObject.jsonPath().getString("name"));
        Assert.assertEquals(responseGetSingleObject.jsonPath().getString("data.year"), "2022",
                "Expected year 2022, but got " + responseGetSingleObject.jsonPath().getString("data.year"));
        Assert.assertEquals(responseGetSingleObject.jsonPath().getString("data.price"), "3000",
                "Expected price 3000, but got " + responseGetSingleObject.jsonPath().getString("data.price"));
        Assert.assertEquals(responseGetSingleObject.jsonPath().getString("data.cpu_model"), "Exynos",  
                "Expected cpu_model Exynos, but got " + responseGetSingleObject.jsonPath().getString("data.cpu_model"));
        Assert.assertEquals(responseGetSingleObject.jsonPath().getString("data.hard_disk_size"), "256 GB",
                "Expected hard_disk_size 256 GB, but got " + responseGetSingleObject.jsonPath().getString("data.hard_disk_size"));
        Assert.assertEquals(responseGetSingleObject.jsonPath().getString("data.capacity"), "123",
                "Expected capacity 123, but got " + responseGetSingleObject.jsonPath().getString("data.capacity"));
        Assert.assertEquals(responseGetSingleObject.jsonPath().getString("data.screen_size"), "5",
                "Expected screen_size 5, but got " + responseGetSingleObject.jsonPath().getString("data.screen_size"));
        Assert.assertEquals(responseGetSingleObject.jsonPath().getString("data.color"), "Black",
                "Expected color Black, but got " + responseGetSingleObject.jsonPath().getString("data.color"));

        System.out.println("Response: " + responseGetSingleObject.asPrettyString());

    }

    /*
     * SCENARIO DELETE OBJECT :
     * 1. Delete Object
     * 2. Get List By ID Object
     * 3. Get Single Object
     */

    @Test
    public void deleteObjectScenario() {
        RestAssured.baseURI = base_url;

        Response responseDeleteObject = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + tokenLogin)
            .log().all()
            .when()
            .delete("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/391");
            
            // + objectId);

        System.out.println("Validate Delete Object for ID: 391");
        Assert.assertEquals(responseDeleteObject.getStatusCode(), 200,
                "Expected status code 200, but got " + responseDeleteObject.statusCode());
        Assert.assertEquals(responseDeleteObject.jsonPath().getString("status"), "deleted",
                "Expected status deleted, but got " + responseDeleteObject.jsonPath().getString("status"));
        Assert.assertEquals(responseDeleteObject.jsonPath().getString("message"), "Object with id = 391, has been deleted.",
                "Expected message Object with id = 391, has been deleted., but got " + responseDeleteObject.jsonPath().getString("message"));

        System.out.println("Response: " + responseDeleteObject.asPrettyString());

        Response responseGetResponseById = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + tokenLogin)
            .log().all()
            .when()
            .get("/webhook/api/objectslistId?id=391");
        System.out.println("Validate Get List By the Deleted ID: 391");

        Assert.assertEquals(responseGetResponseById.getStatusCode(), 200,
                "Expected status code 200, but got " + responseGetResponseById.statusCode());
        Assert.assertEquals(responseGetResponseById.getBody().asString(), "", "Expected empty list of object, but got non-empty list");

        Response responseGetSingleObject = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + tokenLogin)
            .log().all()
            .when()
            .get("/webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/391");

        System.out.println("Validate Get Single Object for the Deleted ID: 391");
        Assert.assertEquals(responseGetSingleObject.getStatusCode(), 200,
                "Expected status code 200, but got " + responseGetSingleObject.statusCode());
        Assert.assertTrue(responseGetSingleObject.jsonPath().getMap("").isEmpty(), "'Single Object' is not empty");
    }
    
    
}