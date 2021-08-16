package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.*;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CancelJob extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(CancelJob.class);
    String processingJobStatus;
    String processingJobId;
    ArrayList cancelProcessingJobId=new ArrayList<Integer>();;

    /**
     * cancelJoBData Method is used to create request payload data for cancelJob
     * @uthor Arun Kumar
     *  @param dataTable
     */
    public void cancelJoBData(DataTable dataTable)
    {
        try {
            List<Map<String, String>> createNewRebateProgramData = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : createNewRebateProgramData) {

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * cancelJobPostCall method is used to hit the endpoint
     * @uthor Arun Kumar
     * @param endPoint
     */
    public void cancelJobPostCall(String endPoint)
    {
        cancelProcessingJobId.add(processingJobId);
        response = postOperation(endPoint, cancelProcessingJobId);
    }

    /**
     * @uthour Arun Kumar
     * Verifies the response format is in JSON
     */
    public void verifyResponseFormatJSON()
    {
        verifyResponseFormatIsJSON();
    }

    /**
     * This method is used to validate the status code of cancelJobPostCall
     * @uthor Arun Kumar
     * @param statusCode
     */
    public void cancelJobPostCallStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("createNewRebateProgramPostCall StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * This method is used to execute query and get ProcessingJobId
     * @param query
     * @param status
     */
    public void getProcessingJobId(String query,String status) {
        try {
            ResultSet getProJobId = dataBaseHelper.executePreparedQuery(query, status);
            getProJobId.next();
            processingJobId = getProJobId.getString("PROCESSING_JOB_RUN_ID");
            processingJobStatus=status;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to update older status after cancelling the job
     */
    public void updateStatus(){
        try {
            String query="update TXN.VW_PROCESSING_JOB_RUN set status="+processingJobStatus+" where PROCESSING_JOB_RUN_ID=?";
            dataBaseHelper.executeUpdatePreparedQueryWithPropertiesFile(query,processingJobId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyIsCanceledIsTrue()
    {
        String actualJsonMsg = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "cancelJobTrueMsgJson");
        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "cancelJobJsonPath");
        verificationHelperClass.verifyAPIResponseJsonWithDBJson(response,actualJsonMsg,jsonPath);
    }

    public void verifyIsCanceledIsFalse()
    {
        String actualJsonMsg = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "cancelJobErrorMessage");
        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "cancelJobJsonPath");
        verificationHelperClass.verifyAPIResponseJsonWithDBJson(response,actualJsonMsg,jsonPath);
    }

    public void verifyErrorMessage()
    {
        String actualJsonMsg = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "cancelJobErrorMessage");
        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "cancelJobErrorMsgJsonPath");
        verificationHelperClass.verifyAPIResponseJsonWithDBJson(response,actualJsonMsg,jsonPath);
    }

    public void verifyNoJobExistsErrorMessage()
    {
        String actualJsonMsg = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "noJobRunExists");
        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "cancelJobErrorMsgJsonPath");
        verificationHelperClass.verifyAPIResponseJsonWithDBJson(response,actualJsonMsg,jsonPath);
    }

    public void invalidProcessingJobID()
    {
        String invalidID = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "invalidProcessingJobID");
        processingJobId=invalidID;
    }
}
