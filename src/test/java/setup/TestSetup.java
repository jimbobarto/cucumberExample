package setup;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "src/test/resources/featureFiles" },
        glue = { "webDriver", "stepDefinitions", "setup" }
)
public class TestSetup {
    public static Scenario scenario;

}