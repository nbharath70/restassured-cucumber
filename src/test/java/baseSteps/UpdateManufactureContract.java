package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.*;
import RequestPojo.DisContractPojo.DiscardContractPojo;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateManufactureContract extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(InitiateNewManufactureContract.class);
    InitManufactureContract initManufactureContractObject;
    private String contractName=null;
    String contractId;
    DiscardContractPojo discardContractPojo;
    Response discardContractResponse;
    String procIns;

    /**
     * updateExistingManufactureContractDetails Method is used to update the manufacture contract detail data for init new manufacture contract
     * @uthor Arun Kumar
     *  @param dataTable
     */
    public void updateExistingManufactureContractDetails(DataTable dataTable)
    {
        try {
            List<Map<String, String>> updateManufactureDetailData = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : updateManufactureDetailData) {
                contractName = map.get("contractName");
                ResultSet getContractId = dataBaseHelper.executePreparedQuery("getContractIdByContractName", map.get("contractName"));
                getContractId.next();
                contractId=getContractId.getString("Contract_ID");
                ResultSet getRowKeyContractHeader = dataBaseHelper.executePreparedQuery("getRowKeyByContractName", map.get("contractName"));
                getRowKeyContractHeader.next();
                String rowKeyContractHeader=getRowKeyContractHeader.getString("Row_Key");
                ResultSet getRowKeyContractDetail = dataBaseHelper.executePreparedQuery("getRowKeyByAmendmentName", map.get("contractName"));
                getRowKeyContractDetail.next();
                String rowKeyContractDetail=getRowKeyContractDetail.getString("Row_Key");
                ContractDetailJson contractDetailJson1 = new ContractDetailJson();
                contractDetailJson1.setLineOfBusiness(map.get("lineOfBusiness"));
                ArrayList lineOfBusiness = contractDetailJson1.getLineOfBusiness();
                contractDetailJson1.setLocations(map.get("locations"));
                ArrayList locations = contractDetailJson1.getLocations();
                Manufacturer manufacturerObject = new Manufacturer(map.get("ManufacturerId"), map.get("name"), Boolean.valueOf(map.get("currentFlag")));
                ContractHeader contractHeaderObject = new ContractHeader(Integer.valueOf(rowKeyContractHeader) ,contractId, map.get("ManufacturerId"), map.get("contractType"), map.get("contractName"), map.get("startDate"), map.get("endDate"), map.get("recCreatedDate"), map.get("recCreatedBy"), map.get("recUpdatedDate"), map.get("recUpdatedBy"), map.get("lifecycleStatus"), map.get("contractDocReference"), map.get("notes"));
                Payment paymentObj = new Payment(Integer.valueOf(map.get("disputeDays")), map.get("lateFee"), map.get("lateFeeFixed"), map.get("lateFeePct"), Boolean.valueOf(map.get("paymentBackup")), map.get("NCPDPReconFile"));
                Audit auditObj = new Audit(map.get("frequency"), Integer.valueOf(map.get("lookback")), map.get("numScreenshots"), Boolean.valueOf(map.get("allowThirdPartyAuditor")), Boolean.valueOf(map.get("auditScreenshots")));
                ContractDetailJson contractDetailJsonObject = new ContractDetailJson(Integer.valueOf(map.get("schemaVersion")), lineOfBusiness,locations, map.get("billingCycle"), Integer.valueOf(map.get("submissionWindow")), Integer.valueOf(map.get("resubmissionWindow")), Integer.valueOf(map.get("paymentTerms")), Boolean.valueOf(map.get("thirdPartyAuth")), map.get("opsAssignee"), map.get("opsQCer"), paymentObj, auditObj);
                ContractDetail contractDetailObject = new ContractDetail(Integer.valueOf(rowKeyContractDetail),contractId, Integer.valueOf(map.get("amendmentNumber")), map.get("amendmentName"), map.get("lifecycleStatus"), map.get("startDate"), map.get("endDate"), Integer.valueOf(map.get("versionNumber")), map.get("recCreatedBy"), map.get("recCreatedDate"), map.get("recUpdatedBy"), map.get("recUpdatedDate"), contractDetailJsonObject);
                initManufactureContractObject = new InitManufactureContract(manufacturerObject, contractHeaderObject, contractDetailObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * This method is used to updateManufactureContractPutCall
     * @uthor Arun Kumar
     * @param endPoint
     */
    public void updateManufactureContractPutCall(String endPoint)
    {
        response = postOperation(endPoint, initManufactureContractObject);
    }

    /**
     * This validationResults used to validate the respanse body value as boolean
     * @uthor Arun Kumar
     * @param actualValue
     * @param expectedValue
     */
    public void validationResultsBooleanforUpdateManfCont(String actualValue,String expectedValue)
    {
        verificationHelperClass.verifyResponseJsonBoolean(response,actualValue,expectedValue);
    }

    /**
     * This validationResultsString used to validate the respanse body value as String
     * @uthor Arun Kumar
     * @param actualValue
     * @param expectedValue
     */
    public void validationResultsStringforUpdateManfCont(String actualValue,String expectedValue)
    {
        verificationHelperClass.verifyResponseJsonString(response,actualValue,expectedValue);
    }

    /**
     * This method is used to validate the status code of postUpdateManufactureContractStatusCode
     * @uthor Arun Kumar
     * @param statusCode
     */
    public void postUpdateManufactureContractStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("PostUpdateManufactureContract StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * This method is used validate verifyUpdateManufactureContractResponseHeaderErrorCode
     * @uthor ArunKumar
     * @param expectedHeaderValue
     */
    public void verifyUpdateManufactureContractResponseHeaderErrorCode(String expectedHeaderValue)
    {
        verificationHelperClass.verifyResponseHeaderApiReturnCodesValue(response,expectedHeaderValue);
    }

    public void executeTheQueryAndValidateForLCS(String queryKey, String expectedLCS) {
        try {
            ResultSet resultSetforLCS = dataBaseHelper.executePreparedQuery(queryKey, contractName);
            resultSetforLCS.next();
            String headerLCSfromDB = resultSetforLCS.getString("headerLCS");
            Assert.assertEquals("Life Cycle Status of contract do not match! in Contract Header table ", headerLCSfromDB,expectedLCS);
            log.info("Life Cycle Status pass in Contract Header where expectedValue=" + expectedLCS + " equals to actualValue=" + headerLCSfromDB);
            String detailLCSfromDB = resultSetforLCS.getString("detailLCS");
            Assert.assertEquals("Life Cycle Status of contract do not match! in Contract Detail table ", detailLCSfromDB,expectedLCS);
            log.info("Life Cycle Status pass in Contract Detail where expectedValue=" + expectedLCS + " equals to actualValue=" + detailLCSfromDB);

        }catch (Exception e){e.printStackTrace();}
    }

    public void verifyProcessInstID(String env,String query)
    {
        String procInsColumnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"procInsColumnName");
        procIns=dataBaseHelper.executeUpdatePreparedQueryAsStringFlowable(env,query,contractId,procInsColumnName);
        String val = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "processInstanceIdJsonPath");
        Object actualValue = JsonPath.read(response.asString(), val);
        Object expValue = procIns;
        log.info("Verify response body where expectedValue=" + expValue + " equals to actualValue=" + procIns);
        Assert.assertTrue("The lists do not match!", expValue.equals(procIns));
    }

    public void verifyContractStatus() {
        try {
            ResultSet getContractLCS = dataBaseHelper.executePreparedQuery("getContractLifecycleStatus", contractId);
            getContractLCS.next();
            String LifecycleStatus = getContractLCS.getString("Lifecycle_Status");
            String expected=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"sendToRebateOpsLSC");
            Assert.assertEquals("Validate the Life Cycle status of contract",expected,LifecycleStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is used to discard the manufacture contract
     * @uthor Arun Kumar
     * @param endpoint
     */
    public void discardContract(String endpoint,String ContractName)
    {
        try {
            ResultSet getContractId = dataBaseHelper.executePreparedQuery("getContractIdByContractName", ContractName);
            getContractId.next();
            String contractId = getContractId.getString("Contract_ID");
            ResultSet getRowKeyContractHeader = dataBaseHelper.executePreparedQuery("getRowKeyByContractName",ContractName);
            getRowKeyContractHeader.next();
            int rowKeyContractHeader = Integer.valueOf(getRowKeyContractHeader.getString("Row_Key"));
            discardContractPojo=new DiscardContractPojo(procIns,contractId,rowKeyContractHeader);
            discardContractResponse = deleteOperation(endpoint, discardContractPojo);
            log.info("Response is "+discardContractResponse.asString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
