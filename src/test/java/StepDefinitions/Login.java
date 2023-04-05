package StepDefinitions;

import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login extends CommonMethods {
    @Given("open the browser and launch HRMS application")
    public void open_the_browser_and_launch_hrms_application() {
        openBrowserAndLaunchApplication();
    }
    @When("user enters valid username and valid password")
    public void user_enters_valid_username_and_valid_password() {

        WebElement usernameSlot= driver.findElement(By.id("txtUsername"));
              sendText(usernameSlot,
                      ConfigReader.getPropertyValue("username"));

        WebElement passSlot=driver.findElement(By.id("txtPassword"));
               sendText(passSlot,
                       ConfigReader.getPropertyValue("password"));
    }
    @When("clicks on login button")
    public void clicks_on_login_button() {
        WebElement loginBtn=driver.findElement(By.id("btnLogin"));
        clickOn(loginBtn);
    }
    @Then("user is logged in successfully")
    public void user_is_logged_in_successfully() {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//a[contains(text(),'Welcome')]")));
        WebElement welcome=driver.findElement(By.xpath(
                "//a[contains(text(),'Welcome')]"));

        boolean userLoggedIn=welcome.isDisplayed();
        if (userLoggedIn){
            System.out.println("user is logged in successfully");
        }
    }
    @Then("close the browser")
    public void close_the_browser() {
        closeBrowser();
    }
}
