package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;


public class CompleteProgramAPI extends TestBase {
    DataBaseHelper dbHelper=new DataBaseHelper();
    VerificationHelperClass verificationHelperClass=new VerificationHelperClass();
    private String programRowkey=null;
    private Response response=null;

    public void runQueryAndPickProgramRowkey(String queryKey, String columnNameKey) {
        String columnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnNameKey);
        programRowkey=dbHelper.getSingleCellValueAsStringFromDB(queryKey,columnName);
    }

    public void hitAPIWithPostRequestAndTryToChangeTheProgramStatus(String endPoint) {
        response=super.postOperation(endPoint,programRowkey);
    }

    public void verifyErrorMsgFromResponse(String errorMsgKey) {
        String errorMsgJsonAsString=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,errorMsgKey);
        String jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathforProgramHavingAnInProgressRO");
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithonlyStringDataTypeValues(response,errorMsgJsonAsString,jsonPath,jsonPath);
    }

    public void verifyTheValidStatusCodeInAPIResponse(int statusCode) {
        verificationHelperClass.verifyStatusCode(response,statusCode);
    }

    public void redoTheChangesMadeByExecutingUpdateQuery(String queryKey) {
        dbHelper.executeUpdatePreparedQueryAsString(queryKey,programRowkey);
    }

    public void verifyTheSuccessMsgFromAPIResponseForRecordupdated(String jsonPathKey, String expectedValue) {
        verificationHelperClass.verifyResponseJsonBoolean(response,jsonPathKey,expectedValue);
    }
}
