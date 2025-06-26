package selenium_page_factory.object_repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class OrderObject extends BaseObject {

    @FindBy(xpath = "//h1[contains(text(), 'Thankyou for the order.')]")
    public WebElement orderSuccessMessage;

    public WebElement titleProductDynamic(String productName) {
        return webDriver.findElement(By.xpath("//div[contains(text(), '" + productName + "')]"));
    }

    public WebElement priceProductDynamic(String productPrice) {
        return webDriver.findElement(By.xpath("//div[contains(text(), '" + productPrice + "')]"));
    }

    public OrderObject(WebDriver webDriver) {
        super(webDriver);

        // Init Element
        PageFactory.initElements(webDriver, this);
    }
}
