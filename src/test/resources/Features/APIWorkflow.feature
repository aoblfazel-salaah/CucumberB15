Feature: API workflow for HRMS

  Background: new token for every scenario
    Given a JWT is generated
  @api
  Scenario: create an employee using API call
    Given a request is prepared to create an employee
    When a post call is made to create and employee
    Then the status code for creating employee is 201
    And the employee contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable to be used in other places


  @api @jsonPayload
  Scenario: create an employee using API call
    Given a request is prepared to create and employee using json payload
    When a post call is made to create and employee
    Then the status code for creating employee is 201
    And the employee contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable to be used in other places

    @api @jsonPayload
  Scenario: retrieve employee using API call
      Given a request is prepared to get the created employee
      When a GET call is made to get the employee
      Then the status code for getting employee is 200
      Then the employee data we get having "employee.employee_id" must match with globally stored employee id
      Then the retrieved data at "employee" object matches with the data of created employee

|emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
|Matt         |Carson      |Henry          |Male      |1983-05-20  |Active    |Technician   |

      @dynamic @api
  Scenario: create an employee using API call
    Given a request is prepared to create an employee with dynamic data "Matt", "Carson", "Henry", "M", "1983-05-20","Active", "Technician"
    When a post call is made to create and employee
    Then the status code for creating employee is 201
    And the employee contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable to be used in other places

        @update @api
  Scenario: updating the created employee
    Given a request is prepared to update an employee as "id", "Daniel", "Carson", "Henry", "M", "1983-05-20","Active", "Technician"
    When a PUT call is made to Update an employee
    Then the status code for updating employee is 200