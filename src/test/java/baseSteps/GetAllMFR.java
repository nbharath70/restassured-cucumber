/**
 * GetAllMFR class contains the methods to hit the getALLMFR end point and retrieve the details from
 * JSON response and compare the details with expected result retrieved from database
 *
 * @author  QATest
 * @version 1.0
 * @since   03/01/2021
 */
package baseSteps;
import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.sql.ResultSet;

public class GetAllMFR extends TestBase {

    public static Logger log=getMyLogger(GetAllMFR.class);
    public static Response getAllMFRResponse;
    public VerificationHelperClass verificationHelperClass= new VerificationHelperClass();
    public DataBaseHelper dataBaseHelper=new DataBaseHelper();
    ResultSet resultSet;

   /*============================================================================================*/
    /**
     * @uthour Smruti
     * This method hits getAllMFRResource  end point and logs the response
     * @param endpoint
     */
    public void getAllMFRResource(String endpoint)
    {
        getAllMFRResponse=getCall(endpoint);
    }
    /**
     * @uthour Smruti
     * This method hits getAllMFRResource  end point and logs the response
     * Verifies the response format is in JSON
     */
    public void verifyResponseFormatJSON()
    {
       verifyResponseFormatIsJSON();
    }


    /**@uthour Smruti
     * getAllMFRResponseStatusCode method used to verify the status code
     * @param statusCode
     */
    public void getAllMFRResponseStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(getAllMFRResponse,statusCode);
        log.info("getSelectOptionsResponse StatusCode is "+statusCode+" and its Pass");
    }
    /**
     * @uthour Smruti
     * validating getAllMFRResponseBody method is used to validate the response body output with database value

     * @param query
     */
    public void verifyRecordCount(String query)
    {
        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "manufacturerIdCount");
        String dbColumn = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "countNoOfMFR");
        log.info("Validating GetSelectOptions Response Body JsonPath="+jsonPath+ " and query="+query);
        verificationHelperClass.verifyRecordCount(getAllMFRResponse,jsonPath,query,dbColumn);
    }
    /**
     * @uthour Smruti
     * validating getAllMFRResponseBody method is used to validate the response body output with database value
     * @param query
     */
    public void verifyMFRDetails(String query)
    {
        log.info("Validating getAll MFR details Response with query as:"+query);
        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "MFRID");
        String dbColumn = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "ManufacturerID");
        verificationHelperClass.verifyResponseJsonAndDbArrayByColumnName(getAllMFRResponse,jsonPath,query,dbColumn);
    }

    /**
     * @uthour Smruti
     * validating getAllMFRResponseBody method is used to validate the response body output with database value
     * @param query
     */
    public void verifyMFRNameDetails(String query)
    {
        log.info("Validating getAll MFR details Response with query as:"+query);
        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "MFRName");
        String dbColumn = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "ManufacturerName");
        verificationHelperClass.verifyResponseJsonAndDbArrayByColumnName(getAllMFRResponse,jsonPath,query,dbColumn);
    }

    public void validateJSONResponse(String query,String columnName){
        try{ String actualquery=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES,query);
            String actualColumnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
            String apiJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathForResponsevalidation");
            String dBJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathForResponsevalidation");
            resultSet=dataBaseHelper.getDataWithoutPropertiesKey(actualquery);
            resultSet.next();
            String dbJson=resultSet.getString(actualColumnName);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getAllMFRResponse,dbJson,apiJsonPath,dBJsonPath);
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}