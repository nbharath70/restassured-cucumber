/**
 * GetAllMFR class contains the methods to hit the getALLMFR end point and retrieve the details from
 * JSON response and compare the details with expected result retrieved from database
 *
 * @author  QATest
 * @version 1.0
 * @since   03/01/2021
 */
package baseSteps;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.http.ContentType;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import static io.restassured.RestAssured.given;
public class GetAllMFR extends TestBase {
 /*   private Response response;
    private RequestSpecification request;
    private int statusCode;
    private String url;
    private Properties prop;
    private String baseURI;
    private String token;
    private String getAllMFRResource;
    private int expectedMFRCount;
    private int actualMFRCount;
    private List<String> actualMFR;
    private List<String>ExpectedAllMFRIDs;
    private List<String>actualAllMFRIDs;
    private List<String> actualAllMFRNames;
    private List<String>ExpectedAllMFRNames;
    JsonPath jsonPath;*/
    public static Logger log=getMyLogger(GetAllMFR.class);
    public static Response getAllMFRResponse;
    public VerificationHelperClass verificationHelperClass= new VerificationHelperClass();
  /*==============================================================================================
    *//**
     * This method retrieves the environment details for get all active MFR API
     *//*
    public String getEnvProperties() {
        try {
            prop = new Properties();
            //load a properties file from class path, inside static method
            prop.load(GetAllMFR.class.getClassLoader()
                    .getResourceAsStream("environment.properties"));
            baseURI = prop.getProperty("baseUri");
            log.info(" Base URI is :" + baseURI);
            token = prop.getProperty("bearerToken");
            log.info(" Bearer token is :" + token);
            getAllMFRResource = prop.getProperty("getAllMFRResource").trim();
            log.info(" MFRContractResource :" + getAllMFRResource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseURI;
    }
    *//**
     * This method constructs the end point
     *//*
    public void hitGetMFREndpoint(String endPoint) {
        getEnvProperties();
        url = baseURI + "/" + getAllMFRResource;
        log.info("=======URL is++++++++++++++++++++++++++ " + url);
    }
    *//**
     * This method hits the end point and logs the response
     *//*
    public void processGetRequest() {

        response =
                given().log().all().header("Authorization", "Bearer " + token).when().get(url);
        log.info("Response is:" + response.asString());
    }
    *//**
     * This method verifies if API returns a response code of 200
     *//*
    public void verifyGetRequestStatusCode200() {
        response.then().assertThat().statusCode(200);
        log.info("API returns 200 http response code");
    }
    *//**
     * This method verifies that the response in properly formatted in JSON format
     *//*
    public void verifyRsponseIsInJSONformat() {
        response.then().assertThat().contentType(ContentType.JSON);
        log.info("The response is in proper JSON format");
    }
    *//**
     * This method retrieves the count of MFR Ids from the API response through JSON Path
     * and asserts the count with the count of MFRs retrieved from DB
     *//*
    public void matchCountOfMFRRecords() {
        actualMFR = JsonPath.read(response.asString(), "$..manufacturerId");
        actualMFRCount = actualMFR.size();
        log.info("No of active MFR retrieved from API are: " + actualMFRCount);
        expectedMFRCount = dbUtil.getNoOfActiveMFR();
        log.info("ExpectedMFR count are: " + actualMFRCount);
        Assert.assertEquals("Expected and Actual MFR count does not Match", expectedMFRCount, actualMFRCount);
        log.info("Assertion success:Matched the expected and Actual MFR count");
    }
    *//**
     * This method retrieves all  MFR Ids from the API response through JSON Path
     * and asserts the all MFR ids retrieved from DB
     *//*
    public void matchMFRIDS() {
        actualAllMFRIDs=new ArrayList<String>();
        actualAllMFRIDs=JsonPath.read(response.asString(), "$..manufacturerId");
        ExpectedAllMFRIDs = dbUtil.getAllMFRID();
        Collections.sort(ExpectedAllMFRIDs);
        Collections.sort(actualAllMFRIDs);
        log.info("Expected MFR IDs from database are:");
        for(String em:ExpectedAllMFRIDs){
            log.info(em);
        }
        log.info("Actual MFR IDs from API are:");
        for(String ac:actualAllMFRIDs){
            log.info(ac);
        }
        log.info("Matching All MFR IDS from DB and API ");
        Assert.assertTrue("The lists do not match!", ExpectedAllMFRIDs.equals(actualAllMFRIDs));
        log.info("Successfully matched  All MFR IDS from DB and API ");
    }
    *//**
     * This method retrieves all  MFR Names from the API response through JSON Path
     * and asserts the all MFR names retrieved from DB
     *//*
    public void matchMFRName() {
        actualAllMFRNames=new ArrayList<String>();
        actualAllMFRNames=JsonPath.read(response.asString(), "$.*.name");
        ExpectedAllMFRNames = dbUtil.getAllMFRName();
        Collections.sort(ExpectedAllMFRNames);
        Collections.sort(actualAllMFRNames);
        log.info("Expected MFR name from database are:");
        for(String em:ExpectedAllMFRNames){
            log.info(em);
        }
        log.info("Actual MFR name from API are:");
        for(String ac:actualAllMFRNames){
            log.info(ac);
        }
        log.info("Matching All MFR names from DB and API ");
        Assert.assertTrue("The lists do not match!", ExpectedAllMFRNames.equals(actualAllMFRNames));
        log.info("Successfully matched  All MFR names from DB and API ");
    }
   ============================================================================================*/
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
        verificationHelperClass.VerifyResponseJsonAndDbArrayByColumnName(getAllMFRResponse,jsonPath,query,dbColumn);
    }
}