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
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.testng.annotations.BeforeClass;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(//dryRun=true,
        format = {"pretty"},
        features = {"src/test/features/CreateNewRebateProgram.feature","src/test/features/initiateNewManufactureContract.feature"},
        glue = {"stepdefs"},
//        tags={"@Smoke,@Regression"},
//        tags={"@Smoke,@Regression,@Automated,~@NonAutomated"},
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
