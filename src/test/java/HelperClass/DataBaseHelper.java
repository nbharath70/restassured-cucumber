package HelperClass;

import TestBase.TestBase;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends TestBase {
    public static Logger log=getMyLogger(DataBaseHelper.class);
    public Connection conn;
    public Statement stmt;
    public PreparedStatement psmt;
    public ResultSet prepareQueryResult;

    /**
     * @Author Arun Kumar
     * This is the  method establishes the connection to database
     * @return stmt
     * @updated by: Rabbani
     * This method will read the environment passed through command line and connects to that environment
     */
    public Statement getStatement(){
        String dbUrl=null;
        String dbUserName=null;
        String dbPassword=null;
        try {
            if(System.getProperty("environment")==null)
            {
                if(System.getProperty("connectTo")==null){
                    dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatDBURL");
                    dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatUser");
                    dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatPassword");
                }
                else if(System.getProperty("connectTo").equalsIgnoreCase("flowable")){
                    log.info("connecting to flowable DB");
                    dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatDBURLFlowable");
                    dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatUserFlowable");
                    dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatPasswordFlowable");
                }

            }
            else if(System.getProperty("environment").equalsIgnoreCase("UAT"))
            {
                if(System.getProperty("connectTo")==null){
                    dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatDBURL");
                    dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatUser");
                    dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatPassword");
                }
                else if(System.getProperty("connectTo").equalsIgnoreCase("flowable")) {
                    log.info("connecting to flowable DB");
                    dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatDBURLFlowable");
                    dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatUserFlowable");
                    dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatPasswordFlowable");
                }


            }
            else if(System.getProperty("environment").equalsIgnoreCase("Dev"))
            {
                if(System.getProperty("connectTo")==null){
                    dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devDBURL");
                    dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devUser");
                    dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devPassword");
                }
                else if(System.getProperty("connectTo").equalsIgnoreCase("flowable")){
                    log.info("connecting to flowable DB");
                    dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devDBURLFlowable");
                    dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devUserFlowable");
                    dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devPasswordFlowable");
                }

            }
            else{
                log.info("Invalid environment");
            }

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
     * @Author Arun Kumar
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
     * @Author Arun Kumar
     * getDataWithoutPropertiesKey Method used to retrieves the data of given query without PropertiesKey
     * @param query
     * @return data
     */
    public ResultSet getDataWithoutPropertiesKey(String query){
        ResultSet rs = null;
        try {
            rs = getStatement().executeQuery(query);
            log.info("Result Set:"+rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    /**
     * @Author Arun Kumar
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
     * @Author Arun Kumar
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
     * @Author Arun Kumar
     * getDataColumnArrayListValueDB method is used to get list values from database
     * @param query
     * @param columnName
     * @return
     */
    public ArrayList<String> getDataColumnArrayListValueDBWithoutPropertiesKey(String query, String columnName)
    {
        try{

            ResultSet result = getDataWithoutPropertiesKey(query);
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
     * @Author Arun Kumar
     * getDataColumnArrayListValueDBInterger method is used to get list values from database
     * @param query
     * @param columnName
     * @return
     */
    public ArrayList<Integer> getDataColumnArrayListValueDBInterger(String query, String columnName)
    {
        try{

            ResultSet result = getData(query);
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            while (result.next()) {
                arrayList.add(result.getInt(columnName));
            }
            return arrayList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author Arun Kumar
     * getDataColumnArrayListValueDBIntergerWithoutPropertiesKey method is used to get list values from database
     * @param query
     * @param columnName
     * @return
     */
    public ArrayList<Integer> getDataColumnArrayListValueDBIntergerWithoutPropertiesKey(String query, String columnName)
    {
        try{

            ResultSet result = getDataWithoutPropertiesKey(query);
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            while (result.next()) {
                arrayList.add(result.getInt(columnName));
            }
            return arrayList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Author Arun Kumar
     * getDataColumnArrayListValueDBWithoutKey method is used to get list values from database
     * @param query
     * @param columnName
     * @return
     */
    public ArrayList<String> getDataColumnArrayListValueDBWithoutKey(String query, String columnName)
    {
        try{

            ResultSet result = getDataWithoutPropertiesKey(query);
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
     * @Author Rabbani
     * getSingleCellValueAsStringFromDB this method reads the single cell value from DB query result and returns it as a String
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
            System.out.println(cellValue);
            return cellValue;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author Bharath
     * executePreparedQuery this method Executes the Prepared Query Upends the Intvalue to the Query
     * @param query
     * @param queryParam
     * @return ResultSet
     */
    public ResultSet executePreparedQuery(String query,int queryParam) {
        try {
            psmt= conn.prepareStatement(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, query));
            log.info("query parameter is"+queryParam);
            psmt.setInt(1,queryParam);
            prepareQueryResult =psmt.executeQuery();
            log.info("Prepared query execution Result is  " + prepareQueryResult + " From DB");
            return prepareQueryResult;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * @Author Bharath
     * executePreparedQuery this method Executes the Prepared Query Upends the Intvalue to the Query
     * @param query
     * @param queryParam
     * @return ResultSet
     */
    public void executeUpdatePreparedQuery(String query,int queryParam) {
        try {
            getStatement();
            psmt= conn.prepareStatement(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, query));
            log.info("query parameter is:"+queryParam);
            psmt.setInt(1,queryParam);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    /**
     * @Author Bharath
     * executePreparedQuery this method Executes the Prepared Query Upends the Stringvalues to the Query
     * @param query
     * @param queryParam
     * @return ResultSet
     */
    public ResultSet executePreparedQuery(String query,String queryParam) {
        try {getStatement();
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

    public ResultSet executePreparedDirectQuery(String query,String queryParam) {
        try {getStatement();
            psmt= conn.prepareStatement(query);
            psmt.setString(1,queryParam);
            prepareQueryResult =psmt.executeQuery();
            log.info("Prepared query execution result is" + prepareQueryResult + " From DB");
            return prepareQueryResult;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * @Author Bharath
     * executePreparedQuery this method Executes the Prepared Query it will Append the List of values into the Query
     * @param initialSplitquery
     * @param finalSplitQuery
     * @param queryParam
     * @return ResultSet
     */
    public ResultSet executePreparedQueryForArrayAsParameter(String initialSplitquery,ArrayList queryParam,String finalSplitQuery) {
        try {getStatement();
            String queryToAppend=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, initialSplitquery);
            String temp="";
            for(int i=0;i<queryParam.size();i++){
                temp+=",?";
            } temp=temp.replaceFirst(",","");
            temp+=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, finalSplitQuery);
            queryToAppend=queryToAppend+temp;
            psmt= conn.prepareStatement(queryToAppend);
            for(int i=0;i<queryParam.size();i++){
                psmt.setObject(i+1,queryParam.get(i));
            }
            prepareQueryResult =psmt.executeQuery();
            log.info("Prepared query execution result is" + prepareQueryResult + " From DB");
            return prepareQueryResult;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * executeUpdatePreparedQueryAsString method is used to execute the Query as parameter which is type os String
     * @Author Bharath
     * @param query
     * @param queryParam
     */
    public void executeUpdatePreparedQueryAsString(String query,String queryParam) {
        try {
            getStatement();
            psmt = conn.prepareStatement(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, query));
            log.info("query parameter is "+queryParam);
            psmt.setString(1,queryParam);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeUpdatePreparedQueryAsStringwithDirectQuery(String query,String queryParam) {
        try {
            getStatement();
            psmt = conn.prepareStatement( query);
            log.info("query parameter is "+queryParam);
            psmt.setString(1,queryParam);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * getDataColumnArrayUsingPreparedStatement method is used to execute the Query as parameter and will return the Columnvalue as String from DB
     * @Author Bharath
     * @param query
     * @param queryParam
     */
    public ArrayList getDataColumnArrayUsingPreparedStatement(String query,String queryParam,String columnName)
    {
        try{
            ResultSet result = executePreparedQuery(query,queryParam);
            ArrayList arrayList = new ArrayList();
            while (result.next()) {
                arrayList.add(result.getString(columnName));
            }
            return arrayList;
        }catch (Exception e){
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

    /**
     * executeUpdatePreparedQueryAsString method is used to execute the Query as parameter which is type os String
     * @Author Arun Kumar
     * @param query
     * @param queryParam2
     */
    public ResultSet executeUpdatePreparedQueryByTwoParamValue(String query,String queryParam1,int queryParam2) {
        try {
            getStatement();
            psmt = conn.prepareStatement(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, query));
            log.info("query parameter is "+queryParam1);
            psmt.setString(1,queryParam1);
            log.info("query parameter is "+queryParam2);
            psmt.setInt(2,queryParam2);
            prepareQueryResult =psmt.executeQuery();
            log.info("Prepared query execution result is" + prepareQueryResult + " From DB");
            return prepareQueryResult;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * @Author Smruti
     * @param
     * @param
     */

    public int executePreparedQuery(String query,String queryParam,String columnName) {
        try {
            psmt= conn.prepareStatement(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, query));
            psmt.setString(1,queryParam);
            prepareQueryResult =psmt.executeQuery();
            log.info("Prepared query execution result is" + prepareQueryResult + " From DB");
            prepareQueryResult.next();
            int count=prepareQueryResult.getInt(1);
            //log.info("The value retrieved from db is:"+count);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public ArrayList executePreparedQuerytoGetColumnArray(String query,String queryParam,String columnName) {
        getStatement();
//            psmt= conn.prepareStatement(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, query));
//            psmt.setString(1,queryParam);
//            prepareQueryResult =psmt.executeQuery();
//            log.info("Prepared query execution result is" + prepareQueryResult + " From DB");
//            return prepareQueryResult;
        try {
            ResultSet result = executePreparedQuery(query, queryParam);
            ArrayList arrayList = new ArrayList();
            String column = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnName);
            while (result.next()) {
                arrayList.add(result.getString(column));
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList executePreparedQuerytoGetColumnArrayofIntValues(String query,String queryParam,String columnName) {
        getStatement();
        try {
            ResultSet result = executePreparedQuery(query, queryParam);
            ArrayList arrayList = new ArrayList();
            String column = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnName);
            while (result.next()) {
                arrayList.add(result.getInt(column));
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @uthor Arun Kumar
     * executePreparedQuery this method Executes the Prepared Query Upends the Intvalue to the Query
     * @param query
     * @param queryParam
     * @return ResultSet
     */
    public void executeUpdatePreparedQueryWithPropertiesFile(String query,String queryParam) {
        try {
            getStatement();
            psmt = conn.prepareStatement(query);
            log.info("query parameter is" + queryParam);
            psmt.setString(1, queryParam);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Author Rabbani
     * preparedQueryWithListOfStrings Method used to replaces strings in query with list of strings
     * @param queryKey
     * @param listStrings - List<String>
     * @return url
     */
    public String preparedQueryWithListOfStrings(String queryKey, List<String> listStrings)
    {
        try {
            String query = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, queryKey);
            for(String strToReplace:listStrings){
                query=query.replaceFirst("strToReplace",strToReplace);
            }
            log.info("*****The Final query="+query+"*****************");
            return query;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @Author Rabbani
     * this method will executes a delete query
     * @param query
     *
     */
    public void executeDeleteQueryWithoutreadingFromPropFile(String query) {
        try {
            boolean result=getStatement().execute(query);
            Assert.assertFalse("Delete query not executed ", result);
            log.info("Delete query executed successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    /**
     * @Author Rabbani
     * this method will set property to connect to flowable DB
     *
     */
    public void connectToOtherDB(String dbName) {
        if(dbName.equalsIgnoreCase("flowable")){
            System.setProperty("connectTo","flowable");
        }
        else {
            log.info("Invalid DB name");
        }
    }

    public void disConnectToFlowable() {
        System.clearProperty("connectTo");
        cleanUp();
    }

    /**
     * @Author Arun Kumar
     * This is the  method establishes the connection to Flowable database
     * @return stmt
     * @updated by: Rabbani
     * This method will read the environment passed through command line and connects to that environment
     */
    public Statement getStatementFlowable(String env){
        String dbUrl=null;
        String dbUserName=null;
        String dbPassword=null;
        try {
            if(env.equalsIgnoreCase("UAT"))
            {
                dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatDBFlowableURL");
                dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatUser");
                dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatPassword");

            }
            else if(env.equalsIgnoreCase("Dev"))
            {
                dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devDBFlowableURL");
                dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devUser");
                dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devPassword");

            }
            else{
                log.info("Invalid environment");
            }

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
     * @Author Arun Kumar
     * getStatementFlowable Method used to retrieves the data of given query
     * @param query
     * @return data
     */
    public ResultSet getDataFlowable(String env,String query){
        ResultSet rs = null;
        try {
            rs = getStatementFlowable(env).executeQuery(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, query));
            log.info("Result Set:"+rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public String executeUpdatePreparedQueryAsStringFlowable(String env, String query, String queryParam,String columnName) {
        try {
            getStatementFlowable(env);
            psmt = conn.prepareStatement(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, query));
            log.info("query parameter is "+queryParam);
            psmt.setString(1,queryParam);
            ResultSet prepareQueryResult =psmt.executeQuery();
            log.info("Prepared query execution result is" + prepareQueryResult + " From DB");
            prepareQueryResult.next();
            String str = prepareQueryResult.getString(columnName);
            return str;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


