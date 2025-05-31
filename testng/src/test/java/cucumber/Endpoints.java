package cucumber;

import io.restassured.response.Response;
import cucumber.definitions.AddObject;
import cucumber.definitions.UpdateObject;
import cucumber.hooks.Hooks;
import helper.ConfigManager;
import io.restassured.RestAssured;

public class Endpoints {

    private static String baseUrl;

    public String getBaseUrl() {
        if (baseUrl == null) {
            baseUrl = ConfigManager.getBaseUrl();
            RestAssured.baseURI = baseUrl;
        }
        
        return baseUrl;
    }

    public String dynamicUrlResolver(String url) {
        switch (url) {
            case "/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/":
                url = url + getAddedObjectId(); //with ID that have been added
                break;

            case "/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/":
                url = url + getFirstObjectId();
                break;
        
            default:
                break;
        }
        
        return url;
    }

    public Response sendRequest(String method, String url, String body) {
        
        Response response = RestAssured
                    .given()
                    .contentType("application/json")
                    .header("Authorization", getHeaderToken())
                    .body(body)
                    .when()
                    .request(method, getBaseUrl() + dynamicUrlResolver(url));

        System.out.println("Sending " + method + " request to: " + getBaseUrl() + dynamicUrlResolver(url));
        return response;
    
    }

    public String getHeaderToken() {
        return Hooks.token != null ? "Bearer " + Hooks.token : "";
    }

    public String getAddedObjectId() {
        System.out.println("Successful Added Object with ID: " + AddObject.addedObjectId);
        return AddObject.addedObjectId;
    }

    public String getFirstObjectId() {
        System.out.println("First Object ID: " + UpdateObject.firstObjectId);
        return UpdateObject.firstObjectId;
    }

}
