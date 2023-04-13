package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeeSearchPage extends CommonMethods {
    @FindBy( id = "empsearch_id")
    public WebElement empIdTextBox;
    @FindBy( id = "searchBtn")
    public WebElement searchBtn;
    @FindBy( id = "empsearch_job_title")
    public WebElement jobTitleDDL;
    @FindBy( id = "empsearch_employee_status")
    public WebElement empStatusDDL;
    @FindBy( id = "empsearch_termination")
    public WebElement includeDDL;

    public EmployeeSearchPage (){
        PageFactory.initElements(driver, this);
    }
}
