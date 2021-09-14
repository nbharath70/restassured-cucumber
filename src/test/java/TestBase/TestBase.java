package TestBase;
import HelperClass.ResourcePath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static io.restassured.RestAssured.given;

public class TestBase {
    public static Logger log=getMyLogger(TestBase.class);
    Response response;
    private String contractID;
    private int rowKey;
    //private int discardDrugGroupRowKey;
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
    public static  String getPropertiesFileValue(String ProFileName,String key)
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
     * @updated by: Rabbani
     * This method will read the environment passed through command line and connects to that environment
     */
    public String getBaseURI()
    {
        try {
            if(System.getProperty("environment")==null)
            {
                return getPropertiesFileValue(ResourcePath.Environment_Properties,"uatBaseUri");
            }
            else if(System.getProperty("environment").equalsIgnoreCase("Dev")){
                return getPropertiesFileValue(ResourcePath.Environment_Properties,"devBaseUri");
            }
            else if(System.getProperty("environment").equalsIgnoreCase("UAT"))
            {
                return getPropertiesFileValue(ResourcePath.Environment_Properties,"uatBaseUri");
            }
            else{
                log.info("Invalid Environment");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author Arun Kumar
     * getEndPointUrl Method used to concornate endpointUrl with baseUri and return the URL
     * @param endpointUrl
     * @return url
     */
    public String getEndPointUrl(String endpointUrl)
    {
        try {
            String baseURl = getBaseURI();
            String endpoint = getPropertiesFileValue(ResourcePath.Environment_Properties, endpointUrl);
            String url = baseURl + "/" + endpoint;
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
     * @Author: Smruti
     * @param endPoint, rowKey and contract id
     * @return response
     * deleteOperation method hits the end point and logs the response
     */
    public Response deleteOperation(String endPoint,int rowKey,String contractID)
    {
        try {
            response = given().pathParam("contractID",contractID)
                    .pathParam("rowKey",rowKey)
                    .log().all().header("Authorization", "Bearer "+getPropertiesFileValue(ResourcePath.Environment_Properties, "bearerToken"))
                    .when().delete(getEndPointUrl(endPoint)+"/{contractID}"+"/{rowKey}");
            return response;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @Author: Smruti
     * @param endPoint, discardDrugGroupRowKey
     * @return response
     * deleteOperation method hits the end point and logs the response
     */
    public Response deleteOperation(String endPoint,int discardDrugGroupRowKey)
    {
        try {
            response = given().pathParam("discardDrugGroupRowKey",discardDrugGroupRowKey)
                    .log().all().header("Authorization", "Bearer "+getPropertiesFileValue(ResourcePath.Environment_Properties, "bearerToken"))
                    .when().delete(getEndPointUrl(endPoint)+"/{discardDrugGroupRowKey}");
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
    /**
     * @uthor Rabbani
     * getEndPointUrl Method used to concatenates Base URI + endpointUrl + Id and returns the URL
     * @param endpointUrl
     * @param id
     * @return url
     */
    public String getEndPointUrl(String endpointUrl,String id)
    {
        try {
            String baseURl = getBaseURI();
            String endpoint = getPropertiesFileValue(ResourcePath.Environment_Properties, endpointUrl);
            String url = baseURl + "/" + endpoint+"/"+ id;
            log.info("****************The request url="+url+"*****************");
            return url;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @uthor: Rabbani
     * @param endPoint
     * @param id
     * @return response
     * getCall (overloaded) method hits the end point and logs the response and also verify response body type as ContentType.JSON
     */
    public Response getCall(String endPoint,String id)
    {
        try {
            response = given().log().all().header("Authorization", "Bearer "+getPropertiesFileValue(ResourcePath.Environment_Properties, "bearerToken")).when().get(getEndPointUrl(endPoint,id));
            log.info("Response is=" + response);
            response.then().assertThat().contentType(ContentType.JSON);
            log.info("The response is in proper JSON format");
            log.info("The response Body="+response.getBody().asString());
            return response;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @uthor: Bharath
     * @param a
     * @param b
     * @return true or false
     * areEqual method is used to compare the values in boolean form and returning either true or false
     */
    public boolean areEqual(Boolean a,Boolean b) {
        if (a == b) {
            return true;
        }

        if (a != null && b != null) {
            return a.booleanValue() == b.booleanValue();
        }

        return false;
    }




    /**
     * postOperation method is used to hits the end point with request Json body and logs the response and also verify response body type as ContentType.JSON
     * @uthor Arun Kumar
     * @param endPoint
     * @param requestPayload
     * @return
     */
    public Response postOperation(String endPoint, Object requestPayload)
    {
        try {
            RequestSpecification requestSpecification = RestAssured.given();
            requestSpecification.header("Authorization", "Bearer " + getPropertiesFileValue(ResourcePath.Environment_Properties, "bearerToken"));
            requestSpecification.header("Content-Type", "application/json").contentType(ContentType.JSON);
            requestSpecification.when();
            response=requestSpecification.body(requestPayload).log().all().post(getEndPointUrl(endPoint));
            log.info("****************** The Response JSON Body**************");
            log.info(response.getBody().jsonPath().prettify());
            response.then().assertThat().contentType(ContentType.JSON);
            log.info("The response is in proper JSON format");
            return response;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * putOperation method is used to hits the end point with request Json body and logs the response and also verify response body type as ContentType.JSON
     * @uthor Arun Kumar
     * @param endPoint
     * @param requestPayload
     * @return
     */
    public Response putOperation(String endPoint, Object requestPayload)
    {
        try {
            RequestSpecification requestSpecification = RestAssured.given();
            requestSpecification.header("Authorization", "Bearer " + getPropertiesFileValue(ResourcePath.Environment_Properties, "bearerToken"));
            requestSpecification.header("Content-Type", "application/json").contentType(ContentType.JSON);
            requestSpecification.when();
            response=requestSpecification.body(requestPayload).log().all().put(getEndPointUrl(endPoint));
            log.info("****************** The Response JSON Body**************");
            log.info(response.getBody().jsonPath().prettify());
            response.then().assertThat().contentType(ContentType.JSON);
            log.info("The response is in proper JSON format");
            return response;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return  null;
    }
    /**
     * This removeDuplicates method will eliminate the Duplicate Values in the ArrayList
     * @uthor Bharath
     * @param list
     */
    public  <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    { ArrayList<T> newList = new ArrayList<T>();
        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }


    /**
     * postOperation method is used to hits the end point with request Json body and logs the response and also verify response body type as ContentType.JSON
     * @uthor Rabbani
     * @param endPoint
     * @param id -
     * @return
     */
    public Response postOperation(String endPoint, String id)
    {
        try {
            RequestSpecification requestSpecification = RestAssured.given();
            requestSpecification.header("Authorization", "Bearer " + getPropertiesFileValue(ResourcePath.Environment_Properties, "bearerToken"));
            requestSpecification.header("Content-Type", "application/json").contentType(ContentType.JSON);
            requestSpecification.when();
            response=requestSpecification.log().all().post(getEndPointUrl(endPoint,id));
            log.info("****************** The Response JSON Body**************");
            log.info(response.getBody().jsonPath().prettify());
            response.then().assertThat().contentType(ContentType.JSON);
            log.info("The response is in proper JSON format");
            return response;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return  null;
    }


    /**
     * @Author Rabbani
     * getEndPointUrl Method used to concatenates Base URI + endpointUrl + and multiple path params and returns the final URL
     * @param endpointUrl
     * @param listPathParams - List<String> of path params
     * @return url
     */
    public String getEndPointUrl(String endpointUrl,List<String> listPathParams)
    {
        try {
            String baseURl = getBaseURI();
            String endpoint = getPropertiesFileValue(ResourcePath.Environment_Properties, endpointUrl);
            String url = baseURl + "/" + endpoint;
            for(String pathParam:listPathParams){
                url = url.replaceFirst("pathparam", pathParam);
            }
            log.info("****************The request url="+url+"*****************");
            return url;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: Rabbani
     * @param endPoint
     * @param listPathParams -List<String> of path parameters
     * @return response
     * getCall (overloaded) method hits the end point and logs the response and also verify response body type as ContentType.JSON
     */
    public Response getCall(String endPoint, List<String> listPathParams)
    {
        try {
            response = given().log().all().urlEncodingEnabled(false).header("Authorization", "Bearer "+getPropertiesFileValue(ResourcePath.Environment_Properties, "bearerToken")).when().get(getEndPointUrl(endPoint,listPathParams));
            //response = given().log().all().urlEncodingEnabled(false).header("Authorization", "Bearer "+getPropertiesFileValue(ResourcePath.Environment_Properties, "bearerToken")).when().get("https://uat-rebate.prescientgroup.com/rebate/api/rebatedata/manufacturer/contract/drug-group-detail/medispan-data/drug-list//ndcs/");
            log.info("Response is=" + response);
            response.then().assertThat().contentType(ContentType.JSON);
            log.info("The response is in proper JSON format");
            log.info("The response Body="+response.getBody().asString());
            return response;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @uthor: Rabbani
     * @param endPoint, requestbody as object
     * @return response
     * deleteOperation method hits the end point with request body and returns the response
     */
    public Response deleteOperation(String endPoint,Object requestBody)
    {
        try {
            RequestSpecification requestSpecification = RestAssured.given();
            requestSpecification.header("Authorization", "Bearer " + getPropertiesFileValue(ResourcePath.Environment_Properties, "bearerToken"));
            requestSpecification.header("Content-Type", "application/json").contentType(ContentType.JSON);
            requestSpecification.when();
            response=requestSpecification.body(requestBody).log().all().delete(getEndPointUrl(endPoint));
            log.info("****************** The Response JSON Body**************");
            log.info(response.getBody().jsonPath().prettify());
            response.then().assertThat().contentType(ContentType.JSON);
            log.info("The response is in proper JSON format");
            return response;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
