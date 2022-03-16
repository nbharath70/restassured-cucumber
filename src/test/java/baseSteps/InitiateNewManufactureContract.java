package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.UtilitiesClass;
import HelperClass.VerificationHelperClass;
import RequestPojo.*;
import RequestPojo.DisContractPojo.DiscardContractPojo;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import java.io.FileReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.util.*;


public class InitiateNewManufactureContract extends TestBase {
    Response response;
    String contractId;
    int rowKeyContractDetail;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(InitiateNewManufactureContract.class);
    InitManufactureContract initManufactureContractObject;
    DiscardContractPojo discardContractPojo;
    Response discardContractResponse;
    private int createContractAttempts=0;
    JSONObject jsonObject;
    UtilitiesClass utilityMethods = new UtilitiesClass();
    JSONObject requestPayLoad;


    /**
     * initiateMFRContract method is used to
     * @Bharath
     * @return list - list of Contract Id Created and row key created
     */
    public List<String> initiateContract() {
        List<String> list=new ArrayList<String>();
        try {
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir")+"\\src\\test\\testdata\\initiateMFRContract.json");
            Object obj=jsonParser.parse(reader);
            jsonObject=(JSONObject) obj;

        }catch (Exception e){e.printStackTrace();}
        return list;
    }

//    public void hitAPI(String endpoint){
//
//    }

//    /**
//     * createNewManufactureContract Method is used for create nee manufacture contract request pay load
//     * @uthor Arun Kumar
//     * @param dataTable
//     */
//    public void createNewManufactureContract(DataTable dataTable)
//    {
//        try {
//            List<Map<String, String>> initManufactureContractData = dataTable.asMaps(String.class, String.class);
//            for (Map<String, String> map : initManufactureContractData) {
//                ContractDetailJson contractDetailJson1 = new ContractDetailJson();
//                contractDetailJson1.setLineOfBusiness(map.get("lineOfBusiness"));
//                ArrayList lineOfBusiness = contractDetailJson1.getLineOfBusiness();
//                Manufacturer manufacturer = new Manufacturer(map.get("ManufacturerId"), map.get("name"));
//                ContractHeader contractHeader = new ContractHeader(map.get("contractName"),map.get("contractType"), map.get("startDate"), map.get("endDate"),map.get("notes"),map.get("lifecycleStatus"));
//                ContractDetailJson contractDetailJson = new ContractDetailJson( lineOfBusiness);
//                ContractDetail contractDetail = new ContractDetail(contractDetailJson);
//                initManufactureContractObject = new InitManufactureContract(manufacturer, contractHeader, contractDetail);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    /**
     * This method is used to initiateNewManufactureContractPostCall
     * @uthor Arun Kumar
     * @param endPoint
     */
    public void initiateNewManufactureContractPostCall(String endPoint)
    {
        response = postOperation(endPoint, requestPayLoad);
    }

    /**
     * This method is used to validate the status code of InitiateNewManufactureContractStatusCode
     * @uthor Arun Kumar
     * @ModifiedBy  Gudi
     * @param statusCode
     */
    public void postInitiateNewManufactureContractStatusCode(int statusCode)
    {
        boolean blnStatus = verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("PostInitiateNewManufactureContract StatusCode is " + statusCode + " and its Pass");

        if(blnStatus == false && (contractIdNewlyCreated.size() != 0)){
            for(int i=0; i<contractIdNewlyCreated.size(); i++){
                deleteContractRecordFromDB("deleteContractHeaderByContractName", "deleteContractDetailByAmendmentName", contractIdNewlyCreated.get(i).toString());
                Assert.assertTrue(false, "Validation was failed. Hence stopping the scenario: '" + scenarioName+"'");
            }
            Assert.assertTrue(false, "Validation was failed. Hence stopping the scenario: '" + scenarioName+"'");
        }
    }

