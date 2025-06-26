package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class ProductDisplayObject extends BaseObject {
    
    @FindBy(xpath = "//div[@class='card']//div//h5//b")
    public WebElement getProductName;

    @FindBy(xpath = "//div[@class='card-body']//div//div")
    public WebElement getProductPrice;

    @FindBy(xpath = "//div[@class='card-body']//button[normalize-space(text())='Add To Cart']")
    public WebElement addToCartButton;

    public ProductDisplayObject(WebDriver webDriver) {
        super(webDriver);

        // Init Element
        PageFactory.initElements(webDriver, this);
    }

}
