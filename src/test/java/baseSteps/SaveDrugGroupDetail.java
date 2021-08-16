package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.UtilitiesClass;
import HelperClass.VerificationHelperClass;
import RequestPojo.SaveDrugGroupDetailPojo.SaveDrugGroupDetailPojoClass;
import TestBase.TestBase;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import java.sql.ResultSet;
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
    int drugGroupRowKey;
    SaveDrugGroupDetailPojoClass saveDrugGroupDetail;
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
                    saveDrugGroupDetail=new SaveDrugGroupDetailPojoClass(drugListId,drugGroupRowKey,ndcs,map.get("startDate"),map.get("endDate"));
                }
                else if(map.get("condition").equalsIgnoreCase("invalidDrugListId"))
                {
                    SaveDrugGroupDetailPojoClass saveDrugGroupDetailobj1=new SaveDrugGroupDetailPojoClass();
                    saveDrugGroupDetailobj1.setNdcs(map.get("query"),map.get("columnName"));
                    ArrayList ndcs = saveDrugGroupDetailobj1.getNdcs();
                    saveDrugGroupDetail=new SaveDrugGroupDetailPojoClass(Integer.valueOf(map.get("drugListId")),drugGroupRowKey,ndcs,map.get("startDate"),map.get("endDate"));
                }
                else if(map.get("condition").equalsIgnoreCase("invalidDrugListRowKey"))
                {
                    SaveDrugGroupDetailPojoClass saveDrugGroupDetailobj1=new SaveDrugGroupDetailPojoClass();
                    saveDrugGroupDetailobj1.setNdcs(map.get("query"),map.get("columnName"));
                    ArrayList ndcs = saveDrugGroupDetailobj1.getNdcs();
                    saveDrugGroupDetail=new SaveDrugGroupDetailPojoClass(drugListId,ndcs,map.get("startDate"),map.get("endDate"));
                }
                else if(map.get("condition").equalsIgnoreCase("invalidNDCS"))
                {
                    SaveDrugGroupDetailPojoClass saveDrugGroupDetailobj1=new SaveDrugGroupDetailPojoClass();
                    saveDrugGroupDetailobj1.setNdcs(map.get("ndcs"));
                    ArrayList ndcs = saveDrugGroupDetailobj1.getNdcs();
                    saveDrugGroupDetail=new SaveDrugGroupDetailPojoClass(drugListId,drugGroupRowKey,ndcs,map.get("startDate"),map.get("endDate"));
                }
                else {
                    SaveDrugGroupDetailPojoClass saveDrugGroupDetailobj1 = new SaveDrugGroupDetailPojoClass();
                    saveDrugGroupDetailobj1.setNdcs(map.get("query"), map.get("columnName"));
                    ArrayList ndcs = saveDrugGroupDetailobj1.getNdcs();
                    saveDrugGroupDetail = new SaveDrugGroupDetailPojoClass(drugListId, drugGroupRowKey, ndcs, utilitiesClass.getStartDate(), utilitiesClass.getEndDate());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get the DrugListId from the drugGroupName
     * @param drugGroupName
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
     * This method is used to get the getDrugGroupRowKey from the drugGroupName
     * @param drugGroupName
     * @Author: Arun Kumar
     */
    public void getDrugGroupRowKey(String query,String drugGroupName) {
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
     * @param endPoint
     */
    public void saveDrugGroupDetailsPostCall(String endPoint)
    {
        log.info("save drug group details request & response payload of post operation API");
        response = postOperation(endPoint, saveDrugGroupDetail);
    }

    /**
     * This method is used to validate the status code of SaveDrugGroupDetailsStatusCode
     * @uthor Arun Kumar
     * @param statusCode
     */
    public void verifySaveDrugGroupDetailsStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("saveDrugGroupDetails StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * @uthour Arun Kumar
     * validating getAllNDCDetailsResponseBody method is used to validate the response body output with database value
     * @param json
     * @param columnName
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
     * @param json
     * @param columnName
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
     * @param actualValue
     * @param expectedValue
     */
    public void validationResultsString(String actualValue,String expectedValue)
    {
        verificationHelperClass.verifyResponseJsonString(response,actualValue,expectedValue);
    }

    /**
     * This validationResultsBoolean used to validate the respanse body value as Boolean
     * @uthor Arun Kumar
     * @param actualValue
     * @param expectedValue
     */
    public void validationResultsBoolean(String actualValue,String expectedValue)
    {
        verificationHelperClass.verifyResponseJsonBoolean(response,actualValue,expectedValue);
    }
}
