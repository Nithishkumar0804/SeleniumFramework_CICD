@Regression
Feature: Purchase the order from Ecommerce Website

  Background:
    Given I landed on Ecommerce page

  @Positive
  Scenario Outline: Positive test for Submitting the orders
    Given Logged in with "<name>" and password "<password>"
    When I add product "<productName>" to Cart
    And Checkout "<productName>" and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed in confirmation page

    Examples:
      | name                          | password     | productName     |
      | rnithishkumar080417@gmail.com | Rnithish21## | ADIDAS ORIGINAL |
