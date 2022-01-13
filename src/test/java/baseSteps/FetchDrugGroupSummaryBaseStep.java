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
//            String apiJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathForResponsevalidation");
//            String dBJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathForResponsevalidation");
            String drugListRowKey=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"drugListRowKeyforFetcDrugGroupSummary");
            String mfrDrugListId=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"mfrDrugListIdforFetcDrugGroupSummary");
            String opsQc=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"opsQcforFetcDrugGroupSummary");
            String recCreatedBy=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"recCreatedByforFetcDrugGroupSummary");
            String opsAssignee=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"opsAssigneeforFetcDrugGroupSummary");
            String openForEditFlag=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"openForEditFlagforFetcDrugGroupSummary");
            String instanceKey=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"instanceKeyforFetcDrugGroupSummary");
            String drugGroupName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"drugGroupNameForFetcDrugGroupSummary");
            String mfrName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"mfrNameJsonPathForFetcDrugGroupSummary");
            String drugGroupType=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"drugGroupTypeForFetcDrugGroupSummary");
            String drugGroupTypeDesc=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"drugGroupTypeDescFetcDrugGroupSummary");
            String lifecycleStatus=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"lifecycleStatusForFetcDrugGroupSummary");
            String previouslyApproved=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"previouslyApprovedForFetcDrugGroupSummary");
            resultSet=dataBaseHelper.executePreparedQuery(query,rowkeyValue);
            resultSet.next();
            String dbJson=resultSet.getString(actualColumnName);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,drugListRowKey,drugListRowKey);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,mfrDrugListId,mfrDrugListId);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,opsQc,opsQc);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,recCreatedBy,recCreatedBy);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,opsAssignee,opsAssignee);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,openForEditFlag,openForEditFlag);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,instanceKey,instanceKey);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,drugGroupName,drugGroupName);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,mfrName,mfrName);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,drugGroupType,drugGroupType);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,drugGroupTypeDesc,drugGroupTypeDesc);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,lifecycleStatus,lifecycleStatus);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,previouslyApproved,previouslyApproved);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
