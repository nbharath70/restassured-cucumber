package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

public class PostUpdateManufactureContract extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(PostInitiateNewManufactureContract.class);
    /**
     * This method is used to updateManufactureContractPutCall
     * @uthor Arun Kumar
     * @param endPoint
     * @param requestJsonBody
     */
    public void updateManufactureContractPutCall(String endPoint, Object requestJsonBody)
    {
        response = postOperation(endPoint, requestJsonBody);
    }

    /**
     * This validationResults used to validate the respanse body value as boolean
     * @uthor Arun Kumar
     * @param actualValue
     * @param expectedValue
     */
    public void validationResultsBooleanforUpdateManfCont(String actualValue,String expectedValue)
    {
        verificationHelperClass.verifyResponseJsonBoolean(response,actualValue,expectedValue);
    }

    /**
     * This validationResultsString used to validate the respanse body value as String
     * @uthor Arun Kumar
     * @param actualValue
     * @param expectedValue
     */
    public void validationResultsStringforUpdateManfCont(String actualValue,String expectedValue)
    {
        verificationHelperClass.verifyResponseJsonString(response,actualValue,expectedValue);
    }

    /**
     * This method is used to validate the status code of postUpdateManufactureContractStatusCode
     * @uthor Arun Kumar
     * @param statusCode
     */
    public void postUpdateManufactureContractStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("PostUpdateManufactureContract StatusCode is " + statusCode + " and its Pass");
    }

}
