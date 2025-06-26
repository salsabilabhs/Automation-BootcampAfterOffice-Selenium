package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class HomeObject extends BaseObject {
    
    @FindBy(xpath = "//ul//li//button[normalize-space(text())='Sign Out']")
    public WebElement signOutBtn;

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']//child::label")
    public WebElement counterCart;

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    public WebElement menuCartButton;

    @FindBy(xpath = "//section[@id='sidebar']//form//input[@name='search']")
    public WebElement inputSearch;  

    public HomeObject(WebDriver webDriver) {
        super(webDriver);

        // Init Element
        PageFactory.initElements(webDriver, this);
    }
}
