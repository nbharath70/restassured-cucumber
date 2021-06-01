package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.CreateDrugGroupPojo;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class GetJobStatusSummary extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(GetJobStatusSummary.class);


    /*============================================================================================*/
    /**
     * @uthour Smruti
     * This method hits getJobStatusResource  end point and logs the response
     * @param endpoint
     */
    public void getJobStatusResource(String endpoint)
    {
        response=getCall(endpoint);
    }
    /**@uthour Smruti
     * getjobStatusSummary Response Status Code method used to verify the status code
     * @param statusCode
     */
    public void getJobStatusSummaryResponseStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response,statusCode);
        log.info("getjobStatuscode API's StatusCode is: "+statusCode);
    }

    /**
     * @uthour Smruti
     * This method hits getJob Summary Status Resource  end point and
     * Verifies the response format is in JSON
     */
    public void verifyResponseFormatJSON()
    {
        verifyResponseFormatIsJSON();
    }

    /**
     * @uthour Smruti
     * validating verifyScheduledRecordCount method is used to validate the response body output with database value
     * for Scheduled jobs count
     * @param query
     */
    public void verifyScheduledRecordCount(String query)
    {

        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "scheduledJobJsonPath");
        String dbColumn = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "scheduledJobCountColumn");
        log.info("Validating get Scheduled job status summary Response Body JsonPath="+jsonPath+ " and query="+query);
        verificationHelperClass.matchRecordCount(response,jsonPath,query,dbColumn);
    }
    /**
     * @uthour Smruti
     * validating verifyRunningRecordCount method is used to validate the response body output with database value
     * @param query
     */
    public void verifyRunningRecordCount(String query)
    {

        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "runningJobJsonPath");
        String dbColumn = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "runningJobCountColumn");
        log.info("Validating get running job status summary Response Body JsonPath="+jsonPath+ " and query="+query);
        verificationHelperClass.matchRecordCount(response,jsonPath,query,dbColumn);
    }
    /**
     * @uthour Smruti
     * validating verifyFinishedRecordCount method is used to validate the response body output with database value
     * @param query
     */
    public void verifyFinishedRecordCount(String query)
    {

        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "finishedJobJsonPath");
        String dbColumn = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "finishedJobCountColumn");
        log.info("Validating get running job status summary Response Body JsonPath="+jsonPath+ " and query="+query);
        verificationHelperClass.matchRecordCount(response,jsonPath,query,dbColumn);
    }
    /**
     * @uthour Smruti
     * validating verifyCancelledRecordCount method is used to validate the response body output with database value
     * @param query
     */
    public void verifyCancelledRecordCount(String query)
    {

        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "canceledJobJsonPath");
        String dbColumn = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "cancelledJobCountColumn");
        log.info("Validating get running job status summary Response Body JsonPath="+jsonPath+ " and query="+query);
        verificationHelperClass.matchRecordCount(response,jsonPath,query,dbColumn);
    }
    /**
     * @uthour Smruti
     * validating verifyAbortedRecordCount method is used to validate the response body output with database value
     * @param query
     */
    public void verifyAbortedRecordCount(String query)
    {

        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "abortedJobJsonPath");
        String dbColumn = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "abortedJobCountColumn");
        log.info("Validating get running job status summary Response Body JsonPath="+jsonPath+ " and query="+query);
        verificationHelperClass.matchRecordCount(response,jsonPath,query,dbColumn);
    }
}
