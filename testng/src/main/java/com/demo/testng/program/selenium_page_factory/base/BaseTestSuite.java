package com.demo.testng.program.selenium_page_factory.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.testng.program.selenium_page_factory.constant.Env;

public abstract class BaseTestSuite {

    public WebDriver webDriver;
    public Wait<WebDriver> wait;

    public void setUp() {
        
        // Open the browser
        System.out.println("Starting browser.....");
        System.setProperty("webdriver.chrome.driver", Env.DRIVER_PATH);
        
        // Initialize the WebDriver (e.g., ChromeDriver)
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }

    public void openUrl(String url) {
        
        // Open a website
        webDriver.get(url);
        // Maximize the browser window
        webDriver.manage().window().maximize();
        
        // Timeout for the page to load
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void tearDown() {
        webDriver.close();
    }
}
