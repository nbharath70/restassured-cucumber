package baseSteps;


import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.sql.ResultSet;


public class FetchJobListBaseStep extends TestBase {
    Response response;
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(FetchJobListBaseStep.class);
    ResultSet resultSet;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();

    /**
     * @uthour Bharath
     * This method is Used to Hit the Endpoint
     * @param endpoint-
     */
    public void hitFetchJobListAPI(String endpoint)
    {
        response=getCall(endpoint);
    }

    /**
     * @uthour Bharath
     * This method is Used Verify the Status Code
     * @param statusCode-
     */
    public void verifyFetchJobListAPIresponseCode (int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response,statusCode);
        log.info("fetchJobList API's StatusCode is: "+statusCode);
    }

    /**
     * @uthour Bharath
     * This method is Used Verify the Response is in JSOn Format
     */
    public void verifyFetchJobListAPIResponseFormatJSON()
    {
        verifyResponseFormatIsJSON();
    }

    /**
     * @uthour Bharath
     * This method is used to fetch the Respnse Details
     * @param query-
     * @param columnName-
     * @param jsonPath-
     */
    public void verifyFetchJobListAPIResponseDetails(String query,String columnName,String jsonPath){
        String actualQuery=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES,query);
        String actualColumnname=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
        String actualJsonPathofAPI=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,jsonPath);
        verificationHelperClass.verifyResponseJsonAndDbArrayByColumnNameWithoutPropertiesKey(response,actualJsonPathofAPI,actualQuery,actualColumnname);

    }

    /**
     * @uthour Bharath
     * This method is Used to verify the API Response with DB json Response
     * @param query-
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
            if (dbJson==null){
                log.info("There is NO Valid Job for Present to fetch");
            }
            else{
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,apiJsonPath,dBJsonPath);}
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
