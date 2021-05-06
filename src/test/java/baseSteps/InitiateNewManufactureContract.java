package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.VerificationHelperClass;
import RequestPojo.*;
import TestBase.TestBase;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class InitiateNewManufactureContract extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(InitiateNewManufactureContract.class);
    InitManufactureContract initManufactureContractObject;

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
                Manufacturer manufacturerObject = new Manufacturer(map.get("ManufacturerId"), map.get("name"), Boolean.valueOf(map.get("currentFlag")));
                ContractHeader contractHeaderObject = new ContractHeader(Integer.valueOf(map.get("rowKey")), map.get("contractId"), map.get("ManufacturerId"), map.get("contractType"), map.get("contractName"), map.get("startDate"), map.get("endDate"), map.get("recCreatedDate"), map.get("recCreatedBy"), map.get("recUpdatedDate"), map.get("recUpdatedBy"), map.get("lifecycleStatus"), map.get("contractDocReference"), map.get("notes"));
                Payment paymentObj = new Payment(Integer.valueOf(map.get("disputeDays")), map.get("lateFee"), map.get("lateFeeFixed"), map.get("lateFeePct"), Boolean.valueOf(map.get("paymentBackup")), map.get("NCPDPReconFile"));
                Audit auditObj = new Audit(map.get("frequency"), Integer.valueOf(map.get("lookback")), map.get("numScreenshots"), Boolean.valueOf(map.get("allowThirdPartyAuditor")), Boolean.valueOf(map.get("auditScreenshots")));
                ContractDetailJson contractDetailJsonObject = new ContractDetailJson(Integer.valueOf(map.get("schemaVersion")), lineOfBusiness, map.get("locations"), map.get("billingCycle"), Integer.valueOf(map.get("submissionWindow")), Integer.valueOf(map.get("resubmissionWindow")), Integer.valueOf(map.get("paymentTerms")), Boolean.valueOf(map.get("thirdPartyAuth")), map.get("opsAssignee"), map.get("opsQCer"), paymentObj, auditObj);
                ContractDetail ContractDetailObject = new ContractDetail(Integer.valueOf(map.get("rowKey")), map.get("contractId"), Integer.valueOf(map.get("submissionWindow")), map.get("amendmentName"), map.get("lifecycleStatus"), map.get("startDate"), map.get("endDate"), Integer.valueOf(map.get("versionNumber")), map.get("recCreatedBy"), map.get("recCreatedDate"), map.get("recUpdatedBy"), map.get("recUpdatedDate"), contractDetailJsonObject);
                initManufactureContractObject = new InitManufactureContract(manufacturerObject, contractHeaderObject, ContractDetailObject);
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

}