    /**
     * This validationResults used to validate the respanse body value as boolean
     * @uthor Arun Kumar
     * @param actualValue
     * @param expectedValue
     * @ModifiedBy  Gudi
     */
    public void validationResults(String actualValue,String expectedValue)
    {
        boolean blnStatus = verificationHelperClass.verifyResponseJsonBoolean(response,actualValue,expectedValue);
        if(blnStatus == false && (contractIdNewlyCreated.size() != 0)){
            for(int i=0; i<contractIdNewlyCreated.size(); i++){
                deleteContractRecordFromDB("deleteContractHeaderByContractName", "deleteContractDetailByAmendmentName", contractIdNewlyCreated.get(i).toString());
            }
            Assert.assertTrue(false, "Validation was failed. Hence stopping the scenario: '" + scenarioName+"'");
        }else if(blnStatus == false && (contractIdNewlyCreated.size() ==0)){
            Assert.assertTrue(false, "Validation was failed. Hence stopping the scenario: '" + scenarioName+"'");
        }
    }

    /**
     * deleteContractRecordFromDB methos used to delete the record details in contract header table amd contract detail table by given contract & Amendemnt name
     * @uthor Arun kumar
     * @param contractHeaderQuery
     * @param contractDetailQuery
     * @param contractName
     */
    public void deleteContractRecordFromDB(String contractHeaderQuery,String contractDetailQuery,String contractName)
    {
        dataBaseHelper.executeUpdatePreparedQueryAsString(contractHeaderQuery,contractName);
        dataBaseHelper.executeUpdatePreparedQueryAsString(contractDetailQuery,contractName);
    }


    /**
     * deleteContractRecordFromDB methos used to delete the record details in contract header table and contract detail table by given contract_Id from runTime
     * @uthor Gudi
     * @param contractHeaderQuery
     * @param contractDetailQuery
     *
     */
    public void deleteContractRecordFromDB(String contractHeaderQuery,String contractDetailQuery)
    {
        for(int i=0; i<contractIdNewlyCreated.size(); i++){
            deleteContractRecordFromDB(contractHeaderQuery, contractDetailQuery, contractIdNewlyCreated.get(i).toString());
        }
    }


    /**
     * This validationResultsString used to validate the respanse body value as String
     * @uthor Arun Kumar
     * @param actualValue
     * @param expectedValue
     */
    public void validationResultsString(String actualValue,String expectedValue)
    {
        verificationHelperClass.verifyResponseJsonString(response,actualValue,expectedValue);
    }

