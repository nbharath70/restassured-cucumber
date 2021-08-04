package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FetchDrugGroupSummaryBaseStep extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(FetchJobListBaseStep.class);
    int rowkeyValue;
    ResultSet resultSet;
    String dbJson;

    public void getRowKeyOfDrugGroup(String query){
        ResultSet res =dataBaseHelper.getData(query);
        String rowkey=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"rowKey");
        try {
            res.next();
             rowkeyValue=res.getInt(rowkey);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hitEndpoint(String endpoint){
        response=getCall(endpoint,String.valueOf(rowkeyValue));
    }
    public void verifyFetchDrugGroupSummaryStatusCode (int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response,statusCode);
        log.info("fetchDrugGroupSummary API's StatusCode is: "+statusCode);
    }
    public void verifyFetchDrugGroupSummaryAPIResponseFormatJSON()
    {
        verifyResponseFormatIsJSON();
    }

//    public void verifyFetchDruGroupSummary(String query,String columnName,String jsonPath){
//        //String actualQuery=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES,query);
//        String actualColumnname=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
//        String actualJsonPathofAPI=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,jsonPath);
//        ResultSet res=dataBaseHelper.executePreparedQuery(query,rowkeyValue);
//
//        try {
//            res.next();
//            String drugGroupSummaryResults =res.getString(actualColumnname);
//           String actualValue = JsonPath.read(response.asString(), actualJsonPathofAPI);
//            log.info("expectedValue from DB" + drugGroupSummaryResults + " And actualValue from Json response=" + actualValue);
////            Assert.assertTrue("The lists do not match!", drugGroupSummaryResults.equals(actualValue));
////            log.info("Verification pass where expectedValue=" + drugGroupSummaryResults + " equals to actualValue=" + actualValue);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//
//    }
    public void verifyFetchDruGroupSummary(String query,String columnName,String jsonPath) {
        try{
            resultSet=dataBaseHelper.executePreparedQuery(query,rowkeyValue);
            resultSet.next();
            String actualColumnname=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
            dbJson=resultSet.getString(actualColumnname);
        }catch (Exception e){ e.printStackTrace(); }
        String actualJsonPathofAPI=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,jsonPath);
        verificationHelperClass.verifyAPIResponseJsonWithDBJson(response,dbJson,actualJsonPathofAPI);
    }
}
