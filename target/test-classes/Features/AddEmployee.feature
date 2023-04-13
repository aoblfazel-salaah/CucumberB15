Feature: Employee

  @smoke
  Scenario: Adding a new Employee

    #Given open the browser and launch HRMS application
    When user enters valid username and valid password
    And clicks on login button
    When user clicks on PIM option
    And user clicks on add employee button
    And user enters firstname, middle name and  lastname
    And user clicks on save button
    #And close the browser
