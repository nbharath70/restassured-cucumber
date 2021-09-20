package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import stepdefs.GetLOBsByContractRowkey;

public class GetLOBsByContractRowkeyBaseStep extends TestBase{

    private int rowKey;
    private Response getLOBAPIresponse;
    private String contractDetailJsonAsString;
    public static Logger log= TestBase.getMyLogger(GetLOBsByContractRowkey.class);


    DataBaseHelper dbHelper=new DataBaseHelper();
    TestBase testBase=new TestBase();
    VerificationHelperClass verHelper=new VerificationHelperClass();


    /**
     * @uthor Rabbani
     * This Method is used to get the RowKey for Column from DB
     * @param queryKey-
     * @param columnNameKey-
     */
    public void getValueForRowkeyColFromDb(String queryKey, String columnNameKey) {
        String columnName=testBase.getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnNameKey);
        rowKey= dbHelper.getDataColumnCountDB(queryKey,columnName);
        log.info("RowKey selected:" + rowKey);
    }

    /**
     * @uthor Rabbani
     * This Method is used to hit the API with get Request
     * @param endPointUrl-
     */
    public void hitEndPoint(String endPointUrl)  {
        getLOBAPIresponse=testBase.getCall(endPointUrl,String.valueOf(rowKey));
    }

    /**
     * @uthor Rabbani
     * This Method is used to validate the Status code of get LOB API
     * @param expectedStatusCode-
     */
    public void validateStatusCode(int expectedStatusCode) {
        verHelper.verifyStatusCode(getLOBAPIresponse,expectedStatusCode);
    }

    /**
     * @uthor Rabbani
     * This Method is used to grt the contract in the form of JSON
     * @param queryKey-
     * @param columnNameKey-
     */
    public void executeQueryAndGetContractFromDB(String queryKey, String columnNameKey) {
        String columnName=testBase.getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnNameKey);
        contractDetailJsonAsString=dbHelper.getSingleCellValueAsStringFromDB(queryKey,columnName);
    }

    /**
     * @uthor Rabbani
     * This Method is used to verify the API response with DB
     */
    public void verifiyAPIResponseWithDBResponse() {
        String jsonPath=testBase.getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "jasonPathforLOB");
        verHelper.verifyAPIResponseJsonWithDBJson(getLOBAPIresponse,contractDetailJsonAsString,jsonPath);
    }

    /**
     * @uthor Rabbani
     * This Method is used to dynamically fetch the RowKey and Add 9 to it and make that invalid rowkey
     */
    public void getValueForRowkeyFromDbAndGenerateInvalidRowkey(String queryKey, String columnNameKey) {
        String columnName=testBase.getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnNameKey);
        rowKey= dbHelper.getDataColumnCountDB(queryKey,columnName)+9;
        log.info("RowKey selected:" + rowKey);
    }

    /**
     * @uthor Rabbani
     * This Method is used verify invalid error Message
     * @param errorMsgKey-
     */
    public void verifyAPIResponseWithErrorMsg(String errorMsgKey) {
        String jsonPathForErrorMsg=testBase.getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "jsonPathforErrorMsgforLOBofInvalidContractRowKey");
        String errorMsgAsJson=testBase.getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, errorMsgKey);
        String fullErrorMsgAsJson=errorMsgAsJson.concat(String.valueOf(rowKey)).concat("\"}");
        verHelper.verifyAPIResponseJsonWithDBJsonWithonlyStringDataTypeValues(getLOBAPIresponse,fullErrorMsgAsJson,jsonPathForErrorMsg,jsonPathForErrorMsg);
    }

    /**
     * @uthor Rabbani
     * This Method is used to hit the API with invalid Key
     * @param endPointKey-
     */
    public void hitsEndPointWithBlankRowKeyAndGetAPIRequest(String endPointKey) {
        getLOBAPIresponse=testBase.getCall(endPointKey,"");
    }

    /**
     * @uthor Rabbani
     * This Method is used to verify the invalid message for Blank rowkey
     * @param errorMsgKey-
     */
    public void verifyAPIResponsewithBlankContractRowKeyErrorMsg(String errorMsgKey) {
        String jsonPathForErrorMsg=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "jsonPathforErrorMsgforLOBofBlankContractRowKey");
        String errorMsgAsJson=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, errorMsgKey);
        verHelper.verifyAPIResponseJsonWithDBJsonWithonlyStringDataTypeValues(getLOBAPIresponse,errorMsgAsJson,jsonPathForErrorMsg,jsonPathForErrorMsg);

    }

    /**
     * @uthor Rabbani
     * This Method is used to hit the API with missMatch Rowkey
     * @param endPointKey-
     * @param typeMismatchRowkey-
     */
    public void hitEndPointWithMismatchRowKeyandGetAPIRequest(String endPointKey, String typeMismatchRowkey) {
        String typeMissMatchedRowKey=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,typeMismatchRowkey);
        getLOBAPIresponse=testBase.getCall(endPointKey,typeMissMatchedRowKey);
    }

    /**
     * @uthor Rabbani
     * This Method is used to verify the error message of MissmacthRowKey Response
     * @param errorMsgKey-
     */
    public void verifiesAPIResponseWithTypeMismatchErrorMsg(String errorMsgKey) {
        String jsonPathForErrorMsg=testBase.getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "jsonPathforErrorMsgforLOBofInvalidContractRowKey");
        String errorMsgAsJson=testBase.getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, errorMsgKey);
        String fullErrorMsgAsJson=errorMsgAsJson.concat("ABCD\\\"\"}");
        verHelper.verifyAPIResponseJsonWithDBJsonWithonlyStringDataTypeValues(getLOBAPIresponse,fullErrorMsgAsJson,jsonPathForErrorMsg,jsonPathForErrorMsg);

    }
    /**
     * @uthor Rabbani
     * This Method is used to verify the error message of MissmacthRowKey Response
     * @param expectedHeaderErrorCode-
     */
    public void verifiesTheLOBsForAContractResponseHeaderErrorCodeValue(String expectedHeaderErrorCode) {
        verHelper.verifyResponseHeaderApiReturnCodesValue(getLOBAPIresponse,expectedHeaderErrorCode);
    }
}
