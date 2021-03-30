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
    public void hitGetContractDetailsByIDEndpoint() {
        getEnvProperties();
        rowKeyVal=dbUtil.getRowKey();
        url = baseURI + "/" +getAllContractDetailsByID+"/"+rowKeyVal;
        logger.info("=======URL is++++++++++++++++++++++++++ " + url);
    }
    public void processGetRequest() {

        response =
                given().log().all().header("Authorization", "Bearer " + token).when().get(url);
        logger.info("Response is:" + response.asString());
    }
    public void verifyGetRequestStatusCode200() {
        response.then().assertThat().statusCode(200);
        logger.info("API returns 200 http response code");
    }

    public void verifyRsponseIsInJSONformat() {
        response.then().assertThat().contentType(ContentType.JSON);
        logger.info("The response is in proper JSON format");
    }

}
