package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryTest {
    public static int count = 0;

    @Test(retryAnalyzer = retry_mechanism.RetrySample.class)
    public void flackyTest() {
        count++;
        System.out.println("Running test method " + count);
        Assert.assertTrue(count > 2);
    }
}
