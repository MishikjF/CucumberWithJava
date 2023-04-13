
@Login
Feature: Login

	#Background:
		
  
  @TC0001
  Scenario: With valid user credentials
  	Given I have opened the browser and navigated to login page
    When I login with valid credentials
    Then I should be redirected to account page
    And I have closed the browser
    
  @TC0002
  Scenario: With invalid user credentials
  	Given I have opened the browser and navigated to login page
    When I login with invalid credentials
    Then I should get an error message
    And I have closed the browser  
    
  @TC0003
  Scenario: With no user credentials
  	Given I have opened the browser and navigated to login page
    When I login with no credentials
    Then I should get an error message for missing username
    And I have closed the browser  
    
  @TC0004
  Scenario: With lost password link
  	Given I have opened the browser and navigated to login page
    When I login with valid username
    And I click on lost password link
    Then I should get an reset email alert
    And I have closed the browser   
  
  

 
