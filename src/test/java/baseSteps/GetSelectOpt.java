package baseSteps;

import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

public class GetSelectOpt extends TestBase {
    public static Logger log=getMyLogger(GetSelectOpt.class);
    public static Response getSelectOptionsResponse;
    public VerificationHelperClass verificationHelperClass= new VerificationHelperClass();
    /**
     * @uthour Arun Kumar
     * This method hits getSelectOptionsResource the end point and logs the response and also verify json formate type
     * @param endpoint
     */
    public void getSelectOptionsResource(String endpoint)
    {
        getSelectOptionsResponse=getCall(endpoint);
    }

    /**
     * getSelectOptionsResponseStatusCode method used to verify the status code
     * @param statusCode
     */
    public void getSelectOptionsResponseStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(getSelectOptionsResponse,statusCode);
        log.info("getSelectOptionsResponse StatusCode is "+statusCode+" and its Pass");
    }

    /**
     * @uthour Arun Kumar
     * validatingGetSelectOptionsResponseBody method is used to validate the response body output with database value
     * @param jsonPath
     * @param query
     * @param columnName
     */
    public void validatingGetSelectOptionsResponseBody(String jsonPath,String query,String columnName)
    {
        log.info("Validating GetSelectOptions Response Body JsonPath="+jsonPath+ " and query="+query);
        verificationHelperClass.VerifyResponseJsonAndDbArrayByColumnName(getSelectOptionsResponse,jsonPath,query,columnName);
    }
}
