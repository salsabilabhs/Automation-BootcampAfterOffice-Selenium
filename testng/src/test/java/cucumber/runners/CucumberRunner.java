package cucumber.runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources",
                 glue = "cucumber.definitions",
                 plugin = {"pretty", "json:target/cucumber-reports.json"},
                 monochrome = true)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    // This class is used to run Cucumber tests with TestNG
    // The @CucumberOptions annotation specifies the features, glue, and plugins
}