    /**
     * This method is used to discard the manufacture contract
     * @uthor Arun Kumar
     * @param endpoint
     * @param rowKey
     * @ModifiedBy  Gudi
     */
    public void discardContract(String endpoint,String rowKey,String ContractName)
    {
        ResultSet getContractId;
        try {
            if(ContractName.isEmpty()){
                ContractName = contractDataRequiredToDelete.get(1).toString();
            }
            getContractId = dataBaseHelper.executePreparedQuery("getContractIdByContractName", ContractName);
            getContractId.next();
            contractId = getContractId.getString("Contract_ID");
            ResultSet getRowKeyContractDetails = dataBaseHelper.executePreparedQuery("getRowKeyByAmendmentName",ContractName);
            getRowKeyContractDetails.next();
            rowKeyContractDetail = Integer.valueOf(getRowKeyContractDetails.getString("Row_Key"));
            discardContractPojo=new DiscardContractPojo("undefined",contractId,rowKeyContractDetail);
            discardContractResponse = deleteOperation(endpoint, discardContractPojo);
            log.info("Response is "+discardContractResponse.asString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method is used verifyIfIsManufacturerContractDiscarded
     * @uthor Arun Kumar
     */
    public void verifyIfIsManufacturerContractIDDiscarded()
    {
        String isContractDiscarded=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "jasonPathForIsDiscardContract");
        verificationHelperClass.verifyAPIResponseBooleanValueTrue(discardContractResponse,isContractDiscarded);

    }

     /**
     * This method is used validate verifyInitiateNewManufactureContractResponseHeaderErrorCode
     * @uthor ArunKumar
      * @ModifiedBy Gudi
     * @param expectedHeaderValue
     */
    public void verifyInitiateNewManufactureContractResponseHeaderErrorCode(String expectedHeaderValue)
    {
        boolean blnStatus = verificationHelperClass.verifyResponseHeaderApiReturnCodesValue(response,expectedHeaderValue);
        if(blnStatus == false && (contractIdNewlyCreated.size() !=0)){
            for(int i=0; i<contractIdNewlyCreated.size(); i++){
                deleteContractRecordFromDB("deleteContractHeaderByContractName", "deleteContractDetailByAmendmentName", contractIdNewlyCreated.get(i).toString());
            }
            Assert.assertTrue(false, "Validation was failed. Hence stopping the scenario: '" + scenarioName+"'");
        }else if(blnStatus == false && (contractIdNewlyCreated.size() ==0)){
            Assert.assertTrue(false, "Validation was failed. Hence stopping the scenario: '" + scenarioName+"'");
        }
    }



    public JSONObject newContractRequestPayload(DataTable dataTable){
        try{
            contractDataRequiredToDelete = new ArrayList<Object>();
            List<Map<String, String>> contractData = dataTable.asMaps(String.class, String.class);
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\testdata\\initiateMFRContract.json");
            Object obj=jsonParser.parse(reader);
            JSONObject jsonObject=(JSONObject) obj;
            Map<String, Object> map=new HashMap<>();

            //Verify that only the required key should be updated in the json payload
            if(!contractData.get(0).get("ManufacturerId").isEmpty()) map.put("$.manufacturer.manufacturerId", contractData.get(0).get("ManufacturerId"));
            if(!contractData.get(0).get("ManufacturerName").isEmpty()) map.put("$.manufacturer.name", contractData.get(0).get("ManufacturerName"));
            if(!contractData.get(0).get("contractType").isEmpty()) map.put("$.contractHeader.contractType", contractData.get(0).get("contractType"));
            String contractName = "QAAutomation" + UtilitiesClass.getDateTime("YYYYMMddhhmmss");
            map.put("$.contractHeader.contractName", contractName);
            if(!contractData.get(0).get("startDate").isEmpty()) map.put("$.contractHeader.startDate", contractData.get(0).get("startDate"));
            if(!contractData.get(0).get("endDate").isEmpty()) map.put("$.contractHeader.endDate", contractData.get(0).get("endDate"));
            if(!contractData.get(0).get("notes").isEmpty()) map.put("$.contractHeader.notes", contractData.get(0).get("notes"));

            if(!contractData.get(0).get("autoRenewFlag").isEmpty()) map.put("$.contractHeader.autoRenewFlag", contractData.get(0).get("autoRenewFlag"));

            if(contractData.get(0).get("autoRenewFlag").equalsIgnoreCase("Y")){
                if(!contractData.get(0).get("autoRenewTerm").isEmpty()) map.put("$.contractHeader.autoRenewTerm", contractData.get(0).get("autoRenewTerm"));
                if(!contractData.get(0).get("autoRenewNotifyDate").isEmpty()) map.put("$.contractHeader.autoRenewNotifyDate", contractData.get(0).get("autoRenewNotifyDate"));
            }else{
                if(!contractData.get(0).get("autoRenewTerm").isEmpty()) map.put("$.contractHeader.autoRenewTerm", "");
                if(!contractData.get(0).get("autoRenewNotifyDate").isEmpty()) map.put("$.contractHeader.autoRenewNotifyDate", "");
            }

            if(!contractData.get(0).get("lineOfBusiness").isEmpty()) map.put("$.contractDetail.contractDetailJson.lineOfBusiness", UtilitiesClass.constructStringArrayForJsonPayload(contractData.get(0).get("lineOfBusiness")));
            if(!contractData.get(0).get("locations").isEmpty()) map.put("$.contractDetail.contractDetailJson.locations", UtilitiesClass.constructStringArrayForJsonPayload(contractData.get(0).get("locations")));

            contractDataRequiredToDelete.add(0, contractData.get(0).get("ManufacturerId"));
            contractDataRequiredToDelete.add(1, contractName);
            contractDataRequiredToDelete.add(2, contractData.get(0).get("startDate"));
            contractDataRequiredToDelete.add(3, contractData.get(0).get("ManufacturerName"));
            log.info("Details required to fetch the 'Contract_Id' which is used to delete the created Contract : "+ contractDataRequiredToDelete);

            requestPayLoad = utilityMethods.jsonValueReplacer(jsonObject,map);
        }catch(Exception e){
            e.printStackTrace();
        }
        return requestPayLoad;
    }

}
