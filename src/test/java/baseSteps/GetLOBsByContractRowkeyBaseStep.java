package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import stepdefs.GetLOBsByContractRowkey;

public class GetLOBsByContractRowkeyBaseStep {

    private int rowKey;
    private Response getLOBAPIresponse;
    private String contractDetailJsonAsString;
    public static Logger log= TestBase.getMyLogger(GetLOBsByContractRowkey.class);


    DataBaseHelper dbHelper=new DataBaseHelper();
    TestBase testBase=new TestBase();
    VerificationHelperClass verHelper=new VerificationHelperClass();

    public void get_value_for_rowkey_col_from_DB(String queryKey, String columnNameKey) {
        String columnName=testBase.getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnNameKey);
        rowKey= dbHelper.getDataColumnCountDB(queryKey,columnName);
        log.info("RowKey selected:" + rowKey);
    }
    public void hitEndPointWithGetAPIRequest(String endPointUrl)  {
        getLOBAPIresponse=testBase.getCall(endPointUrl,String.valueOf(rowKey));
    }
    public void VerifyTheValidStatusCodeInGetLOBsAPIResponse(int expectedStatusCode) {
        verHelper.verifyStatusCode(getLOBAPIresponse,expectedStatusCode);
    }

    public void executeQueryAndGetContractFromDB(String queryKey, String columnNameKey) throws Throwable {
        String columnName=testBase.getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnNameKey);
        contractDetailJsonAsString=dbHelper.getSingleCellValueAsStringFromDB(queryKey,columnName);
    }
    public void verifiyAPIResponseWithDBResponse() {
        String jsonPath=testBase.getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "jasonPathforLOB");
        verHelper.verifyAPIResponseJsonWithDBJson(getLOBAPIresponse,contractDetailJsonAsString,jsonPath);
    }


}
