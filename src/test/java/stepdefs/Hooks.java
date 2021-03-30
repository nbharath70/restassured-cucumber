package stepdefs;
import baseSteps.DatabaseUtils;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class Hooks extends DatabaseUtils {
        private DatabaseUtils dbUtils;
    static Logger logger = Logger.getLogger(Hooks.class.getName());
      public  Hooks(DatabaseUtils dbUtils) {
            this.dbUtils = dbUtils;
        }

        @Before
        public void setUp() {
            logger.info("+++++++++++++Setting up DB connection and API End Point+++++++++++++++++++++++++");
        }

        @After
            public void cleanUp(){
                try {
                    logger.info("Cleaning up connection, statement and Result Set");
                    logger.info("trying to check and close  the connection");
                    if (dbUtils.conn != null && !dbUtils.conn.isClosed()) {
                        dbUtils.conn.close();
                        logger.info("Closed the connection");
                    }
                    logger.info("trying to check and close  the statement");
                    if (dbUtils.stmt != null ) {
                        dbUtils.stmt.close();
                        logger.info("Closed the statement");
                    }
                    logger.info("trying to check and close  the result Set");
                    if(dbUtils.result!=null){
                        dbUtils.result.close();
                        logger.info("Closed the Result set");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        }

