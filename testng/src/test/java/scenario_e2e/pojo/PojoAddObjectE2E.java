package scenario_e2e.pojo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demo.testng.program.response_model.AddObjectResponse;
import com.demo.testng.program.response_model.GetListObjectByIdResponse;
import com.demo.testng.program.response_model.GetSingleObjectResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import io.restassured.RestAssured;
import com.demo.testng.program.model.ObjectModel;
import io.restassured.response.Response;

public class PojoAddObjectE2E {

   @BeforeClass
   public void setUp() {

        /* 
        ----- OLD WITHOUT POJO -----
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
        */
        
        StaticVar.object = new ObjectModel();
        StaticVar.objectDataDetail = new ObjectModel.DataDetail();

        StaticVar.object.setName("Samsung A53 5G");
        StaticVar.objectDataDetail.setYear("2022");
        StaticVar.objectDataDetail.setPrice(3000.0);
        StaticVar.objectDataDetail.setCpuModel("Exynos");
        StaticVar.objectDataDetail.setHardDiskSize("256 GB");
        StaticVar.objectDataDetail.setCapacity("123");
        StaticVar.objectDataDetail.setScreenSize("5");
        StaticVar.objectDataDetail.setColor("Black");

        StaticVar.object.setData(StaticVar.objectDataDetail);
    }

    /*
     * SCENARIO ADD OBJECT :
     * 1. Add Object
     * 2. Get List By ID Object
     * 3. Get Single Object
     */

    @Test(dependsOnGroups = "LoginGroup")
    public void addObject() throws Exception {


        ObjectMapper objectMapper = new ObjectMapper();
        String bodyAddObject = objectMapper.writeValueAsString(StaticVar.object);

        Response resAddObject = RestAssured.given()
            .header("Content-Type", "application/json")
            // Passing token dari APILogin ke API Add Object
            .header("Authorization", "Bearer " + StaticVar.tokenLogin)
            .body(bodyAddObject)
            .log().all()
            .when()
            .post(StaticVar.BASE_URL +"/api/objects");

        // Extract value id & simpan ke variable id
        StaticVar.objectId = resAddObject.jsonPath().getInt("[0].id");
        System.out.println("Object has been added successfully with ID: " + StaticVar.objectId);

        Assert.assertEquals(resAddObject.getStatusCode(), 200,
                "Expected status code 200, but got " + resAddObject.statusCode());

        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(resAddObject.body().asString(),new TypeReference<List<AddObjectResponse>>() {});

        Assert.assertNotNull(addObjectResponse.get(0).getId(), "'ID' is null");

        Assert.assertEquals(addObjectResponse.get(0).getName(), StaticVar.object.getName(),
                "Expected name "+ StaticVar.object.getName() +", but got " + addObjectResponse.get(0).getName());

        Assert.assertEquals(addObjectResponse.get(0).getData().getYear(), StaticVar.objectDataDetail.getYear(),
                "Expected year "+ StaticVar.objectDataDetail.getYear() +", but got " + addObjectResponse.get(0).getData().getYear());

        Assert.assertEquals(addObjectResponse.get(0).getData().getPrice(), StaticVar.objectDataDetail.getPrice(),
                "Expected price "+ StaticVar.objectDataDetail.getPrice() +", but got " + addObjectResponse.get(0).getData().getPrice());

        Assert.assertEquals(addObjectResponse.get(0).getData().getCpuModel(), StaticVar.objectDataDetail.getCpuModel(),
                "Expected cpu_model "+ StaticVar.objectDataDetail.getCpuModel() +", but got " + addObjectResponse.get(0).getData().getCpuModel());

        Assert.assertEquals(addObjectResponse.get(0).getData().getHardDiskSize(), StaticVar.objectDataDetail.getHardDiskSize(),
                "Expected hard_disk_size "+ StaticVar.objectDataDetail.getHardDiskSize() +", but got " + addObjectResponse.get(0).getData().getHardDiskSize());

        Assert.assertEquals(addObjectResponse.get(0).getData().getCapacity(), StaticVar.objectDataDetail.getCapacity(),
                "Expected capacity "+ StaticVar.objectDataDetail.getCapacity() +", but got " + addObjectResponse.get(0).getData().getCapacity());

        Assert.assertEquals(addObjectResponse.get(0).getData().getScreenSize(), StaticVar.objectDataDetail.getScreenSize(),
                "Expected screen_size "+ StaticVar.objectDataDetail.getScreenSize() +", but got " + addObjectResponse.get(0).getData().getScreenSize());

        Assert.assertEquals(addObjectResponse.get(0).getData().getColor(), StaticVar.objectDataDetail.getColor(),
                "Expected color "+ StaticVar.objectDataDetail.getColor() +", but got " + addObjectResponse.get(0).getData().getColor());

        System.out.println("Response: " + resAddObject.asPrettyString());


        /* 
        ----- OLD WITHOUT POJO -----
         * Assert.assertNotNull(resAddObject.jsonPath().getInt("[0].id"), "'iD' is null");
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
        */

    }

        
    @Test(dependsOnMethods = "addObject", groups = "assertAddObject", priority = 1)
    public void getListObjectbyId() throws Exception {
        
        Response resGetListById = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + StaticVar.tokenLogin)
            .log().all()
            .when()
            .get(StaticVar.BASE_URL +"/api/objectslistId?id=" + StaticVar.objectId);

