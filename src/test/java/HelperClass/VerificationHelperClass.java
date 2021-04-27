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
    public void VerifyResponseJsonAndDbArrayByColumnName(Response response, String actualJsonPath, String Query, String ColumnName) {
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
     * @param APIresponse
     * @param dbResponseJsonAsString
     * @param jsonPath
     * verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues method is used verify the given jsonPath and DB having different values with different datatypes using List
     */
    public void verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues(Response APIresponse, String dbResponseJsonAsString, String jsonPath) {
        try {
           List actualValue = JsonPath.read(APIresponse.asString(), jsonPath);
           List expectedValue = JsonPath.read(dbResponseJsonAsString, jsonPath);
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
     * @param APIjsonPath
     * @param DBJSON
     * verifyAPIResponseJsonWithDBJsonWithStringDataTypeValues method is used verify the given jsonPath and DB having String Type values in result
     */

    public void verifyAPIResponseJsonWithDBJsonWithStringDataTypeValues(Response APIresponse, String dbResponseJsonAsString, String APIjsonPath,String DBJSON) {

            try {
                String actualValue = JsonPath.read(APIresponse.asString(), APIjsonPath);
                String expectedValue = JsonPath.read(dbResponseJsonAsString, DBJSON);
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
     * @param APIjsonPath
     * @param DBJSON
     * verifyAPIResponseJsonWithDBJsonWithIntDataTypeValues method is used verify the given jsonPath and DB having Int type values in result
     */
    public void verifyAPIResponseJsonWithDBJsonWithIntDataTypeValues(Response APIresponse, String dbResponseJsonAsString, String APIjsonPath,String DBJSON) {

        try {
            int actualValue = JsonPath.read(APIresponse.asString(), APIjsonPath);
         int expectedValue = JsonPath.read(dbResponseJsonAsString, DBJSON);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue==(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Author Bharath
     * @param APIresponse
     * @param dbResponseJsonAsString
     * @param APIjsonPath
     * @param DBJSON
     * verifyAPIResponseJsonWithDBJsonWithObjectDataTypeValues method is used verify the given jsonPath and DB having Object type values in result
     */
    public void verifyAPIResponseJsonWithDBJsonWithObjectDataTypeValues(Response APIresponse, String dbResponseJsonAsString, String APIjsonPath,String DBJSON) {

        try {
            List<Object>actualValue = JsonPath.read(APIresponse.asString(), APIjsonPath);
            List<Object>expectedValue = JsonPath.read(dbResponseJsonAsString, DBJSON);
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
     * @param APIjsonPath
     * @param DBJSON
     * verifyAPIResponseJsonWithDBJsonNullValues method is used verify the given jsonPath and DB having null values in result
     */
    public void verifyAPIResponseJsonWithDBJsonNullValues(Response APIresponse, String dbResponseJsonAsString, String APIjsonPath,String DBJSON) {

        try {
          String actualValue = JsonPath.read(APIresponse.asString(), APIjsonPath);
           String expectedValue = JsonPath.read(dbResponseJsonAsString, DBJSON);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", StringUtils.equals(actualValue,expectedValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Author Bharath
     * @param APIresponse
     * @param dbResponseJsonAsString
     * @param APIjsonPath
     * @param DBJSON
     * verifyAPIResponseJsonWithDBJsonWithBooleanDataTypeValues method is used verify the given jsonPath and DB having Boolean values in result
     */
    public void verifyAPIResponseJsonWithDBJsonWithBooleanDataTypeValues(Response APIresponse, String dbResponseJsonAsString, String APIjsonPath,String DBJSON) {

        try {
            boolean actualValue = JsonPath.read(APIresponse.asString(), APIjsonPath);
            boolean expectedValue = JsonPath.read(dbResponseJsonAsString, DBJSON);
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
}
