package selenium;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Locator {
    // Public biar bisa dipakai di class/test case lain
    public WebDriver webDriver;
    public WebElement webElement;
    public Wait<WebDriver> wait;

    @BeforeSuite
    public void startBrowser() throws Exception {
        // Open the browser
        System.out.println("Starting browser.....");
        System.setProperty("webdriver.chrome.driver", "/Users/Maxxi/maven-project/afteroffice-selenium/chromedriver-win64/chromedriver.exe");
        
        // Initialize the WebDriver (e.g., ChromeDriver)
        webDriver = new ChromeDriver();
        
        // Open a website
        webDriver.get("https://rahulshettyacademy.com/client/");
        // Maximize the browser window
        webDriver.manage().window().maximize();
        
        // Timeout for the page to load
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    // @AfterSuite
    public void closeBrowser() {
        webDriver.close();
        System.out.println("Browser closed.");
    }

    @Test
    public void accessLoginPage() {
        String currentUrl = webDriver.getCurrentUrl();
        // Verify the URL
        Assert.assertTrue(currentUrl.contains("/auth/login"), "URL does not contain '/auth/login'");
    }
    
    @Test(dependsOnMethods = "accessLoginPage", groups = "fillEmail&Password")
    public void fillTheEmail() throws Exception {
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        WebElement emailField = webDriver.findElement(By.xpath("//input[@id='userEmail']"));
        emailField.sendKeys("simanjuntakalbert57@gmail.com");
    }

    @Test(dependsOnMethods = "fillTheEmail", groups = "fillEmail&Password")
    public void fillThePassword() throws Exception {
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        WebElement passwordField = webDriver.findElement(By.xpath("//input[@id='userPassword']"));
        passwordField.sendKeys("XBf@rWNvByn!#K8");
    }

    @Test(dependsOnGroups = "fillEmail&Password")
    public void clickLoginButton() throws Exception {
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        WebElement loginButton = webDriver.findElement(By.xpath("//input[@id='login']"));
        loginButton.click();
    }

    @Test(dependsOnMethods = "clickLoginButton")
    public void loggedInValidation() {
        // Wait for the next page to load
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        // Verify that the user is logged in by checking the URL
        WebElement signOutButton = webDriver.findElement(By.xpath("//button[contains(text(), 'Sign Out')]"));
        wait.until(d -> signOutButton.isDisplayed());
    }

}
