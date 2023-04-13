package StepDefinitions;

import Pages.AddEmployeePage;
import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AddEmployee extends CommonMethods {
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        clickOn(addEmployee.PIMOption);
    }
    @When("user clicks on add employee button")
    public void user_clicks_on_add_employee_button() {
        clickOn(addEmployee.addEmployeeBtn);
    }
    @When("user enters firstname, middle name and  lastname")
    public void user_enters_firstname_middle_name_and_lastname() {

        sendText(addEmployee.firstNSlot,
                ConfigReader.getPropertyValue("firstName"));

        sendText(addEmployee.midNSlot,
                ConfigReader.getPropertyValue("middleName"));

        sendText(addEmployee.lastNSlot,
                ConfigReader.getPropertyValue("lastName"));
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        clickOn(addEmployee.saveBtn);
    }
}
