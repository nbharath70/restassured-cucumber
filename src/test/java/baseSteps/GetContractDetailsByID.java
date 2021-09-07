/**
 * GetContractDetailsByID class contains the methods to hit the GetContractDetailsByID end point and retrieve the details from
 * JSON response and compare the details with expected result retrieved from database
 *
 * @author  Bharath.N
 * @version 1.0
 * @since   30/03/2021
 */
package baseSteps;

import HelperClass.ResourcePath;
import HelperClass.DataBaseHelper;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetContractDetailsByID extends TestBase {
    public ResultSet result;
    private int rowKeyVal;
    private String contractID;
    private String manufactuerID;
    private String manufactuerDetails;
    private String contractHeaderDetails;
    private String contractDetailsJSON;
    public static Logger log = getMyLogger(GetContractDetailsByID.class);
    public static Response getContractDetailsByIDResponse;
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public DataBaseHelper dbHepler = new DataBaseHelper();

    /**
     * This method retrieves the Rowkey Details from  DB
     * @author Bharath
     * @param query
     * @exception SQLException
     */
    public  void getRowKey(String query) {
        try {
            log.info("query is "+query);
            result = dbHepler.getData(query);
            result.next();
            String rk=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "rowkey");
            rowKeyVal = result.getInt(rk);
            log.info("RowKey of ActiveContract is  " + rowKeyVal + " From DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method retrieves the ContractID Details from  DB
     * @author Bharath
     * @exception SQLException
     * @param query
     */
    public void getContractID(String query){
        try {
            result=dbHepler.executePreparedQuery(query,rowKeyVal);
            result.next();
            String cid=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "contractID");
            contractID=result.getString(cid);
            log.info("contractID of Respective rowkey is  " + contractID + " From DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method retrieves the getManufacturerID Details from  DB
     * @author Bharath
     * @exception SQLException
     * @param query
     */
    public void getManufacturerID(String query){
        try {result=dbHepler.executePreparedQuery(query,contractID);
            result.next();
            String mid=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "ManufacturerID");
            manufactuerID=result.getString(mid);
            log.info("ManufactuereID of Respective ContractID is  " + manufactuerID + " From DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method used to hit the ContractDetailsByIdEndpoint
     * @author Bharath
     * @param endpoint
     */
    public void hitGetContractDetilsByIdEndpoint(String endpoint) {
        String url=getEndPointUrl(endpoint,String.valueOf(rowKeyVal));
        log.info("GetContractDetails By ID API Endpoint is " + url);
        getContractDetailsByIDResponse = getCall(endpoint,String.valueOf(rowKeyVal));
    }

    /**
     * This method used to verify contractDetailsByIDResponse
     * @author Bharath
     * @param statusCode
     */
    public void verifyContractDetailsByIDResponse(int statusCode) {
        verificationHelperClass.verifyStatusCode(getContractDetailsByIDResponse, statusCode);
        log.info("getContractDetailsByIDResponse StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * This method used verify the resopnse is in JSON format or not
     * @author Bharath
     */
    public void verifygetContractDetailsByIDJSONFormat(){
        verifyResponseFormatIsJSON();
    }

    /**
     * This method used get Manufacturer details from DB
     * @author Bharath
     * @exception Exception
     */
    public void getManufacturerDetails(String query){
        try {result=dbHepler.executePreparedQuery(query,manufactuerID);
            result.next();
            manufactuerDetails=result.getString("manufacturerDetails");
            log.info("manufacturerDetails of Respective ManufacturerID is  " + manufactuerDetails + " From DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method used to Match Manufacturerdetails with API response
     * @author Bharath
     */
    public void matchManufacturerDetails(){
        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "manufactuerDetailsJSON");
        log.info("contractDetailsJson response is");
        verificationHelperClass.verifyAPIResponseJsonWithDBJson(getContractDetailsByIDResponse,manufactuerDetails,jsonPath);
    }
    /**
     * This method used get ContractDtailsHeader from Db
     * @author Bharath
     * @exception SQLException
     * @param query
     */
    public void getContractHeaderDetails(String query){
        try {result=dbHepler.executePreparedQuery(query,contractID);
            result.next();
            contractHeaderDetails=result.getString("Contract_Header_Details");
            log.info("manufacturerDetails of Respective ManufacturerID is  " + contractHeaderDetails + " From DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method used to match ContractHeaderDetails from Db
     * @author Bharath
     */
    public void matchContractHeaderDetails(){
        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "contractHeaderDetailsJSON");
        log.info("contractDetailsJson response is");
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithIntandStringCombinationDataTypeValues(getContractDetailsByIDResponse,contractHeaderDetails,jsonPath);
    }

    /**
     * This method used get ContractDetailJSON from Db
     * @author Bharath
     * @param query
     */
    public void getContractDetailJSON(String query){
        try {result=dbHepler.executePreparedQuery(query,contractID);
            result.next();
            contractDetailsJSON=result.getString("Contract_Detail_Json");
            log.info("manufacturerDetails of Respective ManufacturerID is  " + contractDetailsJSON + " From DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**
     * This method used to match ContractDetailJSON with Api response JSON
     * @author Bharath
     * @param JSON
     */
    public void matchContractDetailsJSON(String JSON){
        String jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "contractHeaderDetailsJSON");
        log.info("contractDetailsJson response is");
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues(getContractDetailsByIDResponse,contractHeaderDetails,jsonPath);
    }

    /**
     * This method used to match ContractDetailJSON with Api response JSON
     * @author Bharath
     * @param apiJSON
     * @param dbJSON
     */
    public void matchContractDetailsJSONwithTwoJSONPAths(String apiJSON,String dbJSON){
        if(apiJSON.equalsIgnoreCase("APIcontractDetailsJSONSchemaversion")||
                apiJSON.equalsIgnoreCase("APIcontractDetailsJSONSubmissionWindow")||
                                apiJSON.equalsIgnoreCase("APIcontractDetailsJSONPaymentTerms"))
        {
        String apiJsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, apiJSON);
        String dbJsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, dbJSON);
        log.info("contractDetailsJson response is");
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithIntDataTypeValues(getContractDetailsByIDResponse,contractDetailsJSON,apiJsonPath,dbJsonPath);
        }
        else if (apiJSON.equalsIgnoreCase("APIcontractDetailsJSONLocations") ||
                apiJSON.equalsIgnoreCase("APIcontractDetailsJSONLineOfBusiness")
        )

        {
            {
                String apiJsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, apiJSON);
                String dbJsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, dbJSON);
                log.info("contractDetailsJson response is");
                verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithStringDataTypeValues(getContractDetailsByIDResponse,contractDetailsJSON,apiJsonPath,dbJsonPath);
            }
        }
        else if (apiJSON.equalsIgnoreCase("APIcontractDetailsJSONBillingCycle")||
        apiJSON.equalsIgnoreCase("APIcontractDetailsJSONResubmissionWindow"))
        {
            String apiJsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, apiJSON);
            String dbJsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, dbJSON);
            log.info("contractDetailsJson response is");
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithonlyStringDataTypeValues(getContractDetailsByIDResponse,contractDetailsJSON,apiJsonPath,dbJsonPath);
        }
        else if(apiJSON.equalsIgnoreCase("APIcontractDetailsJSONOpsAssignee")||
                apiJSON.equalsIgnoreCase("APIcontractDetailsJSONOpsQCer")){
            {
                String apiJsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, apiJSON);
                String dbJsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, dbJSON);
                log.info("contractDetailsJson response is");
                verificationHelperClass.verifyAPIResponseJsonWithDBJsonNullValues(getContractDetailsByIDResponse,contractDetailsJSON,apiJsonPath,dbJsonPath);
            }

        }
        else if(apiJSON.equalsIgnoreCase("APIcontractDetailsJSONThirdPartyAuth")){
            {
                String apiJsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, apiJSON);
                String dbJsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, dbJSON);
                log.info("contractDetailsJson response is");
                verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithBooleanDataTypeValues(getContractDetailsByIDResponse,contractDetailsJSON,apiJsonPath,dbJsonPath);
            }

        }
        else {
            {
                String apiJsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, apiJSON);
                String dbJsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, dbJSON);
                log.info("contractDetailsJson response is");
                verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithObjectDataTypeValues(getContractDetailsByIDResponse,contractDetailsJSON,apiJsonPath,dbJsonPath);
            }

        }
        }
    public void validateJSONResponse(String query,String columnName){
       try{ String actualquery=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES,query);
            String actualColumnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
            String conRowKey=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForContractRowkey");
            String conLCS=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForContractLCS");
            String contractStartDate=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForContractStartDate");
            String contractEndDate=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForContractEndDate");
            String recordCreatedBy=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForRecordCreatedBy");
            String recordUpdatedBy=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForRecordUpdatedBy");
            String contractID=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForContractID");
            String amendmentNumber=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForAmendmentNumber");
            String amendmentName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForAmendmentName");
            String versionNumber=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForVersionNumber");
            String contractDetailJson=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForContractDetailJson");
            String contractExpiry=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForContractExpiry");
            String contractType=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForContractType");
            String contractName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForContractName");
            String contractNotes=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForContractNotes");
            String manufacturerName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForManufacturerName");
            String iRManager=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonForIRManager");
            String accountManager=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"JsonForAccountManager");

            result=dbHepler.executePreparedQuery(query,String.valueOf(rowKeyVal));
            result.next();
            String dbJson=result.getString(actualColumnName);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,conRowKey,conRowKey);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,conLCS,conLCS);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,contractStartDate,contractStartDate);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,contractEndDate,contractEndDate);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,recordCreatedBy,recordCreatedBy);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,recordUpdatedBy,recordUpdatedBy);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,contractID,contractID);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,amendmentNumber,amendmentNumber);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,amendmentName,amendmentName);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,versionNumber,versionNumber);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,contractDetailJson,contractDetailJson);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,contractExpiry,contractExpiry);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,contractType,contractType);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,contractName,contractName);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,contractNotes,contractNotes);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,manufacturerName,manufacturerName);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,iRManager,iRManager);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getContractDetailsByIDResponse,dbJson,accountManager,accountManager);
        }catch (Exception e){
            e.printStackTrace();
        }

        }

    public void  validateTask(){
        dbHepler.connectToOtherDB("flowable");
        getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"");
        dbHepler.executePreparedQuery("","");
        dbHepler.disConnectToFlowable();
    }
    }






























