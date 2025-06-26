package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class CheckoutObject extends BaseObject {

    @FindBy(xpath = "//div[@class='payment__title']")
    public WebElement pageCheckoutTitle;

    @FindBy(xpath = "//div[contains(text(), 'Credit Card Number')]//following-sibling::input")
    public WebElement getCreditNumber;

    @FindBy(xpath = "//div[contains(text(), 'CVV Code')]//following-sibling::input")
    public WebElement inputCVV;

    @FindBy(xpath = "//div[contains(text(), 'Name on Card')]//following-sibling::input")
    public WebElement inputNameOnCard;

    @FindBy (xpath = "//div[@class='user__address']//input")
    public WebElement selectCountry;

    @FindBy(xpath = "//input[contains(@placeholder, 'Select Country')]//following-sibling::section")
    public WebElement countryRecommendation;

    @FindBy(xpath = "//a[contains(text(), 'Place Order')]")
    public WebElement placeOrderButton;


    public CheckoutObject(WebDriver webDriver) {
        super(webDriver);

        // Init Element
        PageFactory.initElements(webDriver, this);
    }
}
