package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.CreateDrugGroupPojo;
import RequestPojo.UpdateDrugGroupPojo;
import RequestPojo.discardDrugGroup.DiscardDrugGroupPojo;
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

public class UpdateDrugGroup extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(UpdateDrugGroup.class);
    private UpdateDrugGroupPojo updateDrugGroupPojo;
    private CreateDrugGroupPojo createDrugGroupPojo;
    private String mfrDrugListID;
    private String drugGroupName;
    private String drugGroupType;
    private String condition;
    private int drugGroupRowKey;
    private int attempts = 0;


    /**
     * This method is used to create new drug group details request body data
     *
     * @param dataTable
     * @uthor Arun Kumar
     */
    public void createNewDrugGroupDetails(DataTable dataTable) {
        try {
            List<Map<String, String>> drugGroupData = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : drugGroupData) {
                createDrugGroupPojo = new CreateDrugGroupPojo(map.get("mfrId"), map.get("drugGroupName"), map.get("drugGroupType"));
                mfrDrugListID = map.get("mfrId");
                drugGroupName = map.get("drugGroupName");
                drugGroupType = map.get("drugGroupType");
                condition = map.get("condition");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * This method is used to createNewDrugGroup
     *
     * @param endPoint-
     * @param query-
     * @uthor Bharath
     */
    public void createNewDrugGroup(String endPoint, String query) {
        log.info("Create new drug group request & response payload of post operation API");
        String jsonPath="recordSaved";
        response = postOperation(endPoint, createDrugGroupPojo);
        if(condition.equalsIgnoreCase("invalid")){
            response = postOperation(endPoint, createDrugGroupPojo);
        }
        else if (JsonPath.read(response.asString(), jsonPath).equals(Boolean.TRUE)&& condition.equalsIgnoreCase("valid")) {
            List<Object> parameters = new ArrayList<>();
            parameters.add(mfrDrugListID);
            parameters.add(drugGroupType);
            parameters.add(drugGroupName);
            ResultSet rs = dataBaseHelper.replacePathParamsAndExecuteQuery(query, parameters);
            try {
                rs.next();
                drugGroupRowKey = rs.getInt("Row_key");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        } else if (JsonPath.read(response.asString(), jsonPath) == Boolean.valueOf(false) && attempts < 3 && condition.equalsIgnoreCase("valid")) {
            List<Object> parameters = new ArrayList<>();
            parameters.add(mfrDrugListID);
            parameters.add(drugGroupType);
            parameters.add(drugGroupName);
            ResultSet rs = dataBaseHelper.replacePathParamsAndExecuteQuery(query, parameters);
            try {
                rs.next();
                drugGroupRowKey = rs.getInt("Row_key");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            String discardDrugGroupEndpoint = "discardDrugGroup";
            deleteOperation(discardDrugGroupEndpoint, drugGroupRowKey);
            String queryKeyToDeleteDrugGroup = "deleteDrugGroup";
            deleteDrugGroupFromDB(queryKeyToDeleteDrugGroup, String.valueOf(drugGroupRowKey));
            attempts++;
            createNewDrugGroup(endPoint, query);

        }

    }


    /**
     * deleteDrugGroupFromDB method used to delete the drug group detail from data base table by List Name
     *
     * @param deleteDrugGroupQuery
     * @param queryparam
     * @uthor Arun kumar
     */
    public void deleteDrugGroupFromDB(String deleteDrugGroupQuery, String queryparam) {
        log.info("Delete the existing Drug group details from the database");
//        String queryParam = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, String.valueOf(queryparam));
        dataBaseHelper.executeUpdatePreparedQueryAsString(deleteDrugGroupQuery, queryparam);
    }
    /**
     * This method is used to update drug group details request body data
     * @uthor Arun Kumar
     * @param dataTable
     */
    public void updateDrugGroupDetails(DataTable dataTable)
    {
        try {
            List<Map<String, String>> drugGroupData = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : drugGroupData) {
                ResultSet resultSet = dataBaseHelper.executePreparedQuery("getDrugListdetails", drugGroupRowKey);
                resultSet.next();
                String rec_updated_date=resultSet.getString("rec_Updated_Date");
                String instancekey=rec_updated_date.replaceFirst(" ","T");
                String mfrDrugListId=resultSet.getString("MFR_DrugList_ID");
                ResultSet userAssignmentResult = dataBaseHelper.executePreparedQuery("getUserAssignment",drugGroupRowKey );
                userAssignmentResult.next();
                String opsAssignee=userAssignmentResult.getString(getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "opsAssignee"));
               String opsQC=userAssignmentResult.getString(getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "opsQC"));
                updateDrugGroupPojo=new UpdateDrugGroupPojo(drugGroupRowKey,mfrDrugListId,map.get("mfrId"),map.get("drugGroupName"),map.get("drugGroupType"),instancekey,opsAssignee,opsQC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method is used to updateDrugGroupPostCall
     * @uthor Arun Kumar
     * @param endPoint
     */
    public void updateDrugGroupPutCall(String endPoint)
    {
        log.info("Update drug group request & response payload of put operation API");
        response = putOperation(endPoint, updateDrugGroupPojo);
    }

    /**
     * This method is used to validate the status code of updateDrugGroupStatusCode
     * @uthor Arun Kumar
     * @param statusCode
     */
    public void verifyUpdateDrugGroupStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("updateDrugGroup StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * This validateResponseBodyUpdateDrugGroup used to validate the response body value as boolean
     * @uthor Arun Kumar
     * @param actualValue
     * @param expectedValue
     */
    public void validateResponseBodyUpdateDrugGroup(String actualValue,String expectedValue)
    {
        verificationHelperClass.verifyResponseJsonBoolean(response,actualValue,expectedValue);
    }

    /**
     * This validateUpdateDrugGroupResponseByString used to validate the response body value as String
     * @uthor Arun Kumar
     * @param actualValue
     * @param expectedValue
     */
    public void validateUpdateDrugGroupResponseByString(String actualValue,String expectedValue)
    {   String expectedErrorMessage=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,expectedValue);
        verificationHelperClass.verifyResponseJsonString(response,actualValue,expectedErrorMessage);
    }

    /**
     * This method is used to validate Manufacture Drug GroupID
     * @uthor Arun Kumar
     * @param jsonPath
     * @param drugGroupName
     */
    public void verifyUpdateManufactureDrugGroupID(String jsonPath,String drugGroupName){
        try {
            String columnName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "columnNameDrugList_ID");
            ResultSet manufactureDrugListID = dataBaseHelper.executePreparedQuery("getDrugListID", drugGroupName);
            manufactureDrugListID.next();
            int manfDrugListID = Integer.valueOf(manufactureDrugListID.getString(columnName));
            String val = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, jsonPath);
            Object actualValue = JsonPath.read(response.asString(), val);
            Assert.assertEquals("Verify the manfDrugListID",manfDrugListID,actualValue);
            log.info("Verify the manfDrugListID expectedResult="+manfDrugListID+" and actualResulut="+actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used validate verifyUpdateDrugGroupResponseHeaderErrorCode
     * @uthor ArunKumar
     * @param expectedHeaderValue
     */
    public void verifyUpdateDrugGroupResponseHeaderErrorCode(String expectedHeaderValue)
    {
        verificationHelperClass.verifyResponseHeaderApiReturnCodesValue(response,expectedHeaderValue);
    }

    /**
     * This method is used to Deletes the Record from DB
     *
     * @uthor Bharath
     */
    public void deleteDrugGroup(){
        String queryKeyToDeleteDrugGroup = "deleteDrugGroup";
        log.info("deleting row key of DrugGroup is"+drugGroupRowKey);
        deleteDrugGroupFromDB(queryKeyToDeleteDrugGroup, String.valueOf(drugGroupRowKey));
    }

    private String getInstanceKey(String query){

        try {
            ResultSet resultSet=dataBaseHelper.executePreparedQuery(query,drugGroupRowKey);
            resultSet.next();
            String columnLabel=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"instanceKey");
            String recUpdateDate=resultSet.getString(columnLabel);
            String instanceKey=recUpdateDate.replaceFirst(" ","T");
            return instanceKey;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
    /**
     * This method is used to discard the DrugGroup
     *
     * @uthor Bharath
     */
    public void discardDrugGroup() {

        DiscardDrugGroupPojo discardDrugGroupPojo = new DiscardDrugGroupPojo(String.valueOf(drugGroupRowKey), getInstanceKey("getDrugListdetails"), "");
        String discardDrugGroupEndpoint = "discardDrugGroup";
        deleteOperation(discardDrugGroupEndpoint, discardDrugGroupPojo);

    }
}
