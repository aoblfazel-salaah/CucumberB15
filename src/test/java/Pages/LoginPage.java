package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonMethods {
    //POM is also called design pattern

    //page factory model approach --- form selenium
    @FindBy (id = "txtUsername")
    public WebElement usernameSlot;
    @FindBy(id ="txtPassword")
    public WebElement passSlot;
    @FindBy(id ="btnLogin")
    public WebElement loginBtn;
    @FindBy(linkText = "Logout")
    public WebElement logoutBtn;
    @FindBy (xpath = "//a[contains(text(),'Welcome')]")
    public WebElement welcome;

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }
}
