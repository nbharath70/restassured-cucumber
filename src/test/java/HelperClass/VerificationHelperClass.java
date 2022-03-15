package HelperClass;

import RequestPojo.DisContractPojo.DiscardContractPojo;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.runtime.junit.Assertions;
import io.restassured.http.Headers;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.testng.asserts.Assertion;


import java.util.*;

public class VerificationHelperClass extends TestBase {
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public static Logger log = getMyLogger(VerificationHelperClass.class);


    /**
     * @param jsonResponse-
     * @param statusCode-
     * @uthour Arun Kumar
     * @ModifiedBy  Gudi
     * verifyStatusCode Method used to verify given the status code
     */
    public boolean verifyStatusCode(Response jsonResponse, int statusCode) {
        boolean blnStatus = false;
        try {
            jsonResponse.then().assertThat().statusCode(statusCode);
            log.info("API returns http response status code=" + statusCode);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @param response-
     * @param actualJsonPath-
     * @param Query-
     * @param ColumnName-
     * @uthour Arun Kumar
     * @ModifiedBy  Gudi
     * verifyRecordCount method is used verify the total record count from given jsonPath and DB query column value
     */
    public boolean verifyRecordCount(Response response, String actualJsonPath, String Query, String ColumnName) {
        boolean blnStatus = false;
        try {
            List<String> actual = JsonPath.read(response.asString(), actualJsonPath);
            int actualValue = actual.size();
            int expectedValue = dataBaseHelper.getDataColumnCountDB(Query, ColumnName);
            Assert.assertEquals("Verification failed, expectedValue is not same as  actual value", expectedValue, actualValue);
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @param response-
     * @param actualJsonPath-
     * @param Query-
     * @param ColumnName-
     * @uthor Arun Kumar
     * @ModifiedBy  Gudi
     * VerifyResponseJsonAndDbArrayByColumnName method is used verify the given jsonPath and DB query column value ArrayList value
     */
    public boolean verifyResponseJsonAndDbArrayByColumnName(Response response, String actualJsonPath, String Query, String ColumnName) {
        boolean blnStatus = false;
        try {
            ArrayList<String> actualValue = JsonPath.read(response.asString(), actualJsonPath);
            ArrayList<String> expectedValue = dataBaseHelper.getDataColumnArrayListValueDB(Query, ColumnName);
            Collections.sort(actualValue);
            Collections.sort(expectedValue);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from Json response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        }catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        }catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @param response-
     * @param actualJsonPath-
     * @param Query-
     * @param ColumnName-
     * @uthor Arun Kumar
     * @ModifiedBy  Gudi
     * verifyResponseJsonAndDbArrayByColumnNameWithoutPropertiesKey method is used verify the given jsonPath and DB query column value ArrayList value as String
     */
    public boolean verifyResponseJsonAndDbArrayByColumnNameWithoutPropertiesKey(Response response, String actualJsonPath, String Query, String ColumnName) {
        boolean blnStatus = false;
        try {
            ArrayList<String> actualValue = JsonPath.read(response.asString(), actualJsonPath);
            ArrayList<String> expectedValue = dataBaseHelper.getDataColumnArrayListValueDBWithoutPropertiesKey(Query, ColumnName);
            Collections.sort(actualValue);
            Collections.sort(expectedValue);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from Json response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /** * @Author Rabbani * @param APIresp... by Rabbani Shaik
     /**
     * @Author Rabbani
     * @param APIresponse-
     * @param dbResponseJsonAsString-
     * @param jsonPath
     * @ModifiedBy  Gudi
     * VerifyResponseJsonAndDbArrayByColumnName method is used verify the given jsonPath and DB query column value ArrayList value
     */
    public boolean verifyAPIResponseJsonWithDBJson(Response APIresponse, String dbResponseJsonAsString, String jsonPath) {
        boolean blnStatus = false;
        try {
            ArrayList<String> actualValue = JsonPath.read(APIresponse.asString(), jsonPath);
            ArrayList<String> expectedValue = JsonPath.read(dbResponseJsonAsString, jsonPath);
            Collections.sort(actualValue);
            Collections.sort(expectedValue);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



     /**
     * @Author Bharath
     * @param aPIresponse-
     * @param dbResponseJsonAsString-
     * @param jsonPath
     * @ModifiedBy  Gudi
     * verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues method is used verify the given jsonPath and DB having different values with different datatypes using List
     */
    public boolean verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues(Response aPIresponse, String dbResponseJsonAsString, String jsonPath) {
        boolean blnStatus = false;
        try {
           List actualValue = JsonPath.read(aPIresponse.asString(), jsonPath);
           List expectedValue = JsonPath.read(dbResponseJsonAsString, jsonPath);
           Collections.sort(actualValue);
           Collections.sort(expectedValue);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @Author Bharath
     * @param aPIresponse-
     * @param dbResponseJsonAsString-
     * @param jsonPath
     * @ModifiedBy  Gudi
     * verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues method is used verify the given jsonPath and DB having different values with different datatypes using List
     */
    public boolean verifyAPIResponseJsonWithDBJsonWithIntandStringCombinationDataTypeValues(Response aPIresponse, String dbResponseJsonAsString, String jsonPath) {
        boolean blnStatus = false;
        try {
            List<Object> actualValue = JsonPath.read(aPIresponse.asString(), jsonPath);
            List<Object > expectedValue = JsonPath.read(dbResponseJsonAsString, jsonPath);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @Author Bharath
     * @param aPIresponse-
     * @param dbResponseJsonAsString-
     * @param dBJSON
     * @ModifiedBy  Gudi
     * verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues method is used verify the given jsonPath and DB having different values with different datatypes using List
     */
    public boolean verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues(Response aPIresponse, String dbResponseJsonAsString, String aPIjsonPath,String dBJSON) {
        boolean blnStatus = false;
        try {
            List actualValue = JsonPath.read(aPIresponse.asString(), aPIjsonPath);
            List expectedValue = JsonPath.read(dbResponseJsonAsString, dBJSON);
            Collections.sort(actualValue);
            Collections.sort(expectedValue);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @Author Bharath
     * @param APIresponse-
     * @param dbResponseJsonAsString-
     * @param aPIjsonPath-
     * @param dBJSON
     * @ModifiedBy  Gudi
     * verifyAPIResponseJsonWithDBJsonWithStringDataTypeValues method is used verify the given jsonPath and DB having String Type values in result
     */

    public boolean verifyAPIResponseJsonWithDBJsonWithStringDataTypeValues(Response APIresponse, String dbResponseJsonAsString, String aPIjsonPath,String dBJSON) {
        boolean blnStatus = false;
        try {
            List<String> actualValue = JsonPath.read(APIresponse.asString(), aPIjsonPath);
            List<String> expectedValue = JsonPath.read(dbResponseJsonAsString, dBJSON);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @Author Bharath
     * @param APIresponse-
     * @param dbResponseJsonAsString-
     * @param aPIjsonPath-
     * @param dBJSON
     * @ModifiedBy  Gudi
     * verifyAPIResponseJsonWithDBJsonWithStringDataTypeValues method is used verify the given jsonPath and DB having String Type values in result
     */
    public boolean verifyAPIResponseJsonWithDBJsonWithonlyStringDataTypeValues(Response APIresponse, String dbResponseJsonAsString, String aPIjsonPath,String dBJSON) {
        boolean blnStatus = false;
        try {
            String actualValue = JsonPath.read(APIresponse.asString(), aPIjsonPath);
            String expectedValue = JsonPath.read(dbResponseJsonAsString, dBJSON);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @Author Bharath
     * @param aPIresponse-
     * @param dbResponseJsonAsString-
     * @param aPIjsonPath-
     * @param dBJSON
     * @ModifiedBy  Gudi
     * verifyAPIResponseJsonWithDBJsonWithIntDataTypeValues method is used verify the given jsonPath and DB having Int type values in result
     */
    public boolean verifyAPIResponseJsonWithDBJsonWithIntDataTypeValues(Response aPIresponse, String dbResponseJsonAsString, String aPIjsonPath,String dBJSON) {
        boolean blnStatus = false;
        try {
            int actualValue = JsonPath.read(aPIresponse.asString(), aPIjsonPath);
            int expectedValue = JsonPath.read(dbResponseJsonAsString, dBJSON);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue==(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }


    /**
     * @Author Bharath
     * @param aPIresponse-
     * @param dbResponseJsonAsString-
     * @param aPIjsonPath-
     * @param dBJSON
     * @ModifiedBy  Gudi
     * verifyAPIResponseJsonWithDBJsonWithObjectDataTypeValues method is used verify the given jsonPath and DB having Object type values in result
     */
    public boolean verifyAPIResponseJsonWithDBJsonWithObjectDataTypeValues(Response aPIresponse, String dbResponseJsonAsString, String aPIjsonPath,String dBJSON) {
        boolean blnStatus = false;
        try {
            List<Object>actualValue = JsonPath.read(aPIresponse.asString(), aPIjsonPath);
            List<Object>expectedValue = JsonPath.read(dbResponseJsonAsString, dBJSON);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @Author Bharath
     * @param aPIresponse-
     * @param dbResponseJsonAsString-
     * @param aPIjsonPath-
     * @param dBJSON
     * @ModifiedBy  Gudi
     * verifyAPIResponseJsonWithDBJsonNullValues method is used verify the given jsonPath and DB having null values in result
     */
    public boolean verifyAPIResponseJsonWithDBJsonNullValues(Response aPIresponse, String dbResponseJsonAsString, String aPIjsonPath,String dBJSON) {
        boolean blnStatus = false;
        try {
            String actualValue = JsonPath.read(aPIresponse.asString(), aPIjsonPath);
            String expectedValue = JsonPath.read(dbResponseJsonAsString, dBJSON);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", StringUtils.equals(actualValue,expectedValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }




    public boolean verifyAPIResponseJsonWithDBJsonAsWholeJson(Response aPIresponse, String dbResponseJsonAsString, String aPIjsonPath,String dBJSON) {
        boolean blnStatus = false;
        try {
            String actualValue = (JsonPath.read(aPIresponse.asString(), aPIjsonPath)).toString();
            String expectedValue = (JsonPath.read(dbResponseJsonAsString, dBJSON)).toString();
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response" + actualValue);
            Assert.assertEquals("The lists do not match!",expectedValue,actualValue );
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @Author Bharath
     * @param aPIresponse-
     * @param dbResponseJsonAsString-
     * @param aPIjsonPath-
     * @param dBJSON
     * @ModifiedBy  Gudi
     * verifyAPIResponseJsonWithDBJsonWithBooleanDataTypeValues method is used verify the given jsonPath and DB having Boolean values in result
     */
    public boolean verifyAPIResponseJsonWithDBJsonWithBooleanDataTypeValues(Response aPIresponse, String dbResponseJsonAsString, String aPIjsonPath,String dBJSON) {
        boolean blnStatus = false;
        try {
            boolean actualValue = JsonPath.read(aPIresponse.asString(), aPIjsonPath);
            boolean expectedValue = JsonPath.read(dbResponseJsonAsString, dBJSON);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", areEqual(actualValue,expectedValue) );
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * verifyResponseJsonBoolean method is used to validate the jsonResponse Body which is type of boolean
     * @uthor Arun Kumar
     * @param response-
     * @param jsonResponseBodyKey-
     * @param expectedValue-
     * @ModifiedBy  Gudi
     */
    public boolean verifyResponseJsonBoolean(Response response,String jsonResponseBodyKey,String expectedValue)
    {
        boolean blnStatus = false;
        try {
            String val = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, jsonResponseBodyKey);
            Object actualValue = JsonPath.read(response.asString(), val);
            Boolean expValue = Boolean.valueOf(expectedValue);
            log.info("Verify response body where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            Assert.assertTrue("The lists do not match!", expValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        }catch (Exception e){
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * verifyResponseJsonBoolean method is used to validate the jsonResponse Body which is type of String
     * @uthor Arun Kumar
     * @param response-
     * @param jsonResponseBodyKey-
     * @param expectedValue-
     * @ModifiedBy  Gudi
     */
    public boolean verifyResponseJsonString(Response response,String jsonResponseBodyKey,String expectedValue)
    {
        boolean blnStatus = false;
        try {
            String val = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, jsonResponseBodyKey);
            Object actualValue = JsonPath.read(response.asString(), val);
            Object expValue = expectedValue;
            log.info("Verify response body where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            Assert.assertTrue("The lists do not match!", expValue.equals(actualValue));
            blnStatus = true;
        }catch  (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @Author Bharath
     * @param response-
     * @param actualJsonPath-
     * @param query-
     * @param queryparam
     * @ModifiedBy  Gudi
     * verifyResponseJsonAndDbArrayByColumnNameWithPreparedQuery method is used verify the JsonArray and DbArray columnname using prepared query
     */
    public boolean verifyResponseJsonAndDbArrayByColumnNameWithPreparedQuery(Response response, String actualJsonPath, String query, String columnName,String queryparam) {
        boolean blnStatus = false;
        try {
            ArrayList actualValue = JsonPath.read(response.asString(), actualJsonPath);
            ArrayList expectedValue = dataBaseHelper.getDataColumnArrayUsingPreparedStatement(query, queryparam,columnName);
            Collections.sort(expectedValue);
            Collections.sort(actualValue);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from Json response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @param jsonResponse-
     * @param APIjsonPath-
     * @authour Smruti
     * @ModifiedBy  Gudi
     * verifyAPIResponseBooleanValue Method used to verify given boolean value in response
     */
    public boolean verifyAPIResponseBooleanValueTrue(Response jsonResponse, String APIjsonPath) {
        boolean blnStatus = false;
        try {
            Boolean actualValue = JsonPath.read(jsonResponse.asString(), APIjsonPath);
            Assert.assertTrue(actualValue);
            log.info("Verified the boolean response true in API response");
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @authour Smruti
     * verifyAPIResponseError message in response
     * @ModifiedBy  Gudi
     */
    public boolean verifyAPIResponseErrorMessage(Response jsonResponse,String expectedErrorMessage, String APIjsonPath) {
        boolean blnStatus = false;
        try {
            String actualValue = JsonPath.read(jsonResponse.asString(), APIjsonPath);
            Assert.assertTrue("The lists do not match!", expectedErrorMessage.equals(actualValue));
            log.info("Error message returned from API is:"+actualValue);
            log.info("Expected  message returned  is:"+expectedErrorMessage);
            log.info("Verified the error message  in API response");
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }


    /**
     * This Reusable methods can be used to Verify Response Values having Null in it with DB Nulls
     * @Author Bharath
     * @param response-
     * @param actualJsonPath-
     * @param query-
     * @param queryparam
     * @ModifiedBy  Gudi
     *
     */
    public boolean verifyResponseJsonAndDbArrayByColumnNameHavingNullValuesWithPreparedQuery(Response response, String actualJsonPath, String query, String columnName,String queryparam) {
        boolean blnStatus = false;
        try {
            List<Object> actualValue = JsonPath.read(response.asString(), actualJsonPath);
            List<Object> expectedValue = dataBaseHelper.getDataColumnArrayUsingPreparedStatement(query, queryparam,columnName);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from Json response=" + actualValue);
            Assert.assertTrue("The lists do not match!",actualValue.equals(expectedValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @param response-
     * @param actualJsonPath--
     * @param query-
     * @param ColumnName-
     * @uthor Arun Kumar
     * @ModifiedBy  Gudi
     * verifyResponseJsonAndDbArrayByColumnNameInteger method is used verify the given jsonPath and DB query column value ArrayList value
     */
    public boolean verifyResponseJsonAndDbArrayByColumnNameForInteger(Response response, String actualJsonPath, String query, String ColumnName) {
        boolean blnStatus = false;
        try {
            ArrayList<Integer> actualValue = JsonPath.read(response.asString(), actualJsonPath);
            ArrayList<Integer> expectedValue = dataBaseHelper.getDataColumnArrayListValueDBInterger(query, ColumnName);
            Collections.sort(actualValue);
            Collections.sort(expectedValue);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from Json response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }



    /**
     * @param response-
     * @param actualJsonPath-
     * @param Query-
     * @param ColumnName-
     * @uthor Arun Kumar
     * @ModifiedBy  Gudi
     * verifyResponseJsonAndDbArrayByColumnNameInteger method is used verify the given jsonPath and DB query column value ArrayList value
     */
    public boolean verifyResponseJsonAndDbArrayByColumnNameForIntegerWithoutPropertiesKey(Response response, String actualJsonPath, String Query, String ColumnName) {
        boolean blnStatus = false;
        try {
            ArrayList<Integer> actualValue = JsonPath.read(response.asString(), actualJsonPath);
            ArrayList<Integer> expectedValue = dataBaseHelper.getDataColumnArrayListValueDBIntergerWithoutPropertiesKey(Query, ColumnName);
            Collections.sort(actualValue);
            Collections.sort(expectedValue);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from Json response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }


    /**
     * This method is used to validate the ResponseHeader Error code Value
     * @uthor ArunKumar
     * @param response-
     * @param expectedErrorCode
     * @ModifiedBy  Gudi-
     */
    public boolean verifyResponseHeaderApiReturnCodesValue(Response response,String expectedErrorCode)
    {
        boolean blnStatus = false;
        try {
            String s=response.getHeader("rb-api-result");
            int actualCode = JsonPath.read(s, "$.apiReturnCodes[0].code");
            String expectedErrorCodeString=expectedErrorCode;
            int expectedError=Integer.valueOf(expectedErrorCodeString);
            Assert.assertEquals("Error code value do not match!", expectedError,actualCode);
            log.info("Verification error code value pass where expectedValue=" + expectedError + " equals to actualValue=" + actualCode);
            blnStatus = true;
        }catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }


    /**
     * This method is used to validate the verifyResponseHeaderCountValue
     * @uthor ArunKumar
     * @param response-
     * @param expectedCountValue
     * @ModifiedBy  Gudi-
     */
    public boolean verifyResponseHeaderCountValue(Response response,int expectedCountValue)
    {
        boolean blnStatus = false;
        try {
            Headers headers = response.getHeaders();
            int actualHeaderCount = headers.asList().size();
            Assert.assertEquals("verifying Response Header count value",actualHeaderCount,expectedCountValue);
            log.info("Validating Response Header count value is pass actualValue="+expectedCountValue+" expectedValue="+expectedCountValue);
            blnStatus = true;
        }catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }


    /**
     * @param response-
     * @param actualJsonPath-
     * @param Query-
     * @param ColumnName-
     * @uthour Smruti
     * @ModifiedBy  Gudi
     * verifyRecordCount method is used verify the total record count from given jsonPath and DB query column value
     */
    public boolean matchRecordCount(Response response, String actualJsonPath, String Query, String ColumnName) {
        boolean blnStatus = false;
        try {
            int actualValue = JsonPath.read(response.asString(), actualJsonPath);
            int expectedValue = dataBaseHelper.getDataColumnCountDB(Query, ColumnName);
            Assert.assertEquals("Verification failed, expectedValue is not same as  actual value", expectedValue, actualValue);
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }





    /**
     * @Author Arun Kumar
     * @param expValue
     * @param apiJsonPath
     * @ModifiedBy  Gudi
     * verifyAPIResponseJsonWithDBJsonWithObjectDataTypeValues method is used verify the given jsonPath and DB having Object type values in result
     */
    public boolean verifyAPIResponseJsonByArrayListValue(Response aPIresponse, String apiJsonPath,ArrayList expValue) {
        boolean blnStatus = false;
        try {
            ArrayList<Object>actualValue = JsonPath.read(aPIresponse.asString(), apiJsonPath);
            ArrayList<Object>expectedValue = expValue;
            System.out.println("actualValue"+actualValue);
            System.out.println("expectedValue"+expectedValue);
            log.info("expectedValue from DB=" + expectedValue + " And actualValue from APIJson response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
            blnStatus = true;
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }


     /**
     * @Author Rabbani
     * @param APIresponse - API response object
     * @param dbResponseJsonAsString - data base response as Json in String form
     * @param jsonPathList - List<String> if all JsonPaths
     * @ModifiedBy  Gudi
     *This method will compare and verify the API response Json and db response Json values for a List of Json paths
     */
    public boolean verifyAPIResponseJsonWithDBJsonForListofJsonPaths(Response APIresponse, String dbResponseJsonAsString, List<String> jsonPathList) {
        boolean blnStatus = false;
        try {
            List<Boolean> expectedValueBoolean=null;
            for(String jsonPath:jsonPathList) {
                ArrayList<String> actualValue = JsonPath.read(APIresponse.asString(), jsonPath);
                ArrayList<String> expectedValue = JsonPath.read(dbResponseJsonAsString, jsonPath);
                //Assert.assertEquals("APIResponse and DBResponse Json sizes don't match",actualValue.size(),expectedValue.size());
                if (expectedValue.contains("true") || expectedValue.contains("false")) {
                    expectedValueBoolean = new ArrayList<Boolean>();
                    for (String a : expectedValue) {
                        boolean b = Boolean.valueOf(a);
                        expectedValueBoolean.add(b);
                    }
                    log.info("expectedValue from DB" + expectedValueBoolean + " And actualValue from APIJson response=" + actualValue);
                    Assert.assertEquals("The lists do not match!", expectedValueBoolean, actualValue);
                    log.info("Verification pass where expectedValue=" + expectedValueBoolean + " equals to actualValue=" + actualValue);
                continue;
                }
                log.info("expectedValue from DB" + expectedValue + " And actualValue from APIJson response=" + actualValue);
                Assert.assertEquals("The lists do not match!", expectedValue, actualValue);
                log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
                blnStatus = true;
            }
        } catch (AssertionError e) {
            e.printStackTrace();
            blnStatus = false;
        } catch (Exception e) {
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }


    /**
     *This Method is Used for Comparing the 2 String Passed into it and Asserting the Reponse
     * @uthour Bharath
     * @ModifiedBy  Gudi
     *
     */
    public boolean compareTwoStrings(String textone,String textTwo){
        boolean blnStatus = false;
        try{
            Assert.assertEquals("Verification failed, expectedValue is not same as  actual value",textone,textTwo);
            log.info("Verification pass where expectedValue=" + textone + " equals to actualValue=" + textTwo);
            blnStatus = true;
        }catch(AssertionError e){
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }


    /**
     * @Author Rabbani
     * @param dataTable - DataTable object from cucumber steps
     * @return jsonPathList - List if all JsonPaths from VERIFICATION_PROPERTIES file
     *This method will read the DataTable object provided and for the keys it will search the correcsponding values
      *in VERIFICATION_PROPERTIES file and collects them into a list<String> and returns the list
     */
    public List<String> getListofJsonpathsFromPropertiesFile(DataTable dataTable) {
        List<String> jsonPathList=new ArrayList<String>();
        try {
            List<Map<String, String>> paramsToValidate = dataTable.asMaps(String.class, String.class);
            for(Map<String, String> map : paramsToValidate){
                for(Map.Entry me:map.entrySet()){
                    String jsonPathKey= me.getValue().toString();
                    String jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,jsonPathKey);
                    jsonPathList.add(jsonPath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonPathList;
    }


    public boolean verifyexpectedAndActualDirectlyAsStrings(Response response, String expectedResponse) {
        boolean blnStatus = false;
        try{
            String actualResponse=response.asString();
            log.info("expectedValue:" + expectedResponse + " And actualValue from APIJson response=" + actualResponse);
            Assert.assertEquals("The lists do not match!", expectedResponse, actualResponse);
            log.info("Verification pass where expectedValue=" + expectedResponse + " equals to actualValue=" + actualResponse);
            blnStatus = true;
        }catch (AssertionError e){
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }


    public boolean verifyEmptyResponse(Response response){
        boolean blnStatus = false;
        try{
            log.info("Validating the empty response");
            Assert.assertEquals( response.asString(),"[]");
            log.info("Successfully validated empty response");
            blnStatus = true;
        }catch(AssertionError e){
            e.printStackTrace();
            blnStatus = false;
        }
        return blnStatus;
    }


}