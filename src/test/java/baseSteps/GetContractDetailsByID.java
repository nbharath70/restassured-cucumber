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
import org.apache.log4j.Logger;
import org.junit.Assert;
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
    public void verifyRsponseIsInJSONformat() {
        response.then().assertThat().contentType(ContentType.JSON);
        logger.info("The response is in proper JSON format");
    }

}
