@tag
  Feature: Error validation
    @ErrorValidation
    Scenario Outline: To chck the error validations
      Given I landed on Ecommerce page
      When  Logged in with "<name>" and password "<password>"
      Then "Incorrect email or password." message is displayed
      Examples:
        | name                          | password     |
        | rnithishkumar080417@gmail.com | Rnithish |
