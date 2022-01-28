package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.DeleteDrugPojo;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeleteDrugAPI extends TestBase {
    DeleteDrugPojo deleteDrugPojo=new DeleteDrugPojo();
    private int drugRowkey=0;
    private int drugGroupRowKey;
    private String instanceKey;
    private String mfrDLId=null;
    private String ndc=null;
    private String startDate=null;
    private String endDate=null;
    private int drugRowkey1=0;
    private String mfrDLId1=null;
    private String ndc1=null;
    private String startDate1=null;
    private String endDate1=null;
    List<String> listOfQueryParams=null;
    private List<String> instanceKeyFromresponse;

    Response response=null;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    VerificationHelperClass verificationHelperClass=new VerificationHelperClass();
    public void executeTheQueryAndGetAValidDrugDetail(String queryKey, String rowkeyKey, String mfrDLIdKey, String ndcKey,String startDateKey,String endDateKey) {
        try {
            ResultSet rs = dataBaseHelper.getData(queryKey);
            String rowkeyColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, rowkeyKey);
            String mfrDLIdColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, mfrDLIdKey);
            String ndcColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, ndcKey);
            String startDatecolumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, startDateKey);
            String endDatecolumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, endDateKey);
            String drugGroupRowKeyColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "columnDrugGroupRowKey");
            String drugGroupUpdatedDateColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "columndrugGroupUpdatedDate");
            rs.next();
            drugRowkey = Integer.parseInt(rs.getString(rowkeyColumnName));
            mfrDLId=rs.getString(mfrDLIdColumnName);
            ndc = rs.getString(ndcColumnName);
            startDate=rs.getString(startDatecolumnName);
            endDate=rs.getString(endDatecolumnName);
            drugGroupRowKey=rs.getInt(drugGroupRowKeyColumnName);
            String recUpdateDate=rs.getString(drugGroupUpdatedDateColumnName);
            instanceKey=recUpdateDate.replaceFirst(" ","T");
        }catch (Exception e){e.printStackTrace();}

    }

    public void prepareRequestBodyWithTheListOfDrugRowkeys() {
        List<Integer> listOfRowKeys=new ArrayList<Integer>();
        listOfRowKeys.add(drugRowkey);
        System.out.println(listOfRowKeys);
        deleteDrugPojo.setDrugGroupRowKey(drugGroupRowKey);
        deleteDrugPojo.setInstanceKey(instanceKey);
        deleteDrugPojo.setDrugListDetailRowKeys(listOfRowKeys);

    }

    public void hitEndPointWithPostRequest(String endPointKey) {
        response=deleteOperation(endPointKey,deleteDrugPojo);
        instanceKeyFromresponse=JsonPath.read(response.asString(),"$..instanceKey");


    }

    public void verifyAPIresponseWith(String responseMsgKey) {
        List<String> listOfRowKeys=new ArrayList<String>();
        listOfRowKeys.add(instanceKeyFromresponse.get(0));
        for (String a:
        listOfRowKeys) {
            System.out.println(a);

        }
        String expectedResponse=dataBaseHelper.preparedQueryWithListOfStrings(responseMsgKey,listOfRowKeys);
        verificationHelperClass.verifyexpectedAndActualDirectlyAsStrings(response,expectedResponse);
    }

    public void verifyTheDataBaseForTheDeletedDrug(String queryKey) {
        try {
            listOfQueryParams = new ArrayList<String>();
            listOfQueryParams.add(mfrDLId);
            listOfQueryParams.add(ndc);
            listOfQueryParams.add(startDate);
            listOfQueryParams.add(endDate);
            String deletedRecordQuery = dataBaseHelper.preparedQueryWithListOfStrings(queryKey, listOfQueryParams);
            ResultSet rs = dataBaseHelper.getDataWithoutPropertiesKey(deletedRecordQuery);
//            rs.next();
//            String actual_LCS=rs.getString("Life_Cycle_Status");
//            Assert.assertEquals("Deleted record not found in DB","Deleted",actual_LCS);
            Assert.assertTrue("Deleted record not found in DB",rs.next());
            log.info("Deleted record found in DB");

        }catch (Exception e){e.printStackTrace();}

    }

    public void deleteTheNewDeletedRecordFromDB(String queryKey) {
        listOfQueryParams = new ArrayList<String>();
        listOfQueryParams.add(mfrDLId);
        listOfQueryParams.add(ndc);
        listOfQueryParams.add(startDate);
        listOfQueryParams.add(endDate);
        String SQLToDeleteTheDeletedRecord = dataBaseHelper.preparedQueryWithListOfStrings(queryKey, listOfQueryParams);
        dataBaseHelper.executeDeleteQueryWithoutreadingFromPropFile(SQLToDeleteTheDeletedRecord);
    }

    public void verifyTheCorrectStatusCode(int statusCode) {
        verificationHelperClass.verifyStatusCode(response,statusCode);
    }

    public void verifiesAPIresponseMessageWithDeleteAddedDrugResponse(String responseMsgKey) {
        String expectedResponse=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, responseMsgKey);
        verificationHelperClass.verifyexpectedAndActualDirectlyAsStrings(response,expectedResponse);
    }

    public void prepareRequestBodyWithInValidRowKeyOfDrug(int rowKey) {
        List<Integer> listOfRowKeys=new ArrayList<Integer>();
        listOfRowKeys.add(rowKey);
        deleteDrugPojo.setDrugListDetailRowKeys(listOfRowKeys);
    }

    public void executesTheQueryAndGetsADrugDetailRowkey(String queryKey, String rowkeyKey) {
        try {
            ResultSet rs = dataBaseHelper.getData(queryKey);
            String rowkeyColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, rowkeyKey);
            String drugGroupRowKeyColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "columnDrugGroupRowKey");
            String drugGroupUpdatedDateColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "columndrugGroupUpdatedDate");
            rs.next();
            drugRowkey = Integer.parseInt(rs.getString(rowkeyColumnName));
            drugGroupRowKey=rs.getInt(drugGroupRowKeyColumnName);
            String recUpdateDate=rs.getString(drugGroupUpdatedDateColumnName);
            instanceKey=recUpdateDate.replaceFirst(" ","T");
        }catch (Exception e){e.printStackTrace();}
    }

    public void prepareRequestBodyWithValidAndInValidRowKeysOfDrug(int rowKey) {
        List<Integer> listOfRowKeys=new ArrayList<Integer>();
        listOfRowKeys.add(drugRowkey);
        listOfRowKeys.add(rowKey);
        deleteDrugPojo.setDrugListDetailRowKeys(listOfRowKeys);
        deleteDrugPojo.setDrugGroupRowKey(drugGroupRowKey);
        deleteDrugPojo.setInstanceKey(instanceKey);
    }

    public void prepareRequestBodyWithInValidRowKeys(int rowKey1, int rowKey2) {
        List<Integer> listOfRowKeys=new ArrayList<Integer>();
        listOfRowKeys.add(rowKey1);
        listOfRowKeys.add(rowKey2);
        deleteDrugPojo.setDrugListDetailRowKeys(listOfRowKeys);
        deleteDrugPojo.setDrugGroupRowKey(drugGroupRowKey);
        deleteDrugPojo.setInstanceKey(instanceKey);
    }

    public void executeQueryAndGetMultipleValidDrugDetail(String queryKey, String rowkeyKey, String mfrDLIdKey, String ndcKey, String startDateKey, String endDateKey) {
        try {
            ResultSet rs = dataBaseHelper.getData(queryKey);
            String rowkeyColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, rowkeyKey);
            String mfrDLIdColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, mfrDLIdKey);
            String ndcColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, ndcKey);
            String startDatecolumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, startDateKey);
            String endDatecolumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, endDateKey);
            String drugGroupRowKeyColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "columnDrugGroupRowKey");
            String drugGroupUpdatedDateColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "columndrugGroupUpdatedDate");
            rs.next();
            drugRowkey = Integer.parseInt(rs.getString(rowkeyColumnName));
            mfrDLId=rs.getString(mfrDLIdColumnName);
            ndc = rs.getString(ndcColumnName);
            startDate=rs.getString(startDatecolumnName);
            endDate=rs.getString(endDatecolumnName);
            drugGroupRowKey=rs.getInt(drugGroupRowKeyColumnName);
            String recUpdateDate=rs.getString(drugGroupUpdatedDateColumnName);
            instanceKey=recUpdateDate.replaceFirst(" ","T");
            rs.next();
            drugRowkey1 = Integer.parseInt(rs.getString(rowkeyColumnName));
            mfrDLId1=rs.getString(mfrDLIdColumnName);
            ndc1 = rs.getString(ndcColumnName);
            startDate1=rs.getString(startDatecolumnName);
            endDate1=rs.getString(endDatecolumnName);
        }catch (Exception e){e.printStackTrace();}

    }

    public void prepareRequestBodyWithMultipleDrugRowkeys() {
        List<Integer> listOfRowKeys=new ArrayList<Integer>();
        listOfRowKeys.add(drugRowkey);
        listOfRowKeys.add(drugRowkey1);
        deleteDrugPojo.setDrugListDetailRowKeys(listOfRowKeys);
        deleteDrugPojo.setDrugGroupRowKey(drugGroupRowKey);
        deleteDrugPojo.setInstanceKey(instanceKey);
    }

    public void verifyAPIresponseWithSuccessMsgforTwoDrugs(String expectedMsgKey) {
        List<String> listOfRowKeys=new ArrayList<String>();
        listOfRowKeys.add(instanceKeyFromresponse.get(0));
        String expectedResponse=dataBaseHelper.preparedQueryWithListOfStrings(expectedMsgKey,listOfRowKeys);
        verificationHelperClass.verifyexpectedAndActualDirectlyAsStrings(response,expectedResponse);
    }

    public void executeQueryAndVerifyTheDataBaseForTheDeletedRecordForTwoDrugs(String queryKey) {
        try {
            listOfQueryParams = new ArrayList<String>();
            listOfQueryParams.add(mfrDLId);
            listOfQueryParams.add(ndc);
            listOfQueryParams.add(startDate);
            listOfQueryParams.add(endDate);
            String deletedRecordQuery = dataBaseHelper.preparedQueryWithListOfStrings(queryKey, listOfQueryParams);
            ResultSet rs = dataBaseHelper.getDataWithoutPropertiesKey(deletedRecordQuery);
//            rs.next();
//            String actual_LCS=rs.getString("Life_Cycle_Status");
//            Assert.assertEquals("Deleted record not found in DB","Deleted",actual_LCS);
            Assert.assertTrue("Deleted record not found in DB for first Drug", rs.next());
            log.info("Deleted record found in DB for first Drug With RowKey="+rs.getString("Row_Key"));

            listOfQueryParams = new ArrayList<String>();
            listOfQueryParams.add(mfrDLId1);
            listOfQueryParams.add(ndc1);
            listOfQueryParams.add(startDate1);
            listOfQueryParams.add(endDate1);
            String deletedRecordQuery1 = dataBaseHelper.preparedQueryWithListOfStrings(queryKey, listOfQueryParams);
            ResultSet rs1 = dataBaseHelper.getDataWithoutPropertiesKey(deletedRecordQuery1);
            Assert.assertTrue("Deleted record not found in DB for second Drug", rs1.next());
            log.info("Deleted record found in DB for second DrugWith RowKey="+rs.getString("Row_Key"));
        }catch (Exception e){e.printStackTrace();}
    }

    public void deleteTheNewDeletedRecordsFromTheDBByExecutingDeleteQueryForTwoDrugs(String queryKey) {
        listOfQueryParams = new ArrayList<String>();
        listOfQueryParams.add(mfrDLId);
        listOfQueryParams.add(ndc);
        listOfQueryParams.add(startDate);
        listOfQueryParams.add(endDate);
        String SQLToDeleteTheDeletedRecord = dataBaseHelper.preparedQueryWithListOfStrings(queryKey, listOfQueryParams);
        dataBaseHelper.executeDeleteQueryWithoutreadingFromPropFile(SQLToDeleteTheDeletedRecord);

        listOfQueryParams = new ArrayList<String>();
        listOfQueryParams.add(mfrDLId1);
        listOfQueryParams.add(ndc1);
        listOfQueryParams.add(startDate1);
        listOfQueryParams.add(endDate1);
        String SQLToDeleteTheDeletedRecord1 = dataBaseHelper.preparedQueryWithListOfStrings(queryKey, listOfQueryParams);
        dataBaseHelper.executeDeleteQueryWithoutreadingFromPropFile(SQLToDeleteTheDeletedRecord1);

    }

    public void connectToTheFlowableAndExecutesTheQuery(String query) {
        dataBaseHelper.connectToOtherDB("flowablE");
        dataBaseHelper.getSingleCellValueAsStringFromDB(query,"count");
        dataBaseHelper.disConnectToOtherDB();
        String query1="getCountOfMFRName";
        dataBaseHelper.getSingleCellValueAsStringFromDB(query1, "count");
    }
    //SQLite trial
    public void inMemoryDBSQLite(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dbrebate.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
        }
        System.out.println("Opened database successfully");
        try {
//            stmt = c.createStatement();
//            String sqlTCS = "CREATE TABLE IF NOT EXISTS  test_Claim_Summarization" +
//                    "(Claim_Billing_Batch_ID BIGINT NULL," +
//                    " RunDateTime            BIGINT NULL) ";
//            stmt.executeUpdate(sqlTCS);

            stmt = c.createStatement();
//            String sqlCreateSP = "CREATE PROCEDURE procedure_name AS sql_statement GO;";
//            String sqlExecSP ="INSERT INTO test_Claim_Summarization Select 1,date()";
            String sqlExecSP2 ="Declare @v_Claim_Billing_Batch_ID VARCHAR(MAX)='15'";
            stmt.executeUpdate(sqlExecSP2);
            stmt.close();
            System.out.println("SP got Executed successfully(#InmemTable)");

//            stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery( "SELECT * FROM test_Claim_Summarization;" );
//
//            while ( rs.next() ) {
//                int claim_Billing_Batch_ID = rs.getInt("Claim_Billing_Batch_ID");
//                String  runDateTime = rs.getString("RunDateTime");
//
//                System.out.println( "claim_Billing_Batch_ID = " + claim_Billing_Batch_ID );
//                System.out.println( "runDateTime = " + runDateTime );
//                System.out.println();
//            }
//            rs.close();
//            stmt.close();



//            stmt = c.createStatement();
//            String sql = "CREATE TABLE IF NOT EXISTS COMPANY " +
//                    "(ID INT PRIMARY KEY     NOT NULL," +
//                    " NAME           TEXT    NOT NULL, " +
//                    " AGE            INT     NOT NULL, " +
//                    " ADDRESS        CHAR(50), " +
//                    " SALARY         REAL)";
//            stmt.executeUpdate(sql);
//            stmt.close();
//            System.out.println("Table created successfully");
//
//            stmt = c.createStatement();
//            String sql1 = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
//                    "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
//            stmt.executeUpdate(sql1);
//
//            sql1 = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
//                    "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
//            stmt.executeUpdate(sql1);
//
//            stmt.close();
////            c.commit();
//
//
//
//            stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
//
//            while ( rs.next() ) {
//                int id = rs.getInt("id");
//                String  name = rs.getString("name");
//                int age  = rs.getInt("age");
//                String  address = rs.getString("address");
//                float salary = rs.getFloat("salary");
//
//                System.out.println( "ID = " + id );
//                System.out.println( "NAME = " + name );
//                System.out.println( "AGE = " + age );
//                System.out.println( "ADDRESS = " + address );
//                System.out.println( "SALARY = " + salary );
//                System.out.println();
//            }
//            rs.close();
//            stmt.close();
            c.close();
        }catch (Exception e){
            try {
                c.close();
            }catch(Exception e1){e1.printStackTrace();}
            e.printStackTrace();}
        //return c;
    }

    //H2 trial
    public  void inMemoryDBH2(){
        Connection conn = null;
        Statement stmt = null;
        String JDBC_DRIVER = "org.h2.Driver";
        String DB_URL = "jdbc:h2:~/test";
        String USER = "sa";
        String PASS = "";
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS  REGISTRATION " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

//            // STEP 3: Execute a query
//            stmt = conn.createStatement();
//            String sqlINSERT1 = "INSERT INTO Registration " + "VALUES (100, 'Zara', 'Ali', 18)";
//            stmt.executeUpdate(sqlINSERT1);
//
//            sqlINSERT1 = "INSERT INTO Registration " + "VALUES (101, 'Mahnaz', 'Fatma', 25)";
//            stmt.executeUpdate(sqlINSERT1);
//
//            stmt = conn.createStatement();
//            String sqlSelect = "SELECT id, first, last, age FROM Registration";
//            ResultSet rs = stmt.executeQuery(sqlSelect);
//            // STEP 4: Extract data from result set
//            while (rs.next()) {
//                // Retrieve by column name
//                int id = rs.getInt("id");
//                int age = rs.getInt("age");
//                String first = rs.getString("first");
//                String last = rs.getString("last");
//
//                // Display values
//                System.out.print("ID: " + id);
//                System.out.print(", Age: " + age);
//                System.out.print(", First: " + first);
//                System.out.println(", Last: " + last);
//
//            }


//            ////EXECUTING SOME ADV SQLS
//            stmt = conn.createStatement();
//            String sqlINSERT1 = "Declare @v_Claim_Billing_Batch_ID VARCHAR(MAX)='15'";
//            stmt.executeUpdate(sqlINSERT1);
//            ////did not work given some exception

//            ////try one more
//            stmt = conn.createStatement();
//            String sqlSelect = "Declare @v_Claim_Billing_Batch_ID VARCHAR(MAX)='15'; select @v_Claim_Billing_Batch_ID as result;";
//            ResultSet rs = stmt.executeQuery(sqlSelect);
//            // STEP 4: Extract data from result set
//            while (rs.next()) {
//                // Retrieve by column name
//
//                String result = rs.getString("result");
//
//                // Display values
//                System.out.print("result: " + result);
//
//            }
//            ////got same exception for declare

            ////try creating a procedure
            stmt = conn.createStatement();
            String sqlSP = "CREATE PROCEDURE procedure_name AS sql_statement GO;";
            stmt.executeUpdate(sqlSP);
            System.out.println("Created SP...");


        }catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        System.out.println("Goodbye!");
    }


}
