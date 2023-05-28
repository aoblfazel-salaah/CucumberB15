package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage extends CommonMethods {
    @FindBy (css = "a#menu_pim_addEmployee")
    public WebElement addEmployeeBtn;
    @FindBy(id ="firstName")
    public WebElement firstNSlot;
    @FindBy(id ="middleName")
    public WebElement midNSlot;
    @FindBy(id ="lastName")
    public WebElement lastNSlot;
    @FindBy(id = "btnSave" )
    public WebElement saveBtn;
    @FindBy (id = "menu_pim_viewPimModule")
    public WebElement PIMOption;

    @FindBy (id = "employeeId")
    public WebElement employeeIdSlot;

    public AddEmployeePage(){
        PageFactory.initElements(driver, this);
    }
}
