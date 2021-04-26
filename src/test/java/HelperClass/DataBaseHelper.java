package HelperClass;

import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class DataBaseHelper extends TestBase {
    public static Logger log=getMyLogger(DataBaseHelper.class);
    public Connection conn;
    public Statement stmt;
    public PreparedStatement psmt;
    public ResultSet prepareQueryResult;

    /**
     * @uthor Arun Kumar
     * This is the  method establishes the connection to database
     * @return stmt
     */
    public Statement getStatement(){
        try {
            String dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "dbURL");
            String dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "user");
            String dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "password");
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            if (conn != null) {
                log.info("Connected to DB");
            }
            stmt = conn.createStatement();
            return stmt;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return stmt;
    }
    /**
     * @uthor: Arun Kumar
     * getData Method used to retrieves the data of given query
     * @param query
     * @return data
     */
    public ResultSet getData(String query){
        ResultSet rs = null;
        try {
            rs = getStatement().executeQuery(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, query));
            log.info("Result Set:"+rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * @uthor Arun Kumar
     * getDataColumnCount this method used get the total count of value for given query and column value
     * @param query
     * @param columnName
     * @return value
     */
    public int getDataColumnCountDB(String query, String columnName)
    {
        try{

            ResultSet result = getData(query);
            result.next();
            int value = result.getInt(columnName);
            return value;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * @uthor Arun Kumar
     * getDataColumnArrayListValueDB method is used to get list values from database
     * @param query
     * @param columnName
     * @return
     */
    public ArrayList<String> getDataColumnArrayListValueDB(String query, String columnName)
    {
        try{

            ResultSet result = getData(query);
            ArrayList<String> arrayList = new ArrayList<String>();
            while (result.next()) {
                arrayList.add(result.getString(columnName));
            }
            return arrayList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @uthor Rabbani
     * getSingleCellValueAsStringFromDB this method reads
    the single cell value from DB query result and returns it as a String
     * @param query
     * @param columnName
     * @return String
     */
    public String getSingleCellValueAsStringFromDB(String query, String columnName)
    {
        try{

            ResultSet result = getData(query);
            result.next();
            String cellValue = result.getString(columnName);
            return cellValue;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public ResultSet executePreparedQuery(String query,int queryParam) {
        try {
            psmt= conn.prepareStatement(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, query));
            log.info("query parameter is"+queryParam);
            psmt.setInt(1,queryParam);
            prepareQueryResult =psmt.executeQuery();
            log.info("Contract_ID is  " + prepareQueryResult + " From DB");
            return prepareQueryResult;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void executeUpdatePreparedQuery(String query,int queryParam) {
        try {
            psmt= conn.prepareStatement(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, query));
            log.info("query parameter is"+queryParam);
            psmt.setInt(1,queryParam);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public ResultSet executePreparedQuery(String query,String queryParam) {
        try {
            psmt= conn.prepareStatement(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, query));
            psmt.setString(1,queryParam);
            prepareQueryResult =psmt.executeQuery();
            log.info("Prepared query execution result is" + prepareQueryResult + " From DB");
            return prepareQueryResult;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void cleanUp(){
        try {
            log.info("Cleaning up connection, statement and Result Set");
            log.info("trying to check and close  the connection");
            if (conn != null && !conn.isClosed()) {
                conn.close();
                log.info("Closed the connection");
            }
            log.info("trying to check and close  the statement");
            if (stmt != null ) {
                stmt.close();
                log.info("Closed the statement");
            }
            log.info("trying to check and close  the result Set");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}


