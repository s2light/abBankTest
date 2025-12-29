package runners;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import io.cucumber.junit.CucumberOptions;
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
        tags = "@ui",
        plugin = {"pretty"}
)
public class UITestSuite {
        // Runner is configured via annotations
}
