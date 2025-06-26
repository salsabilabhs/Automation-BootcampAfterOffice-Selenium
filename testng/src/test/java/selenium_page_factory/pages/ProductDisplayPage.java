package selenium_page_factory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.demo.testng.program.selenium_page_factory.base.BasePage;
import selenium_page_factory.object_repository.ProductDisplayObject;

public class ProductDisplayPage extends BasePage {
    
    public ProductDisplayObject productDisplayObject;

    public ProductDisplayPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.productDisplayObject = new ProductDisplayObject(webDriver);
    }

    public void verifyProductName(String productName) {
        wait.until(e -> productDisplayObject.getProductName.isDisplayed());
        String actualName = productDisplayObject.getProductName.getText();

        Assert.assertEquals(actualName, productName, "Product name does not match the searched name.");
    }

    public String getProductPrice() {
        return productDisplayObject.getProductPrice.getText();
    }

    public void clickAddToCartBtn() {
        productDisplayObject.addToCartButton.click();
    }
}
