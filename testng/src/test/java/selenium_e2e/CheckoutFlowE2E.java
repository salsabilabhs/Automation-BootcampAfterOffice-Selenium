package selenium_e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutFlowE2E {
    public WebDriver webDriver;
    public WebElement webElement;
    public Wait<WebDriver> wait;
    String productName = "ADIDAS ORIGINAL";
    String productPrice;

    @BeforeSuite
    public void startBrowser() throws Exception {
        // Open the browser
        System.out.println("Starting browser.....");
        System.setProperty("webdriver.chrome.driver", "/Users/Maxxi/maven-project/afteroffice-selenium/chromedriver-win64/chromedriver.exe");
        
        // Initialize the WebDriver (e.g., ChromeDriver)
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        
        // Open a website
        webDriver.get("https://rahulshettyacademy.com/client/");
        // Maximize the browser window
        webDriver.manage().window().maximize();
        
        // Timeout for the page to load
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void loginToPlatform() throws Exception {
        
        // Verify the URL
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/auth/login"), "URL does not contain '/auth/login'");

        //Make sure field login exists
        WebElement emailField = webDriver.findElement(By.xpath("//input[@id='userEmail']"));
        WebElement passwordField = webDriver.findElement(By.xpath("//input[@id='userPassword']"));
        WebElement loginButton = webDriver.findElement(By.xpath("//input[@id='login']"));

        wait.until(e -> emailField.isDisplayed());
        wait.until(e -> passwordField.isDisplayed());
        wait.until(e -> loginButton.isDisplayed());

        //Input email & password
        emailField.sendKeys("simanjuntakalbert57@gmail.com");
        passwordField.sendKeys("XBf@rWNvByn!#K8");

        //Click login button
        loginButton.click();

        // Assert that the user is logged in by checking Log Out button is displayed
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement signOutButton = webDriver.findElement(By.xpath("//ul//li//button[normalize-space(text())='Sign Out']"));
        wait.until(e -> signOutButton.isDisplayed());
    }

    @Test(priority = 2)
    public void searchProduct() throws Exception {
        
        // Make sure field search exists
        Thread.sleep(Duration.ofSeconds(3).toMillis());
        WebElement inputSearch = webDriver.findElement(By.xpath("//section[@id='sidebar']//form//input[@name='search']"));
        wait.until(e -> inputSearch.isDisplayed());

        // Input search of product name
        inputSearch.sendKeys(productName);
        inputSearch.sendKeys(Keys.ENTER);
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        // Get the first product's NAME from the search result
        WebElement firstProductName = webDriver.findElement(By.xpath("//div[@class='card']//div//h5//b"));
        wait.until(e -> firstProductName.isDisplayed());

        // Validate value of first product name is equal to the searched product name
        String actualName = firstProductName.getText();
        Assert.assertEquals(actualName, productName, "Product name does not match the searched name.");

        // Get the first product's PRICE from the search result
        WebElement firstProductPrice = webDriver.findElement(By.xpath("//div[@class='card-body']//div//div"));
        productPrice = firstProductPrice.getText();
    }

    @Test(priority = 3)
    public void addProductToCartFromList() throws Exception {

        // Make sure Add To Cart button exists
        WebElement addToCartButton = webDriver.findElement(By.xpath("//div[@class='card-body']//button[normalize-space(text())='Add To Cart']"));
        wait.until(e -> addToCartButton.isDisplayed());

        // Click the Add To Cart button
        addToCartButton.click();
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        // Validate that the product is added to the cart by checking the cart icon
        WebElement counterCart = webDriver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']//child::label"));
        wait.until(e -> counterCart.isDisplayed());

        // Assert that the cart counter is greater than 0
        String counterValue = counterCart.getText();
        Assert.assertTrue(Integer.parseInt(counterValue) > 0, "Cart counter is not greater than 0. Product may not have been added to the cart.");
    }

    @Test(priority = 4)
    public void viewCart() throws Exception {
        
        // Make sure menu Cart exists
        WebElement menuCart = webDriver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"));
        wait.until(e -> menuCart.isDisplayed());
        
        // Click the Cart menu
        menuCart.click();
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        // Verify the URL & Page Title
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/dashboard/cart"), "URL does not contain '/dashboard/cart'");

        WebElement pageTitle = webDriver.findElement(By.xpath("//div[@class='heading cf']//child::h1"));
        String pageTitleValue = pageTitle.getText();
        Assert.assertEquals(pageTitleValue, "My Cart", "Page title does not match 'My Cart'");

        // Get the product name & price on Cart page
        WebElement productNameOnCart = webDriver.findElement(By.xpath("//div[@class='cartSection']//h3"));
        WebElement productPriceOnCart = webDriver.findElement(By.xpath("//div[@class='prodTotal cartSection']//p"));

        wait.until(e -> productNameOnCart.isDisplayed());
        wait.until(e -> productPriceOnCart.isDisplayed());

        // Validate that the product name and price on the Cart page matches
        String actualProductName = productNameOnCart.getText();
        String actualProductPrice = productPriceOnCart.getText();
        Assert.assertEquals(actualProductName, productName, "Product name on Cart page does not match the added product name.");
        Assert.assertEquals(actualProductPrice, productPrice, "Product price on Cart page does not match the added product price.");
    }

    @Test(priority = 5)
    public void proceedToCheckoutWithoutCoupon() throws Exception {
        
        // Make sure Checkout button exists
        WebElement checkoutButton = webDriver.findElement(By.xpath("//ul//li//button[normalize-space(text())='Checkout']"));
        wait.until(e -> checkoutButton.isDisplayed());

        // Click the Checkout button
        checkoutButton.click();
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        // Verify the URL & Page Title
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/dashboard/order"), "URL does not contain '/dashboard/order'");

        WebElement pageTitle = webDriver.findElement(By.xpath("//div[@class='payment__title']"));
        String pageTitleValue = pageTitle.getText();
        Assert.assertEquals(pageTitleValue, "Payment Method", "Page title does not match 'Payment Method'");

        // Make sure fields are exist
        WebElement creditCardNumberField = webDriver.findElement(By.xpath("//div[contains(text(), 'Credit Card Number')]//following-sibling::input"));
        WebElement cvvCodeField = webDriver.findElement(By.xpath("//div[contains(text(), 'CVV Code')]//following-sibling::input"));
        WebElement nameOnCardField = webDriver.findElement(By.xpath("//div[contains(text(), 'Name on Card')]//following-sibling::input"));
        WebElement countryField = webDriver.findElement(By.xpath("//div[@class='user__address']//input"));

        wait.until(e -> creditCardNumberField.isDisplayed());
        wait.until(e -> cvvCodeField.isDisplayed());
        wait.until(e -> nameOnCardField.isDisplayed());
        wait.until(e -> countryField.isDisplayed());

        // Validate that Credit Card Number field is autofilled
        String actualCreditCardNumberValue = creditCardNumberField.getAttribute("value");
        Assert.assertTrue(actualCreditCardNumberValue.length() > 0, "Field must be autofilled with a valid credit card number.");

        // Input CVV Code, Name on Card
        cvvCodeField.sendKeys("123");
        nameOnCardField.sendKeys("Albert Simanjuntak");
        
        // Pick Country from dropdown
        countryField.sendKeys("Indonesia");

        WebElement countryRecommendation = webDriver.findElement(By.xpath("//input[contains(@placeholder, 'Select Country')]//following-sibling::section"));
        wait.until(e -> countryRecommendation.isDisplayed());
        countryRecommendation.click();

        // Make sure Place Order button exists
        WebElement placeOrderButton = webDriver.findElement(By.xpath("//a[contains(text(), 'Place Order')]"));
        wait.until(e -> placeOrderButton.isDisplayed());

        // Click Place Order button
        placeOrderButton.click();
        Thread.sleep(Duration.ofSeconds(3).toMillis());
    }

    @Test(priority = 6)
    public void validateOrderSuccess() throws Exception {
        
        // Verify the URL
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/dashboard/thanks"), "URL does not contain '/dashboard/thanks'");

        // Validate the order success message
        WebElement orderSuccessMessage = webDriver.findElement(By.xpath("//h1[contains(text(), 'Thankyou for the order.')]"));
        wait.until(e -> orderSuccessMessage.isDisplayed());

        // Get the product name & price on Order Success page
        WebElement productNameOnSuccessOrder = webDriver.findElement(By.xpath("//div[contains(text(), '" + productName + "')]"));
        WebElement productPriceOnSuccessOrder = webDriver.findElement(By.xpath("//div[contains(text(), '" + productPrice + "')]"));

        wait.until(e -> productNameOnSuccessOrder.isDisplayed());
        wait.until(e -> productPriceOnSuccessOrder.isDisplayed());

        // Validate that the product name and price ordered matches
        String actualProductName = productNameOnSuccessOrder.getText();
        String actualProductPrice = productPriceOnSuccessOrder.getText();
        Assert.assertEquals(actualProductName, productName, "Product name user ordered does not match the added product name.");
        Assert.assertEquals(actualProductPrice, productPrice, "Product price user ordered does not match the added product price.");

    }
    
}
