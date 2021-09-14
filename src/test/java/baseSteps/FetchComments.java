package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.*;
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
    public CaptureCommentsPojo captureCommentsPojo=new CaptureCommentsPojo();
    public FetchCommentsPojo fetchCommentsPojo=new FetchCommentsPojo();
    public static Logger log = getMyLogger(InitiateNewManufactureContract.class);
    InitManufactureContract initManufactureContractObject;
    ResultSet resultSet;
    private String contractName=null;
    private String contractId;
    private Response captureCommentsResponse;
    private Response fetchCommentsResponse;
    private String procIns;
    private String taskID;
    private String commentsDetailsJson;


    /**
     * updateExistingManufactureContractDetails Method is used to update the manufacture contract detail data for init new manufacture contract
     * @uthor Arun Kumar
     *  @param dataTable
     */
    public void updateExistingManufactureContractDetails(DataTable dataTable)
    {
        try {
            List<Map<String, String>> updateManufactureDetailData = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : updateManufactureDetailData) {
                contractName = map.get("contractName");
                ResultSet getContractId = dataBaseHelper.executePreparedQuery("getContractIdByContractName", map.get("contractName"));
                getContractId.next();
                contractId=getContractId.getString("Contract_ID");
                ResultSet getRowKeyContractHeader = dataBaseHelper.executePreparedQuery("getRowKeyByContractName", map.get("contractName"));
                getRowKeyContractHeader.next();
                String rowKeyContractHeader=getRowKeyContractHeader.getString("Row_Key");
                ResultSet getRowKeyContractDetail = dataBaseHelper.executePreparedQuery("getRowKeyByAmendmentName", map.get("contractName"));
                getRowKeyContractDetail.next();
                String rowKeyContractDetail=getRowKeyContractDetail.getString("Row_Key");
                ContractDetailJson contractDetailJson1 = new ContractDetailJson();
                contractDetailJson1.setLineOfBusiness(map.get("lineOfBusiness"));
                ArrayList lineOfBusiness = contractDetailJson1.getLineOfBusiness();
                contractDetailJson1.setLocations(map.get("locations"));
                ArrayList locations = contractDetailJson1.getLocations();
                Manufacturer manufacturerObject = new Manufacturer(map.get("ManufacturerId"), map.get("name"), Boolean.valueOf(map.get("currentFlag")));
                ContractHeader contractHeaderObject = new ContractHeader(Integer.valueOf(rowKeyContractHeader) ,contractId, map.get("ManufacturerId"), map.get("contractType"), map.get("contractName"), map.get("startDate"), map.get("endDate"), map.get("recCreatedDate"), map.get("recCreatedBy"), map.get("recUpdatedDate"), map.get("recUpdatedBy"), map.get("lifecycleStatus"), map.get("contractDocReference"), map.get("notes"));
                Payment paymentObj = new Payment(Integer.valueOf(map.get("disputeDays")), map.get("lateFee"), map.get("lateFeeFixed"), map.get("lateFeePct"), Boolean.valueOf(map.get("paymentBackup")), map.get("NCPDPReconFile"));
                Audit auditObj = new Audit(map.get("frequency"), Integer.valueOf(map.get("lookback")), map.get("numScreenshots"), Boolean.valueOf(map.get("allowThirdPartyAuditor")), Boolean.valueOf(map.get("auditScreenshots")));
                ContractDetailJson contractDetailJsonObject = new ContractDetailJson(Integer.valueOf(map.get("schemaVersion")), lineOfBusiness,locations, map.get("billingCycle"), Integer.valueOf(map.get("submissionWindow")), Integer.valueOf(map.get("resubmissionWindow")), Integer.valueOf(map.get("paymentTerms")), Boolean.valueOf(map.get("thirdPartyAuth")), map.get("opsAssignee"), map.get("opsQCer"), paymentObj, auditObj);
                ContractDetail contractDetailObject = new ContractDetail(Integer.valueOf(rowKeyContractDetail),contractId, Integer.valueOf(map.get("amendmentNumber")), map.get("amendmentName"), map.get("lifecycleStatus"), map.get("startDate"), map.get("endDate"), Integer.valueOf(map.get("versionNumber")), map.get("recCreatedBy"), map.get("recCreatedDate"), map.get("recUpdatedBy"), map.get("recUpdatedDate"), contractDetailJsonObject);
                initManufactureContractObject = new InitManufactureContract(manufacturerObject, contractHeaderObject, contractDetailObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * This method is used to updateManufactureContractPutCall
     * @uthor Arun Kumar
     * @param endPoint
     */
    public void updateManufactureContractPutCall(String endPoint)
    {
        response = postOperation(endPoint, initManufactureContractObject);
    }
    public void getProcessInstance(){
        String instanceIdJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"processInstanceIdJsonPath");
        String processInstanceArray= JsonPath.read(response.asString(),instanceIdJsonPath).toString();
        String a=processInstanceArray.replace("[","");
        String b=a.replace("]","");
        procIns=b.replaceAll("^\"|\"$", "");

    }

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

    public void hitEndpoint(String enpoint){
        captureCommentsResponse=postOperation(enpoint,captureCommentsPojo);
    }


    public void deleteComments(String query){
        dataBaseHelper.executeUpdatePreparedQueryAsString(query,contractId);
    }
    public void deleteTask(String query){
        dataBaseHelper.connectToOtherDB("Flowable");
        dataBaseHelper.executeUpdatePreparedQueryAsString(query,procIns);
        dataBaseHelper.disConnectToOtherDB();
    }
    public void verifyStatusCodeOfCaptureComments(int StatusCode) {
        verificationHelperClass.verifyStatusCode(captureCommentsResponse, StatusCode);
    }
        public void verifyStatusCodeOfFetchCommentsapi(int StatusCode){
        verificationHelperClass.verifyStatusCode(captureCommentsResponse,StatusCode);
    }

    public void verifyJsonResponse(){
      verifyResponseFormatIsJSON();
    }
    public void hitFetchCommentsAPI(String enpoint){
        fetchCommentsResponse=postOperation(enpoint,fetchCommentsPojo);
    }

    public void createRequestBodyForFetchCommentsAPI(String query)  {
        try {
            dataBaseHelper.connectToOtherDB("Flowable");
            resultSet=dataBaseHelper.executePreparedQuery(query,contractId);
            resultSet.next();
            taskID=resultSet.getString("ID_");
            dataBaseHelper.disConnectToOtherDB();
        }catch (SQLException e){
            e.printStackTrace();
            dataBaseHelper.disConnectToOtherDB();
        }
        fetchCommentsPojo.setContractId(contractId);
        fetchCommentsPojo.setTaskId(taskID);
    }


    public void verifyResponseWithDB(String query){
        ResultSet resultSet=dataBaseHelper.executePreparedQuery(query,contractId);
        try {
            resultSet.next();
            String columnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"resultColumnName");
            commentsDetailsJson=resultSet.getString(columnName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String recCreatedDatejsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"recCreatedDatejsonPathforFetchComments");
        String recCreatedByjsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"recCreatedbyjsonPathforFetchComments");
        String commentID=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"commentIdJsonPathforFetchComments");
        String category=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"categoryJsonPathforFetchComments");
        String event=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"eventJsonPathforFetchComments");
        String categoryID=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"categoryIdJsonPathforFetchComments");

        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(fetchCommentsResponse,commentsDetailsJson,recCreatedDatejsonPath,recCreatedDatejsonPath);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(fetchCommentsResponse,commentsDetailsJson,recCreatedByjsonPath,recCreatedByjsonPath);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(fetchCommentsResponse,commentsDetailsJson,commentID,commentID);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(fetchCommentsResponse,commentsDetailsJson,category,category);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(fetchCommentsResponse,commentsDetailsJson,event,event);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(fetchCommentsResponse,commentsDetailsJson,categoryID,categoryID);
    }
}
