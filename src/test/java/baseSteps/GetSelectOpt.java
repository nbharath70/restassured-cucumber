package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.sql.ResultSet;

public class GetSelectOpt extends TestBase {
    public static Logger log = getMyLogger(GetSelectOpt.class);
    public static Response getSelectOptionsResponse;
    public String jsonPath;
    public String columnName;
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    ResultSet resultSet;
    /**
     * @param endpoint
     * @uthour Arun Kumar
     * This method hits getSelectOptionsResource the end point and logs the response and also verify json formate type
     */
    public void getSelectOptionsResource(String endpoint) {
        getSelectOptionsResponse = getCall(endpoint);
    }

    /**
     * getSelectOptionsResponseStatusCode method used to verify the status code
     *
     * @param statusCode
     */
    public void getSelectOptionsResponseStatusCode(int statusCode) {
        verificationHelperClass.verifyStatusCode(getSelectOptionsResponse, statusCode);
        log.info("getSelectOptionsResponse StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * Verify getSelectOptionsResponseBody
     * @uthor Arun Kumar
     */
    public void getSelectOptionsResponseBody() {
        verifyResponseFormatIsJSON();
    }

    /**
     * @uthor Bharath
     * @param query-Query that fetches the Columns of DB in the form of json
     * @param columnName-
     */
    public void validateJSONResponse(String query,String columnName){
        try{ String actualquery=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES,query);
            String actualColumnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
            String apiJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathForResponsevalidation");
            String dBJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathForResponsevalidation");
            resultSet=dataBaseHelper.getDataWithoutPropertiesKey(actualquery);
            resultSet.next();
            String dbJson=resultSet.getString(actualColumnName);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getSelectOptionsResponse,dbJson,apiJsonPath,dBJsonPath);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
