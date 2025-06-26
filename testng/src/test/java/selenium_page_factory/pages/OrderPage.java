package selenium_page_factory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.demo.testng.program.selenium_page_factory.base.BasePage;
import selenium_page_factory.object_repository.OrderObject;

public class OrderPage extends BasePage {

    public OrderObject orderObject;

    public OrderPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        orderObject = new OrderObject(webDriver);
    }

    public void verifyOrderSuccessMsg() {
        String msg = orderObject.orderSuccessMessage.getText();
        
        Assert.assertEquals(msg, "THANKYOU FOR THE ORDER.");
    }

    public void verifyProduct(String productName, String productPrice) {
        String actualName = orderObject.titleProductDynamic(productName).getText();
        String actualPrice = orderObject.priceProductDynamic(productPrice).getText();

        Assert.assertEquals(actualName, productName, "Product name user ordered does not match the added product name.");
        Assert.assertEquals(actualPrice, productPrice, "Product price user ordered does not match the added product price.");
    }
    
}
