Feature: Checkout Product E2E
As an authenticated user
I want to checkout a product
So that the product is successfully ordered in the system

Background:
    Given Website "https://rahulshettyacademy.com/client/"
    Then I open the website
    And Set up all the pages for running automation
    And I do login with email "simanjuntakalbert57@gmail.com" and password "XBf@rWNvByn!#K8"

Scenario:
    When I search a product with keyword "ADIDAS ORIGINAL"
    Then Verify the product name "ADIDAS ORIGINAL" is shown
    And I save the product price
    Then I click the Add To Cart button
    And Verify the product name "ADIDAS ORIGINAL" is added to Cart with match price
    Then I click the Checkout button
    Then I fill in the checkout form
    Then I click the Place Order button
    And Verify the order to make sure "ADIDAS ORIGINAL" is ordered with match price
    Then Teardown the test after checkout
    




