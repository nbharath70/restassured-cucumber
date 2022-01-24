package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.UtilitiesClass;
import HelperClass.VerificationHelperClass;
import RequestPojo.CreateDrugGroupPojo;
import RequestPojo.SaveDrugGroupDetailPojo.SaveDrugGroupDetailPojoClass;
import RequestPojo.discardDrugGroup.DiscardDrugGroupPojo;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SaveDrugGroupDetail extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper = new DataBaseHelper();
    UtilitiesClass utilitiesClass =new UtilitiesClass();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(SaveDrugGroupDetail.class);
    int drugListId;
    private String invalidDrugListID;
    private String mfrDrugListID;
    private String drugGroupName;
    private String drugGroupType;
    private int drugGroupRowKey;
    private String condition;
    private int attempts = 0;
    private String instanceKey;
    SaveDrugGroupDetailPojoClass saveDrugGroupDetail;
    CreateDrugGroupPojo createDrugGroupPojo;
    DiscardDrugGroupPojo discardDrugGroupPojo;


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

//    private void getDrugGroupDetails() {
//        List<Object> parameters = new ArrayList<>();
//        parameters.add(mfrDrugListID);
//        parameters.add(drugGroupName);
//        parameters.a
//
//        dataBaseHelper.executePreparedQuery()
//
//    }

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
                drugListId=Integer.valueOf(rs.getString("MFR_DrugList_ID"));

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
                drugListId=Integer.valueOf(rs.getString("MFR_DrugList_ID"));
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
     * saveDrugGroupDetailData Method is used to add drug to drug group by NDC value
     * @param dataTable
     * @uthor Arun Kumar
     */
    public void saveDrugGroupDetailData(DataTable dataTable) {
        try {
            List<Map<String, String>> saveDrugGroupData = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : saveDrugGroupData) {
                if(map.get("condition").equalsIgnoreCase("nonOverLappingDate"))
                {
                    SaveDrugGroupDetailPojoClass saveDrugGroupDetailobj1=new SaveDrugGroupDetailPojoClass();
                    saveDrugGroupDetailobj1.setNdcs(map.get("query"),map.get("columnName"));
                    ArrayList ndcs = saveDrugGroupDetailobj1.getNdcs();
                    saveDrugGroupDetail=new SaveDrugGroupDetailPojoClass(drugListId,drugGroupRowKey,ndcs,map.get("startDate"),map.get("endDate"),getInstanceKey("getDrugListdetails"));

                }
                else if(map.get("condition").equalsIgnoreCase("invalidDrugListId"))
                {
                    SaveDrugGroupDetailPojoClass saveDrugGroupDetailobj1=new SaveDrugGroupDetailPojoClass();
                    saveDrugGroupDetailobj1.setNdcs(map.get("query"),map.get("columnName"));
                    ArrayList ndcs = saveDrugGroupDetailobj1.getNdcs();
                    invalidDrugListID=map.get("drugListId");
                    saveDrugGroupDetail=new SaveDrugGroupDetailPojoClass(Integer.valueOf(map.get("drugListId")),drugGroupRowKey,ndcs,map.get("startDate"),map.get("endDate"),getInstanceKey("getDrugListdetails"));
                }
                else if(map.get("condition").equalsIgnoreCase("invalidDrugListRowKey"))
                {
                    SaveDrugGroupDetailPojoClass saveDrugGroupDetailobj1=new SaveDrugGroupDetailPojoClass();
                    saveDrugGroupDetailobj1.setNdcs(map.get("query"),map.get("columnName"));
                    ArrayList ndcs = saveDrugGroupDetailobj1.getNdcs();
                    saveDrugGroupDetail=new SaveDrugGroupDetailPojoClass(drugListId,ndcs,map.get("startDate"),map.get("endDate"),getInstanceKey("getDrugListdetails"));
                }
                else if(map.get("condition").equalsIgnoreCase("invalidNDCS"))
                {
                    SaveDrugGroupDetailPojoClass saveDrugGroupDetailobj1=new SaveDrugGroupDetailPojoClass();
                    saveDrugGroupDetailobj1.setNdcs(map.get("ndcs"));
                    ArrayList ndcs = saveDrugGroupDetailobj1.getNdcs();
                    saveDrugGroupDetail=new SaveDrugGroupDetailPojoClass(drugListId,drugGroupRowKey,ndcs,map.get("startDate"),map.get("endDate"),getInstanceKey("getDrugListdetails"));
                }
                else {
                    SaveDrugGroupDetailPojoClass saveDrugGroupDetailobj1 = new SaveDrugGroupDetailPojoClass();
                    saveDrugGroupDetailobj1.setNdcs(map.get("query"), map.get("columnName"));
                    ArrayList ndcs = saveDrugGroupDetailobj1.getNdcs();
                    saveDrugGroupDetail = new SaveDrugGroupDetailPojoClass(drugListId, drugGroupRowKey, ndcs, utilitiesClass.getStartDate(), utilitiesClass.getEndDate(),getInstanceKey("getDrugListdetails"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get the DrugListId from the drugGroupName
     * @param drugGroupName-
     * @Author: Arun Kumar
     */
    public void getDrugListId(String query,String drugGroupName) {
        try {
            ResultSet getDrugListIdValue = dataBaseHelper.executePreparedQuery(query, drugGroupName);
            getDrugListIdValue.next();
            drugListId = Integer.valueOf(getDrugListIdValue.getString("MFR_DrugList_ID"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get the getdrugGroupRowKey from the drugGroupName
     * @param drugGroupName-
     * @Author: Arun Kumar
     */
    public void getdrugGroupRowKey(String query,String drugGroupName) {
        try {
            ResultSet getRowKeyDrugGroup = dataBaseHelper.executePreparedQuery(query, drugGroupName);
            getRowKeyDrugGroup.next();
            drugGroupRowKey = Integer.valueOf(getRowKeyDrugGroup.getString("Row_Key"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to saveDrugGroupDetailsPostCall
     * @uthor Arun Kumar
     * @param endPoint-
     */
    public void saveDrugGroupDetailsPostCall(String endPoint)
    {
        log.info("save drug group details request & response payload of post operation API");
        response = postOperation(endPoint, saveDrugGroupDetail);
    }

    /**
     * This method is used to validate the status code of SaveDrugGroupDetailsStatusCode
     * @uthor Arun Kumar
     * @param statusCode-
     */
    public void verifySaveDrugGroupDetailsStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("saveDrugGroupDetails StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * @uthour Arun Kumar
     * validating getAllNDCDetailsResponseBody method is used to validate the response body output with database value
     * @param json-
     * @param columnName-
     */
    public void verifyNDCDetails(String json,String columnName)
    {

        String getDrugProductQuery="select Drug_Product_Code from [cfg].[MFR_DrugList_Detail] where MFR_DrugList_ID="+drugListId+" and Is_Current_Flag=1";
        log.info("Validating getAll NDC details Response with query as:"+getDrugProductQuery);
        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, json);
        String dbColumn = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnName);
        verificationHelperClass.verifyResponseJsonAndDbArrayByColumnNameWithoutPropertiesKey(response,jsonPath,getDrugProductQuery,dbColumn);
    }

    /**
     * @uthour Arun Kumar
     * validating getAllNDCDetailsRowKeyResponseBody method is used to validate the response body output with database value
     * @param json-
     * @param columnName-
     */
    public void verifyNDCDetailsRowKey(String json,String columnName)
    {
        String getNDCDetailsRowKeyQuery="select Row_Key from [cfg].[MFR_DrugList_Detail] where MFR_DrugList_ID="+drugListId+" and Is_Current_Flag=1";
        log.info("Validating getAll NDC Row Key details Response with query as:"+getNDCDetailsRowKeyQuery);
        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, json);
        String dbColumn = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnName);
        verificationHelperClass.verifyResponseJsonAndDbArrayByColumnNameWithoutPropertiesKey(response,jsonPath,getNDCDetailsRowKeyQuery,dbColumn);
    }
    /**
     * This validationResultsString used to validate the respanse body value as String
     * @uthor Arun Kumar
     * @param actualValue-
     * @param expectedValue-
     */
    public void validationResultsString(String actualValue,String expectedValue)
    {   String invalidMessage=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,expectedValue);
        verificationHelperClass.verifyResponseJsonString(response,actualValue,invalidMessage);
    }

    /**
     * This validationResultsBoolean used to validate the respanse body value as Boolean
     * @uthor Arun Kumar
     * @param actualValue-
     * @param expectedValue-
     */
    public void validationResultsBoolean(String actualValue,String expectedValue)
    {
        verificationHelperClass.verifyResponseJsonBoolean(response,actualValue,expectedValue);
    }

    /**
     * This validationResultsBoolean used to validate the respanse body value as Boolean
     * @uthor Arun Kumar
     * @param jsonPath-
     * @param messageKey-
     */
    public void validateInvalidResponseDynamically(String jsonPath,String messageKey){
        String invalidMessagetoReplace=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,messageKey);
        String actualErrorMessage=invalidMessagetoReplace.replace("{pathparam}",invalidDrugListID);
        verificationHelperClass.verifyResponseJsonString(response,jsonPath,actualErrorMessage);

    }
    /**
     * This method is used to discard the DrugGroup
     *
     * @uthor Bharath
     */
    public void discardDrugGroup() {
        discardDrugGroupPojo=new DiscardDrugGroupPojo(String.valueOf(drugGroupRowKey),getInstanceKey("getDrugListdetails"),"") ;
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

}
