package cucumber.runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/java/cucumber/features",
                 glue = {"cucumber.definitions", "cucumber.hooks"},
                 plugin = {"pretty", "json:target/cucumber-reports.json"},
                 monochrome = true)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    // This class is used to run Cucumber tests with TestNG
    // The @CucumberOptions annotation specifies the features, glue, and plugins
}