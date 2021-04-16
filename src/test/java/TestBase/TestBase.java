package TestBase;
import HelperClass.ResourcePath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;

public class TestBase {
    public static Logger log=getMyLogger(TestBase.class);
    Response response;
    /**
     * @uthour :Arun Kumar
     * @param cls
     * Method used to log the information
     * @return
     */
    public static Logger getMyLogger(Class cls)
    {
        try {
            PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\log4j.properties");
            return Logger.getLogger(cls.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @uthor:Arun Kumar
     * @param ProFileName
     * @param key
     * @return
     * getPropertiesFileValue method used to get the value of the properties file by giving input parameter as Resource path constant name and Key
     */
    public String getPropertiesFileValue(String ProFileName,String key)
    {
        try {
            Properties p= new Properties();
            FileInputStream ip= new FileInputStream(ProFileName);
            p.load(ip);
            return p.getProperty(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * @uthoth Arun Kumar
     * getBaseURI method used to return the baseUrl
     * @return BaseURI
     */
    public String getBaseURI()
    {
        try {
            return getPropertiesFileValue(ResourcePath.Environment_Properties,"baseUri");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @uthor Arun Kumar
     * getEndPointUrl Method used to concornate endpointUrl with baseUri and return the URL
     * @param endpointUrl
     * @return url
     */
    public String getEndPointUrl(String endpointUrl)
    {
        try {
            String BaseURl = getBaseURI();
            String Endpoint = getPropertiesFileValue(ResourcePath.Environment_Properties, endpointUrl);
            String url = BaseURl + "/" + Endpoint;
            log.info("****************The request url="+url+"*****************");
            return url;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @uthor: Arun Kumar
     * @param endPoint
     * @return response
     * getCall method hits the end point and logs the response and also verify response body type as ContentType.JSON
     */
    public Response getCall(String endPoint)
    {
        try {
            response = given().log().all().header("Authorization", "Bearer "+getPropertiesFileValue(ResourcePath.Environment_Properties, "bearerToken")).when().get(getEndPointUrl(endPoint));
//            log.info("Response is=" + response);
//            response.then().assertThat().contentType(ContentType.JSON);
//            log.info("The response is in proper JSON format");
//            log.info("The response Body="+response.getBody().asString());
            return response;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @uthor: Smruti
     * @return response
     * getCall method hits the end point and logs the response and also verify response body type as ContentType.JSON
     */
    public void verifyResponseFormatIsJSON()
    {
        try {
            log.info("Verify the  response format is JSON");
            response.then().assertThat().contentType(ContentType.JSON);
            log.info("The response is in proper JSON format");
            log.info("The response Body="+response.getBody().asString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
