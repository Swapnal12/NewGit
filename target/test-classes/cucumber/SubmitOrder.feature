
@tag
Feature: Purchase the order from the website
  
  
  Background:
  Given I landed on Ecommerce Page
  
  
  @tag2
  Scenario Outline: Positive testing of submitting the order
    Given Login with Username <userName> and Password <password>
    When I add product <productName> to the Cart
    And Checkout <productName> and submit order;
    Then "THANKYOU FOR THE ORDER" is displayed on confirmationPage

    Examples: 
      |userName             |password |productName|
      |carythomson@gmail.com|Cary@1234|ZARA COAT 3| 
     
