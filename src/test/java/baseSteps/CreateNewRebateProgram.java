package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.*;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateNewRebateProgram extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(CreateNewRebateProgram.class);
    CreateNewRebateProgramPojo createNewRebateProgramPojo;
    String contractId;

    /**
     * createNewRebateProgramDetailsData Method is used to create data for create new rebate program
     * @uthor Arun Kumar
     *  @param dataTable
     */
    public void createNewRebateProgramDetailsData(DataTable dataTable)
    {
        try {
            List<Map<String, String>> createNewRebateProgramData = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : createNewRebateProgramData) {
                ResultSet getRebateableMFR_DrugList_ID = dataBaseHelper.executePreparedQuery("getMfrDrugListIdByListType", "Rebateable");
                getRebateableMFR_DrugList_ID.next();
                String rebateableMFR_DrugList_ID=getRebateableMFR_DrugList_ID.getString("MFR_DrugList_ID");
                ResultSet getQualificationMFR_DrugList_ID = dataBaseHelper.executePreparedQuery("getMfrDrugListIdByListType", "Qualification");
                getQualificationMFR_DrugList_ID.next();
                String qualificationMFR_DrugList_ID=getQualificationMFR_DrugList_ID.getString("MFR_DrugList_ID");
                ResultSet getMarketBasketFR_DrugList_ID = dataBaseHelper.executePreparedQuery("getMfrDrugListIdByListType", "MarketBasket");
                getMarketBasketFR_DrugList_ID.next();
                String marketBasketMFR_DrugList_ID=getMarketBasketFR_DrugList_ID.getString("MFR_DrugList_ID");
                ResultSet getContractId = dataBaseHelper.executePreparedQuery("getContractIdByContractName", map.get("contractName"));
                getContractId.next();
                contractId=getContractId.getString("Contract_ID");
                if(map.get("condition").equalsIgnoreCase("mandatory")) {
                        ProgramDetailJson programDetailJson = new ProgramDetailJson(map.get("programName"), Integer.valueOf(rebateableMFR_DrugList_ID), Integer.valueOf(qualificationMFR_DrugList_ID), Integer.valueOf(marketBasketMFR_DrugList_ID), map.get("pricingBasis"), map.get("pricingRefDate"),Boolean.valueOf(map.get("psfApply")),Float.parseFloat(map.get("programLevelPsf")));
                        Program program = new Program(contractId, map.get("startDate"), programDetailJson);
                        createNewRebateProgramPojo = new CreateNewRebateProgramPojo(program);
                }
                else if(map.get("condition").equalsIgnoreCase("invalidRebateableDrugGroupId")) {
                    ProgramDetailJson programDetailJson = new ProgramDetailJson(map.get("programName"), map.get("legacyContractID"), Integer.valueOf(map.get("rebateableDrugGroupId")), Integer.valueOf(qualificationMFR_DrugList_ID), Integer.valueOf(marketBasketMFR_DrugList_ID), map.get("pricingBasis"), map.get("pricingRefDate"), Boolean.valueOf(map.get("psfApply")), Float.parseFloat(map.get("programLevelPsf")));
                    Program program = new Program(contractId, map.get("startDate"), map.get("endDate"), programDetailJson);
                    createNewRebateProgramPojo=new CreateNewRebateProgramPojo(program);
                }
                else if(map.get("condition").equalsIgnoreCase("invalidQualCompDrugGroupId")) {
                    ProgramDetailJson programDetailJson = new ProgramDetailJson(map.get("programName"), map.get("legacyContractID"), Integer.valueOf(rebateableMFR_DrugList_ID), Integer.valueOf(map.get("qualCompDrugGroupId")), Integer.valueOf(marketBasketMFR_DrugList_ID), map.get("pricingBasis"), map.get("pricingRefDate"), Boolean.valueOf(map.get("psfApply")), Float.parseFloat(map.get("programLevelPsf")));
                    Program program = new Program(contractId, map.get("startDate"), map.get("endDate"), programDetailJson);
                    createNewRebateProgramPojo=new CreateNewRebateProgramPojo(program);
                }
                else if(map.get("condition").equalsIgnoreCase("invalidMktBasketDrugGroupId")) {
                    ProgramDetailJson programDetailJson = new ProgramDetailJson(map.get("programName"), map.get("legacyContractID"), Integer.valueOf(rebateableMFR_DrugList_ID), Integer.valueOf(qualificationMFR_DrugList_ID), Integer.valueOf(map.get("mktBasketDrugGroupId")), map.get("pricingBasis"), map.get("pricingRefDate"), Boolean.valueOf(map.get("psfApply")), Float.parseFloat(map.get("programLevelPsf")));
                    Program program = new Program(contractId, map.get("startDate"), map.get("endDate"), programDetailJson);
                    createNewRebateProgramPojo=new CreateNewRebateProgramPojo(program);
                }
                else{
                    ProgramDetailJson programDetailJson = new ProgramDetailJson(map.get("programName"), map.get("legacyContractID"), Integer.valueOf(rebateableMFR_DrugList_ID), Integer.valueOf(qualificationMFR_DrugList_ID), Integer.valueOf(marketBasketMFR_DrugList_ID), map.get("pricingBasis"), map.get("pricingRefDate"), Boolean.valueOf(map.get("psfApply")), Float.parseFloat(map.get("programLevelPsf")));
                    Program program = new Program(contractId, map.get("startDate"), map.get("endDate"), programDetailJson);
                    createNewRebateProgramPojo=new CreateNewRebateProgramPojo(program);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * createNewRebateProgramPostCall method is used to hit the endpoint
     * @uthor Arun Kumar
     * @param endPoint
     */
    public void createNewRebateProgramPostCall(String endPoint)
    {
        response = postOperation(endPoint, createNewRebateProgramPojo);
    }

    /**
     * deleteRebateProgramFromDB method used to delete the record details of existing rebate program by given Contract_ID
     * @uthor Arun kumar
     * @param deleteRebateProgramQuery
     * @param contractID
     */
    public void deleteRebateProgramFromDB(String deleteRebateProgramQuery,String contractID)
    {
        dataBaseHelper.executeUpdatePreparedQueryAsString(deleteRebateProgramQuery,contractID);
    }

    /**
     * This method used to updateContractRecordNewToInProgressStatusFromDB in contract header table amd contract detail table by given contract & Amendemnt name
     * @uthor Arun kumar
     * @param contractHeaderQuery
     * @param contractDetailQuery
     * @param contractName
     */
    public void updateContractRecordToInProgressStatusFromDB(String contractHeaderQuery,String contractDetailQuery,String contractName)
    {
        dataBaseHelper.executeUpdatePreparedQueryAsString(contractHeaderQuery,contractName);
        dataBaseHelper.executeUpdatePreparedQueryAsString(contractDetailQuery,contractName);
    }

    public void valid(String query,String jsonPath,String columnName) throws SQLException {
        String columnNameValue = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnName);
        ResultSet getResultMFR_Program_ID = dataBaseHelper.executePreparedQuery(query, contractId);
        getResultMFR_Program_ID.next();
        String getMFR_Program_IDJson=getResultMFR_Program_ID.getString(columnNameValue);
        String prgramIDJsonValue=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,jsonPath);
        Integer prgramIDJsonValueDB=JsonPath.read(response.asString(),prgramIDJsonValue);
        System.out.println(prgramIDJsonValueDB);
        System.out.println(getMFR_Program_IDJson);
        Assert.assertEquals("Error code value do not match!", prgramIDJsonValueDB,getMFR_Program_IDJson);
        log.info("Verification MFR_Prpgram_ID value pass where expectedValue=" + getMFR_Program_IDJson + " equals to actualValue=" + prgramIDJsonValueDB);
    }
    /**
     * This method is used to validate the status code of createNewRebateProgramPostCallStatusCode
     * @uthor Arun Kumar
     * @param statusCode
     */
    public void createNewRebateProgramPostCallStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("createNewRebateProgramPostCall StatusCode is " + statusCode + " and its Pass");
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
     * This method is used validate verifyCreateNewRebateProgramResponseHeaderErrorCode
     * @uthor ArunKumar
     * @param expectedHeaderValue
     */
    public void verifyCreateNewRebateProgramResponseHeaderErrorCode(String expectedHeaderValue)
    {
        verificationHelperClass.verifyResponseHeaderApiReturnCodesValue(response,expectedHeaderValue);
    }

}
