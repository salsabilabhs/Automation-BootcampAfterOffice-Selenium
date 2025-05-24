package scenario_e2e.pojo;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;
import com.demo.testng.program.model.UserModel;
import com.demo.testng.program.response_model.LoginUserResponse;
import com.demo.testng.program.response_model.RegisterUserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PojoRegisterUserE2E {


    @BeforeSuite
    public void setUp() {
        /*  
        ----- OLD WITHOUT POJO!! -----
        StaticVar.email = "mamang_16@gmail.com";
        StaticVar.fullName = "Mamang Hidayat";
        StaticVar.password = "t@st12345";
        StaticVar.department = "Human Resource";
        StaticVar.phoneNumber = "08123456789";
        */

        StaticVar.user = new UserModel();

        StaticVar.user.setEmail("mamang_38@gmail.com");
        StaticVar.user.setFullName("Mamang Hidayat");
        StaticVar.user.setPassword("t@st12345");
        StaticVar.user.setDepartment("Human Resource");
        StaticVar.user.setPhoneNumber("08123456789");
    }

   /*
    * SCENARIO REGISTER USER :
    * 1. Register User
    * 2. Login User
    */

    @Test
    public void RegisterUser() throws Exception {
        
        /*
        ----- OLD WITHOUT POJO!! -----
        String bodyRegister = "{"
                        + "\"email\": \""+ StaticVar.email +"\","
                        + "\"full_name\": \""+ StaticVar.fullName +"\","
                        + "\"password\": \""+ StaticVar.password +"\","
                        + "\"department\": \""+ StaticVar.department +"\","
                        + "\"phone_number\": \""+ StaticVar.phoneNumber +"\""
                        + "}";
        */

        ObjectMapper objectMapper = new ObjectMapper();
        String bodyRegister = objectMapper.writeValueAsString(StaticVar.user);

        Response resRegister = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(bodyRegister)
            .log().all()
            .when()
            .post(StaticVar.BASE_URL + "/api/register");

        System.out.println("Response: " + resRegister.asPrettyString());

        Assert.assertEquals(resRegister.getStatusCode(), 200, 
            "Expected status code 200, but got " + resRegister.statusCode());

        resRegister.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("register_user_schema.json"));
        
        RegisterUserResponse registerUserResponse = objectMapper.readValue(resRegister.body().asString(), RegisterUserResponse.class);
        
        Assert.assertNotNull(registerUserResponse.getId(), "'id' is null");
        Assert.assertEquals(registerUserResponse.getEmail(), StaticVar.user.getEmail(),
            "Expected email " + StaticVar.user.getEmail() + ", but got " + registerUserResponse.getEmail());
        Assert.assertEquals(registerUserResponse.getFullName(), StaticVar.user.getFullName(), 
            "Expected full_name " + StaticVar.user.getFullName() + ", but got " + registerUserResponse.getFullName());
        Assert.assertEquals(registerUserResponse.getDepartment(), StaticVar.user.getDepartment(),
            "Expected department " + StaticVar.user.getDepartment() + ", but got " + registerUserResponse.getDepartment());
        Assert.assertEquals(registerUserResponse.getPhoneNumber(), StaticVar.user.getPhoneNumber(),
            "Expected phone_number " + StaticVar.user.getPhoneNumber() + ", but got " + registerUserResponse.getPhoneNumber());
        Assert.assertNotNull(registerUserResponse.getCreatedAt(), "'created_at' is null");
        Assert.assertNotNull(registerUserResponse.getUpdatedAt(), "'updated_at' is null"); 

        System.out.println("Response: " + resRegister.asPrettyString());

        /* 
        ----- OLD WITHOUT POJO!! -----
        Assert.assertNotNull(resRegister.jsonPath().get("id"), "'id' is null");
        Assert.assertEquals(resRegister.jsonPath().getString("email"), StaticVar.email,
            "Expected email " + StaticVar.email + ", but got " + resRegister.jsonPath().getString("email"));
        Assert.assertEquals(resRegister.jsonPath().getString("full_name"), StaticVar.fullName, 
            "Expected full_name " + StaticVar.fullName + ", but got " + resRegister.jsonPath().getString("full_name"));
        Assert.assertEquals(resRegister.jsonPath().getString("department"), StaticVar.department,
            "Expected department " + StaticVar.department + ", but got " + resRegister.jsonPath().getString("department"));
        Assert.assertEquals(resRegister.jsonPath().getString("phone_number"), StaticVar.phoneNumber,
            "Expected phone_number " + StaticVar.phoneNumber + ", but got " + resRegister.jsonPath().getString("phone_number"));
        Assert.assertNotNull(resRegister.jsonPath().get("create_at"), "'created_at' is null");
        Assert.assertNotNull(resRegister.jsonPath().get("update_at"), "'updated_at' is null"); 
        */

    }

    @Test(dependsOnMethods = "RegisterUser", groups = "LoginGroup")
    public void LoginUser() throws Exception {

        /* 
         * ----- OLD WITHOUT POJO!! -----
         * String bodyLogin = "{"
                        + "\"email\": \""+ StaticVar.email +"\","
                        + "\"password\": \""+ StaticVar.password +"\""
                        + "}";
         */
        
         ObjectMapper objectMapper = new ObjectMapper();
         String bodyLogin = objectMapper.writeValueAsString(StaticVar.user);

        Response resLogin = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(bodyLogin)
            .log().all()
            .when()
            .post(StaticVar.BASE_URL + "/api/login");

        LoginUserResponse loginUserResponse = objectMapper.readValue(resLogin.body().asString(), LoginUserResponse.class);

        Assert.assertEquals(resLogin.getStatusCode(), 200, 
            "Expected status code 200, but got " + resLogin.statusCode());

        Assert.assertNotNull(loginUserResponse.getToken(), "'token' is null");
        // Extract value token & simpan ke variable token
        StaticVar.tokenLogin = loginUserResponse.getToken();

        System.out.println("Response: " + resLogin.asPrettyString());
    }
    
}
