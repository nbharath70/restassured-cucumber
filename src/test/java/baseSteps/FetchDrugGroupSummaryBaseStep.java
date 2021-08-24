package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;


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
    public void hitInvalidEndpoint(String endpoint,String drugListID){
        response=getCall(endpoint,drugListID);
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
    public void validatefetchDrugGroupSummaryInvalidResponse(String actualValue,String expectedValue)
    {
        String actualExpectedValue=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,expectedValue);
        verificationHelperClass.verifyResponseJsonString(response,actualValue,actualExpectedValue);
    }
//    public void validatefetchDrugGroupSummaryInvalidResponse(String actualValue,String expectedValue) {
//        String jsonPathforErrorMsg=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, actualValue);
//        String errorMsgJsonAsString=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, expectedValue);
//        verificationHelperClass.verifyAPIResponseJsonWithDBJson(response,errorMsgJsonAsString,jsonPathforErrorMsg);
//
//    }
public void verifiesAPIResponseWithTypeMismatchErrorMsg(String jsonPath,String errorMesssage) {
    String jsonPathForErrorMsg=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, jsonPath);
    String errorMsgAsJson=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, errorMesssage);
//    String fullErrorMsgAsJson=errorMsgAsJson.concat("ABCD\\\"\"}");
    verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithStringDataTypeValues(response,errorMsgAsJson,jsonPathForErrorMsg,jsonPathForErrorMsg);

}

    public void validateFectDrugGroupSummaryResponse(String actualValue,String expectedValue)
    {
        String expectedValueMessage= getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,expectedValue);
        verificationHelperClass.verifyResponseJsonString(response,actualValue,expectedValueMessage);
    }

    /**
     * @uthor Bharath
     * @param query-Query that fetches the Columns of DB in the form of
     * @param columnName-
     */
    public void validateJSONResponse(String query,String columnName){
        try{ String actualquery=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES,query);
            String actualColumnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
            String apiJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathForResponsevalidation");
            String dBJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathForResponsevalidation");
            resultSet=dataBaseHelper.executePreparedQuery(query,rowkeyValue);
            resultSet.next();
            String dbJson=resultSet.getString(actualColumnName);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,apiJsonPath,dBJsonPath);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
