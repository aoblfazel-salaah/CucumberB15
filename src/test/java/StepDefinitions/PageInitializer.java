package StepDefinitions;

import Pages.AddEmployeePage;
import Pages.EmployeeSearchPage;
import Pages.LoginPage;

public class PageInitializer {
    public static LoginPage login;
    public static AddEmployeePage addEmployee;
    public static EmployeeSearchPage employeeSearch;
    public static void initializePageObjects(){
        login = new LoginPage();
        addEmployee = new AddEmployeePage();
        employeeSearch = new EmployeeSearchPage();
    }
}
