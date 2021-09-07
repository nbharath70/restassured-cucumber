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

    public void hitFetchJobListAPI(String endpoint)
    {
        response=getCall(endpoint);
    }

    public void verifyFetchJobListAPIresponseCode (int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response,statusCode);
        log.info("fetchJobList API's StatusCode is: "+statusCode);
    }

    public void verifyFetchJobListAPIResponseFormatJSON()
    {
        verifyResponseFormatIsJSON();
    }

    public void verifyFetchJobListAPIResponseDetails(String query,String columnName,String jsonPath){
        String actualQuery=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES,query);
        String actualColumnname=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
        String actualJsonPathofAPI=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,jsonPath);
        verificationHelperClass.verifyResponseJsonAndDbArrayByColumnNameWithoutPropertiesKey(response,actualJsonPathofAPI,actualQuery,actualColumnname);

    }

    public void validateJSONResponse(String query,String columnName){
        try{ String actualquery=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES,query);
            String actualColumnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
            String apiJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathForResponsevalidation");
            String dBJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathForResponsevalidation");
            resultSet=dataBaseHelper.getDataWithoutPropertiesKey(actualquery);
            resultSet.next();
            String dbJson=resultSet.getString(actualColumnName);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,apiJsonPath,dBJsonPath);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
