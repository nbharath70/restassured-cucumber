package HelperClass;

import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import org.apache.log4j.Logger;
import io.restassured.response.Response;
import org.junit.Assert;
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
}
