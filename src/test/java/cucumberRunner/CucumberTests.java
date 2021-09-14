/**
 * CucumberTests class is the starting point of execution. It binds the feature files to stepdef files and
 * runs the feature file and generates the report
 *
 * @author  QATest
 * @version 1.0
 * @since   03/01/2021
 */
package cucumberRunner;

import com.cucumber.listener.Reporter;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(//dryRun=true,
        format = {"pretty"},
        glue = {"stepdefs"},
        features = {"src/test/features/getSelectOptions.feature"},
        plugin = {
                "com.cucumber.listener.ExtentCucumberFormatter:src/test/reports/cucumber_report.html",
                "html:output/html-report"}, monochrome = true
)

public class CucumberTests {
    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File("src/test/configuration/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Windows 10");
        Reporter.setTestRunnerOutput("Sample test runner output message");
    }
}
