package cucumber.definitions;

public class SharedData {
    
    /*
    ===== OLD DEFINITION OF BASEURL BEFORE REFACTORING =====
    public static String baseUrl = "https://whitesmokehouse.com/webhook";
    

    private static String baseUrl;
    
    public static String getBaseUrl() {
    if (baseUrl == null) {
        baseUrl = ConfigManager.getBaseUrl();
    }
    return baseUrl;
    }
    */
    
    /*
     * ==== BEFORE REFACTOR TO ENDPOINTS.JAVA ====
     * public static String tokenAuthentication;
     */
    
    public static String objectId;
    public static String firstObjectId;
    public static String deletedObjectId;
    public static String addedObjectId;

    
}
