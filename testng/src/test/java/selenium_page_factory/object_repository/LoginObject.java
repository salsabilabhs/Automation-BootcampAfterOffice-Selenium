package selenium_page_factory.object_repository;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginObject extends BaseObject {
    
    @FindBy(xpath = "//input[@id='userEmail']")
    public WebElement inputEmail;

    @FindBy(xpath = "//input[@id='userPassword']")
    public WebElement inputPassword;  
    
    @FindBy(xpath = "//input[@id='login']")
    public WebElement loginButton;

    public LoginObject(WebDriver webDriver) {
        super(webDriver);

        // Init Element
        PageFactory.initElements(webDriver, this);
    }
}
