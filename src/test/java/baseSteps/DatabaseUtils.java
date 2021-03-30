/**
 * Databaseutils class contains the methods to connect to the database and run SQL query and
 * retrieve the expected results which is going to be compared with actual result retrieved from API
 *
 * @author  QATest
 * @version 1.0
 * @since   03/01/2021
 */
package baseSteps;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
public class DatabaseUtils {
    public  Connection conn;
    public DatabaseMetaData dm;
    public  Statement stmt;
    public  ResultSet result;
    private  Properties prop;
    private String dbURL;
    private String user;
    private String password;
    private String sqlQuery;
    //private String sqlGetContractBYID;
    //protected String manufacturer_ID;
    //protected String MFRid;
    private static List<String> ExpectedAllManufacturerID;
    private static List<String> ExpectedAllManufacturerName;
    public static  int activeMFRCount;
    public static  int rowKey;
    static Logger logger = Logger.getLogger(DatabaseUtils.class.getName());
    /**
     * This is the  method which loads  the database connection details from database.properties file .
     */
    public void getDatabaseProperties() {
        try {
            prop = new Properties();
            //load a properties file from class path, inside static method
            prop.load(DatabaseUtils.class.getClassLoader()
                    .getResourceAsStream("database.properties"));
            dbURL = prop.getProperty("dbURL");
            logger.info(" Db URL is :" + dbURL);
            user=prop.getProperty("user");
            logger.info(" DB User  is :" + user);
            password=prop.getProperty("password");
            logger.info(" DB Password is : ***************************************");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * This is the  method establishes the connection to database
     */
    public void connectToRebateDB() {
        getDatabaseProperties();
        logger.info("Trying to connect to rebate db");
        try {
            conn = DriverManager.getConnection(dbURL, user, password);
            if (conn != null) {
                logger.info("Connected to DB");
            }
        }catch(SQLException ex){
            ex.printStackTrace();

        }
    }
    /**
     * This is the  method retrieves the database details after connection is successful
     */
    public void getDBDetails()  {
        logger.info("Printing the db details");
        try {
            if (conn != null) {
                dm = (DatabaseMetaData) conn.getMetaData();
                logger.info("Driver name: " + dm.getDriverName());
                logger.info("Driver version: " + dm.getDriverVersion());
                logger.info("Product name: " + dm.getDatabaseProductName());
                logger.info("Product version: " + dm.getDatabaseProductVersion());
            }
        }catch(SQLException ex){
            ex.printStackTrace();

        }
    }
    /**
     * This method retrieves the count of MFR records present in DB
     */
    public void executeGetMFRCountQuery(String query)  {
        try {
            sqlQuery=prop.getProperty(query);
            logger.info(" SQL is : "+sqlQuery);
            stmt = conn.createStatement();
            result = stmt.executeQuery(sqlQuery);
            result.next();
            activeMFRCount = result.getInt("count");
            logger.info("No of Active  MFR in DB are: " + activeMFRCount);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * This method returns the no of active MFR details retrieved
     */
    public int getNoOfActiveMFR() {
        return activeMFRCount;
    }
    /**
     * This method executes the SQL to get the no of active MFR IDs
     */
        public void executeAllMFRIDQuery(String query)  {
            try {
                sqlQuery=prop.getProperty(query);
                logger.info(" SQL is : "+sqlQuery);
                stmt = conn.createStatement();
                result = stmt.executeQuery(sqlQuery);
                logger.info("************************"+result);
                ExpectedAllManufacturerID=new ArrayList<String>();
                while (result.next()) {
                    ExpectedAllManufacturerID.add(result.getString("manufacturer_ID"));
                    //logger.info(result.getString("manufacturer_ID"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
        }
    }
    /**
     * This method returns the no of active MFR details retrieved
     */
    public List<String> getAllMFRID() {
        return ExpectedAllManufacturerID;
    }
    /**
     * This method executes the SQL query to get all active MFR names
     */
    public void executeAllMFRNameQuery(String query)  {
        try {
            sqlQuery=prop.getProperty(query);
            logger.info(" SQL is : "+sqlQuery);
            stmt = conn.createStatement();
            result = stmt.executeQuery(sqlQuery);
            logger.info("************************"+result);
            ExpectedAllManufacturerName=new ArrayList<String>();
            while (result.next()) {
                ExpectedAllManufacturerName.add(result.getString("name"));
                logger.info(result.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * This method returns the no of active MFR Names retrieved
     */
    public List<String> getAllMFRName(){
        return ExpectedAllManufacturerName;
    }
    /**
     * This method retrieves the ContractDetails Row_Key from DB and store in rowkey variable
     * @author Bharath
     * @exception  Exception
     *@param query The sql query which is loaded from database.properties file and executed to get Row_Key from DB
     */
    public void executeGetContractBYIDQuery(String query)  {
        try {
            sqlQuery=prop.getProperty(query);
            logger.info(" SQL is : "+sqlQuery);
            stmt = conn.createStatement();
            result = stmt.executeQuery(sqlQuery);
            result.next();
            rowKey= result.getInt("Row_key");
            logger.info("RowKey From DB is: " + rowKey);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * This method returns the ContractDetails Row_Key from DB
     * @author Bharath
     */
    public int getRowKey() {
        return rowKey;
    }

}

