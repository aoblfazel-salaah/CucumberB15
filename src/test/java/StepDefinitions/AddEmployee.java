package StepDefinitions;

import Pages.AddEmployeePage;
import Utils.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;

public class AddEmployee extends CommonMethods {
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        clickOn(addEmployee.PIMOption);
    }
    @When("user clicks on add employee button")
    public void user_clicks_on_add_employee_button() {
        clickOn(addEmployee.addEmployeeBtn);
    }
    @When("user enters {string}, {string} and  {string}")
    public void user_enters_and(String firstName, String middleName, String lastName) {
        sendText(addEmployee.firstNSlot, firstName);
        sendText(addEmployee.midNSlot,middleName);
        sendText(addEmployee.lastNSlot, lastName);
    }


    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        clickOn(addEmployee.saveBtn);
    }


    // --------------------------database-------------------------------
    @When("user captures the employee id")
    public void user_captures_the_employee_id() {
        GlobalVariables.emp_id=
                addEmployee.employeeIdSlot.getAttribute("value");
        System.out.println("The employee id is "+ GlobalVariables.emp_id);
        Log.info("The employee id is "+ GlobalVariables.emp_id);
    }
    @When("query the information in backend")
    public void query_the_information_in_backend() {
        String query= "select * "+
                "from hs_hr_employees " +
                "where employee_id ='"+GlobalVariables.emp_id+"';";

        GlobalVariables.tableData =
                DBUtility.getListOfMapsFromResultSet(query);

    }
    @Then("verify the results from frontend and backend as {string}, {string} and  {string}")
    public void verify_the_results_from_frontend_and_backend_as
            (String firstName, String middleName, String lastName) {
        String firstNameFromDB= GlobalVariables.tableData.get(0).get("emp_firstname");
        System.out.println(firstNameFromDB);
        String middleNameFromDB= GlobalVariables.tableData.get(0).get("emp_middlename");
        System.out.println(firstNameFromDB);
        String lastNameFromDB= GlobalVariables.tableData.get(0).get("emp_lastname");
        System.out.println(lastNameFromDB);

        Assert.assertEquals(firstNameFromDB, firstName);
        Assert.assertEquals(lastNameFromDB, lastName);
    }
}
