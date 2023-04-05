package StepDefinitions;

import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AddEmployee extends CommonMethods {
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        WebElement PIMOption=driver.findElement(By.cssSelector("a#menu_pim_viewPimModule"));
        clickOn(PIMOption);
    }
    @When("user clicks on add employee button")
    public void user_clicks_on_add_employee_button() {
        WebElement addEmployeeBtn=driver.findElement(
                By.cssSelector("a#menu_pim_addEmployee"));
        clickOn(addEmployeeBtn);
    }
    @When("user enters firstname, middle name and  lastname")
    public void user_enters_firstname_middle_name_and_lastname() {

        WebElement firstNSlot=driver.findElement(By.cssSelector("input#firstName"));
        sendText(firstNSlot, ConfigReader.getPropertyValue("firstName"));

        WebElement midNSlot= driver.findElement(By.cssSelector("input#middleName"));
        sendText(midNSlot, ConfigReader.getPropertyValue("middleName"));

        WebElement lastNSlot=driver.findElement(By.cssSelector("input#lastName"));
        sendText(lastNSlot, ConfigReader.getPropertyValue("lastName"));
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        WebElement saveBtn=driver.findElement(By.cssSelector("input#btnSave"));
        clickOn(saveBtn);
    }
}
