@Register
Feature: Register
  

  @TC0005
  Scenario: With valid user credentials
    Given I have opened the browser and navigated to register page
    When I register with valid credentials
    Then I should be redirected to account page after register
    And I have closed the browser after register

  @TC0006
  Scenario: With existing email
    Given I have opened the browser and navigated to register page
    When I register with existing email
    Then I should get an error message about existing email
    And I have closed the browser after register

  @TC0007
  Scenario: With no user credentials
    Given I have opened the browser and navigated to register page
    When I register with no user credentials
    Then I should get an error message about missing email
    And I have closed the browser after register
