/**
 * GetAllMFR class contains the methods to hit the getALLMFR end point and retrieve the details from
 * JSON response and compare the details with expected result retrieved from database
 *
 * @author  QATest
 * @version 1.0
 * @since   03/01/2021
 */
package baseSteps;
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
public class GetAllMFR {
    private Response response;
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
    JsonPath jsonPath;
    static Logger logger = Logger.getLogger(GetAllMFR.class.getName());
    DatabaseUtils dbUtil = new DatabaseUtils();
    /**
     * This method retrieves the environment details for get all active MFR API
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
            getAllMFRResource = prop.getProperty("getAllMFRResource").trim();
            logger.info(" MFRContractResource :" + getAllMFRResource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseURI;
    }

    /*public void setAPIEndpointURL() {
        try {
            prop = new Properties();
            //load a properties file from class path, inside static method
            prop.load(BaseSteps.class.getClassLoader()
                    .getResourceAsStream("environmentAndDBUtils.properties"));
            baseURI = prop.getProperty("baseUri");
            logger.info(" Base URI is :" + baseURI);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
    /**
     * This method constructs the end point
     */
    public void hitGetMFREndpoint() {
        getEnvProperties();
        url = baseURI + "/" + getAllMFRResource;
        logger.info("=======URL is++++++++++++++++++++++++++ " + url);
    }
    /**
     * This method hits the end point and logs the response
     */
    public void processGetRequest() {

        response =
                given().log().all().header("Authorization", "Bearer " + token).when().get(url);
        logger.info("Response is:" + response.asString());
    }
    /**
     * This method verifies if API returns a response code of 200
     */
    public void verifyGetRequestStatusCode200() {
        response.then().assertThat().statusCode(200);
        logger.info("API returns 200 http response code");
    }
    /**
     * This method verifies that the response in properly formatted in JSON format
     */
    public void verifyRsponseIsInJSONformat() {
        response.then().assertThat().contentType(ContentType.JSON);
        logger.info("The response is in proper JSON format");
    }
    /**
     * This method retrieves the count of MFR Ids from the API response through JSON Path
     * and asserts the count with the count of MFRs retrieved from DB
     */
    public void matchCountOfMFRRecords() {
        actualMFR = JsonPath.read(response.asString(), "$..manufacturerId");
        actualMFRCount = actualMFR.size();
        logger.info("No of active MFR retrieved from API are: " + actualMFRCount);
        expectedMFRCount = dbUtil.getNoOfActiveMFR();
        logger.info("ExpectedMFR count are: " + actualMFRCount);
        Assert.assertEquals("Expected and Actual MFR count does not Match", expectedMFRCount, actualMFRCount);
        logger.info("Assertion success:Matched the expected and Actual MFR count");
    }
    /**
     * This method retrieves all  MFR Ids from the API response through JSON Path
     * and asserts the all MFR ids retrieved from DB
     */
    public void matchMFRIDS() {
        actualAllMFRIDs=new ArrayList<String>();
        actualAllMFRIDs=JsonPath.read(response.asString(), "$..manufacturerId");
        ExpectedAllMFRIDs = dbUtil.getAllMFRID();
        Collections.sort(ExpectedAllMFRIDs);
        Collections.sort(actualAllMFRIDs);
        logger.info("Expected MFR IDs from database are:");
        for(String em:ExpectedAllMFRIDs){
            logger.info(em);
        }
        logger.info("Actual MFR IDs from API are:");
        for(String ac:actualAllMFRIDs){
            logger.info(ac);
        }
        logger.info("Matching All MFR IDS from DB and API ");
        Assert.assertTrue("The lists do not match!", ExpectedAllMFRIDs.equals(actualAllMFRIDs));
        logger.info("Successfully matched  All MFR IDS from DB and API ");
    }
    /**
     * This method retrieves all  MFR Names from the API response through JSON Path
     * and asserts the all MFR names retrieved from DB
     */
    public void matchMFRName() {
        actualAllMFRNames=new ArrayList<String>();
        actualAllMFRNames=JsonPath.read(response.asString(), "$.*.name");
        ExpectedAllMFRNames = dbUtil.getAllMFRName();
        Collections.sort(ExpectedAllMFRNames);
        Collections.sort(actualAllMFRNames);
        logger.info("Expected MFR name from database are:");
        for(String em:ExpectedAllMFRNames){
            logger.info(em);
        }
        logger.info("Actual MFR name from API are:");
        for(String ac:actualAllMFRNames){
            logger.info(ac);
        }
        logger.info("Matching All MFR names from DB and API ");
        Assert.assertTrue("The lists do not match!", ExpectedAllMFRNames.equals(actualAllMFRNames));
        logger.info("Successfully matched  All MFR names from DB and API ");
    }
   /* //-------------------------------- POST ---------------------------------
    public void createPostsUsingPostOperations(int id, String title, String author) {
        PostRequest postObject = new PostRequest();
        postObject.setId(id);
        postObject.setTitle(title);
        postObject.setAuthor(author);
        response = given().log().all().when().contentType(ContentType.JSON).body(postObject).post(url);
    }

    public void verifyPostCorrectlyCreated(int id, String title, String author) {
        body = response.getBody().asString();
        logger.info("body is" + body);

        Assert.assertTrue(body.contains(Integer.toString(id)));
        Assert.assertTrue(body.contains(title));
        Assert.assertTrue(body.contains(author));

    }

    //------------------------------- PUT ------------------------------------

    public void createPutRequest(int id, String body, int postId) {
        comment = new Comments();
        comment.setId(id);
        comment.setBody(body);
        comment.setPostId(postId);
    }

    *//*public void hitServiceWithPutRequest(int id) {
        //setAPIEndpointURL();
        String putUrl = baseURL + "/comments/" + id;
        logger.info("Put URL is========== " + putUrl);
        response = given().log().all().when().contentType(ContentType.JSON).body(comment).put(putUrl);
        logger.info("put response++ " + response.getBody().asString());
    }*//*

    public void put_VerifyResultOfOperation(int id, String body) {
        Assert.assertTrue(body.equals(body));
    }

    //---------------------------- DELETE ----------------------------------


    *//*public void delete_HitServiceWithDeleteRequest(int id) {
        String deleteUrl = baseURL + "/comments/" + id;
        response = given().log().all().when().contentType(ContentType.JSON).delete(deleteUrl);
        logger.info("patch response++ " + response.getBody().asString());
    }
*//*
    public void delete_VerifyResultOfOperation() {
        Assert.assertTrue((response.getBody().asString()).equals("{}"));
    }


//-----------------common operations/ reusable methods======================================

//    public void getJSONResponse() {
//        // Base URI
//        //RestAssured.baseURI = baseURL;
//        // validate that response is 200 and content type is JSON
//        Response rawResponse = given().log().all().when().get(url).then().extract().response();
//        logger.info("Raw Response is:" + rawResponse);
//        String stringResponse = rawResponse.asString();
//        logger.info("Response in String format is:" + stringResponse);
//        JsonPath jsonResponse = new JsonPath(stringResponse);
//        logger.info("Response in JSON format is:" + jsonResponse);
////        String firstUser = jsonResponse.get("[0].author");
////        logger.info(firstUser);
//
//    }
//    public void verifyIDInResponse(int id) {
//        // Base URI
//        //RestAssured.baseURI = baseURL;
//        // validate that response is 200 and content type is JSON
//        logger.info("URL for get with append string is:+++++++++"+url);
//        Response response = given().log().all().when().get(url).then().extract().response();
//        logger.info("Raw Response is:" + response);
//        String stringResponse = response.asString();
//        logger.info("Response in String format is:" + stringResponse);
//        JsonPath jsonPath = response.jsonPath();
//       // logger.info("Response in JSON format is:" + jsonResponse);
//       // int aid=jsonPath.get("id");
//        logger.info("Actual response id is:"+jsonPath.get("id"));
//            }*/
}