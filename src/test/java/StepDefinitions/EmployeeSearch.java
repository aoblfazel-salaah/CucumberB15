package StepDefinitions;

import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EmployeeSearch extends CommonMethods {
    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
        sendText(employeeSearch.empIdTextBox, ConfigReader.getPropertyValue("empId"));
    }
    @When("clicks on search button")
    public void clicks_on_search_button() {
        clickOn(employeeSearch.searchBtn);
    }
    @When("user see employee information is displayed")
    public void user_see_employee_information_is_displayed() {
        System.out.println("OK");
    }
    @When("user select job title")
    public void user_select_job_title() {
        selectByOptions(employeeSearch.jobTitleDDL, "Singer");
        selectByOptions(employeeSearch.empStatusDDL, "Active");
        selectByOptions(employeeSearch.includeDDL, "Current and Past Employees");
    }

}
