package baseSteps;

import HelperClass.DataBaseHelper;
import RequestPojo.CreateDrugGroupPojo;
import RequestPojo.SaveDrugGroupDetailPojo.SaveDrugGroupDetailPojoClass;
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

public class SendToOpsAssigneeDrugGroup extends TestBase {
    public static Logger log = getMyLogger(SendToOpsAssigneeDrugGroup.class);
    private String mfrDrugListID;
    private String drugGroupName;
    private String drugGroupType;
    private String createDrugGroupEndpointkey;
    private String condition;
    private int drugGroupRowKey;
    private int attempts = 0;
    private String instanceKey;
    private int drugListId;
    DataBaseHelper dataBaseHelper;
    Response responseOfDrugGroupCreated;
    Response responseForAddingDrugsToDruGroup;
    private int drugGrouprowKey;
    CreateDrugGroupPojo createDrugGroupPojo;
    SaveDrugGroupDetailPojoClass addingDrugsToDruGroupPojo;

    /**
     * This method is used to create new drug group details request body data
     *
     * @param dataTable
     * @uthor Bharath
     */
    public void createNewDrugGroup(DataTable dataTable) {
        try {
            List<Map<String, String>> drugGroupData = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : drugGroupData) {
                createDrugGroupPojo = new CreateDrugGroupPojo(map.get("mfrId"), map.get("drugGroupName"), map.get("drugGroupType"));
                mfrDrugListID = map.get("mfrId");
                drugGroupName = map.get("drugGroupName");
                drugGroupType = map.get("drugGroupType");
                condition = map.get("condition");
                createDrugGroupEndpointkey = map.get("endpoint");
            }
            responseOfDrugGroupCreated = postOperation(createDrugGroupEndpointkey, createDrugGroupPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    /**
//     * This method is used to createNewDrugGroup
//     *
//     * @param endPoint-
//     * @param query-
//     * @uthor Bharath
//     */
//    public void createNewDrugGroup(String endPoint, String query) {
//        log.info("Create new drug group request & response payload of post operation API");
//        String jsonPath="recordSaved";
//        responseOfDrugGroupCreated = postOperation(endPoint, createDrugGroupPojo);
//        if(condition.equalsIgnoreCase("invalid")){
//            responseOfDrugGroupCreated = postOperation(endPoint, createDrugGroupPojo);
//        }
//        else if (JsonPath.read(responseOfDrugGroupCreated.asString(), jsonPath).equals(Boolean.TRUE)&& condition.equalsIgnoreCase("valid")) {
//            List<Object> parameters = new ArrayList<>();
//            parameters.add(mfrDrugListID);
//            parameters.add(drugGroupType);
//            parameters.add(drugGroupName);
//            ResultSet rs = dataBaseHelper.replacePathParamsAndExecuteQuery(query, parameters);
//            try {
//                rs.next();
//                drugGroupRowKey = rs.getInt("Row_key");
//                drugListId=Integer.valueOf(rs.getString("MFR_DrugList_ID"));
//
//            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
//            }
//        } else if (JsonPath.read(responseOfDrugGroupCreated.asString(), jsonPath) == Boolean.valueOf(false) && attempts < 3 && condition.equalsIgnoreCase("valid")) {
//            List<Object> parameters = new ArrayList<>();
//            parameters.add(mfrDrugListID);
//            parameters.add(drugGroupType);
//            parameters.add(drugGroupName);
//            ResultSet rs = dataBaseHelper.replacePathParamsAndExecuteQuery(query, parameters);
//            try {
//                rs.next();
//                drugGroupRowKey = rs.getInt("Row_key");
//                drugListId=Integer.valueOf(rs.getString("MFR_DrugList_ID"));
//            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
//            }
//            String discardDrugGroupEndpoint = "discardDrugGroup";
//            deleteOperation(discardDrugGroupEndpoint, drugGroupRowKey);
//            String queryKeyToDeleteDrugGroup = "deleteDrugGroup";
//            deleteDrugGroupFromDB(queryKeyToDeleteDrugGroup, String.valueOf(drugGroupRowKey));
//            attempts++;
//            createNewDrugGroup(endPoint, query);
//
//        }
//
//    }

//
//    /**
//     *This method is used adding some drugs to druGroup
//     * @uthor Bharath
//     */
//    public void addingSomeDrugsToDruGroup(DataTable dataTable) {
//        List<Map<String, String>> saveDrugGroupData = dataTable.asMaps(String.class, String.class);
//        for (Map<String, String> map : saveDrugGroupData) {
//            if (map.get("condition").equalsIgnoreCase("nonOverLappingDate")) {
//                SaveDrugGroupDetailPojoClass saveDrugGroupDetailobj1 = new SaveDrugGroupDetailPojoClass();
//                saveDrugGroupDetailobj1.setNdcs(map.get("query"), map.get("columnName"));
//                ArrayList ndcs = saveDrugGroupDetailobj1.getNdcs();
//                addingDrugsToDruGroupPojo = new SaveDrugGroupDetailPojoClass(drugListId, drugGroupRowKey, ndcs, map.get("startDate"), map.get("endDate"), getInstanceKey("getDrugListdetails"));
//
//            }
//        }
//    /**
//     * deleteDrugGroupFromDB method used to delete the drug group detail from data base table by List Name
//     *
//     * @param deleteDrugGroupQuery
//     * @param queryparam
//     * @uthor Bharath
//     */
//    public void deleteDrugGroupFromDB(String deleteDrugGroupQuery, String queryparam) {
//        log.info("Delete the existing Drug group details from the database");
////        String queryParam = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, String.valueOf(queryparam));
//        dataBaseHelper.executeUpdatePreparedQueryAsString(deleteDrugGroupQuery, queryparam);
//    }


}
