Feature: Search for an employee

  Background:
    When user enters valid username and valid password
    And clicks on login button
    When user clicks on PIM option

  @smoke
  Scenario: Searching for an employee by id
    #Given open the browser and launch HRMS application
    #When user enters valid username and valid password
    #And clicks on login button
    #When user clicks on PIM option
    When user enters valid employee id
    And clicks on search button
    And user see employee information is displayed
    #And close the browser


  @smoke
  Scenario:Search Employee By Job Title
    #Given open the browser and launch HRMS application
    #When user enters valid username and valid password
    #And clicks on login button
    #When user clicks on PIM option
    When user select job title
    And clicks on search button
    And user see employee information is displayed
    #And close the browser

  # Background : used to define all the common steps that multiple scenarios
  #             have in the same feature file, till the flow is not broken