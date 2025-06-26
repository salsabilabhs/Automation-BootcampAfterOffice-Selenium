package selenium_page_factory.test_suites;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.demo.testng.program.selenium_page_factory.base.BaseTestSuite;
import selenium_page_factory.pages.CartPage;
import selenium_page_factory.pages.CheckoutPage;
import selenium_page_factory.pages.HomePage;
import selenium_page_factory.pages.LoginPage;
import selenium_page_factory.pages.OrderPage;
import selenium_page_factory.pages.ProductDisplayPage;

public class CheckoutFlowE2E extends BaseTestSuite {

    // Initiate the Product Name
    String productName = "ADIDAS ORIGINAL";
    String productPrice;

    // Initiate the LoginPage.java
    public LoginPage loginPage;
    public HomePage homePage;
    public ProductDisplayPage productDisplayPage;
    public CartPage cartPage;
    public OrderPage orderPage;
    public CheckoutPage checkoutPage;
    
    
    @BeforeSuite
    public void setUp() {
        super.setUp();
        this.openUrl("https://rahulshettyacademy.com/client/");
        this.loginPage = new LoginPage(webDriver, wait);
        this.homePage = new HomePage(webDriver, wait);
        this.productDisplayPage = new ProductDisplayPage(webDriver, wait);
        this.cartPage = new CartPage(webDriver, wait);
        this.checkoutPage = new CheckoutPage(webDriver, wait);
        this.orderPage = new OrderPage(webDriver, wait);
    }

    @Test
    public void loginToPlatform() throws InterruptedException {
        loginPage.fillTheEmail("simanjuntakalbert57@gmail.com");
        loginPage.fillThePassword("XBf@rWNvByn!#K8");
        loginPage.clickLoginBtn();

        // Assert that the user is logged in by checking Log Out button is displayed
        homePage.verifySignOutBtn();
    }

    @Test(priority = 2)
    public void searchProduct() throws Exception {

        homePage.doSearch(productName);
        
        productDisplayPage.verifyProductName(productName);
        productPrice = productDisplayPage.getProductPrice();        
    }

    @Test(priority = 3)
    public void addProductToCartFromList() throws Exception {

        productDisplayPage.clickAddToCartBtn();

        homePage.verifyCounterCart();
        homePage.clickMenuCart();

        cartPage.verifyProductName(productName);
        cartPage.verifyProductPrice(productPrice);
    }

    @Test(priority = 4)
    public void proceedToCheckoutWithoutCoupon() {

        cartPage.clickCheckoutBtn();

        checkoutPage.verifyCreditCardNumber();
        checkoutPage.fillTheCVV("123");
        checkoutPage.fillTheNameCard("Albert Simanjuntak");
        checkoutPage.pickTheCountry("Indonesia");

        checkoutPage.clickPlaceOrderBtn();
        
        orderPage.verifyOrderSuccessMsg();
        orderPage.verifyProduct(productName, productPrice);
    }

    @AfterSuite
    public void tearDown() {
        super.tearDown();
    }
}
