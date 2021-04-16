package stepdefs;
import HelperClass.DataBaseHelper;
import baseSteps.DatabaseUtils;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class Hooks extends DataBaseHelper {
    DataBaseHelper dbHelper = new DataBaseHelper();

    public static Logger log=getMyLogger(Hooks.class);

    public Hooks(DataBaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Before
    public void setUp() {
        log.info("+++++++++++++Setting up DB connection and API End Point+++++++++++++++++++++++++");
    }

    @After
    public void cleanUp() {
        log.info("+++++++++++++Closing up the  up DB connection +++++++++++++++++++++++++");
        dbHelper.cleanUp();
    }
}

