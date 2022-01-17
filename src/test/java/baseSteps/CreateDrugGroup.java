package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.*;
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

public class CreateDrugGroup extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper = new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(CreateDrugGroup.class);
    private String mfrDrugListID;
    private String drugGroupName;
    private String drugGroupType;
    private int drugGrouprowKey;
    private String condition;
    private int attempts = 0;
    private ResultSet result;
    private String mfrDrugListId;
    private String instanceKey;
    private DiscardDrugGroupPojo discardDrugGroupPojo;

    CreateDrugGroupPojo createDrugGroupPojo;

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
                drugGrouprowKey = rs.getInt("Row_key");
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
                drugGrouprowKey = rs.getInt("Row_key");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            String discardDrugGroupEndpoint = "discardDrugGroup";
            deleteOperation(discardDrugGroupEndpoint, drugGrouprowKey);
            String queryKeyToDeleteDrugGroup = "deleteDrugGroup";
            deleteDrugGroupFromDB(queryKeyToDeleteDrugGroup, String.valueOf(drugGrouprowKey));
            attempts++;
            createNewDrugGroup(endPoint, query);

        }

    }

    /**
     * This method is used to validate the status code of CreateNewDrugGroupStatusCode
     *
     * @param statusCode
     * @uthor Arun Kumar
     */
    public void verifyCreateNewDrugGroupStatusCode(int statusCode) {
        verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("CreateNewDrugGroup StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * This validateResponseBodyCreateDrugGroup used to validate the response body value as boolean
     *
     * @param actualValue
     * @param expectedValue
     * @uthor Arun Kumar
     */
    public void validateResponseBodyCreateDrugGroup(String actualValue, String expectedValue) {
        verificationHelperClass.verifyResponseJsonBoolean(response, actualValue, expectedValue);
    }

    /**
     * This validateCreateDrugGroupResponseByString used to validate the response body value as String
     *
     * @param actualValue
     * @param expectedValue
     * @uthor Arun Kumar
     */
    public void validateCreateDrugGroupResponseByString(String actualValue, String expectedValue) {
        verificationHelperClass.verifyResponseJsonString(response, actualValue, expectedValue);
    }

    /**
     * This method is used to validate Manufacture Drug GroupID
     * @param jsonPath
     * @param query
     * @uthor Arun Kumar
     */
    public void verifyManufactureDrugGroupID(String jsonPath, String query) {
        try {
            String columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "columnNameDrugList_ID");
            List<Object> parameters = new ArrayList<>();
            parameters.add(mfrDrugListID);
            parameters.add(drugGroupType);
            parameters.add(drugGroupName);
            ResultSet manufactureDrugListID = dataBaseHelper.replacePathParamsAndExecuteQuery(query, parameters);
            manufactureDrugListID.next();
            int manfDrugListID = Integer.valueOf(manufactureDrugListID.getString(columnName));
            String val = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, jsonPath);
            Object actualValue = JsonPath.read(response.asString(), val);
            Assert.assertEquals("Verify the manfDrugListID", manfDrugListID, actualValue);
            log.info("Verify the manfDrugListID expectedResult=" + manfDrugListID + " and actualResulut=" + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used validate verifyCreateDrugGroupResponseHeaderErrorCode
     *
     * @param expectedHeaderValue
     * @uthor ArunKumar
     */
    public void verifyCreateDrugGroupResponseHeaderErrorCode(String expectedHeaderValue) {
        verificationHelperClass.verifyResponseHeaderApiReturnCodesValue(response, expectedHeaderValue);
    }

    /**
     * This method retrieves the rowKey of a drug group as per the supplied query  from  DB
     * @author Smruti
     * @param query
     * @exception SQLException
     */

    public  void getDrugGroupRowKey(String query) {
        try {
            log.info("query is "+query);
            result = dataBaseHelper.getData(query);
            result.next();
            String drugGroupRowKey=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "discardDrugGroupRowKey");
            String mfrDLId=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "discardDrugGroupdrugListId");
            String recordUpdatedDate=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "instanceKey");
            mfrDrugListId=result.getString(mfrDLId);
            String updatedDate=result.getString(recordUpdatedDate);
            instanceKey=updatedDate.replaceFirst(" ","T");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is used to discard the DrugGroup
     *
     * @uthor Bharath
     */
    public void discardDrugGroup() {
        discardDrugGroupPojo=new DiscardDrugGroupPojo(String.valueOf(drugGrouprowKey),instanceKey,"") ;
        String discardDrugGroupEndpoint = "discardDrugGroup";
        deleteOperation(discardDrugGroupEndpoint, discardDrugGroupPojo);

    }

    /**
     * This method is used to Deletes the Record from DB
     *
     * @uthor Bharath
     */
    public void deleteDrugGroup(){
        String queryKeyToDeleteDrugGroup = "deleteDrugGroup";
        log.info("deleting row key of DrugGroup is"+drugGrouprowKey);
        deleteDrugGroupFromDB(queryKeyToDeleteDrugGroup, String.valueOf(drugGrouprowKey));
    }

}
