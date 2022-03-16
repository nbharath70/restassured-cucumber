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
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import java.io.FileReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.util.*;

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
    UtilitiesClass utilityMethods = new UtilitiesClass();
    JSONObject requestPayLoad;

    /**
     * updateExistingManufactureContractDetails Method is used to update the manufacture contract detail data for init new manufacture contract
     * @uthor       Arun Kumar
     *  @param      dataTable
     *
     */
    public void updateExistingManufactureContractDetails_Old(DataTable dataTable)
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



    /***************************************************************************************************
     * @Author      Gudi
     * @Purpose     updateExistingManufactureContractDetails Method is used to update the manufacture contract detail data for init new manufacture contract
     * @param       dataTable
     * @ReturnType  JSONObject
     ******************************************************************************************************
     */
    public void updateExistingManufactureContractDetails(DataTable dataTable)
    {
        try {
            List<Map<String, String>> contractAppendData = dataTable.asMaps(String.class, String.class);
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\testdata\\updateContractAndSendToRebateOps.json");
            Object obj=jsonParser.parse(reader);
            JSONObject jsonObject=(JSONObject) obj;
            Map<String, Object> map=new HashMap<>();

            List<Map<String, String>> getContractHeaderData = dataBaseHelper.getQueryResultsInListMap("getContractHeaderData", contractIdNewlyCreated);
            List<Map<String, String>> getContractDetailsData = dataBaseHelper.getQueryResultsInListMap("getContractDetailsData", contractIdNewlyCreated);

            //Verify that only the required key should be updated in the json payload
            if(!contractAppendData.get(0).get("manufacturerId").isEmpty()){
                map.put("$.manufacturer.manufacturerId", contractAppendData.get(0).get("manufacturerId"));
            }else{
                map.put("$.manufacturer.manufacturerId", getContractHeaderData.get(0).get("Manufacturer_ID"));
            }

            if(!contractAppendData.get(0).get("manufacturerId").isEmpty()){
                map.put("$.manufacturer.manufacturerId", contractAppendData.get(0).get("manufacturerId"));
            }else{
                map.put("$.manufacturer.manufacturerId", getContractHeaderData.get(0).get("Manufacturer_ID"));
            }

            if(!contractAppendData.get(0).get("manufactureName").isEmpty()){
                map.put("$.manufacturer.name", contractAppendData.get(0).get("manufactureName"));
            }else{
                map.put("$.manufacturer.name", contractDataRequiredToDelete.get(3));
            }

            if(!contractAppendData.get(0).get("contractName").isEmpty()){
                map.put("$.contractDetail.amendmentName", contractAppendData.get(0).get("contractName"));
                map.put("$.contractHeader.contractName", contractAppendData.get(0).get("contractName"));
            }else{
                map.put("$.contractDetail.amendmentName", getContractHeaderData.get(0).get("Contract_Name"));
                map.put("$.contractHeader.contractName", getContractHeaderData.get(0).get("Contract_Name"));
            }

            if(!contractAppendData.get(0).get("startDate").isEmpty()){
                map.put("$.contractDetail.startDate", contractAppendData.get(0).get("startDate"));
                map.put("$.contractHeader.startDate", contractAppendData.get(0).get("startDate"));
                map.put("$.manufacturer.startDate", contractAppendData.get(0).get("startDate"));
            }else{
                map.put("$.contractDetail.startDate", getContractHeaderData.get(0).get("Start_date"));
                map.put("$.contractHeader.startDate", getContractHeaderData.get(0).get("Start_date"));
                map.put("$.manufacturer.startDate", getContractHeaderData.get(0).get("Start_date"));
            }

            if(!contractAppendData.get(0).get("endDate").isEmpty()){
                map.put("$.contractDetail.endDate", contractAppendData.get(0).get("endDate"));
                map.put("$.contractHeader.endDate", contractAppendData.get(0).get("endDate"));
                map.put("$.manufacturer.endDate", contractAppendData.get(0).get("endDate"));
            }else{
                map.put("$.contractDetail.endDate", getContractHeaderData.get(0).get("End_Date"));
                map.put("$.contractHeader.endDate", getContractHeaderData.get(0).get("End_Date"));
                map.put("$.manufacturer.endDate", getContractHeaderData.get(0).get("End_Date"));
            }

            if(!contractAppendData.get(0).get("mfr_recCreatedDate").isEmpty()){
                map.put("$.manufacturer.recCreatedDate", contractAppendData.get(0).get("mfr_recCreatedDate"));
            }else{
                map.put("$.manufacturer.recCreatedDate", (getContractDetailsData.get(0).get("Rec_Created_Date").split("T"))[1]);
            }

            if(!contractAppendData.get(0).get("mfr_recUpdatedDate").isEmpty()){
                map.put("$.manufacturer.recUpdatedDate", contractAppendData.get(0).get("mfr_recUpdatedDate"));
            }else{
                map.put("$.manufacturer.recUpdatedDate", (getContractDetailsData.get(0).get("Rec_Updated_Date").split("T"))[1]);
            }

            if(!contractAppendData.get(0).get("wf_taskId").isEmpty()) map.put("$.workFlowRequestInfo.taskId", contractAppendData.get(0).get("wf_taskId"));
            if(!contractAppendData.get(0).get("wf_approve").isEmpty()) map.put("$.workFlowRequestInfo.approve", contractAppendData.get(0).get("wf_approve"));

            if(!contractAppendData.get(0).get("recCreatedBy").isEmpty()){
                map.put("$.manufacturer.recCreatedBy", contractAppendData.get(0).get("recCreatedBy"));
                map.put("$.contractDetail.recCreatedBy", contractAppendData.get(0).get("recCreatedBy"));
            }else{
                map.put("$.manufacturer.recCreatedBy", getContractHeaderData.get(0).get("Rec_Created_By"));
                map.put("$.contractDetail.recCreatedBy", getContractHeaderData.get(0).get("Rec_Created_By"));
            }

            if(!contractAppendData.get(0).get("recUpdatedBy").isEmpty()){
                map.put("$.manufacturer.recUpdatedBy", contractAppendData.get(0).get("recUpdatedBy"));
                map.put("$.contractDetail.recUpdatedBy", contractAppendData.get(0).get("recUpdatedBy"));
            }else{
                map.put("$.manufacturer.recUpdatedBy", getContractHeaderData.get(0).get("Rec_Updated_By"));
                map.put("$.contractDetail.recUpdatedBy", getContractHeaderData.get(0).get("Rec_Updated_By"));
            }

            if(!contractAppendData.get(0).get("rowKey").isEmpty()){
                map.put("$.contractHeader.rowKey", contractAppendData.get(0).get("rowKey"));
                map.put("$.contractDetail.rowKey", contractAppendData.get(0).get("rowKey"));
            }else{
                map.put("$.contractHeader.rowKey", getContractHeaderData.get(0).get("Row_Key"));
                map.put("$.contractDetail.rowKey", getContractHeaderData.get(0).get("Row_Key"));
            }

            if(!contractAppendData.get(0).get("lifeCycleStatus").isEmpty()){
                map.put("$.contractHeader.lifeCycleStatus", contractAppendData.get(0).get("lifeCycleStatus"));
                map.put("$.contractDetail.lifeCycleStatus", contractAppendData.get(0).get("lifeCycleStatus"));
            }else{
                map.put("$.contractHeader.lifeCycleStatus", getContractHeaderData.get(0).get("Lifecycle_Status"));
                map.put("$.contractDetail.lifeCycleStatus", getContractHeaderData.get(0).get("Lifecycle_Status"));
            }

            if(!contractAppendData.get(0).get("ch_recCreatedDate").isEmpty()){
                map.put("$.contractHeader.ch_recCreatedDate", contractAppendData.get(0).get("ch_recCreatedDate"));
            }else{
                map.put("$.contractHeader.ch_recCreatedDate", getContractHeaderData.get(0).get("Rec_Created_Date"));
            }

            if(!contractAppendData.get(0).get("ch_recUpdatedDate").isEmpty()){
                map.put("$.contractHeader.recUpdatedDate", contractAppendData.get(0).get("ch_recUpdatedDate"));
            }else{
                map.put("$.contractHeader.recUpdatedDate", getContractHeaderData.get(0).get("Rec_Updated_Date"));
            }

            if(!contractAppendData.get(0).get("contractID").isEmpty()){
                map.put("$.contractHeader.contractID", contractAppendData.get(0).get("contractID"));
                map.put("$.contractDetail.contractID", contractAppendData.get(0).get("contractID"));
            }else{
                map.put("$.contractHeader.contractID", getContractHeaderData.get(0).get("Contract_ID"));
                map.put("$.contractDetail.contractID", getContractHeaderData.get(0).get("Contract_ID"));
            }

            if(!contractAppendData.get(0).get("ch_contractType").isEmpty()){
                map.put("$.contractHeader.contractType", contractAppendData.get(0).get("ch_contractType"));
            }else{
                map.put("$.contractHeader.contractType", getContractHeaderData.get(0).get("Contract_Type"));
            }

            if(!contractAppendData.get(0).get("ch_contractDocReference").isEmpty()){
                map.put("$.contractHeader.contractDocReference", contractAppendData.get(0).get("ch_contractDocReference"));
            }else{
                map.put("$.contractHeader.contractDocReference", getContractHeaderData.get(0).get("Contract_Doc_Reference"));
            }

            if(!contractAppendData.get(0).get("ch_autoRenewFlag").isEmpty()){
                map.put("$.contractHeader.autoRenewFlag", contractAppendData.get(0).get("ch_autoRenewFlag"));
                map.put("$.contractHeader.autoRenewTerm", contractAppendData.get(0).get("ch_autoRenewTerm"));
                map.put("$.contractHeader.autoRenewNotifyDate", contractAppendData.get(0).get("ch_autoRenewNotifyDate"));

            }else{
                map.put("$.contractHeader.autoRenewFlag", getContractHeaderData.get(0).get("Auto_Renew_Flag"));
                map.put("$.contractHeader.autoRenewTerm", getContractHeaderData.get(0).get("Auto_Renew_Flag"));
                map.put("$.contractHeader.autoRenewNotifyDate", getContractHeaderData.get(0).get("Auto_Renew_Flag"));
            }

            if(!contractAppendData.get(0).get("ch_notes").isEmpty()){
                map.put("$.contractHeader.notes", contractAppendData.get(0).get("ch_notes"));
            }else{
                map.put("$.contractHeader.notes", getContractHeaderData.get(0).get("Notes"));
            }

            if(!contractAppendData.get(0).get("cd_recCreatedDate").isEmpty()){
                map.put("$.contractDetail.recCreatedDate", contractAppendData.get(0).get("cd_recCreatedDate"));
            }else{
                map.put("$.contractDetail.recCreatedDate", getContractDetailsData.get(0).get("Rec_Created_date"));
            }

            if(!contractAppendData.get(0).get("cd_recUpdatedDate").isEmpty()){
                map.put("$.contractDetail.recUpdatedDate", contractAppendData.get(0).get("cd_recUpdatedDate"));
            }else{
                map.put("$.contractDetail.recUpdatedDate", getContractDetailsData.get(0).get("Rec_Updated_date"));
            }

            if(!contractAppendData.get(0).get("cd_amendmentNumber").isEmpty()){
                map.put("$.contractDetail.amendmentNumber", contractAppendData.get(0).get("cd_amendmentNumber"));
            }else{
                map.put("$.contractDetail.amendmentNumber", getContractDetailsData.get(0).get("Amendment_Number"));
            }

            if(!contractAppendData.get(0).get("cd_amendmentName").isEmpty()){
                map.put("$.contractDetail.amendmentName", contractAppendData.get(0).get("cd_amendmentName"));
            }else{
                map.put("$.contractDetail.amendmentName", getContractDetailsData.get(0).get("Amendment_Name"));
            }

            if(!contractAppendData.get(0).get("cd_versionNumber").isEmpty()){
                map.put("$.contractDetail.versionNumber", contractAppendData.get(0).get("cd_versionNumber"));
            }else{
                map.put("$.contractDetail.versionNumber", getContractDetailsData.get(0).get("Version_Number"));
            }

            if(!contractAppendData.get(0).get("cd_contractExpired").isEmpty()) map.put("$.contractDetail.contractExpired", contractAppendData.get(0).get("cd_contractExpired"));
            if(!contractAppendData.get(0).get("cdjson_schemaVersion").isEmpty()) map.put("$.contractDetail.contractDetailJson.schemaVersion", contractAppendData.get(0).get("cdjson_schemaVersion"));
            if(!contractAppendData.get(0).get("cdjson_lineOfBusiness").isEmpty()) map.put("$.contractDetail.contractDetailJson.lineOfBusiness", UtilitiesClass.constructStringArrayForJsonPayload(contractAppendData.get(0).get("cdjson_lineOfBusiness")));
            if(!contractAppendData.get(0).get("cdjson_locations").isEmpty()) map.put("$.contractDetail.contractDetailJson.locations", UtilitiesClass.constructStringArrayForJsonPayload(contractAppendData.get(0).get("cdjson_locations")));
            if(!contractAppendData.get(0).get("cdjson_billingCycle").isEmpty()) map.put("$.contractDetail.contractDetailJson.billingCycle", contractAppendData.get(0).get("cdjson_billingCycle"));
            if(!contractAppendData.get(0).get("cdjson_submissionWindow").isEmpty()) map.put("$.contractDetail.contractDetailJson.submissionWindow", contractAppendData.get(0).get("cdjson_submissionWindow"));
            if(!contractAppendData.get(0).get("cdjson_resubmissionWindow").isEmpty()) map.put("$.contractDetail.contractDetailJson.resubmissionWindow", contractAppendData.get(0).get("cdjson_resubmissionWindow"));
            if(!contractAppendData.get(0).get("cdjson_thirdPartyAuth").isEmpty()) map.put("$.contractDetail.contractDetailJson.thirdPartyAuth", contractAppendData.get(0).get("cdjson_thirdPartyAuth"));

            if(!contractAppendData.get(0).get("cdjson_requestMarketBasket").isEmpty()) {
                if(contractAppendData.get(0).get("cdjson_requestMarketBasket").equalsIgnoreCase("True")) {
                    map.put("$.contractDetail.contractDetailJson.requestMarketBasket", contractAppendData.get(0).get("cdjson_requestMarketBasket"));
                    map.put("$.contractDetail.contractDetailJson.manageMarketBasket", null);
                }else if(contractAppendData.get(0).get("cdjson_requestMarketBasket").equalsIgnoreCase("False")){
                    map.put("$.contractDetail.contractDetailJson.requestMarketBasket", contractAppendData.get(0).get("cdjson_requestMarketBasket"));
                    if(!contractAppendData.get(0).get("cdjson_manageMarketBasket").isEmpty()) map.put("$.contractDetail.contractDetailJson.manageMarketBasket", contractAppendData.get(0).get("cdjson_manageMarketBasket"));
                }
            }

            if(!contractAppendData.get(0).get("cdjson_paymentTerms").isEmpty()) map.put("$.contractDetail.contractDetailJson.paymentTerms", contractAppendData.get(0).get("cdjson_paymentTerms"));
            if(!contractAppendData.get(0).get("cdjson_disputeDays").isEmpty()) map.put("$.contractDetail.contractDetailJson.disputeDays", contractAppendData.get(0).get("cdjson_disputeDays"));
            if(!contractAppendData.get(0).get("cdjson_lateFee").isEmpty()) map.put("$.contractDetail.contractDetailJson.lateFee", contractAppendData.get(0).get("cdjson_lateFee"));
            if(!contractAppendData.get(0).get("cdjson_lateFeeFixed").isEmpty()) map.put("$.contractDetail.contractDetailJson.lateFeeFixed", contractAppendData.get(0).get("cdjson_lateFeeFixed"));
            if(!contractAppendData.get(0).get("cdjson_resetPayment").isEmpty()) map.put("$.contractDetail.contractDetailJson.resetPayment", contractAppendData.get(0).get("cdjson_resetPayment"));
            if(!contractAppendData.get(0).get("cdjson_paymentBackup").isEmpty()) map.put("$.contractDetail.contractDetailJson.paymentBackup", contractAppendData.get(0).get("cdjson_paymentBackup"));
            if(!contractAppendData.get(0).get("cdjson_NCPDPReconFile").isEmpty()) map.put("$.contractDetail.contractDetailJson.NCPDPReconFile", contractAppendData.get(0).get("cdjson_NCPDPReconFile"));
            if(!contractAppendData.get(0).get("cdjson_frequency").isEmpty()) map.put("$.contractDetail.contractDetailJson.frequency", contractAppendData.get(0).get("cdjson_frequency"));
            if(!contractAppendData.get(0).get("cdjson_lookback").isEmpty()) map.put("$.contractDetail.contractDetailJson.lookback", contractAppendData.get(0).get("cdjson_lookback"));
            if(!contractAppendData.get(0).get("cdjson_auditScreenshots").isEmpty()) map.put("$.contractDetail.contractDetailJson.auditScreenshots", contractAppendData.get(0).get("cdjson_auditScreenshots"));
            if(!contractAppendData.get(0).get("cdjson_allowThirdPartyAuditor").isEmpty()) map.put("$.contractDetail.contractDetailJson.allowThirdPartyAuditor", contractAppendData.get(0).get("cdjson_allowThirdPartyAuditor"));

            requestPayLoad = utilityMethods.jsonValueReplacer(jsonObject,map);
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
//        procIns=dataBaseHelper.executeUpdatePreparedQueryAsStringFlowable(env,query,contractId,procInsColumnName);
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
