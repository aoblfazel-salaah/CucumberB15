Feature: Employee

  @smoke3
  Scenario Outline: Adding a new Employee

    #Given open the browser and launch HRMS application
    When user enters valid username and valid password
    And clicks on login button
    When user clicks on PIM option
    And user clicks on add employee button
    And user enters "<firstname>", "<middle name>" and  "<lastname>"
    And user clicks on save button
    #And close the browser

  Examples:
    |firstname|middle name|lastname|
    |Gul      |Lala       |Azami   |


  @database
  Scenario Outline: adding the employee from front end and verifying it from back end
    When user enters valid username and valid password
    And clicks on login button
    When user clicks on PIM option
    And user clicks on add employee button
    And user enters "<firstname>", "<middle name>" and  "<lastname>"
    And user captures the employee id
    And user clicks on save button
    And query the information in backend
    Then verify the results from frontend and backend as "<firstname>", "<middle name>" and  "<lastname>"

    Examples:
    |firstname|middle name|lastname|
    |Gul      |Lala       |Azami   |
    |Sayed    |Mansoor    |Agha   |

