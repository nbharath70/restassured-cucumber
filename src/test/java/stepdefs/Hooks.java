package stepdefs;
import HelperClass.DataBaseHelper;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import org.apache.log4j.Logger;

public class Hooks extends DataBaseHelper {
    DataBaseHelper dbHelper = new DataBaseHelper();

    public static Logger log=getMyLogger(Hooks.class);

    public Hooks(DataBaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Before
    public void setUp() {
        log.info("+++++++++++++Setting up DB connection and API End Point+++++++++++++++++++++++++");
        RestAssured.useRelaxedHTTPSValidation();
    }

    @After
    public void cleanUp() {
        log.info("+++++++++++++Closing up the  up DB connection +++++++++++++++++++++++++");
        dbHelper.cleanUp();
    }
}

