package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Serenity + Cucumber Test Runner
 *
 * Serenity will automatically generate HTML reports in target/site/serenity
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        tags = "@api",
        plugin = {"pretty"}
)
public class ApiTestSuite {
        // Runner is configured via annotations
}
