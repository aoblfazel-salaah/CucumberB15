package StepDefinitions;

import Utils.CommonMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends CommonMethods {
    @Before
    public void preConditions(){
        openBrowserAndLaunchApplication();
    }
    //scenario class holds the complete information of your test execution in Cuc
    @After
    public void postConditions (Scenario scenario){
        byte [] pic;
        if (scenario.isFailed()){
            pic = screenshotAs("failed/" + scenario.getName());
        } else {
            pic = screenshotAs("passed/" + scenario.getName());
        }

        //attach screenshot in my report
        scenario.attach(pic, "image/png", scenario.getName());

        closeBrowser();
    }
}
