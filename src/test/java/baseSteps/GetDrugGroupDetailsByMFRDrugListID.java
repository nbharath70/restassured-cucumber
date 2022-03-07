package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

public class GetDrugGroupDetailsByMFRDrugListID extends TestBase {
    DataBaseHelper dbHelper=new DataBaseHelper();
    VerificationHelperClass verificationHelper=new VerificationHelperClass();
    public static Logger log= TestBase.getMyLogger(GetDrugGroupDetailsByMFRDrugListID.class);
    private String manfDrugListRowKey=null;
    public Response response;
    public String drugDetailsJson=null;

    /**
     * This method is used get Gets top MFRDrugListID  from MFR_DrugList_Detail table from database
     * @uthor Arun Kumar
     * @param queryKey
     * @param columnKey
     */
    public void userExecutesQueryAndGetsMFRDrugListRowKey(String queryKey, String columnKey)
    {
        String columnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnKey);
        manfDrugListRowKey=dbHelper.getSingleCellValueAsStringFromDB(queryKey, columnName);
        log.info("Manufacture DrugListID picked from DB: " + manfDrugListRowKey);
    }

    /**
     * This method hit the getcall of Drug group details
     * @uthor Arun Kumar
     * @param endPointKey
     */
    public void userHitsAPI(String endPointKey) {
        log.info("Drug group details GET operation API");
        response = getCall(endPointKey, manfDrugListRowKey);
    }

    /**
     * verifyStatusCodeReturnedForDrugGroupDetailsAPI method is used to validate the status code  drug group details API
     * @uthor Arun Kumar
     * @param statusCode
     */
    public void verifyStatusCodeReturnedForDrugGroupDetailsAPI(int statusCode) {
        verificationHelper.verifyStatusCode(response,statusCode);
        log.info("Get drug group details StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * This is used validate the response body from DrugGroupDetails API with DB
     * @uthor Arun Kumar
     * @param columnName
     * @param actualJsonPathAndColumnName
     */
    public void validationOfDrugGroupDetails(String columnName,String actualJsonPathAndColumnName) {
        try {
            String jsonPathAndColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, actualJsonPathAndColumnName);
            String dbColumn = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnName);
            String query = "select " + dbColumn + " from [cfg].[MFR_DrugList_Detail] DD inner join [ref].[Drug_Product] DP  ON DD.Drug_Product_Code=DP.DRUG_PRODUCT_CODE where DD.MFR_DrugList_ID='" + manfDrugListRowKey + "'";
            log.info(query);
            ArrayList<String> actualValue = JsonPath.read(response.asString(), jsonPathAndColumnName);
            ArrayList<String> expectedValue = dbHelper.getDataColumnArrayListValueDBWithoutKey(query, actualJsonPathAndColumnName);
            Collections.sort(actualValue);
            Collections.sort(expectedValue);
            log.info("expectedValue from DB" + expectedValue + " And actualValue from Json response=" + actualValue);
            Assert.assertTrue("The lists do not match!", expectedValue.equals(actualValue));
            log.info("Verification pass where expectedValue=" + expectedValue + " equals to actualValue=" + actualValue);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void executeQueryToGetDrugDetails(String queryKey) {
        try {
            ResultSet rs = dbHelper.executePreparedQuery(queryKey, manfDrugListRowKey);
            rs.next();
            drugDetailsJson = rs.getString("result");
        }catch (Exception e){
            e.printStackTrace();
        }

//        drugDetailsJson = dbHelper.getSingleCellValueAsStringFromDB(queryKey, "result");
    }

    public void verifyDrugDetailsByDrugGroupRowKeyAPIResponseWithDBResponse() {
        log.info("Response from API: "+response.asString());
        log.info("Response from DB : "+drugDetailsJson);
        io.restassured.path.json.JsonPath js= response.jsonPath();

        verificationHelper.compareTwoStrings(response.asString(), drugDetailsJson);
    }

    public void userHitsAPIWithInvalidDrugListRowKey(String endPointKey) {
        //invalid rowkey considered is 111001100
        response = getCall(endPointKey, "111001100");
    }

    public void executeQueryWithInvalidDrugListRowKeyAndGetsDrugDetails(String queryKey) {
        try {
            //invalid rowkey considered is 111001100
            ResultSet rs = dbHelper.executePreparedQuery(queryKey, "111001100");
            rs.next();
            drugDetailsJson = rs.getString("result");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
