package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.DeleteDrugPojo;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.junit.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBAutomationTest extends TestBase {
    DeleteDrugPojo deleteDrugPojo=new DeleteDrugPojo();

    Response response=null;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    VerificationHelperClass verificationHelperClass=new VerificationHelperClass();

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

//            // STEP 3: Execute an insert  query
//            stmt = conn.createStatement();
//            String sqlINSERT1 = "INSERT INTO Registration " + "VALUES (100, 'Zara', 'Ali', 18)";
//            stmt.executeUpdate(sqlINSERT1);
//
//            sqlINSERT1 = "INSERT INTO Registration " + "VALUES (101, 'Mahnaz', 'Fatma', 25)";
//            stmt.executeUpdate(sqlINSERT1);
//
//            stmt = conn.createStatement();
//            String sqlSelect = "SELECT id, first, last, age FROM Registration where id=200";
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
//            stmt = conn.createStatement();
//            String sqlSP = "CREATE PROCEDURE procedure_name AS sql_statement GO;";
//            stmt.executeUpdate(sqlSP);
//            System.out.println("Created SP...");


            ////creating an Aliase in H2 for an SP and executing it
            stmt = conn.createStatement();
//            String sqlSP6 = "CREATE ALIAS GET_ORDERS6 AS  $$\n" +
//                    "import java.sql.Connection;\n" +
//                    "import java.sql.PreparedStatement;\n" +
//                    "import java.sql.ResultSet;\n" +
//                    "import java.sql.SQLException;\n" +
//                    "import java.util.Collection;\n" +
//                    "import java.util.HashSet;\n" +
//                    "import java.util.stream.Collectors;\n" +
//                    "    @CODE\n" +
//                    "    ResultSet getOrdersWithValidItems(final Connection conn, final int valueId) throws SQLException\n" +
//                    "    {\n" +
//                    "        final Collection<String> recordsWithoutValidity = new HashSet<String>();\n" +
//                    "        final Collection<String> validRecords = new HashSet<String>();\n" +
//                    "        // get records without validity information\n" +
//                    "        StringBuffer sql = new StringBuffer();\n" +
//                    "        sql.append(\"set @id=\"+valueId+\" INSERT INTO Registration VALUES (@id, 'Test2', 'first2', 11)\");\n" +
//                    "//        sql.append(\"WHERE CUSTOMER='\" + customer + \"' \");\n" +
//                    "        PreparedStatement ps = conn.prepareStatement(sql.toString());\n" +
//                    "        ps.execute();\n" +
//                    "        return null;\n" +
//                    "    }\n" +
//                    "    $$;";
//            stmt.executeUpdate(sqlSP6);
            stmt.execute("CALL GET_ORDERS6 ('202');");




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
