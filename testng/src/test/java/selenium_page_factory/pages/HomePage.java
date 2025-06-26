package selenium_page_factory.pages;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.demo.testng.program.selenium_page_factory.base.BasePage;
import selenium_page_factory.object_repository.HomeObject;

public class HomePage extends BasePage {

    public HomeObject homeObject;

    public HomePage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.homeObject = new HomeObject(webDriver);
    }

    public void verifySignOutBtn() {
        wait.until(e -> homeObject.signOutBtn.isDisplayed());
    }

    public void doSearch(String productName) throws Exception {
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        wait.until(e -> homeObject.inputSearch.isDisplayed());
        homeObject.inputSearch.sendKeys(productName);
        homeObject.inputSearch.sendKeys(Keys.ENTER);

        Thread.sleep(Duration.ofSeconds(3).toMillis());
    }

    public void verifyCounterCart() throws Exception {
        Thread.sleep(Duration.ofSeconds(3).toMillis()); 
        
        wait.until(e -> homeObject.counterCart.isDisplayed());
        String counterValue = homeObject.counterCart.getText();

        Assert.assertTrue(Integer.parseInt(counterValue) > 0, "Cart counter is not greater than 0. Product may not have been added to the cart.");
    }

    public void clickMenuCart() {
        homeObject.menuCartButton.click();

        Assert.assertTrue(webDriver.getCurrentUrl().contains("/dashboard/cart"));
    }

}