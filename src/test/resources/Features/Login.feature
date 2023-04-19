Feature: Login Functionalities

  @smoke
  Scenario: Valid Admin Login
    #Given open the browser and launch HRMS application
    When user enters valid username and valid password
    And clicks on login button
    Then user is logged in successfully
    #And close the browser

    #Hooks:
  #     used to define pre and post steps in any cucumber framework
  #        This is always created in stepDefinitions folder
  #       This class can not be inherited

  @smoke2
  Scenario: Valid Admin Login with regEx
    #Given open the browser and launch HRMS application
    When user enters "Admin" and "Hum@nhrm123"
    And clicks on login button
    Then user is logged in successfully
    #And close the browser

  # Ways of retrieving data to our code
  # 1. HardCode 2. properties file ---> both from java library
  # Cucumber itself provides multiple options though which we can feed data
  #     from feature file into stepDefinitions
  #     3. Regular Expressions --  put data in " "

  # If your application has less data driven testing, use cucumber regex feature
  # else use properties or other files for reading data

  # Parameterization
  # Executing the same test case with multiple test data
  # it's alternate in testNG is dataProvider

  #  Data driven only means your data is in an external source and you fetch it
  #   in your framework

  #     4. Scenario Outline
  #     Provides you an alternative to data driven testing
  #  use it when you want parameterization or implement data driven testing

  @scenarioOutline
  Scenario Outline: Login with multiple credentials using scenario outline
    When user enters "<username>" and "<password>"
    And clicks on login button
    Then user is logged in successfully

    Examples:
    |username|password|
    |admin   |Hum@nhrm123|
    |ADMIN   |Hum@nhrm123|
    |Jason   |Hum@nhrm123|


    # DATA TABLE
  # we can do the same thing in data table as scenario outline
  # in data table the browser opens only once and execute all and then close

  @dataTable
  Scenario: Login with multiple credentials using data table
    When user enters username and password and verifies login
      |username|password|
      |admin   |Hum@nhrm123|
      |ADMIN   |Hum@nhrm123|
      |Jason   |Hum@nhrm123|