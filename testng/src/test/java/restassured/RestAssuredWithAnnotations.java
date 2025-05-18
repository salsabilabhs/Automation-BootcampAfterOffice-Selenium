package restassured;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static org.testng.Assert.assertNotNull;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredWithAnnotations {
    // Variable untuk extract token agar bisa dipakai ke API lain
        String token;
        Integer id;

    @BeforeSuite
    public void BeforeSuite() {

        // Set the base URI for the REST API
        RestAssured.baseURI = "https://whitesmokehouse.com";

        String responseBody = "{\n"
                            + "  \"email\": \"salsabilabhs@gmail.com\",\n"
                            + "  \"password\": \"l@l@12345\"\n"
                            + "}";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(responseBody)
            .log().all()
            .when()
            .post("/webhook/api/login");
            // .then()
            // .statusCode(200)
            
        // Extract value token & simpan ke variable token
        token = response.jsonPath().getString("token");
        // System.out.println("Token: " + token);

        assert response.statusCode() == 200 : "Expected status code 200, but got " + response.statusCode();
        assert response.jsonPath().getString("token") != null : "Token should not be null";

        System.out.println("Response: " + response.asPrettyString());
    }

    @AfterSuite
    public void testAPILogin_Negative() {

        System.out.println("yang mau diapus" + id);

        RestAssured.baseURI = "https://whitesmokehouse.com";

        String responseBody = "{\n"
                        + "    \"email\": \"salsabilabhs1@gmail.com\",\n" 
                        + "    \"password\": \"l@l@12347\"\n" 
                        + "}";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(responseBody)
            .log().all()
            .when()
            .post("/webhook/api/login");

        assert response.statusCode() == 200 : "Expected status code 200, but got " + response.statusCode();
        assert response.jsonPath().getString("status").equals("success") : "Expected result 'success', but got " + response.jsonPath().getString("status");
        assert response.jsonPath().getString("message").equals("This email= salsabilabhs1@gmail.com has not been registered yet.") : "Expected message 'This email= salsabilabhs1@gmail.com has not been registered yet.', but got " + response.jsonPath().getString("message");
        
        System.out.println("Response: " + response.asPrettyString());
    }

    @Test(priority = 7)
    public void testAPIRegister_Positive() {
        RestAssured.baseURI = "https://whitesmokehouse.com";
        
        String bodyRegister = "{"
                        + "\"email\": \"salsabilabhs112@gmail.com\","
                        + "\"full_name\": \"Salsabila Bahhas 112\","
                        + "\"password\": \"l@l@12345\","
                        + "\"department\": \"branch manager\","
                        + "\"phone_number\": \"08123456789\""
                        + "}";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            // Passing token dari APILogin ke API Register
            .header("Authorization", "Bearer" + token)
            .body(bodyRegister)
            .log().all()
            .when()
            .post("/webhook/api/register");

            assert response.statusCode() == 200 : "Expected status code 200, but got " + response.statusCode();
            assert response.jsonPath().getString("email").equals("salsabilabhs112@gmail.com") : "Expected email salsabilabhs112@gmail.com, but got " + response.jsonPath().getString("email");
            assert response.jsonPath().getString("full_name").equals("Salsabila Bahhas 112") : "Expected full name Salsabila Bahhas 112, but got " + response.jsonPath().getString("full_name");
            assert response.jsonPath().getString("department").equals("branch manager") : "Expected department branch manager, but got " + response.jsonPath().getString("department");
            assert response.jsonPath().getString("phone_number").equals("08123456789") : "Expected phone number 08123456789, but got " + response.jsonPath().getString("phone_number");

            System.out.println("Response: " + response.asPrettyString());
    }

    @Test(priority = 8)
    public void testAPIRegister_Negative() {
        RestAssured.baseURI = "https://whitesmokehouse.com";
        
        String bodyRegister = "{"
                        + "\"email\": \"salsabilabhs@gmail.com\","
                        + "\"full_name\": \"Salsabila Bahhas\","
                        + "\"password\": \"l@l@12345\","
                        + "\"department\": \"branch manager\","
                        + "\"phone_number\": \"08123456789\""
                        + "}";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            // Passing token dari APILogin ke API Register
            .header("Authorization", "Bearer" + token)
            .body(bodyRegister)
            .log().all()
            .when()
            .post("/webhook/api/register");

            assert response.statusCode() == 200 : "Expected status code 200, but got " + response.statusCode();
            assert response.jsonPath().getString("result").equals("failed") : "Expected email salsabilabhs10@gmail.com, but got " + response.jsonPath().getString("result");
            assert response.jsonPath().getString("message").equals("Email = salsabilabhs@gmail.com already registered") : "Expected message 'Email = salsabilabhs@gmail.com already registered', but got " + response.jsonPath().getString("message");

            System.out.println("Response: " + response.asPrettyString());
    }

    @BeforeMethod
    public void testAPIAddObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com";
        
        String bodyAddObject =   "{\n"
                            + "  \"name\": \"Tab Galaxy S10\",\n"
                            + "  \"data\": {\n"
                            + "    \"year\": 2025,\n"
                            + "    \"price\": 1000.87,\n" 
                            + "    \"cpu_model\": \"iOS 17\",\n" 
                            + "    \"hard_disk_size\": \"1 TB\",\n" 
                            + "    \"capacity\": \"255\",\n" 
                            + "    \"screen_size\": \"7 Inch\",\n" 
                            + "    \"color\": \"Black\"\n" 
                            + "  }\n" 
                            + "}";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            // Passing token dari APILogin ke API Add Object
            .header("Authorization", "Bearer " + token)
            .body(bodyAddObject)
            .log().all()
            .when()
            .post("/webhook/api/objects");

            // Extract value id & simpan ke variable id
            id = response.jsonPath().getInt("[0].id");

            assert response.statusCode() == 200 : "Expected status code 200, but got " + response.statusCode();
            assert response.jsonPath().getString("[0].name").equals("Tab Galaxy S10") : "Expected name 'Tab Galaxy S10', but got " + response.jsonPath().getString("[0].name");
            assert response.jsonPath().getInt("[0].data.year") == 2025 : "Expected year '2025', but got " + response.jsonPath().getInt("[0].data.year"); 
            assert response.jsonPath().getDouble("[0].data.price") == 1000.87 : "Expected price '1000.87', but got " + response.jsonPath().getDouble("[0].data.price");
            assert response.jsonPath().getString("[0].data.cpu_model").equals("iOS 17") : "Expected cpu_model 'iOS 17', but got " + response.jsonPath().getString("[0].data.cpu_model");
            assert response.jsonPath().getString("[0].data.hard_disk_size").equals("1 TB") : "Expected hard_disk_size '1 TB', but got " + response.jsonPath().getString("[0].data.hard_disk_size");
            assert response.jsonPath().getString("[0].data.capacity").equals("255") : "Expected capacity '255', but got " + response.jsonPath().getString("[0].data.capacity");
            assert response.jsonPath().getString("[0].data.screen_size").equals("7 Inch") : "Expected screen_size '7 Inch', but got " + response.jsonPath().getString("[0].data.screen_size");
            assert response.jsonPath().getString("[0].data.color").equals("Black") : "Expected color 'Black', but got " + response.jsonPath().getString("[0].data.color");

            System.out.println("Object has been added successfully with ID: " + id);
            System.out.println("Response: " + response.asPrettyString());

    }

    @Test(priority = 2)
    public void testAPIUpdateObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com";
        
        String bodyUpdateObject = "{\n" +
                        "  \"name\": \"Samsung Flip 17\",\n" +
                        "  \"data\": {\n" +
                        "    \"year\": 2029,\n" +
                        "    \"price\": 20987.72,\n" +
                        "    \"cpu_model\": \"Exynos\",\n" +
                        "    \"hard_disk_size\": \"2 GB\",\n" +
                        "    \"capacity\": \"120\",\n" +
                        "    \"screen_size\": \"7.6 Inch\",\n" +
                        "    \"color\": \"Purple\"\n" +
                        "  }\n" +
                        "}";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            // Passing token dari APILogin ke API Update Object
            .header("Authorization", "Bearer " + token)
            // Passing id dari APIAddObjects ke API Update Object
            .pathParam("id", id)
            .body(bodyUpdateObject)
            .log().all()
            .when()
            .put("/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/{id}");

        assert response.statusCode() == 200 : "Expected status code 200, but got " + response.statusCode();
        assert response.jsonPath().getString("[0].name").equals("Samsung Flip 17") : "Expected name 'Samsung Flip 17', but got " + response.jsonPath().getString("[0].name");
        assert response.jsonPath().getInt("[0].data.year") == 2029 : "Expected year '2029', but got " + response.jsonPath().getInt("[0].data.year");
        assert response.jsonPath().getDouble("[0].data.price") == 20987.72 : "Expected price '20987.72', but got " + response.jsonPath().getDouble("[0].data.price");
        assert response.jsonPath().getString("[0].data['CPU model']").equals("Exynos") : "Expected cpu_model 'Exynos', but got " + response.jsonPath().getString("[0].data['CPU model']");
        assert response.jsonPath().getString("[0].data['Hard disk size']").equals("2 GB") : "Expected hard_disk_size '2 GB', but got " + response.jsonPath().getString("[0].data['Hard disk size']");
        assert response.jsonPath().getString("[0].data.capacity").equals("120") : "Expected capacity '120, but got " + response.jsonPath().getString("[0].data.capacity");
        assert response.jsonPath().getString("[0].data.screen_size").equals("7.6 Inch") : "Expected screen_size '7.6 Inch', but got " + response.jsonPath().getString("[0].data.screen_size");
        assert response.jsonPath().getString("[0].data.color").equals("Purple") : "Expected color 'Purple', but got " + response.jsonPath().getString("[0].data.color"); 
        
        System.out.println("Object with ID " + id + " has been updated successfully.");
        System.out.println("Response: " + response.asPrettyString());
    }

    @Test(priority = 1)
    public void testAPIPartiallyUpdateObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        String bodyPartialUpdate = "{\n"
                        + "  \"name\": \"[PROMO] Galaxy S10\",\n"
                        + "  \"year\": \"2030\"\n"
                        + "}";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            // Passing token dari APILogin ke API Partially Update Object
            .header("Authorization", "Bearer " + token)
            // Passing id dari APIAddObjects ke API Partially Update Object
            .pathParam("id", id)
            .body(bodyPartialUpdate)
            .log().all()
            .when()
            .patch("/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/{id}");

        assert response.statusCode() == 200 : "Expected status code 200, but got " + response.statusCode();
        assert response.jsonPath().getString("name").equals("[PROMO] Galaxy S10") : "Expected name '[PROMO] Galaxy S10', but got " + response.jsonPath().getString("name");
        assert response.jsonPath().getInt("data.year") == 2030 : "Expected year '2030', but got " + response.jsonPath().getInt("data.year");
        assert response.jsonPath().getDouble("data.price") == 1000.87 : "Expected price '1000.87', but got " + response.jsonPath().getDouble("data.price");
        assert response.jsonPath().getString("data.cpu_model").equals("iOS 17") : "Expected cpu_model 'iOS 17', but got " + response.jsonPath().getString("data.cpu_model");
        assert response.jsonPath().getString("data.hard_disk_size").equals("1 TB") : "Expected hard_disk_size '1 TB', but got " + response.jsonPath().getString("data.hard_disk_size");
        assert response.jsonPath().getString("data.capacity").equals("255") : "Expected capacity '255', but got " + response.jsonPath().getString("data.capacity");
        assert response.jsonPath().getString("data.screen_size").equals("7 Inch") : "Expected screen_size '7 Inch', but got " + response.jsonPath().getString("data.screen_size");
        assert response.jsonPath().getString("data.color").equals("Black") : "Expected color 'Black', but got " + response.jsonPath().getString("data.color");
        
        System.out.println("Object with ID " + id + " has been partially updated successfully.");
        System.out.println("Response: " + response.asPrettyString());
    }

    @Test(priority = 5)
    public void testAPIGetAllDepartment() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            // Passing token dari APILogin ke API Get All Department
            .header("Authorization", "Bearer " + token)
            .log().all()
            .when()
            .get("/webhook/api/department");

        assert response.statusCode() == 200 : "Expected status code 200, but got " + response.statusCode();
        assert response.jsonPath().getList("").size() > 0 : "Expected non-empty list of departments, but got empty list";

        if(response.jsonPath().getList("").size() > 0) {
            assertNotNull(response.jsonPath().get("[0].id"), "Expected department ID to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].department"), "Expected department name to be present, but got null");

            System.out.println("Data for the first department in the list is not null.");
        }

        System.out.println("Response: " + response.asPrettyString());
    }

    @Test(priority = 6)
    public void testAPIDeleteObject_Positive() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            // Passing token dari APILogin ke API Delete Object
            .header("Authorization", "Bearer " + token)
            // Passing id dari APIAddObjects ke API Delete Object
            .pathParam("id", id)
            .log().all()
            .when()
            .delete("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/{id}");

        assert response.statusCode() == 200 : "Expected status code 200, but got " + response.statusCode();
        assert response.jsonPath().getString("status").equals("deleted") : "Expected result 'deleted', but got " + response.jsonPath().getString("status");
        assert response.jsonPath().getString("message").equals("Object with id = " + id + ", has been deleted.") : "Expected deletion message, but got " + response.jsonPath().getString("message");

        System.out.println("Object with ID " + id + " has been deleted successfully.");
        System.out.println("Response: " + response.asPrettyString());
    }

    @Test(dependsOnMethods = "testAPIDeleteObject_Positive")
    public void testAPIDeleteObject_Negative() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            // Passing token dari APILogin ke API Delete Object
            .header("Authorization", "Bearer " + token)
            // Passing id yang sudah dihapus sebelumnya
            .pathParam("id", id)
            .log().all()
            .when()
            .delete("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/{id}");

        assert response.statusCode() == 200 : "Expected status code 200, but got " + response.statusCode();
        assert response.jsonPath().getString("status").equals("failed") : "Expected result 'failed', but got " + response.jsonPath().getString("status");
        assert response.jsonPath().getString("message").equals("object with id = " + id + " has been deleted or doesn't exist") : "Expected not found message, but got " + response.jsonPath().getString("message");

        System.out.println("Attempt to delete object with ID " + id + " after deletion returned expected failed.");
        System.out.println("Response: " + response.asPrettyString());
    }

    @Test(priority = 0)
    public void testAPIGetSingleObject_Positive() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            // Passing token dari APILogin ke API Get Single Object
            .header("Authorization", "Bearer " + token)
            // Passing id dari APIAddObjects ke API Get Single Object
            .pathParam("id", id)
            .log().all()
            .when()
            .get("webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/{id}");

        assert response.statusCode() == 200 : "Expected status code 200, but got " + response.statusCode();
        assert response.jsonPath().getInt("id") == id : "Expected name '" + id + "', but got " + response.jsonPath().getString("id");
        assert response.jsonPath().getString("name").equals("Tab Galaxy S10") : "Expected name 'Tab Galaxy S10', but got " + response.jsonPath().getString("name");
        assert response.jsonPath().getInt("data.year") == 2025 : "Expected year '2025', but got " + response.jsonPath().getInt("data.year"); 
        assert response.jsonPath().getDouble("data.price") == 1000.87 : "Expected price '1000.87', but got " + response.jsonPath().getDouble("data.price");
        assert response.jsonPath().getString("data.cpu_model").equals("iOS 17") : "Expected cpu_model 'iOS 17', but got " + response.jsonPath().getString("data.cpu_model");
        assert response.jsonPath().getString("data.hard_disk_size").equals("1 TB") : "Expected hard_disk_size '1 TB', but got " + response.jsonPath().getString("data.hard_disk_size");
        assert response.jsonPath().getString("data.capacity").equals("255") : "Expected capacity '255', but got " + response.jsonPath().getString("data.capacity");
        assert response.jsonPath().getString("data.screen_size").equals("7") : "Expected screen_size '7', but got " + response.jsonPath().getString("data.screen_size");
        assert response.jsonPath().getString("data.color").equals("Black") : "Expected color 'Black', but got " + response.jsonPath().getString("data.color");
      
        System.out.println("Object with ID " + id + " has been retrieved successfully.");
        System.out.println("Response: " + response.asPrettyString());
    }

    @Test(priority = 3)
    public void testAPIGetListOfObjectsByIds() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            // Passing token dari APILogin ke API Get List of Objects by IDs
            .header("Authorization", "Bearer " + token)
            .pathParam("id", id)
            .log().all()
            .when()
            .get("/webhook/api/objects?id={id}");

        assert response.statusCode() == 200 : "Expected status code 200, but got " + response.statusCode();
        assert response.jsonPath().getList("").size() > 0 : "Expected non-empty list of objects, but got empty list";
        
        if(response.jsonPath().getList("").size() > 0) {
            assertNotNull(response.jsonPath().get("[0].id"), "Expected object ID to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].name"), "Expected object name to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].data.year"), "Expected object year to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].data.price"), "Expected object price to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].data['CPU model']"), "Expected object CPU model to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].data['Hard disk size']"), "Expected object hard disk size to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].data.capacity"), "Expected object capacity to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].data.screen_size"), "Expected object screen size to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].data.color"), "Expected object color to be present, but got null");

            System.out.println("Data for the first object in the list is not null.");
        }
    }

    @Test(priority = 4)
    public void testAPIGetAllObject() {

        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            // Passing token dari APILogin ke API Get All Object
            .header("Authorization", "Bearer " + token)
            .log().all()
            .when()
            .get("/webhook/api/objects");


        assert response.statusCode() == 200 : "Expected status code 200, but got " + response.statusCode();
        assert response.jsonPath().getList("").size() > 0 : "Expected non-empty list of objects, but got empty list";

        if(response.jsonPath().getList("").size() > 0) {
            assertNotNull(response.jsonPath().get("[0].id"), "Expected object ID to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].name"), "Expected object name to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].data.year"), "Expected object year to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].data.price"), "Expected object price to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].data['CPU model']"), "Expected object CPU model to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].data['Hard disk size']"), "Expected object hard disk size to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].data.capacity"), "Expected object capacity to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].data.screen_size"), "Expected object screen size to be present, but got null");
            assertNotNull(response.jsonPath().get("[0].data.color"), "Expected object color to be present, but got null");

            System.out.println("Data for the first object in the list is not null.");
        }

    } 


}
