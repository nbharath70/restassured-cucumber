package HelperClass;

import TestBase.TestBase;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBaseHelper extends TestBase {
    public static Logger log=getMyLogger(DataBaseHelper.class);
    public Connection conn;
    public Statement stmt;

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
        ResultSet data = null;
        try {
            data = getStatement().executeQuery(getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, query));
            log.info("DataBase="+data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
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



}
