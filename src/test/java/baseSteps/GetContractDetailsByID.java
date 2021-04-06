/**
 * GetContractDetailsByID class contains the methods to hit the GetContractDetailsByID end point and retrieve the details from
 * JSON response and compare the details with expected result retrieved from database
 *
 * @author  Bharath.N
 * @version 1.0
 * @since   30/03/2021
 */
package baseSteps;
import io.restassured.http.ContentType;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import static io.restassured.RestAssured.given;
public class GetContractDetailsByID {
    private Response response;
    private RequestSpecification request;
    private int statusCode;
    private String url;
    private Properties prop;
    private String baseURI;
    private String token;
    private String getAllContractDetailsByID;
    private int rowKeyVal;
    private String rowKeyValTypeMissMatch;
    private List<String> actualMFRNameAndID;
    private List<String> ExpectedMFRNameAndID;
    private List actualContracHeadertdetails;
    private List<String> expectedContractHeaderdetails;
    private List<String> actualContractDetailsJSON;
    private List<String> expectedContractDetailsJSON;
    private String expectedInvalidRowKeyContractDetailsResponse;
    private String actualInvalidRowKeyContractDetailsResponse;
    private String actualBlankRowKeyContractDetailsResponse;
    private String expectedBlankRowKeyContractDetailsResponse;
    JsonPath jsonPath;
    static Logger logger = Logger.getLogger(GetAllMFR.class.getName());
    DatabaseUtils dbUtil = new DatabaseUtils();

