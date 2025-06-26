package selenium_page_factory.test_suites_cucumber.definitions;

import com.demo.testng.program.selenium_page_factory.base.BaseTestSuite;

import selenium_page_factory.pages.CartPage;
import selenium_page_factory.pages.CheckoutPage;
import selenium_page_factory.pages.HomePage;
import selenium_page_factory.pages.LoginPage;
import selenium_page_factory.pages.OrderPage;
import selenium_page_factory.pages.ProductDisplayPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutProductE2E extends BaseTestSuite {
    // Initiate the Product Name
    String productName;
    String productPrice;
    public String url;

    // Initiate the LoginPage.java
    public LoginPage loginPage;
    public HomePage homePage;
    public ProductDisplayPage productDisplayPage;
    public CartPage cartPage;
    public OrderPage orderPage;
    public CheckoutPage checkoutPage;

    @Given("Website {string}")
    public void setUrl(String url) {
        this.url = url;
    }   

    @Then("I open the website")
    public void openUrl() {
        super.setUp();
        this.openUrl(this.url);
    }

    @And("Set up all the pages for running automation")
    public void initPage() {
        this.loginPage = new LoginPage(webDriver, wait);
        this.homePage = new HomePage(webDriver, wait);
        this.productDisplayPage = new ProductDisplayPage(webDriver, wait);
        this.cartPage = new CartPage(webDriver, wait);
        this.checkoutPage = new CheckoutPage(webDriver, wait);
        this.orderPage = new OrderPage(webDriver, wait);
    }

    @And("I do login with email {string} and password {string}")
    public void loginToPlatform(String email, String password) throws InterruptedException {
        loginPage.fillTheEmail(email);
        loginPage.fillThePassword(password);
        loginPage.clickLoginBtn();

        // Assert that the user is logged in by checking Log Out button is displayed
        homePage.verifySignOutBtn();
    }

    @When("I search a product with keyword {string}")
    public void searchProduct(String productName) throws Exception {
        homePage.doSearch(productName);
    }

    @Then("Verify the product name {string} is shown")
    public void result_verifyProductName(String productName) {
        productDisplayPage.verifyProductName(productName);
    }

    @And("I save the product price")
    public void result_getProductPrice() {
        productPrice = productDisplayPage.getProductPrice();  
    }

    @Then("I click the Add To Cart button")
    public void addProductToCartFromList() throws Exception {
        productDisplayPage.clickAddToCartBtn();

        homePage.verifyCounterCart();
    }

    @And("Verify the product name {string} is added to Cart with match price")
    public void cart_verifyProduct(String productName) {
        homePage.clickMenuCart();

        cartPage.verifyProductName(productName);
        cartPage.verifyProductPrice(productPrice);
    }

    @Then("I click the Checkout button")
    public void click_checkoutBtn() {
        cartPage.clickCheckoutBtn();
    }

    @Then("I fill in the checkout form")
    public void enter_checkoutForm() {
        checkoutPage.verifyCreditCardNumber();
        checkoutPage.fillTheCVV("123");
        checkoutPage.fillTheNameCard("Albert Simanjuntak");
        checkoutPage.pickTheCountry("Indonesia");
    }

    @Then("I click the Place Order button")
    public void click_placeOrderBtn() {
        checkoutPage.clickPlaceOrderBtn();
    }

    @And("Verify the order to make sure {string} is ordered with match price")
    public void order_verifyProduct(String productName) {
        orderPage.verifyOrderSuccessMsg();
        orderPage.verifyProduct(productName, productPrice);
    }

    @Then("Teardown the test after checkout")
    public void teardown() {
        super.tearDown();
    }
    
}
