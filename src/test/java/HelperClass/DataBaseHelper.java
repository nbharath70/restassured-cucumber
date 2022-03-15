package HelperClass;

import TestBase.TestBase;
import org.apache.log4j.Logger;
import org.junit.Assert;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseHelper extends TestBase {
    public static Logger log=getMyLogger(DataBaseHelper.class);
    public Connection conn;
    public Statement stmt;
    public PreparedStatement psmt;
    public ResultSet prepareQueryResult;
    public CallableStatement cstmt;
    private static final int DB_RUN_QUERY_MAX_ROWS = 2000;
    private static final String DB_NULL_CHAR = "";


    /**
     * @Author Arun Kumar
     * This is the  method establishes the connection to database
     * @return stmt
     * @updated by: Rabbani
     * This method will read the environment passed through command line and connects to that environment
     * Also it checks the system property "connectTo" set by user and connects to the respective DB(like dbFlowable)
     * If no system property is set for connectTo variable them it connects to dbRebate by default
     * Also added capability to get the data permissions for the organisation:ORG_DG and Role:DATA_REBATE_ACCESS_DG by default
     * If user sets system properties setSessionToOtherOrg and setSessionToOtherRoles then this method gets permissions for that organization and role
     *
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
                    log.info("connecting to UATflowable DB");
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
            //checking the property values setSessionToOtherOrg and setSessionToOtherRoles
            if(System.getProperty("setSessionToOtherOrg")==null && System.getProperty("setSessionToOtherRoles")==null){
                log.info("getting data permissions for ORG_DG and DATA_REBATE_ACCESS_DG");
                cstmt = conn.prepareCall(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "sessionQueryOne"));
                cstmt.setString(1,"Organization");
                cstmt.setString(2,"ORG_DG");
                cstmt.execute();
                cstmt = conn.prepareCall(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "sessionQueryTwo"));
                cstmt.setString(1,"Roles");
                cstmt.setString(2,"[\"DATA_REBATE_ACCESS_DG\"]");
                cstmt.execute();
                log.info("successfully got data permissions for ORG_DG and DATA_REBATE_ACCESS_DG");
            }
            else if(System.getProperty("setSessionToOtherOrg")!=null && System.getProperty("setSessionToOtherRoles")!=null){
                log.info("getting data permissions for "+System.getProperty("setSessionToOtherOrg")+" and "+System.getProperty("setSessionToOtherRoles"));
                cstmt = conn.prepareCall(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "sessionQueryOne"));
                cstmt.setString(1,"Organization");
                cstmt.setString(2,System.getProperty("setSessionToOtherOrg"));
                cstmt.execute();
                cstmt = conn.prepareCall(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "sessionQueryTwo"));
                cstmt.setString(1,"Roles");
                cstmt.setString(2,System.getProperty("setSessionToOtherRoles"));
                cstmt.execute();
                //log.info("successfully got data permissions for "+System.getProperty("setSessionToOtherOrg")+" and "+System.getProperty("setSessionToOtherRoles"));
            }
            else if (System.getProperty("setSessionToOtherOrg")==null && System.getProperty("setSessionToOtherRoles")!=null){
                log.info("User needs to mention Organization for data permissions");
            }
            else if (System.getProperty("setSessionToOtherOrg")!=null && System.getProperty("setSessionToOtherRoles")==null){
                log.info("User needs to mention Role for data permissions");
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
        try {
            getStatement();
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
     * replaceMultipleQueryParamWithOneString Method used to replace one string multiple query params
     * @param queryKey
     * @param queryParam -
     * @return url
     */
    public String replaceMultipleQueryParamWithOneString(String queryKey, String queryParam)
    {
        try {
            String query = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, queryKey);
            query=query.replaceAll("strToReplace",queryParam);
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

    public void disConnectToOtherDB() {
        System.clearProperty("connectTo");
        cleanUp();
    }
//    public void setSession(){
//            String dbUrl=null;
//            String dbUserName=null;
//            String dbPassword=null;
//            try {
//                if(System.getProperty("environment")==null)
//                {
//                    if(System.getProperty("connectTo")==null){
//                        dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatDBURL");
//                        dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatUser");
//                        dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatPassword");
//                    }
//                    else if(System.getProperty("connectTo").equalsIgnoreCase("flowable")){
//                        log.info("connecting to flowable DB");
//                        dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatDBURLFlowable");
//                        dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatUserFlowable");
//                        dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatPasswordFlowable");
//                    }
//
//                }
//                else if(System.getProperty("environment").equalsIgnoreCase("UAT"))
//                {
//                    if(System.getProperty("connectTo")==null){
//                        dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatDBURL");
//                        dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatUser");
//                        dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatPassword");
//                    }
//                    else if(System.getProperty("connectTo").equalsIgnoreCase("flowable")) {
//                        log.info("connecting to UATflowable DB");
//                        dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatDBURLFlowable");
//                        dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatUserFlowable");
//                        dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "uatPasswordFlowable");
//                    }
//
//
//                }
//                else if(System.getProperty("environment").equalsIgnoreCase("Dev"))
//                {
//                    if(System.getProperty("connectTo")==null){
//                        dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devDBURL");
//                        dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devUser");
//                        dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devPassword");
//                    }
//                    else if(System.getProperty("connectTo").equalsIgnoreCase("flowable")){
//                        log.info("connecting to flowable DB");
//                        dbUrl = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devDBURLFlowable");
//                        dbUserName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devUserFlowable");
//                        dbPassword = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "devPasswordFlowable");
//                    }
//
//                }
//                else{
//                    log.info("Invalid environment");
//                }
//            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
//            if (conn != null) {
//                log.info("Connected to DB");
//            }
//            cstmt = conn.prepareCall(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "sessionQueryOne"));
//                cstmt.setString(1,"Organization");
//                cstmt.setString(2,"ORG_DG");
//            cstmt.execute();
//            cstmt = conn.prepareCall(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "sessionQueryTwo"));
//                cstmt.setString(1,"Roles");
//                cstmt.setString(2,"[\"DATA_REBATE_ACCESS_DG\"]");
//            cstmt.execute();
//            Statement stm1=conn.createStatement();
//            ResultSet rs1=stm1.executeQuery("select top 1 *  from [cfg].[MFR_Contract_Detail] where Is_Current_Flag=1");
//            rs1.next();
//            System.out.println("result rowkey"+rs1.getString(1));
//
////                String SPsql =getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "sessionQueryTwo") ;   // for stored proc taking 2 parameters
////                PreparedStatement ps = conn.prepareStatement(SPsql);
////                ps.setEscapeProcessing(true);
//////                ps.setQueryTimeout(<timeout value>);
////                ps.setString(1, "Organization");
////                ps.setString(2, "ORG_DG");
////                ps.executeQuery();
////
////                 SPsql =getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "sessionQueryTwo") ;   // for stored proc taking 2 parameters
////                 ps = conn.prepareStatement(SPsql);
////                ps.setEscapeProcessing(true);
//////                ps.setQueryTimeout(<timeout value>);
////                ps.setString(1, "Roles");
////                ps.setString(2, "[\"DATA_REBATE_ACCESS_DG\"]");
////                 ps.executeQuery();
//
//            //getStatement().execute(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "sessionQueryOne"));
//            //getStatement().execute(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "sessionQueryTwo"));
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }
//    }


    /**
     * @Author Rabbani
     * @param organisation - name of the organisation for data permissions
     * @param role - name of the role for data permissions
     * this method will set the system properties to setSessionToOtherOrg and setSessionToOtherRoles which are
     * used for data permissions in getStatement method
     */
    public void setSession(String organisation, String role) {
        System.setProperty("setSessionToOtherOrg",organisation);
        System.setProperty("setSessionToOtherRoles",role);

    }
    /**
     * @Author Rabbani
     * this method will clear the system properties setSessionToOtherOrg and setSessionToOtherRoles
     *
     */
    public void clearSetSession() {
        System.clearProperty("setSessionToOtherOrg");
        System.clearProperty("setSessionToOtherRoles");
        cleanUp();
    }

    /**
     * @Author Bharath
     * This method is used to replace the multiple pathparams in Query with of values passed in List
     * @param queryKey
     * @param listOfParams - List<object> of path params
     * @return url
     */
    public ResultSet replacePathParamsAndExecuteQuery(String queryKey,List<Object> listOfParams)
    {
        try {
            String query = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, queryKey);
//            String replacedQuery= query;
            for(int i=0;i<listOfParams.size();i++){
                query = query.replaceFirst("pathparam", String.valueOf(listOfParams.get(i)));
            }
            log.info("The Query Replaced is "+ query);
            ResultSet rs=getDataWithoutPropertiesKey(query);
            return rs;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /***************************************************************************************************
     * @Author      Gudi
     * @Purpose     This method will return the given query output in the for of List<Map<String,String>>
     * @param       queryKey
     * @param       listOfParams - List<object> of path params
     * @ReturnType  List<Map<String,String>>
     ******************************************************************************************************
     */
    public List<Map<String,String>> getQueryResultsInListMap(String queryKey, List<Object> listOfParams){
        List<Map<String, String>> resultsMap = null;
        int count = 0;
        int numberOfColumns = 0;
        ResultSet res = null;
        ResultSetMetaData resMeta = null;
        try{
            resultsMap = new ArrayList<>();
            //ResultSet resSet = replacePathParamsAndExecuteQuery(queryKey, listOfParams);
            getStatement();
            psmt = conn.prepareStatement(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, queryKey), ResultSet.TYPE_SCROLL_SENSITIVE
            ,ResultSet.CONCUR_UPDATABLE);
            log.info("query parameter is "+listOfParams);
            for (int i = 1; i<= listOfParams.size(); i++) {
                psmt.setString(i, listOfParams.get(i-1).toString());
            }

            res = psmt.executeQuery();
            //resMeta = res.getMetaData();
            //numberOfColumns = resMeta.getColumnCount();
            //res.beforeFirst();
            //res.absolute(1);

            while(res.next()){
                System.out.println(res.getString("Contract_ID"));
                System.out.println(res.getString("Contract_Name"));
            }
           // if(res.next()){
                /*do {
                    Map<String, String> row = new HashMap<>();
                    resultsMap.add(row);

                    for (int i = 1; i <= numberOfColumns; i++) {
                        res.getString(i);
                        if (res.wasNull()) {
                            row.put(resMeta.getColumnName(i), DB_NULL_CHAR);
                        }
                        else {
                            row.put(resMeta.getColumnName(i), res.getString(i));
                        }
                    }
                    count++;
                } while (res.next() && count < DB_RUN_QUERY_MAX_ROWS);*/
            //}


            log.info("Retrieved " + count + " record(s) from database.");
            log.debug ("Resultset has "+ numberOfColumns + " columns");
            return resultsMap;
        }catch(Exception e){
            log.info("There was a SQL exception during query result handling. " + e.getMessage());
            throw new RuntimeException("There was an unexpected exception while trying to execute the 'getQueryResultsInListMap()' method", e);
        }finally{
            try{
                res.close();
                res = null;
                resMeta = null;
            }catch(Exception e){
                log.info("There was a SQL exception during query result handling.  " + e.getMessage());
                throw new RuntimeException("There was an unexpected exception while trying to execute the 'getQueryResultsInListMap()' method", e);
            }
        }
    }

}


