package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.*;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class CreateDrugGroup extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(CreateDrugGroup.class);
    CreateDrugGroupPojo createDrugGroupPojo;

    /**
     * This method is used to create new drug group details request body data
     * @uthor Arun Kumar
     * @param dataTable
     */
    public void createNewDrugGroupDetails(DataTable dataTable)
    {
        try {
            List<Map<String, String>> drugGroupData = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : drugGroupData) {
                createDrugGroupPojo=new CreateDrugGroupPojo(map.get("mfrId"),map.get("drugGroupName"),map.get("drugGroupType"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     * deleteDrugGroupFromDB method used to delete the drug group detail from data base table by List Name
     * @uthor Arun kumar
     * @param deleteDrugGroupQuery
     * @param colName
     */
    public void deleteDrugGroupFromDB(String deleteDrugGroupQuery,String colName)
    {
        log.info("Delete the existing Drug group details from the database");
        dataBaseHelper.executeUpdatePreparedQueryAsString(deleteDrugGroupQuery,colName);
    }

    /**
     * This method is used to createNewDrugGroupPostCall
     * @uthor Arun Kumar
     * @param endPoint
     */
    public void createNewDrugGroupPostCall(String endPoint)
    {
        log.info("Create new drug group request & response payload of post operation API");
        response = postOperation(endPoint, createDrugGroupPojo);
    }

    /**
     * This method is used to validate the status code of CreateNewDrugGroupStatusCode
     * @uthor Arun Kumar
     * @param statusCode
     */
    public void verifyCreateNewDrugGroupStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("CreateNewDrugGroup StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * This validateResponseBodyCreateDrugGroup used to validate the response body value as boolean
     * @uthor Arun Kumar
     * @param actualValue
     * @param expectedValue
     */
    public void validateResponseBodyCreateDrugGroup(String actualValue,String expectedValue)
    {
        verificationHelperClass.verifyResponseJsonBoolean(response,actualValue,expectedValue);
    }

    /**
     * This validateCreateDrugGroupResponseByString used to validate the response body value as String
     * @uthor Arun Kumar
     * @param actualValue
     * @param expectedValue
     */
    public void validateCreateDrugGroupResponseByString(String actualValue,String expectedValue)
    {
        verificationHelperClass.verifyResponseJsonString(response,actualValue,expectedValue);
    }

    /**
     * This method is used to validate Manufacture Drug GroupID
     * @uthor Arun Kumar
     * @param jsonPath
     * @param query
     */
    public void verifyManufactureDrugGroupID(String jsonPath,String query){
        try {
            String columnName = getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "columnNameDrugList_ID");
            ResultSet manufactureDrugListID = dataBaseHelper.executePreparedQuery("getDrugListID", query);
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
     * This method is used validate verifyCreateDrugGroupResponseHeaderErrorCode
     * @uthor ArunKumar
     * @param expectedHeaderValue
     */
    public void verifyCreateDrugGroupResponseHeaderErrorCode(String expectedHeaderValue)
    {
        verificationHelperClass.verifyResponseHeaderApiReturnCodesValue(response,expectedHeaderValue);
    }

}
