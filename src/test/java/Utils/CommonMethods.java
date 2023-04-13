package Utils;

import StepDefinitions.PageInitializer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;


public class CommonMethods extends PageInitializer {
    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication() {
        ConfigReader.readProperties();

        String browser = ConfigReader.getPropertyValue("browserType");
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Constants.WAIT_TIME));

        initializePageObjects(); //will initialize all the pages we have in out
                                    // page initializer class
    }

    public static void closeBrowser() {
        driver.close();
    }

    public static void clickOn (WebElement element) {
        element.click();
    }

    public static void sendText(WebElement element, String str) {
        element.clear();
        element.sendKeys(str);
    }
    public static Select clickOnDropDown(WebElement element){
        Select select=new Select(element);
        return select;
    }
    public static void selectByValue(WebElement element, String value){
        clickOnDropDown(element).selectByValue(value);
    }
    public static void selectByVisibleText(WebElement element, String text){
        clickOnDropDown(element).selectByVisibleText(text);
    }
    public static void selectByIndex(WebElement element, int index){
        clickOnDropDown(element).selectByIndex(index);
    }
    public static void selectByOptions(WebElement element , String desiredText){
        List<WebElement> options=clickOnDropDown(element).getOptions();
        for (WebElement option : options) {
            String ddlOptionText=option.getText();
            if (ddlOptionText.equals(desiredText)){
                option.click();
            }
        }
    }
}