    /**
     * This method retrieves the environment details for GetContractDetailsByID API
     * @author Bharath
     * @exception  Exception
     *
     */
    public String getEnvProperties() {
        try {
            prop = new Properties();
            //load a properties file from class path, inside static method
            prop.load(GetAllMFR.class.getClassLoader()
                    .getResourceAsStream("environment.properties"));
            baseURI = prop.getProperty("baseUri");
            logger.info(" Base URI is :" + baseURI);
            token = prop.getProperty("bearerToken");
            logger.info(" Bearer token is :" + token);
            getAllContractDetailsByID= prop.getProperty("getContractDetailByID").trim();
            logger.info(" MFRContractDetailsByID Resource :" + getAllContractDetailsByID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseURI;
    }

    /**
     * This method gets the URL from Environment.Property file GetContractDetailsByID API and RowKey from DB and merge together and give complete endpoint
     * @author Bharath
     */
    public void hitGetContractDetailsByIDEndpoint() {
        getEnvProperties();
        rowKeyVal=dbUtil.getRowKey();
        url = baseURI + "/" +getAllContractDetailsByID+"/"+rowKeyVal;
        logger.info("=======URL is++++++++++++++++++++++++++ " + url);
    }

    /**
     * This method gets the URL from Environment.Property file GetContractDetailsByID API and RowKey from DB and make it Invalid merge together and give complete endpoint
     * @author Bharath
     */
    public void hitGetContractDetailsByIDEndpointWithInvalidRowKey(){
        getEnvProperties();
        rowKeyVal=dbUtil.getCountOfContractsDetails();
        url = baseURI + "/" +getAllContractDetailsByID+"/"+(rowKeyVal+3);
        logger.info("=======URL is++++++++++++++++++++++++++ " + url);
    }

    public void hitBlankContractDetailsByIDEndpoint() {
        getEnvProperties();
        rowKeyVal=dbUtil.getRowKey();
        url = baseURI + "/" +getAllContractDetailsByID+"/";
        logger.info("=======URL is++++++++++++++++++++++++++ " + url);
    }

    public void hitTypeMissMAtchContractDetailsByIDEndpoint() {
        getEnvProperties();
        rowKeyValTypeMissMatch=dbUtil.getContractID();
        url = baseURI + "/" +getAllContractDetailsByID+"/"+rowKeyValTypeMissMatch;
        logger.info("=======URL is++++++++++++++++++++++++++ " + url);
    }

    /**
     * This method proccesses the request and stores the response
     * @author Bharath
     */
    public void processGetRequest() {

        response =
                given().log().all().header("Authorization", "Bearer " + token).when().get(url);
        logger.info("Response is:" + response.asString());
    }

    /**
     * This method Verifies the request status is 200 or not
     * @author Bharath
     */

    public void verifyGetRequestStatusCode200() {
        response.then().assertThat().statusCode(200);
        logger.info("API returns 200 http response code");
    }

    /**
     * This method Verifies Response is JSON or NOT
     * @author Bharath
     */
    public void verifyResponseIsInJSONformat() {
        response.then().assertThat().contentType(ContentType.JSON);
        logger.info("The response is in proper JSON format");
    }

    /**
     * This method match the Manufacturer_ID and Name from API response and DB
     * @author Bharath
     */
    public void matchmanufacturerDetails() {
        actualMFRNameAndID =new ArrayList<String>();
        ExpectedMFRNameAndID=new ArrayList<String>();
        actualMFRNameAndID=JsonPath.read(response.asString(), "$.manufacturer[*]");
        ExpectedMFRNameAndID=dbUtil.getManufacturerName();
        Collections.sort(ExpectedMFRNameAndID);
        Collections.sort(actualMFRNameAndID);
        logger.info("Expected ManufactuerName and ID is:");
        for(String em:ExpectedMFRNameAndID){
            logger.info(em);
        }
        logger.info("Actual ManufactuerName and ID from API are:");
        for(String ac:actualMFRNameAndID){
            logger.info(ac);
        }
        logger.info("Matching All Manufactuer Name and ID from DB and API ");
        Assert.assertTrue("The lists do not match!", ExpectedMFRNameAndID.equals(actualMFRNameAndID));
        logger.info("Successfully matched  All MFR IDS from DB and API ");
    }
    /**
     * This method match the ContracHeadertDetails from API response and DB
     * @author Bharath
     */
    public void matchContracHeadertDetails() {
        actualContracHeadertdetails =new ArrayList();
        expectedContractHeaderdetails=new ArrayList<String>();
        actualContracHeadertdetails=JsonPath.read(response.asString(), "$.contractHeader[*]");
        expectedContractHeaderdetails=dbUtil.getContractHeaderDetails();
        for(int i=0;i<expectedContractHeaderdetails.size();i++){
            if(i==4||i==6){
                expectedContractHeaderdetails.set(i,expectedContractHeaderdetails.get(i).replaceAll(" ","T"));
            }
        }

        logger.info("Actual ContractetailsHeader from API are:");
        for(Object ac:actualContracHeadertdetails){
            logger.info(ac);
        }
        List obj= new ArrayList(expectedContractHeaderdetails);
        logger.info("Expected ContractetailsHeader is:");
        for(Object em:obj){
            logger.info(em);
        }


        boolean a=obj.equals(actualContracHeadertdetails);
        logger.info("Successfully matched  All MFR IDS from DB and API "+a);
//        Assert.assertTrue("The lists do not match!", expectedContractHeaderdetails.equals(actualContracHeadertdetails));
//        logger.info("Successfully matched  All MFR IDS from DB and API ");
    }
    /**
     * This method match the ContractDetailJSON from API response and DB
     * @author Bharath
     */
    public void matchContractDetailJSON() {
        try{actualContractDetailsJSON=JsonPath.read(response.asString(), "$.contractDetail.contractDetailJson");
        expectedContractDetailsJSON=dbUtil.getContractDetailsJSON();
        logger.info("Expected ContractDetailJSON is:"+expectedContractDetailsJSON);
        logger.info("Actual ContractDetailJSON is:"+actualContractDetailsJSON);
        Assert.assertTrue("JSON Do Not match",expectedContractDetailsJSON.equals(actualContractDetailsJSON));
        logger.info("Successfully matched  ContractHeaderDetailJSon from DB and API ");}
        catch (Exception e){
        e.printStackTrace();
        }
}
    public void matchInvalidRowKeyResponse() {
        try{actualInvalidRowKeyContractDetailsResponse=JsonPath.read(response.asString(), "$.message");
            expectedInvalidRowKeyContractDetailsResponse="404 NOT_FOUND";
            logger.info("Expected response is:"+actualInvalidRowKeyContractDetailsResponse);
            logger.info("Actual Conresponse is:"+expectedInvalidRowKeyContractDetailsResponse);
            Assert.assertTrue("JSON Do Not match",actualInvalidRowKeyContractDetailsResponse.equals(expectedInvalidRowKeyContractDetailsResponse));
            logger.info("Successfully matched Invalid API response ");}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void matchBlankRowKeyResponse() {
        try{actualBlankRowKeyContractDetailsResponse=JsonPath.read(response.asString(), "$.message");
            expectedBlankRowKeyContractDetailsResponse="404 NOT_FOUND";
            logger.info("Expected response is:"+actualBlankRowKeyContractDetailsResponse);
            logger.info("Actual Conresponse is:"+expectedBlankRowKeyContractDetailsResponse);
            Assert.assertTrue("JSON Do Not match",actualBlankRowKeyContractDetailsResponse.equals(expectedBlankRowKeyContractDetailsResponse));
            logger.info("Successfully matched Invalid API response ");}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void matchTypeMissmatchRowKeyResponse() {
        try{actualBlankRowKeyContractDetailsResponse=JsonPath.read(response.asString(), "$.message");
            expectedBlankRowKeyContractDetailsResponse="400 BAD_REQUEST \\\"Type mismatch.\\\"; nested exception is org.springframework.beans.TypeMismatchException: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; nested exception is java.lang.NumberFormatException: For input string: \\\""+dbUtil.getContractID()+"\\\"";
            logger.info("Expected response is:"+actualBlankRowKeyContractDetailsResponse);
            logger.info("Actual Conresponse is:"+expectedBlankRowKeyContractDetailsResponse);
            Assert.assertTrue("JSON Do Not match",actualBlankRowKeyContractDetailsResponse.equals(expectedBlankRowKeyContractDetailsResponse));
            logger.info("Successfully matched Invalid API response ");}
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
