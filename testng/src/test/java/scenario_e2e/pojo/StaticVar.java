package scenario_e2e.pojo;

import com.demo.testng.program.model.ObjectModel;
import com.demo.testng.program.model.UserModel;

public class StaticVar {

    public final static String BASE_URL = "https://whitesmokehouse.com/webhook";
    public static String tokenLogin;
    public static int objectId;

    // public static String email;
    // public static String fullName;
    // public static String password;
    // public static String department;
    // public static String phoneNumber;

    public static UserModel user;

    
    public static ObjectModel object;
    public static ObjectModel.DataDetail objectDataDetail;

}
