package stepdefs;
import HelperClass.DataBaseHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.junit.Assume;
//import org.testng.annotations.AfterMethod;

public class Hooks extends DataBaseHelper {
    DataBaseHelper dbHelper = new DataBaseHelper();

    public static Logger log=getMyLogger(Hooks.class);

    public Hooks(DataBaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Before(value ="@NonAutomated",order=0)
    public  void skipScenario(Scenario scenario)
    {
        System.out.println("SKIPPED SCENARIO IS NOT AUTOMATED :"+scenario.getName());
        Assume.assumeTrue(false);
    }

    @Before
    public void setUp(Scenario scenario) {
        scenarioName = scenario.getName();
        log.info("+++++++++++++Setting up DB connection and API End Point+++++++++++++++++++++++++");
        RestAssured.useRelaxedHTTPSValidation();
    }

    @After
    public void cleanUp() {
        log.info("+++++++++++++Closing up the  up DB connection +++++++++++++++++++++++++");
        dbHelper.cleanUp();
    }
}

