package com.demo.testng.program.selenium_page_factory.base;

import org.openqa.selenium.WebDriver;

public class BaseObject {

    public WebDriver webDriver;

    // For BaseObject, no need to use "Wait"
    public BaseObject(WebDriver webDriver) {
        
        this.webDriver = webDriver;
    }
}
