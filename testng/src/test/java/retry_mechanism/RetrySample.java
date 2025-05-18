package retry_mechanism;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetrySample implements IRetryAnalyzer {
    private int retryCount = 0;
    // Static Final itu tetap, gak bisa diubah lagi
    private static final int maxRetryCount = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying test " + result.getName() + " for the " + retryCount + " time.");
            return true; // Retry the test
        }
        return false; // Do not retry
    }
}
