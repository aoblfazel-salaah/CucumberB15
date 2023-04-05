Feature: Login Functionalities

  @testcase1
  Scenario: Valid Admin Login
    Given open the browser and launch HRMS application
    When user enters valid username and valid password
    And clicks on login button
    Then user is logged in successfully
    And close the browser
