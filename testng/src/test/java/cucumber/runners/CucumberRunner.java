package cucumber.runners;

import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.AfterSuite;

import helper.GenerateReport;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/java/cucumber/features",
                 glue = {"cucumber.definitions", "cucumber.hooks"},
                 plugin = {"pretty", 
                    "json:target/cucumber-reports.json",
                    "html:target/cucumber-reports.html"},
                 monochrome = true)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    // This class is used to run Cucumber tests with TestNG
    // The @CucumberOptions annotation specifies the features, glue, and plugins
    @AfterSuite
    public void after_suite() {
        GenerateReport.generateReport();
        System.out.println("generate report");
    }
}