        System.out.println("Validate Get List By ID: " + StaticVar.objectId);

        Assert.assertEquals(resGetListById.getStatusCode(), 200,
                "Expected status code 200, but got " + resGetListById.statusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        List<GetListObjectByIdResponse> getListObjectByIdResponse = objectMapper.readValue(resGetListById.body().asString(), new TypeReference<List<GetListObjectByIdResponse>>() {});

        Assert.assertNotNull(getListObjectByIdResponse.get(0).getId(), "'ID' is null");

        Assert.assertEquals(getListObjectByIdResponse.get(0).getName(), StaticVar.object.getName(),
                "Expected name " + StaticVar.object.getName() + ", but got " + getListObjectByIdResponse.get(0).getName());

        Assert.assertEquals(getListObjectByIdResponse.get(0).getData().getYear(), StaticVar.objectDataDetail.getYear(),
                "Expected year " + StaticVar.objectDataDetail.getYear() + ", but got " + getListObjectByIdResponse.get(0).getData().getYear());

        Assert.assertEquals(getListObjectByIdResponse.get(0).getData().getPrice(), StaticVar.objectDataDetail.getPrice(),
                "Expected price " + StaticVar.objectDataDetail.getPrice() + ", but got " + getListObjectByIdResponse.get(0).getData().getPrice());

        Assert.assertEquals(getListObjectByIdResponse.get(0).getData().getCpuModel(), StaticVar.objectDataDetail.getCpuModel(),
                "Expected cpu_model " + StaticVar.objectDataDetail.getCpuModel() + ", but got " + getListObjectByIdResponse.get(0).getData().getCpuModel());

        Assert.assertEquals(getListObjectByIdResponse.get(0).getData().getHardDiskSize(), StaticVar.objectDataDetail.getHardDiskSize(),
                "Expected hard_disk_size " + StaticVar.objectDataDetail.getHardDiskSize() + ", but got " + getListObjectByIdResponse.get(0).getData().getHardDiskSize());

        Assert.assertEquals(getListObjectByIdResponse.get(0).getData().getCapacity(), StaticVar.objectDataDetail.getCapacity(),
                "Expected capacity " + StaticVar.objectDataDetail.getCapacity() + ", but got " + getListObjectByIdResponse.get(0).getData().getCapacity());

        Assert.assertEquals(getListObjectByIdResponse.get(0).getData().getScreenSize(), StaticVar.objectDataDetail.getScreenSize(),
                "Expected screen_size " + StaticVar.objectDataDetail.getScreenSize() + ", but got " + getListObjectByIdResponse.get(0).getData().getScreenSize());

        Assert.assertEquals(getListObjectByIdResponse.get(0).getData().getColor(), StaticVar.objectDataDetail.getColor(),
                "Expected color " + StaticVar.objectDataDetail.getColor() + ", but got " + getListObjectByIdResponse.get(0).getData().getColor());

        System.out.println("Response: " + resGetListById.asPrettyString());

        /*
         * ----- OLD WITHOUT POJO -----
         * 
         * Assert.assertNotNull(resGetListById.jsonPath().getInt("[0].id"), "'iD' is null");
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
         */


    }
        
