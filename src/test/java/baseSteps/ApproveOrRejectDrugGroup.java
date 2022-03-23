package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.UtilitiesClass;
import HelperClass.VerificationHelperClass;
import RequestPojo.discardDrugGroup.DiscardDrugGroupPojo;
import TestBase.TestBase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import java.io.FileReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApproveOrRejectDrugGroup extends TestBase {
    public static Logger log = getMyLogger(SendToQCDrugGroup.class);
    ObjectMapper mapper = new ObjectMapper();
    private String mfrDrugListID;
    private String drugGroupName;
    private String drugGroupType;
    private String drugListID;
    private String procinstanceId;
    private String taskId;
    private String opsAssigneeName;
    private String opsQCName;
    private String instanceKey;
    JSONObject requestPayLoadForCreatDrugGroupAPI;
    JSONObject requestAddingDrugsToDrugGroupAPI;
    JSONObject requestPayloadsendingToQCDrugGroupAPI;
    JSONObject requestPayloadForApproveOrRejectDrugGroupAPI;
    DataBaseHelper dataBaseHelper = new DataBaseHelper();
    Response responseOfDrugGroupCreated;
    Response responseForAddingDrugsToDruGroup;
    Response responseForSendingDrugGroupToQC;
    Response responseForApproveOrRejectDrugGroupAPI;
    DiscardDrugGroupPojo discardDrugGroupPojo;
    private int drugGrouprowKey;
    UtilitiesClass utilityMethods = new UtilitiesClass();
    VerificationHelperClass verificationHelperClass = new VerificationHelperClass();


    /**
     * This method is used to create new drug group details request body data
     *
     * @param dataTable
     * @uthor Bharath
     */
    public JSONObject createNewDrugGroup(DataTable dataTable) {
        try {
            List<Map<String, String>> drugGroupData = dataTable.asMaps(String.class, String.class);
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\testdata\\createDrugGroup.json");
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            Map<String, Object> map = new HashMap<>();
            JsonNode rootNode = mapper.readTree(String.valueOf(jsonObject));
            if (!drugGroupData.get(0).get("mfrId").isEmpty()) {
                map.put("$.mfrId", drugGroupData.get(0).get("mfrId"));
                mfrDrugListID = drugGroupData.get(0).get("mfrId");
            } else {
                mfrDrugListID = rootNode.path("mfrId").textValue();
            }

            if (!drugGroupData.get(0).get("drugGroupName").isEmpty()) {
                map.put("$.drugGroupName", drugGroupData.get(0).get("drugGroupName"));
                drugGroupName = drugGroupData.get(0).get("drugGroupName");
            } else {
                drugGroupName = rootNode.path("drugGroupName").textValue();
            }

            if (!drugGroupData.get(0).get("drugGroupType").isEmpty()) {
                map.put("$.drugGroupType", drugGroupData.get(0).get("drugGroupType"));
                drugGroupType = drugGroupData.get(0).get("drugGroupType");
            } else {
                drugGroupType = rootNode.path("drugGroupType").textValue();
            }

            requestPayLoadForCreatDrugGroupAPI = utilityMethods.jsonValueReplacer(jsonObject, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestPayLoadForCreatDrugGroupAPI;
    }

    /**
     * This method is used to initiateNewManufactureContractPostCall
     *
     * @param endPoint
     * @uthor Bharath
     */
    public void hitCreateDrugGroupAPI(String endPoint) {
        responseOfDrugGroupCreated = postOperation(endPoint, requestPayLoadForCreatDrugGroupAPI);

    }

    /**
     * This method is used to get the rowkey of DrugGroup created
     *
     * @param query
     * @uthor Bharath
     */
    public void getRowKeyAndDrugListIDOfDrugGroupCreated(String query) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(mfrDrugListID);
        parameters.add(drugGroupType);
        parameters.add(drugGroupName);
        try {
            ResultSet rs = dataBaseHelper.replacePathParamsAndExecuteQuery(query, parameters);
            rs.next();
            drugGrouprowKey = rs.getInt("Row_key");
            drugListID = rs.getString("MFR_DrugList_ID");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    /**
     * This method is used to create add drugs to drug group request body data
     *
     * @param dataTable
     * @uthor Bharath
     */
    public JSONObject AddingSomeDrugsToDrugGroupJsonRequest(DataTable dataTable) {
        try {
            List<Map<String, String>> drugGroupData = dataTable.asMaps(String.class, String.class);
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\testdata\\addingDrugsToDrugGroup.json");
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            Map<String, Object> map = new HashMap<>();
            map.put("$.drugListId", drugListID);
            map.put("$.drugGroupRowKey", drugGrouprowKey);
            map.put("$.instanceKey", getInstanceKey("getDrugListdetails"));
            if (!drugGroupData.get(0).get("startDate").isEmpty()) map.put("$.startDate", utilityMethods.getStartDate());
            if (!drugGroupData.get(0).get("endDate").isEmpty()) map.put("$.endDate", utilityMethods.getEndDate());
            if (!drugGroupData.get(0).get("ndcs").isEmpty())
                map.put("$.startDate", UtilitiesClass.constructStringArrayForJsonPayload(drugGroupData.get(0).get("getListOfNdcs")));
            requestAddingDrugsToDrugGroupAPI = utilityMethods.jsonValueReplacer(jsonObject, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestAddingDrugsToDrugGroupAPI;
    }

    /**
     * This method is used to get the instance Key
     *
     * @param query
     * @uthor Bharath
     */
    private String getInstanceKey(String query) {

        try {
            String columnLabel = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "instanceKey");
            ResultSet rsc = dataBaseHelper.executePreparedQuery(query, drugGrouprowKey);
            rsc.next();
            String recUpdateDate = rsc.getString(columnLabel);
            instanceKey = recUpdateDate.replaceFirst(" ", "T");

            return instanceKey;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to add drugs to drug group
     *
     * @param endPoint
     * @uthor Bharath
     */
    public void hitAddDrugsToDrugGroupAPI(String endPoint) {
        responseForAddingDrugsToDruGroup = postOperation(endPoint, requestAddingDrugsToDrugGroupAPI);

    }

    /**
     * This method is used to create the Request payload for DruGroup API
     *
     * @param dataTable
     * @uthor Bharath
     */
    public void createSendToQCJsonData(DataTable dataTable) {
        try {

            List<Map<String, String>> drugGroupDetaildata = dataTable.asMaps(String.class, String.class);
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\testdata\\sendToQCDrugGroup.json");
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            Map<String, Object> map = new HashMap<>();
            JsonNode rootNode = mapper.readTree(String.valueOf(jsonObject));
            map.put("$.drugGroupRowKey", drugGrouprowKey);
            map.put("$.mfrDrugListId", drugListID);
            map.put("$.mfrId", mfrDrugListID);
            map.put("$.drugGroupName", drugGroupName);
            map.put("$.drugGroupType", drugGroupType);
            if (!drugGroupDetaildata.get(0).get("opsAssignee").isEmpty()) {
                map.put("$.opsAssignee", drugGroupDetaildata.get(0).get("opsAssignee"));
                opsAssigneeName = drugGroupDetaildata.get(0).get("opsAssignee");
            } else {
                opsAssigneeName = rootNode.path("opsAssignee").textValue();
            }
            if (!drugGroupDetaildata.get(0).get("opsQc").isEmpty()) {
                map.put("$.opsQc", drugGroupDetaildata.get(0).get("opsQc"));
                opsQCName = drugGroupDetaildata.get(0).get("opsQc");
            } else {
                opsQCName = rootNode.path("opsQc").textValue();
            }
            map.put("$.instanceKey", getInstanceKey("getDrugListdetails"));
            requestPayloadsendingToQCDrugGroupAPI = utilityMethods.jsonValueReplacer(jsonObject, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getProcessInstanceAndtaskIdForDrugGroup() {
        try {
            dataBaseHelper.connectToOtherDB("flowable");
            ResultSet procInstanceResultset = dataBaseHelper.executePreparedQuery("getProcInstanceIdForDrugGroupCreated", drugGrouprowKey);
            procInstanceResultset.next();
            procinstanceId = procInstanceResultset.getString("PROC_INST_ID_");
            ResultSet taskIdResultset = dataBaseHelper.executePreparedQuery("getTaskId", procinstanceId);
            taskIdResultset.next();
            taskId = taskIdResultset.getString("ID_");
            dataBaseHelper.disConnectToOtherDB();
            return taskId;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataBaseHelper.disConnectToOtherDB();
        }
        return null;
    }

    /**
     * deleteDrugGroupFromDB method used to delete the drug group detail from data base table by List Name
     *
     * @param deleteDrugGroupQuery
     * @param queryparam
     * @uthor Bharath
     */
    public void deleteDrugGroupFromDB(String deleteDrugGroupQuery, String queryparam) {
        log.info("Delete the existing Drug group details from the database");
//        String queryParam = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, String.valueOf(queryparam));
        dataBaseHelper.executeUpdatePreparedQueryAsString(deleteDrugGroupQuery, queryparam);
    }

    /**
     * This Method is used to Hit the End point Send to QC DrugGroup API
     *
     * @param endpoint
     * @uthor Bharath
     */
    public void hitSendToQCForDrugGroupAPI(String endpoint) {
        responseForSendingDrugGroupToQC = putOperation(endpoint, requestPayloadsendingToQCDrugGroupAPI);

    }


    /**
     * This method is used to validate the status code of InitiateNewManufactureContractStatusCode
     *
     * @param statusCode
     * @uthor Arun Kumar
     * @ModifiedBy Gudi
     */
    public void verifyStatusCode(int statusCode) {
        try {
            boolean status = verificationHelperClass.verifyStatusCode(responseForSendingDrugGroupToQC, statusCode);
            log.info("Approve Or reject API StatusCode is " + statusCode + " and its Pass");

            if (status == false) {
//                discardDrugGroup();
                deleteDrugGroupAndDrugGroupDetails();
                Assert.assertTrue(false, "Validation was failed. Hence stopping the scenario: '" + scenarioName + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to discard the DrugGroup
     *
     * @uthor Bharath
     */
    public void discardDrugGroup() {
        getProcessInstanceAndtaskIdForDrugGroup();
        discardDrugGroupPojo = new DiscardDrugGroupPojo(String.valueOf(drugGrouprowKey), instanceKey, taskId);
        String discardDrugGroupEndpoint = "discardDrugGroup";
        deleteOperation(discardDrugGroupEndpoint, discardDrugGroupPojo);

    }

    /**
     * This method is used to Deletes the Record from DB
     *
     * @uthor Bharath
     */
    public void deleteDrugGroupAndDrugGroupDetails() {
        String queryKeyToDeleteDrugGroup = "deleteDrugGroup";
        String queryKeyToDeleteDruGroupDetails = "deleteDrugGroupDetails";
        log.info("deleting row key of DrugGroup is" + drugGrouprowKey);
        deleteDrugGroupFromDB(queryKeyToDeleteDrugGroup, String.valueOf(drugGrouprowKey));
        deleteDrugGroupFromDB(queryKeyToDeleteDruGroupDetails, drugListID);
    }


    /**
     * This method is used to
     *
     * @uthor Bharath
     */
    public void createdataForApproveOrrejectDrugGroup(DataTable dataTable) {
        try {
            List<Map<String, String>> drugGroupDetaildata = dataTable.asMaps(String.class, String.class);
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\testdata\\approveOrRejectDrugGroup.json");
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            Map<String, Object> map = new HashMap<>();
            map.put("$.drugGroupRowKey", drugGrouprowKey);
            map.put("$.mfrDrugListId", drugListID);
            map.put("$.mfrId", mfrDrugListID);
            map.put("$.drugGroupName", drugGroupName);
            map.put("$.drugGroupType", drugGroupType);
            map.put("$.opsAssignee", opsAssigneeName);
            map.put("$.opsQc", opsQCName);
            map.put("$.instanceKey", getInstanceKey("getDrugListdetails"));
            map.put("$.workFlowRequestInfoDTO.taskId", taskId);
            if (!drugGroupDetaildata.get(0).get("ApproveorrejectFlag").isEmpty())
                map.put("$.workFlowRequestInfoDTO.approve", drugGroupDetaildata.get(0).get("ApproveorrejectFlag"));
            requestPayloadForApproveOrRejectDrugGroupAPI = utilityMethods.jsonValueReplacer(jsonObject, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hitApproveOrRejectDrugGroupAPI(String endpoint) {
        responseForApproveOrRejectDrugGroupAPI = postOperation(endpoint, requestPayloadForApproveOrRejectDrugGroupAPI);
    }

    public void verifyLifeCycleStatus(String lifeCycleStatus) {
        try {
            String columnLabel = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "getDrugGroupLifeCycleStatus");
            String queryKey = "getDrugListdetails";
            ResultSet rsc = dataBaseHelper.executePreparedQuery(queryKey, drugGrouprowKey);
            rsc.next();
            String dbLifeCycleStatus = rsc.getString(columnLabel);
            boolean status = verificationHelperClass.compareTwoStrings(dbLifeCycleStatus, lifeCycleStatus);
            if (status == false) {
                discardDrugGroup();
                deleteDrugGroupAndDrugGroupDetails();
                Assert.assertTrue(false, "Validation was failed. Hence stopping the scenario: '" + scenarioName + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
