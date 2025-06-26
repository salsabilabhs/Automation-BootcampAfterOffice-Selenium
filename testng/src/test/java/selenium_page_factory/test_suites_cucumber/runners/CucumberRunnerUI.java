package selenium_page_factory.test_suites_cucumber.runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import selenium_page_factory.test_suites_cucumber.helper.GenerateReport;

import org.testng.annotations.AfterSuite;

@CucumberOptions(features = "src/test/java/selenium_page_factory/test_suites_cucumber/features",
                 glue = {"selenium_page_factory.test_suites_cucumber.definitions", "cucumber.hooks"},
                 plugin = {"pretty", 
                    "json:target/cucumber-reports.json",
                    "html:target/cucumber-reports.html"},
                 monochrome = true)

public class CucumberRunnerUI extends AbstractTestNGCucumberTests {
    // This class is used to run Cucumber tests with TestNG
    // The @CucumberOptions annotation specifies the features, glue, and plugins
    @AfterSuite
    public void after_suite() {
        GenerateReport.generateReport();
    }
}