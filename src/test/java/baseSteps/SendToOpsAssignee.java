package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.UtilitiesClass;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import java.io.FileReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendToOpsAssignee extends TestBase {

    private String contractIdCreated=null;
    private String contractRowKeyCreated=null;
    private String procInstId=null;
    private JSONObject finalRequest=null;
    private Response response=null;
    private String opsAssingee=null;
    private String opsQCer=null;
    private JSONObject expectedResponse=null;
    private String taskId=null;

    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    UtilitiesClass utilities=new UtilitiesClass();
    VerificationHelperClass verificationHelperClass=new VerificationHelperClass();


    public void createNewContractUpdateAndSendToRebateOps() {
        List<String> listOfContractIdandRowkey=utilities.initiateMFRContract();
        contractIdCreated=listOfContractIdandRowkey.get(0);
        contractRowKeyCreated=listOfContractIdandRowkey.get(1);
        procInstId=utilities.updateContractAndSendToRebateOps(contractIdCreated,Integer.parseInt(contractRowKeyCreated));

    }

    public void getTaskIdWithProcessInstanceId(String queryKey) {
        try {
            dataBaseHelper.connectToOtherDB("flowable");
            ResultSet rs = dataBaseHelper.executePreparedQuery(queryKey, procInstId);
            rs.next();
            taskId = rs.getString("taskId");
            dataBaseHelper.disConnectToOtherDB();
        }catch (Exception e){e.printStackTrace();}

    }

    public void updateOpsAssigneeAndOpsQCer(String opsAssigneeName, String opsQCerName) {
        try {
            opsAssingee=opsAssigneeName;
            opsQCer=opsQCerName;
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\testdata\\sendToOpsAssignee.json");
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            Map<String, Object> map = new HashMap<>();
            map.put("$.contractDetail.contractId", contractIdCreated);
            map.put("$.contractHeader.contractId", contractIdCreated);
            map.put("$.contractDetail.rowKey", contractRowKeyCreated);
            map.put("$.contractHeader.rowKey", contractRowKeyCreated);
            map.put("$.workFlowRequestInfo.taskId",taskId);
            map.put("$.contractDetail.contractDetailJson.opsAssignee",opsAssigneeName);
            map.put("$.contractDetail.contractDetailJson.opsQCer",opsQCerName);
            finalRequest = utilities.jsonValueReplacer(jsonObject, map);
            //log.info(new GsonBuilder().setPrettyPrinting().create().toJson(finalRequest));


        }catch (Exception e){e.printStackTrace();}
    }

    public void hitSendToOpsAssigneePostRequest(String endPointKey) {
        response=postOperation(endPointKey,finalRequest);
    }


    public void prepareExpectedResultJsonAfterSendingToOpsAssignee() {
        try {
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\testdata\\sendToOpsAssigneeResponse.json");
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            Map<String, Object> map = new HashMap<>();
            map.put("$.processInstanceId", procInstId);
            map.put("$.message", "Task assigned to :"+opsAssingee);
            expectedResponse=utilities.jsonValueReplacer(jsonObject,map);
            //log.info(new GsonBuilder().setPrettyPrinting().create().toJson(expectedResponse));


        }catch (Exception e){e.printStackTrace();}
    }

    public void verifyActualResponseWithExpectedResponse() {
        verificationHelperClass.verifyexpectedAndActualDirectlyAsStrings(response,expectedResponse.toString());
    }

    public void verifyLifecycleStatusOfContractChangedToInProgress(String lifecycleStatus) {
        try {
            String finalQuery = dataBaseHelper.replaceMultipleQueryParamWithOneString("sqlToGetLifeCycleStatusofContract", contractIdCreated);
            ResultSet rs = dataBaseHelper.getData(finalQuery);
            rs.next();
            Assert.assertEquals("Lifecycle status of contract header is not InProgress","InProgress",rs.getString("header_LCS"));
            Assert.assertEquals("Lifecycle status of contract detail is not InProgress","InProgress",rs.getString("detail_LCS"));
            log.info("Lifecycle status of contract header and detail is InProgress");
        }catch (Exception e){e.printStackTrace();}
    }

    public void userDeletesTheFlowableRecordsForTheProcessInstanceId() {
        dataBaseHelper.connectToOtherDB("flowable");
        utilities.deleteFlowableWithProcInstId(procInstId);
        dataBaseHelper.disConnectToOtherDB();
    }

    public void deleteTheQAAutomationContract(String queryKey) {
        String deleteQuery=dataBaseHelper.replaceMultipleQueryParamWithOneString(queryKey,contractIdCreated);
        dataBaseHelper.executeDeleteQueryWithoutreadingFromPropFile(deleteQuery);
    }

    public void checkStatusCodeFromTheResponse(int statusCode) {
        verificationHelperClass.verifyStatusCode(response,statusCode);

    }

    public void updateOpsAssigneeOpsQCerAndInvalidTaskId(String opsAssigneeName, String opsQCerName) {
        try {
            opsAssingee=opsAssigneeName;
            opsQCer=opsQCerName;
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\testdata\\sendToOpsAssignee.json");
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            Map<String, Object> map = new HashMap<>();
            map.put("$.contractDetail.contractId", contractIdCreated);
            map.put("$.contractHeader.contractId", contractIdCreated);
            map.put("$.contractDetail.rowKey", contractRowKeyCreated);
            map.put("$.contractHeader.rowKey", contractRowKeyCreated);
            map.put("$.workFlowRequestInfo.taskId",taskId+"9999");
            map.put("$.contractDetail.contractDetailJson.opsAssignee",opsAssigneeName);
            map.put("$.contractDetail.contractDetailJson.opsQCer",opsQCerName);
            finalRequest = utilities.jsonValueReplacer(jsonObject, map);
            //log.info(new GsonBuilder().setPrettyPrinting().create().toJson(finalRequest));
            
        }catch (Exception e){e.printStackTrace();}
    }

    public void prepareExpectedResultJsonAfterSendingToOpsAssigneeForInvalidTaskId() {
        try {
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\testdata\\sendToOpsAssigneeResponse.json");
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            Map<String, Object> map = new HashMap<>();
            map.put("$.validationResults", null);
            map.put("$.message", "task "+taskId+"9999 doesn't exist");
            JSONObject modifiedJsonObject=utilities.jsonValueReplacer(jsonObject,map);
            List<String> listOfJsonPathsToRemove=new ArrayList<>();
            listOfJsonPathsToRemove.add("$.processInstanceId");
            expectedResponse=utilities.jsonValueRemover(modifiedJsonObject,listOfJsonPathsToRemove);
            //log.info(new GsonBuilder().setPrettyPrinting().create().toJson(expectedResponse));

        }catch (Exception e){e.printStackTrace();}
    }


}
