package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.*;
import RequestPojo.DisContractPojo.DiscardContractPojo;
import TestBase.TestBase;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

    /**
     * createNewManufactureContract Method is used for create nee manufacture contract request pay load
     * @uthor Arun Kumar
     * @param dataTable
     */
    public void createNewManufactureContract(DataTable dataTable)
    {
        try {
            List<Map<String, String>> initManufactureContractData = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : initManufactureContractData) {
                ContractDetailJson contractDetailJson1 = new ContractDetailJson();
                contractDetailJson1.setLineOfBusiness(map.get("lineOfBusiness"));
                ArrayList lineOfBusiness = contractDetailJson1.getLineOfBusiness();
                Manufacturer manufacturer = new Manufacturer(map.get("ManufacturerId"), map.get("name"));
                ContractHeader contractHeader = new ContractHeader(map.get("contractName"),map.get("contractType"), map.get("startDate"), map.get("endDate"),map.get("notes"),map.get("lifecycleStatus"));
                ContractDetailJson contractDetailJson = new ContractDetailJson( lineOfBusiness);
                ContractDetail contractDetail = new ContractDetail(contractDetailJson);
                initManufactureContractObject = new InitManufactureContract(manufacturer, contractHeader, contractDetail);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method is used to initiateNewManufactureContractPostCall
     * @uthor Arun Kumar
     * @param endPoint
     */
    public void initiateNewManufactureContractPostCall(String endPoint)
    {
        response = postOperation(endPoint, initManufactureContractObject);
    }

    /**
     * This method is used to validate the status code of InitiateNewManufactureContractStatusCode
     * @uthor Arun Kumar
     * @param statusCode
     */
    public void postInitiateNewManufactureContractStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("PostInitiateNewManufactureContract StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * This validationResults used to validate the respanse body value as boolean
     * @uthor Arun Kumar
     * @param actualValue
     * @param expectedValue
     */
    public void validationResults(String actualValue,String expectedValue)
    {
        verificationHelperClass.verifyResponseJsonBoolean(response,actualValue,expectedValue);
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
     */
    public void discardContract(String endpoint,String rowKey,String ContractName)
    {
        try {
            ResultSet getContractId = dataBaseHelper.executePreparedQuery("getContractIdByContractName", ContractName);
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
     * @param expectedHeaderValue
     */
    public void verifyInitiateNewManufactureContractResponseHeaderErrorCode(String expectedHeaderValue)
    {
        verificationHelperClass.verifyResponseHeaderApiReturnCodesValue(response,expectedHeaderValue);
    }

}
