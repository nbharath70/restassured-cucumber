package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import HelperClass.UtilitiesClass;
import RequestPojo.*;
import RequestPojo.DisContractPojo.DiscardContractPojo;
import TestBase.TestBase;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CaptureCommentsAPI extends TestBase {
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    UtilitiesClass utilities=new UtilitiesClass();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public CaptureCommentsPojo captureCommentsPojo=new CaptureCommentsPojo();
    public CaptureCommentsPojo invalidcaptureCommentsPojo=new CaptureCommentsPojo();
    public DiscardContractPojo discardContractPojo;
    public static Logger log = getMyLogger(InitiateNewManufactureContract.class);
    private String commentDetailsFromDB;
    private String contractId;
    private String contractRowKey;
    private Response captureCommentsResponse;
    private Response discardContractResponse;
    private String taskId;
    private String procIns;


    /**
     * @uthor: Bharath
     *This Method is Used to create the new Contract and Sending the Same Contract Rebate ops
     */
    public void createNewContractUpdateAndSendToRebateOps() {
        List<String> listOfContractIdandRowkey=utilities.initiateMFRContract();
        contractId=listOfContractIdandRowkey.get(0);
        contractRowKey=listOfContractIdandRowkey.get(1);
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
     *This Method is Used to create  the Comments Request body
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
     *This Method is Used to hit the endpoint
     * @param enpoint-
     */
    public void hitEndpoint(String enpoint){
        captureCommentsResponse=postOperation(enpoint,captureCommentsPojo);
    }

    /**
     * @uthor: Bharath
     *This Method is Used to create the Invalid Request body
     * @param dataTable-
     */
    public void createInValidRequestBody(DataTable dataTable){
        try{
            List<Map<String, String>> captureComments = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : captureComments) {
                invalidcaptureCommentsPojo.setCategory(map.get("category"));
                invalidcaptureCommentsPojo.setCategoryId("catagoryID");
                invalidcaptureCommentsPojo.setEvent(map.get("event"));
                invalidcaptureCommentsPojo.setComment(map.get("comment"));
                invalidcaptureCommentsPojo.setProcessInstanceId("ProcessInstance");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @uthor: Bharath
     *This Method is Used to hit the endpoint with invalid request
     * @param enpoint-
     */
    public void hitEndpointWithInvalidRquest(String enpoint){
        captureCommentsResponse=postOperation(enpoint,invalidcaptureCommentsPojo);
    }

    /**
     * @uthor: Bharath
     *This Method is Used to delete the Commments
     * @param query-
     */
    public void deleteComments(String query){
        dataBaseHelper.executeUpdatePreparedQueryAsString(query,contractId);
    }

    public void deleteTask(String query){
        dataBaseHelper.connectToOtherDB("Flowable");
        dataBaseHelper.executeUpdatePreparedQueryAsString(query,procIns);
        dataBaseHelper.disConnectToOtherDB();
    }

    /**
     * @uthor: Bharath
     *This Method is Used to check the Status code
     * @param StatusCode-
     */
    public void verifyStatusCodeOFapi(int StatusCode){
        verificationHelperClass.verifyStatusCode(captureCommentsResponse,StatusCode);
    }

    /**
     * @uthor: Bharath
     *This Method is Used to verify the response in in Json format
     */
    public void verifyJsonResponse(){
        verifyResponseFormatIsJSON();
    }

    /**
     * @uthor: Bharath
     *This Method is Used to verify the Response with DB
     * @param query-
     */
    public void verifyCommentsResponseWithDB(String query){
        ResultSet resultSet=dataBaseHelper.executePreparedQuery(query,contractId);
        String resultColumnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"resultColumnName");
        String commentsID=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"commentsID");
        String categoryId=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"categoryId");

        try {
            resultSet.next();
            commentDetailsFromDB=resultSet.getString(resultColumnName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(captureCommentsResponse,commentDetailsFromDB,commentsID,commentsID);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(captureCommentsResponse,commentDetailsFromDB,categoryId,categoryId);


    }

    /**
     * @uthor: Bharath
     *This Method is Used to verify error message for invalid scenarios
     */
    public void verifyErrorMessage(){
        String inValidMessageWithoutParameterReplaced=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"invalidCatagoryIDMessage");
        String invalidcatagoryID=invalidcaptureCommentsPojo.getCategoryId();
        String invalidMeaageWithReplacedValue=inValidMessageWithoutParameterReplaced.replace("{pathparam}",invalidcatagoryID);
        String invlidMessageJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"JsonPathForErrorMessage");
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(captureCommentsResponse,invalidMeaageWithReplacedValue,invlidMessageJsonPath,invlidMessageJsonPath);
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

    /**
     * This method is used to delete the Contracts Created
     * @uthor Bharath
     * @param queryKey
     */
    public void deleteTheQAAutomationContract(String queryKey) {
        String deleteQuery=dataBaseHelper.replaceMultipleQueryParamWithOneString(queryKey,contractId);
        dataBaseHelper.executeDeleteQueryWithoutreadingFromPropFile(deleteQuery);
    }

}
