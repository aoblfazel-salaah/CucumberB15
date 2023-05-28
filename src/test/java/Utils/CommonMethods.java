package Utils;

import StepDefinitions.PageInitializer;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
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

        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Constants.WAIT_TIME));

        initializePageObjects(); //will initialize all the pages we have in out
                                    // page initializer class

        //To configure the File and pattern it has
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("This is the beginning of my Test case");
        Log.info("My test case is executing right now");
        Log.warning("My test case might have some trivial issues");
    }

    public static void closeBrowser() {
        Log.info("This test case is about to get completed");
        Log.endTestCase("This test case is finished");
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
    public static byte [] screenshotAs(String imageName){
        // casting as TakesScreenshot cuz it's an interface
        TakesScreenshot takesScreenshot=(TakesScreenshot) driver;

        //Captures screenshot and stores it as byte array
        byte[] picBytes= takesScreenshot.getScreenshotAs(OutputType.BYTES);
        File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile,
                    new File(Constants.SCREENSHOT_FILEPATH
                            +imageName+"  "
                            +getTimeStamp("yyyy-MM-dd -- HH:mm:ss")+".png"));

        }catch (IOException e){
            throw new RuntimeException("SS file path");
        }
        return picBytes;
    }
    public static String getTimeStamp(String pattern){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
