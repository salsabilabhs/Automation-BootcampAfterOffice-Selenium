package testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
    @DataProvider(name = "data-provider")
    public static Object[][] dataProviderMethod() {
        return new Object[][] {
            { "data1", 1 },
            { "data2", 2 },
            { "data3", 3 }
        };
    }
    
    @Test(dataProvider = "data-provider")
    public void testMethod(String data, int number) {
        System.out.println("Data: " + data + ", Number: " + number);
    }
}