    @Test(dependsOnMethods = "getListObjectbyId", groups = "assertAddObject", priority = 2)
    public void getSingleObject() throws Exception {
        Response resGetSingleObject = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + StaticVar.tokenLogin)
            .log().all()
            .when()
            .get(StaticVar.BASE_URL +"/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/" + StaticVar.objectId);

        System.out.println("Validate Get Single Object for ID: " + StaticVar.objectId);

        Assert.assertEquals(resGetSingleObject.getStatusCode(), 200,
                "Expected status code 200, but got " + resGetSingleObject.statusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        GetSingleObjectResponse getSingleObjectResponse = objectMapper.readValue(resGetSingleObject.body().asString(), GetSingleObjectResponse.class);
        
        Assert.assertNotNull(getSingleObjectResponse.getId(), "'ID' is null");

        Assert.assertEquals(getSingleObjectResponse.getName(), StaticVar.object.getName(),
                "Expected name " + StaticVar.object.getName() + ", but got " + getSingleObjectResponse.getName());

        Assert.assertEquals(getSingleObjectResponse.getData().getYear(), StaticVar.objectDataDetail.getYear(),
                "Expected year " + StaticVar.objectDataDetail.getYear() + ", but got " + getSingleObjectResponse.getData().getYear());

        Assert.assertEquals(getSingleObjectResponse.getData().getPrice(), StaticVar.objectDataDetail.getPrice(),
                "Expected price " + StaticVar.objectDataDetail.getPrice() + ", but got " + getSingleObjectResponse.getData().getPrice());

        Assert.assertEquals(getSingleObjectResponse.getData().getCpuModel(), StaticVar.objectDataDetail.getCpuModel(),
                "Expected cpu_model " + StaticVar.objectDataDetail.getCpuModel() + ", but got " + getSingleObjectResponse.getData().getCpuModel());

        Assert.assertEquals(getSingleObjectResponse.getData().getHardDiskSize(), StaticVar.objectDataDetail.getHardDiskSize(),
                "Expected hard_disk_size " + StaticVar.objectDataDetail.getHardDiskSize() + ", but got " + getSingleObjectResponse.getData().getHardDiskSize());

        Assert.assertEquals(getSingleObjectResponse.getData().getCapacity(), StaticVar.objectDataDetail.getCapacity(),
                "Expected capacity " + StaticVar.objectDataDetail.getCapacity() + ", but got " + getSingleObjectResponse.getData().getCapacity());

        Assert.assertEquals(getSingleObjectResponse.getData().getScreenSize(), StaticVar.objectDataDetail.getScreenSize(),
                "Expected screen_size " + StaticVar.objectDataDetail.getScreenSize() + ", but got " + getSingleObjectResponse.getData().getScreenSize());

        Assert.assertEquals(getSingleObjectResponse.getData().getColor(), StaticVar.objectDataDetail.getColor(),
                "Expected color " + StaticVar.objectDataDetail.getColor() + ", but got " + getSingleObjectResponse.getData().getColor());

        System.out.println("Response: " + resGetSingleObject.asPrettyString());

        /*
         * ----- OLD WITHOUT POJO -----
         *  
         * Assert.assertEquals(resGetSingleObject.getStatusCode(), 200,
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

         */
        
    }
    
}