//
//    public String getEnvProperties() {
//        try {
//            prop = new Properties();
//            //load a properties file from class path, inside static method
//            prop.load(GetAllMFR.class.getClassLoader()
//                    .getResourceAsStream("environment.properties"));
//            baseURI = prop.getProperty("baseUri");
//            logger.info(" Base URI is :" + baseURI);
//            token = prop.getProperty("bearerToken");
//            logger.info(" Bearer token is :" + token);
//            getAllContractDetailsByID= prop.getProperty("getContractDetailByID").trim();
//            logger.info(" MFRContractDetailsByID Resource :" + getAllContractDetailsByID);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return baseURI;
//    }
//
//    /**
//     * This method gets the URL from Environment.Property file GetContractDetailsByID API and RowKey from DB and merge together and give complete endpoint
//     * @author Bharath
//     */
//    public void hitGetContractDetailsByIDEndpoint() {
//        getEnvProperties();
//        rowKeyVal=dbUtil.getRowKey();
//        url = baseURI + "/" +getAllContractDetailsByID+"/"+rowKeyVal;
//        logger.info("=======URL is++++++++++++++++++++++++++ " + url);
//    }
//
//    /**
//     * This method gets the URL from Environment.Property file GetContractDetailsByID API and RowKey from DB and make it Invalid merge together and give complete endpoint
//     * @author Bharath
//     */
//    public void hitGetContractDetailsByIDEndpointWithInvalidRowKey(){
//        getEnvProperties();
//        rowKeyVal=dbUtil.getCountOfContractsDetails();
//        url = baseURI + "/" +getAllContractDetailsByID+"/"+(rowKeyVal+3);
//        logger.info("=======URL is++++++++++++++++++++++++++ " + url);
//    }
//
//    public void hitBlankContractDetailsByIDEndpoint() {
//        getEnvProperties();
//        rowKeyVal=dbUtil.getRowKey();
//        url = baseURI + "/" +getAllContractDetailsByID+"/";
//        logger.info("=======URL is++++++++++++++++++++++++++ " + url);
//    }
//
//    public void hitTypeMissMAtchContractDetailsByIDEndpoint() {
//        getEnvProperties();
//        rowKeyValTypeMissMatch=dbUtil.getContractID();
//        url = baseURI + "/" +getAllContractDetailsByID+"/"+rowKeyValTypeMissMatch;
//        logger.info("=======URL is++++++++++++++++++++++++++ " + url);
//    }
//
//    /**
//     * This method proccesses the request and stores the response
//     * @author Bharath
//     */
//    public void processGetRequest() {
//
//        response =
//                given().log().all().header("Authorization", "Bearer " + token).when().get(url);
//        logger.info("Response is:" + response.asString());
//    }
//
//    /**
//     * This method Verifies the request status is 200 or not
//     * @author Bharath
//     */
//
//    public void verifyGetRequestStatusCode200() {
//        response.then().assertThat().statusCode(200);
//        logger.info("API returns 200 http response code");
//    }
//
//    /**
//     * This method Verifies Response is JSON or NOT
//     * @author Bharath
//     */
//    public void verifyResponseIsInJSONformat() {
//        response.then().assertThat().contentType(ContentType.JSON);
//        logger.info("The response is in proper JSON format");
//    }
//
//    /**
//     * This method match the Manufacturer_ID and Name from API response and DB
//     * @author Bharath
//     */
//    public void matchmanufacturerDetails() {
//        actualMFRNameAndID =new ArrayList<String>();
//        ExpectedMFRNameAndID=new ArrayList<String>();
//        actualMFRNameAndID=JsonPath.read(response.asString(), "$.manufacturer[*]");
//        ExpectedMFRNameAndID=dbUtil.getManufacturerName();
//        Collections.sort(ExpectedMFRNameAndID);
//        Collections.sort(actualMFRNameAndID);
//        logger.info("Expected ManufactuerName and ID is:");
//        for(String em:ExpectedMFRNameAndID){
//            logger.info(em);
//        }
//        logger.info("Actual ManufactuerName and ID from API are:");
//        for(String ac:actualMFRNameAndID){
//            logger.info(ac);
//        }
//        logger.info("Matching All Manufactuer Name and ID from DB and API ");
//        Assert.assertTrue("The lists do not match!", ExpectedMFRNameAndID.equals(actualMFRNameAndID));
//        logger.info("Successfully matched  All MFR IDS from DB and API ");
//    }
//    /**
//     * This method match the ContracHeadertDetails from API response and DB
//     * @author Bharath
//     */
//    public void matchContracHeadertDetails() {
//        actualContracHeadertdetails =new ArrayList();
//        expectedContractHeaderdetails=new ArrayList<String>();
//        actualContracHeadertdetails=JsonPath.read(response.asString(), "$.contractHeader[*]");
//        expectedContractHeaderdetails=dbUtil.getContractHeaderDetails();
//        for(int i=0;i<expectedContractHeaderdetails.size();i++){
//            if(i==4||i==6){
//                expectedContractHeaderdetails.set(i,expectedContractHeaderdetails.get(i).replaceAll(" ","T"));
//            }
//        }
//
//        logger.info("Actual ContractetailsHeader from API are:");
//        for(Object ac:actualContracHeadertdetails){
//            logger.info(ac);
//        }
//        List obj= new ArrayList(expectedContractHeaderdetails);
//        logger.info("Expected ContractetailsHeader is:");
//        for(Object em:obj){
//            logger.info(em);
//        }
//
//
//        boolean a=obj.equals(actualContracHeadertdetails);
//        logger.info("Successfully matched  All MFR IDS from DB and API "+a);
////        Assert.assertTrue("The lists do not match!", expectedContractHeaderdetails.equals(actualContracHeadertdetails));
////        logger.info("Successfully matched  All MFR IDS from DB and API ");
//    }
//    /**
//     * This method match the ContractDetailJSON from API response and DB
//     * @author Bharath
//     */
//    public void matchContractDetailJSON() {
//
//        try{actualContractDetailsJSON= JsonPath.read(response.asString(), "$.contractDetail.contractDetailJson");
//        expectedContractDetailsJSON=dbUtil.getContractDetailsJSON();
//        logger.info("Expected ContractDetailJSON is:"+expectedContractDetailsJSON);
//        logger.info("Actual ContractDetailJSON is:"+actualContractDetailsJSON);
//        Assert.assertTrue("JSON Do Not match",expectedContractDetailsJSON.equals(actualContractDetailsJSON));
//        logger.info("Successfully matched  ContractHeaderDetailJSon from DB and API ");}
//        catch (Exception e){
//        e.printStackTrace();
//        }
//}
//    public void matchInvalidRowKeyResponse() {
//        try{actualInvalidRowKeyContractDetailsResponse=JsonPath.read(response.asString(), "$.message");
//            expectedInvalidRowKeyContractDetailsResponse="Incorrect result size: expected 1, actual 0";
//            logger.info("Expected response is:"+expectedInvalidRowKeyContractDetailsResponse);
//            logger.info("Actual Conresponse is:"+actualInvalidRowKeyContractDetailsResponse);
//            Assert.assertTrue("JSON Do Not match",actualInvalidRowKeyContractDetailsResponse.equals(expectedInvalidRowKeyContractDetailsResponse));
//            logger.info("Successfully matched Invalid API response ");}
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public void matchBlankRowKeyResponse() {
//        try{actualBlankRowKeyContractDetailsResponse=JsonPath.read(response.asString(), "$.message");
//            expectedBlankRowKeyContractDetailsResponse="404 NOT_FOUND";
//            logger.info("Expected response is:"+actualBlankRowKeyContractDetailsResponse);
//            logger.info("Actual Conresponse is:"+expectedBlankRowKeyContractDetailsResponse);
//            Assert.assertTrue("JSON Do Not match",actualBlankRowKeyContractDetailsResponse.equals(expectedBlankRowKeyContractDetailsResponse));
//            logger.info("Successfully matched Invalid API response ");}
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public void matchTypeMissmatchRowKeyResponse() {
//        try{actualBlankRowKeyContractDetailsResponse=JsonPath.read(response.asString(), "$.message");
//            expectedBlankRowKeyContractDetailsResponse="400 BAD_REQUEST \\\"Type mismatch.\\\"; nested exception is org.springframework.beans.TypeMismatchException: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; nested exception is java.lang.NumberFormatException: For input string: \\\"ABV0011144\\\"";
//            logger.info("Expected response is:"+actualBlankRowKeyContractDetailsResponse);
//            logger.info("Actual Conresponse is:"+expectedBlankRowKeyContractDetailsResponse);
//            Assert.assertTrue("JSON Do Not match",actualBlankRowKeyContractDetailsResponse.equals(expectedBlankRowKeyContractDetailsResponse));
//            logger.info("Successfully matched Invalid API response ");}
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
