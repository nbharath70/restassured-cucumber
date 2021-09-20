package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.UtilitiesClass;
import HelperClass.VerificationHelperClass;
import RequestPojo.*;
import RequestPojo.DisContractPojo.DiscardContractPojo;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import TestBase.TestBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FetchComments extends TestBase{

    Response response;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    UtilitiesClass utilities=new UtilitiesClass();
    public CaptureCommentsPojo captureCommentsPojo=new CaptureCommentsPojo();
    public DiscardContractPojo discardContractPojo;
    public FetchCommentsPojo fetchCommentsPojo=new FetchCommentsPojo();
    public static Logger log = getMyLogger(InitiateNewManufactureContract.class);
    private String contractRowKey;
    private String contractId;
    private Response captureCommentsResponse;
    private Response fetchCommentsResponse;
    private Response discardContractResponse;
    private String procIns;
    private String taskId;
    private String commentsDetailsJson;


    /**
     * @uthor: Bharath
     *This Method is Used to create the new Contract and Sending the Same Contract Rebate ops
     */
    public void createNewContractUpdateAndSendToRebateOps() {
        List<String> listOfContractIdAndRowkey=utilities.initiateMFRContract();
        contractId=listOfContractIdAndRowkey.get(0);
        contractRowKey=listOfContractIdAndRowkey.get(1);
        procIns=utilities.updateContractAndSendToRebateOps(contractId,Integer.parseInt(contractRowKey));

    }

    /**
     * @uthor: Bharath
     *This Method is Used to fetch the Task Id of the contract using processInstanceID
     * @param queryKey-
     */
    public void getTaskIdWithProcessInstanceId(String queryKey) {
        try {
            dataBaseHelper.connectToOtherDB("flowable");
            ResultSet rs = dataBaseHelper.executePreparedQuery(queryKey, procIns);
            rs.next();
            taskId = rs.getString("taskId");
            dataBaseHelper.disConnectToOtherDB();
        }catch (Exception e){e.printStackTrace();}

    }


    /**
     * @uthor: Bharath
     *This Method is Used to create the Comments API request body
     * @param dataTable-
     */
    public void createRequestBody(DataTable dataTable){
        try{
            List<Map<String, String>> captureComments = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : captureComments) {
                captureCommentsPojo.setCategory(map.get("category"));
                captureCommentsPojo.setCategoryId(contractId);
                captureCommentsPojo.setEvent(map.get("event"));
                captureCommentsPojo.setComment(map.get("comment"));
                captureCommentsPojo.setProcessInstanceId(procIns);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @uthor: Bharath
     *This Method is Used to hit the API endPoint
     * @param endpoint-
     */
    public void hitEndpoint(String endpoint){
        captureCommentsResponse=postOperation(endpoint,captureCommentsPojo);
    }

    /**
     * @uthor: Bharath
     *This Method is Used to delete the Comments
     * @param query-
     */
    public void deleteComments(String query){
        dataBaseHelper.executeUpdatePreparedQueryAsString(query,contractId);
    }

    /**
     * @uthor: Bharath
     *This Method is Used to delete the task
     * @param query-
     */
    public void deleteTask(String query){
        dataBaseHelper.connectToOtherDB("Flowable");
        dataBaseHelper.executeUpdatePreparedQueryAsString(query,procIns);
        dataBaseHelper.disConnectToOtherDB();
    }

    /**
     * @uthor: Bharath
     *This Method is Used to verify the Status code of the comments
     * @param StatusCode-
     */
    public void verifyStatusCodeOfCaptureComments(int StatusCode) {
        verificationHelperClass.verifyStatusCode(captureCommentsResponse, StatusCode);
    }

    /**
     * @uthor: Bharath
     *This Method is Used to verify the Status code
     * @param StatusCode-
     */
        public void verifyStatusCodeOfFetchCommentsApi(int StatusCode){
        verificationHelperClass.verifyStatusCode(captureCommentsResponse,StatusCode);
    }

    /**
     * @uthor: Bharath
     *This Method is Used to verify the json Response
     */
    public void verifyJsonResponse(){
      verifyResponseFormatIsJSON();
    }

    /**
     * @uthor: Bharath
     *This Method is Used to hit the Fetch Comments API
     * @param endpoint-
     */
    public void hitFetchCommentsAPI(String endpoint){
        fetchCommentsResponse=postOperation(endpoint,fetchCommentsPojo);
    }

    /**
     * @uthor: Bharath
     *This Method is Used to create the Request body to hit the API
     */
    public void createRequestBodyForFetchCommentsAPI(String query)  {
        fetchCommentsPojo.setContractId(contractId);
        fetchCommentsPojo.setTaskId(taskId);
    }

    /**
     * @uthor: Bharath
     *This Method is Used to verify the response with DB
     * @param query
     */
    public void verifyResponseWithDB(String query){
        ResultSet resultSet=dataBaseHelper.executePreparedQuery(query,contractId);
        try {
            resultSet.next();
            String columnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"resultColumnName");
            commentsDetailsJson=resultSet.getString(columnName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String recCreatedDateJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"recCreatedDateJsonPathForFetchComments");
        String recCreatedByJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"recCreatedByJsonPathForFetchComments");
        String commentID=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"commentIdJsonPathForFetchComments");
        String category=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"categoryJsonPathForFetchComments");
        String event=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"eventJsonPathForFetchComments");
        String categoryID=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"categoryIdJsonPathForFetchComments");

        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(fetchCommentsResponse,commentsDetailsJson,recCreatedDateJsonPath,recCreatedDateJsonPath);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(fetchCommentsResponse,commentsDetailsJson,recCreatedByJsonPath,recCreatedByJsonPath);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(fetchCommentsResponse,commentsDetailsJson,commentID,commentID);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(fetchCommentsResponse,commentsDetailsJson,category,category);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(fetchCommentsResponse,commentsDetailsJson,event,event);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(fetchCommentsResponse,commentsDetailsJson,categoryID,categoryID);
    }

    public void deleteTheQAAutomationContract(String queryKey) {
        String deleteQuery=dataBaseHelper.replaceMultipleQueryParamWithOneString(queryKey,contractId);
        dataBaseHelper.executeDeleteQueryWithoutreadingFromPropFile(deleteQuery);
    }

    /**
     * This method is used to discard the manufacture contract
     * @uthor Bharath
     * @param endpoint
     */
    public void discardContract(String endpoint)
    {
        try {

            discardContractPojo=new DiscardContractPojo(taskId,contractId,Integer.valueOf(contractRowKey));
            discardContractResponse = deleteOperation(endpoint, discardContractPojo);
            log.info("Response is "+discardContractResponse.asString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
