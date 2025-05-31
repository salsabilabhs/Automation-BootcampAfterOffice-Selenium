package helper;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigManager {
    
    // Load .Env-Production
    private static final Dotenv dotenv = Dotenv.configure().filename(".env-production").load();

    public static String getBaseUrl() {
        return dotenv.get("BASE_URL");
    }
}
