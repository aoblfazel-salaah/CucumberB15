package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "StepDefinitions",
        dryRun = false,
        tags = "@dataTable",
        plugin = { "pretty", "html:target/report.html", "json:target/report.json"}
)


public class SmokeRunner {
}
