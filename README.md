# Automation-BootcampAfterOffice-Selenium

# WEEK 2 (TASK 2) - API Testing with TestNG & POJO
1. How to run the test?
   - Using Terminal write
     ``` mvn test -DsuiteXml="src\test\resources\testng_pojo_e2e.xml" ```
   - The result will be displaying on Terminal & Test Results  
2. E2E Test Scenario that i scripted;
   - Register and validation to login with registered data
   - Add Object & Validation on the list and single object after added the object
   - Delete Object & Validation on the list and single object after deleted the object
3. Location of the scripts :
   ```
   src\main\java\com\demo\testng\program\model
   src\main\java\com\demo\testng\program\response_model
   src\test\java\scenario_e2e\pojo
   src\test\resources\register_user_schema.json
   ```
5. TestNG Annotations that I used;
   - @BeforeSuite
   - @BeforeClass
  

# WEEK 1 - API Testing with Rest Assured
1. How do I set up the test?
    - Copy the RestAssured script from the Maven Repository
    - Add it as a dependency in the pom.xml file
    - Create a RestAssured folder to store the Java API Testing test files
2. How do I run the test?
    - Click the "play" icon on the API Testing test class to compile
    - See the DEBUG CONSOLE & TEST RESULTS
3. TestNG Annotations that I used;
    - @BeforeSuite - dijalankan sekali sebelum semua test dimulai
    - @BeforeMethod – dijalankan sekali sebelum test di class tersebut
    - @Test – method yang menandakan sebuah test case
    - @AfterSuite – dijalankan setelah semua test selesai
    - @Test(dependsOnMethods = {...}) – untuk membuat dependency antar test agar urutan eksekusi logis
    - Priority
4. Tested Features (Explanation):
    - Login API (testAPILogin_Positive & testAPILogin_Negative)
    - Register API (testAPIRegister_Positive & testAPIRegister_Negative)
    - Add Object (testAPIAddObject)
    - Update Object (testAPIUpdateObject)
    - Partially Update Object (testAPIPartiallyUpdateObject)
    - Get Single Object by ID (testAPIGetSingleObject_Positive)
    - Get All Departments (testAPIGetAllDepartment)
    - Get List of Objects by IDs (testAPIGetListOfObjectsByIds)
    - Get All Objects (testAPIGetAllObject)
    - Delete Object(testAPIDeleteObject_Positive & testAPIDeleteObject_Negative)

5. Example Command:

    A. Implementation using DependsonMethod;
    
    ```@Test(dependsOnMethods = {"testAPILogin_Positive", "testAPIAddObject", "testAPIUpdateObject", "testAPIPartiallyUpdateObject", "testAPIGetSingleObject_Positive"})
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
    ```

    B. Implementation using TestNG Annotations;
    
    ```
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
    ```
 
