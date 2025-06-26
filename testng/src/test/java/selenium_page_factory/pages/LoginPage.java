package selenium_page_factory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import com.demo.testng.program.selenium_page_factory.base.BasePage;
import selenium_page_factory.object_repository.LoginObject;

public class LoginPage extends BasePage {

    // Define The Objects from LoginObject.java
    public LoginObject loginObject; 

    public LoginPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.loginObject = new LoginObject(webDriver);
    }

    public void fillTheEmail(String email) {
        loginObject.inputEmail.sendKeys(email);
    }

    public void fillThePassword(String password) {
        loginObject.inputPassword.sendKeys(password);
    }

    public void clickLoginBtn() {
        loginObject.loginButton.click();
    }
    
}
