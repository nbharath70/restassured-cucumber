package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.UpdateDrugGroupPojo;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class UpdateDrugGroup extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(UpdateDrugGroup.class);
    UpdateDrugGroupPojo updateDrugGroupPojo;
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
                String columnName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "columnNameDrugList_ID");
                ResultSet manufactureDrugListID = dataBaseHelper.executePreparedQuery("getDrugListID", map.get("oldDrugGroupName"));
                manufactureDrugListID.next();
                int manfDrugListID = Integer.valueOf(manufactureDrugListID.getString(columnName));
                ResultSet getDrugListRowKey = dataBaseHelper.executePreparedQuery("getDrugListRowKey", map.get("oldDrugGroupName"));
                getDrugListRowKey.next();
                int rowKey = Integer.valueOf(getDrugListRowKey.getString("Row_Key"));
                updateDrugGroupPojo=new UpdateDrugGroupPojo(rowKey, manfDrugListID,map.get("mfrId"),map.get("drugGroupName"),map.get("drugGroupType"));
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
    {
        verificationHelperClass.verifyResponseJsonString(response,actualValue,expectedValue);
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
}
