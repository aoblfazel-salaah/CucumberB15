package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "StepDefinitions",
        dryRun = false,
        tags = "@smoke2 or @smoke3",
        plugin = { "pretty", "html:target/report.html",
                            "json:target/report.json",
                "rerun:target/failed.txt"}
)


public class SmokeRunner {
}
