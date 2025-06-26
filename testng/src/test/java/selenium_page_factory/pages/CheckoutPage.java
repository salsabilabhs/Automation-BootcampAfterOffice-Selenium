package selenium_page_factory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.demo.testng.program.selenium_page_factory.base.BasePage;
import selenium_page_factory.object_repository.CheckoutObject;

public class CheckoutPage extends BasePage {

    public CheckoutObject checkoutObject;

    public CheckoutPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        checkoutObject = new CheckoutObject(webDriver);
    }

    public void verifyCreditCardNumber() {

        String actualCreditCardNumberValue = checkoutObject.getCreditNumber.getAttribute("value");
        Assert.assertTrue(actualCreditCardNumberValue.length() > 0, "Field must be autofilled with a valid credit card number.");
    }

    public void fillTheCVV(String cvvCode) {
        checkoutObject.inputCVV.sendKeys(cvvCode);
    }

    public void fillTheNameCard(String name) {
        checkoutObject.inputNameOnCard.sendKeys(name);
    }

    public void pickTheCountry(String country) {
        checkoutObject.selectCountry.sendKeys(country);

        wait.until(e -> checkoutObject.countryRecommendation.isDisplayed());
        checkoutObject.countryRecommendation.click();
    }

    public void clickPlaceOrderBtn() {
        wait.until(e -> checkoutObject.placeOrderButton.isDisplayed());
        checkoutObject.placeOrderButton.click();
    }


    

}
