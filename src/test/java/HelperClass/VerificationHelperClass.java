package HelperClass;

import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import io.restassured.response.Response;
import org.junit.Assert;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VerificationHelperClass extends TestBase {
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public static Logger log = getMyLogger(VerificationHelperClass.class);

    /**
     * @param jsonResponse
     * @param statusCode
     * @uthour Arun Kumar
     * verifyStatusCode Method used to verify given the status code
     */
    public void verifyStatusCode(Response jsonResponse, int statusCode) {
        try {
            jsonResponse.then().assertThat().statusCode(statusCode);
            log.info("API returns http response status code=" + statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param response
     * @param actualJsonPath
     * @param Query
     * @param ColumnName
     * @uthour Arun Kumar
     * verifyRecordCount method is used verify the total record count from given jsonPath and DB query column value
     */
    public void verifyRecordCount(Response response, String actualJsonPath, String Query, String ColumnName) {
        try {
            List<String> actual = JsonPath.read(response.asString(), actualJsonPath);
            int actualValue = actual.size();
            int expectedValue = dataBaseHelper.getDataColumnCountDB(Query, ColumnName);
            Assert.assertEquals("Verification failed, expectedValue is not same as  actual value", expectedValue, actualValue);
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param response
     * @param actualJsonPath
     * @param Query
     * @param ColumnName
     * @uthor Arun Kumar
     * VerifyResponseJsonAndDbArrayByColumnName method is used verify the given jsonPath and DB query column value ArrayList value
     */
    public void verifyResponseJsonAndDbArrayByColumnName(Response response, String actualJsonPath, String Query, String ColumnName) {
        try {
            ArrayList<String> actualValue = JsonPath.read(response.asString(), actualJsonPath);
            ArrayList<String> expectedValue = dataBaseHelper.getDataColumnArrayListValueDB(Query, ColumnName);
            Collections.sort(actualValue);
            Collections.sort(expectedValue);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from Json response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** * @Author Rabbani * @param APIresp... by Rabbani Shaik
     /**
     * @Author Rabbani
     * @param APIresponse
     * @param dbResponseJsonAsString
     * @param jsonPath
     * VerifyResponseJsonAndDbArrayByColumnName method is used verify the given jsonPath and DB query column value ArrayList value
     */
    public void verifyAPIResponseJsonWithDBJson(Response APIresponse, String dbResponseJsonAsString, String jsonPath) {
        try {
            ArrayList<String> actualValue = JsonPath.read(APIresponse.asString(), jsonPath);
            ArrayList<String> expectedValue = JsonPath.read(dbResponseJsonAsString, jsonPath);
            Collections.sort(actualValue);
            Collections.sort(expectedValue);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     /**
     * @Author Bharath
     * @param aPIresponse
     * @param dbResponseJsonAsString
     * @param jsonPath
     * verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues method is used verify the given jsonPath and DB having different values with different datatypes using List
     */
    public void verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues(Response aPIresponse, String dbResponseJsonAsString, String jsonPath) {
        try {
           List actualValue = JsonPath.read(aPIresponse.asString(), jsonPath);
           List expectedValue = JsonPath.read(dbResponseJsonAsString, jsonPath);
           Collections.sort(actualValue);
           Collections.sort(expectedValue);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author Bharath
     * @param aPIresponse
     * @param dbResponseJsonAsString
     * @param dBJSON
     * verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues method is used verify the given jsonPath and DB having different values with different datatypes using List
     */
    public void verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues(Response aPIresponse, String dbResponseJsonAsString, String aPIjsonPath,String dBJSON) {
        try {
            List actualValue = JsonPath.read(aPIresponse.asString(), aPIjsonPath);
            List expectedValue = JsonPath.read(dbResponseJsonAsString, dBJSON);
            Collections.sort(actualValue);
            Collections.sort(expectedValue);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Author Bharath
     * @param APIresponse
     * @param dbResponseJsonAsString
     * @param aPIjsonPath
     * @param dBJSON
     * verifyAPIResponseJsonWithDBJsonWithStringDataTypeValues method is used verify the given jsonPath and DB having String Type values in result
     */

    public void verifyAPIResponseJsonWithDBJsonWithStringDataTypeValues(Response APIresponse, String dbResponseJsonAsString, String aPIjsonPath,String dBJSON) {

            try {
                String actualValue = JsonPath.read(APIresponse.asString(), aPIjsonPath);
                String expectedValue = JsonPath.read(dbResponseJsonAsString, dBJSON);
                log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
                Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
                log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    /**
     * @Author Bharath
     * @param aPIresponse
     * @param dbResponseJsonAsString
     * @param aPIjsonPath
     * @param dBJSON
     * verifyAPIResponseJsonWithDBJsonWithIntDataTypeValues method is used verify the given jsonPath and DB having Int type values in result
     */
    public void verifyAPIResponseJsonWithDBJsonWithIntDataTypeValues(Response aPIresponse, String dbResponseJsonAsString, String aPIjsonPath,String dBJSON) {

        try {
            int actualValue = JsonPath.read(aPIresponse.asString(), aPIjsonPath);
         int expectedValue = JsonPath.read(dbResponseJsonAsString, dBJSON);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue==(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author Bharath
     * @param aPIresponse
     * @param dbResponseJsonAsString
     * @param aPIjsonPath
     * @param dBJSON
     * verifyAPIResponseJsonWithDBJsonWithObjectDataTypeValues method is used verify the given jsonPath and DB having Object type values in result
     */
    public void verifyAPIResponseJsonWithDBJsonWithObjectDataTypeValues(Response aPIresponse, String dbResponseJsonAsString, String aPIjsonPath,String dBJSON) {

        try {
            List<Object>actualValue = JsonPath.read(aPIresponse.asString(), aPIjsonPath);
            List<Object>expectedValue = JsonPath.read(dbResponseJsonAsString, dBJSON);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Author Bharath
     * @param aPIresponse
     * @param dbResponseJsonAsString
     * @param aPIjsonPath
     * @param dBJSON
     * verifyAPIResponseJsonWithDBJsonNullValues method is used verify the given jsonPath and DB having null values in result
     */
    public void verifyAPIResponseJsonWithDBJsonNullValues(Response aPIresponse, String dbResponseJsonAsString, String aPIjsonPath,String dBJSON) {

        try {
          String actualValue = JsonPath.read(aPIresponse.asString(), aPIjsonPath);
           String expectedValue = JsonPath.read(dbResponseJsonAsString, dBJSON);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", StringUtils.equals(actualValue,expectedValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Author Bharath
     * @param aPIresponse
     * @param dbResponseJsonAsString
     * @param aPIjsonPath
     * @param dBJSON
     * verifyAPIResponseJsonWithDBJsonWithBooleanDataTypeValues method is used verify the given jsonPath and DB having Boolean values in result
     */
    public void verifyAPIResponseJsonWithDBJsonWithBooleanDataTypeValues(Response aPIresponse, String dbResponseJsonAsString, String aPIjsonPath,String dBJSON) {

        try {
            boolean actualValue = JsonPath.read(aPIresponse.asString(), aPIjsonPath);
            boolean expectedValue = JsonPath.read(dbResponseJsonAsString, dBJSON);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", areEqual(actualValue,expectedValue) );
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * verifyResponseJsonBoolean method is used to validate the jsonResponse Body which is type of boolean
     * @uthor Arun Kumar
     * @param response
     * @param jsonResponseBodyKey
     * @param expectedValue
     */
    public void verifyResponseJsonBoolean(Response response,String jsonResponseBodyKey,String expectedValue)
    {
        try {
            String val = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, jsonResponseBodyKey);
            Object actualValue = JsonPath.read(response.asString(), val);
            Boolean expValue = Boolean.valueOf(expectedValue);
            Assert.assertTrue("The lists do not match!", expValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        }catch  (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * verifyResponseJsonBoolean method is used to validate the jsonResponse Body which is type of String
     * @uthor Arun Kumar
     * @param response
     * @param jsonResponseBodyKey
     * @param expectedValue
     */
    public void verifyResponseJsonString(Response response,String jsonResponseBodyKey,String expectedValue)
    {
        try {
            String val = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, jsonResponseBodyKey);
            Object actualValue = JsonPath.read(response.asString(), val);
            Object expValue = expectedValue;
            Assert.assertTrue("The lists do not match!", expValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        }catch  (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Author Bharath
     * @param response
     * @param actualJsonPath
     * @param query
     * @param queryparam
     * verifyResponseJsonAndDbArrayByColumnNameWithPreparedQuery method is used verify the JsonArray and DbArray columnname using prepared query
     */
    public void verifyResponseJsonAndDbArrayByColumnNameWithPreparedQuery(Response response, String actualJsonPath, String query, String columnName,String queryparam) {
        try {
            ArrayList actualValue = JsonPath.read(response.asString(), actualJsonPath);
            ArrayList expectedValue = dataBaseHelper.getDataColumnArrayUsingPreparedStatement(query, queryparam,columnName);
            Collections.sort(expectedValue);
            Collections.sort(actualValue);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from Json response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}