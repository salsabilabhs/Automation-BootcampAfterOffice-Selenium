package selenium_page_factory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.demo.testng.program.selenium_page_factory.base.BasePage;

import selenium_page_factory.object_repository.CartObject;

public class CartPage extends BasePage {
    public CartObject cartObject;

    public CartPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.cartObject = new CartObject(webDriver);
    }

    public void verifyProductName(String productName) {
        wait.until(driver -> cartObject.getProductName.isDisplayed());
        String actualName = cartObject.getProductName.getText();

        Assert.assertEquals(actualName, productName, "Product name on Cart page does not match the added product name.");
    }

    public void verifyProductPrice(String productPrice) {
        wait.until(driver -> cartObject.getProductPrice.isDisplayed());
        String actualPrice = cartObject.getProductPrice.getText();

        Assert.assertEquals(actualPrice, productPrice, "Product price on Cart page does not match the added product price.");
    }

    public void clickCheckoutBtn() {
        cartObject.checkoutButton.click();
    }

}
