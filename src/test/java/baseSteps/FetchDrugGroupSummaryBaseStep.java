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


    /**
     * @uthour Bharath
     * This method get the Rowkey of the Druglist
     * @param query-
     */
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

    /**
     * @uthour Bharath
     * This method is Used to Hit the Endpoint
     * @param endpoint-
     */
    public void hitEndpoint(String endpoint){
        response=getCall(endpoint,String.valueOf(rowkeyValue));
    }

    /**
     * @uthour Bharath
     * This method is Used to Hit the InValid Endpoint
     * @param endpoint-
     * @param RowKey-
     */
    public void hitInvalidEndpoint(String endpoint,String RowKey){
        String invalidDrugListID=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,RowKey);
        response=getCall(endpoint,invalidDrugListID);
    }

    /**
     * @uthour Bharath
     * This method is Used to Verify the Status code is proper by Comparing with Response
     * @param statusCode-
     */
    public void verifyFetchDrugGroupSummaryStatusCode (int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response,statusCode);
        log.info("fetchDrugGroupSummary API's StatusCode is: "+statusCode);
    }

    /**
     * @uthour Bharath
     * This method is Used to Verify the Response is in JSON Format
     */
    public void verifyFetchDrugGroupSummaryAPIResponseFormatJSON()
    {
        verifyResponseFormatIsJSON();
    }



    /**
     * @uthour Bharath
     * This method is to Verify the Type Missmatch Scenario
     * @param jsonPath-
     * @param errorMesssage-
     */
public void verifiesAPIResponseWithTypeMismatchErrorMsg(String jsonPath,String errorMesssage) {
    String jsonPathForErrorMsg=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, jsonPath);
    String errorMsgAsJson=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, errorMesssage);
    String invalidMessage=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"inValidDrugListID");
    String completeErrorMessageAsJSON=errorMsgAsJson.replace("{pathparam}",invalidMessage);
    verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithStringDataTypeValues(response,completeErrorMessageAsJSON,jsonPathForErrorMsg,jsonPathForErrorMsg);
}

    /**
     * @uthor Bharath
     * This Method Used to validate the Respnse
     * @param actualValue-It is
     * @param expectedValue-It is the ColumnName of the JSON Got from the Query
     */
    public void validateFectDrugGroupSummaryResponse(String actualValue,String expectedValue)
    {
        String expectedValueMessage= getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,expectedValue);
        verificationHelperClass.verifyResponseJsonString(response,actualValue,expectedValueMessage);
    }

    /**
     * @uthor Bharath
     * This Method Used to validate the Respnse
     * @param query-Query that fetches the Columns of DB in the form of
     * @param columnName-It is the ColumnName of the JSON Got from the Query
     */
    public void validateJSONResponse(String query,String columnName){
        try{
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
