package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class FindAllManufactureContractDetailsByManufactureName extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(FindAllManufactureContractDetailsByManufactureName.class);

    /**
     * This method is used to FindAllManufactureContractDetailsByManufactureNamePostCall
     * @uthor Arun Kumar
     * @param endPoint
     * @param requestJsonBody
     */
    public void findAllManufactureContractDetailsByManufactureNamePostCall(String endPoint, Object requestJsonBody)
    {
        log.info("User hit post call for FindAllManufactureContractDetails");
        response = postOperation(endPoint, requestJsonBody);
        String s=response.getHeader("rb-api-result");
        log.info("FindAllManufactureContractDetails API error code value"+s);
    }

    /**
     * This method is used to validate the status code of findAllManufactureContractDetailsByManufactureName
     * @uthor Arun Kumar
     * @param statusCode
     */
    public void findAllManufactureContractDetailsByManufactureNameStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("FindAllManufactureContractDetailsByManufactureName StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * @uthour ArunKumar
     * validating findAllMFRResponseBody method is used to validate the response body output with database value
     * @param query
     */
    public void verifyFindAllManufactureCount(String query)
    {
        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "findAllManufactureCountByID");
        String dbColumn = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "countNoOfMFR");
        log.info("Validating findAllManufactureContract Response Body JsonPath="+jsonPath+ " and query="+query);
        verificationHelperClass.verifyRecordCount(response,jsonPath,query,dbColumn);
    }
}
