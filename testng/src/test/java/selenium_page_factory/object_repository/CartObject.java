package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class CartObject extends BaseObject{

    @FindBy(xpath = "//div[@class='heading cf']//child::h1")
    public WebElement pageCartTitle;

    @FindBy(xpath = "//div[@class='cartSection']//h3")
    public WebElement getProductName;

    @FindBy(xpath = "//div[@class='prodTotal cartSection']//p")
    public WebElement getProductPrice;

    @FindBy(xpath = "//ul//li//button[normalize-space(text())='Checkout']")
    public WebElement checkoutButton;

    public CartObject(WebDriver webDriver) {
        super(webDriver);

        // Init Element
        PageFactory.initElements(webDriver, this);
    }
}
