package StepDefinitions;

import Pages.LoginPage;
import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.datatable.DataTable;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class Login extends CommonMethods {

    @When("user enters valid username and valid password")
    public void user_enters_valid_username_and_valid_password() {

        sendText(login.usernameSlot,
                ConfigReader.getPropertyValue("username"));

        sendText(login.passSlot,
                ConfigReader.getPropertyValue("password"));
    }
    @When("clicks on login button")
    public void clicks_on_login_button() {

        clickOn(login.loginBtn);
    }
    @Then("user is logged in successfully")
    public void user_is_logged_in_successfully() {

        boolean userLoggedIn=login.welcome.isDisplayed();
        if (userLoggedIn){
            System.out.println("user is logged in successfully");
        }
    }
    @When("user enters {string} and {string}")
    public void user_enters_and(String username, String password) {
        sendText(login.usernameSlot, username);
        sendText(login.passSlot, password);
    }

    @When("user enters username and password and verifies login")
    public void user_enters_username_and_password_and_verifies_login
            (DataTable dataTable) {

        List<Map<String, String>> userCredentials = dataTable.asMaps();

        for (Map<String, String> userCredential : userCredentials) {

            String username=userCredential.get("username");
            String password=userCredential.get("password");

            sendText(login.usernameSlot, username);

            sendText(login.passSlot, password);

            clickOn(login.loginBtn);

            login.welcome.click();

            login.logoutBtn.click();
        }
    }

}
