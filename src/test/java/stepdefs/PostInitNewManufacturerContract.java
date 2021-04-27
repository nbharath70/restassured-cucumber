package stepdefs;

import HelperClass.DataBaseHelper;
import RequestPojo.*;
import TestBase.TestBase;
import baseSteps.PostInitiateNewManufactureContract;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostInitNewManufacturerContract extends TestBase {
    PostInitiateNewManufactureContract postInitiateNewManufactureContract=new PostInitiateNewManufactureContract();
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    InitManufactureContract InitManufactureContractObject;
    List<List<String>> initiateNewManufactureContractDate1;
    @Given("^User create the Initiate New Manufacture Contract date$")
    public void userCreateTheInitiateNewManufactureContractDate(DataTable dataTable) {
        try {
            List<Map<String, String>> Date1 = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : Date1) {
                ContractDetailJson obj1 = new ContractDetailJson();
                obj1.setLineOfBusiness(map.get("lineOfBusiness"));
                ArrayList val = obj1.getLineOfBusiness();
                Manufacturer manufacturerObject = new Manufacturer(map.get("ManufacturerId"), map.get("name"), Boolean.valueOf(map.get("currentFlag")));
                ContractHeader ContractHeaderObject = new ContractHeader(Float.parseFloat(map.get("rowKey")), map.get("contractId"), map.get("ManufacturerId"), map.get("contractType"), map.get("contractName"), map.get("startDate"), map.get("endDate"), map.get("recCreatedDate"), map.get("recCreatedBy"), map.get("recUpdatedDate"), map.get("recUpdatedBy"), map.get("lifecycleStatus"), map.get("contractDocReference"), map.get("notes"));
                Payment paymentObj = new Payment(Float.parseFloat(map.get("disputeDays")), map.get("lateFee"), map.get("lateFeeFixed"), map.get("lateFeePct"), Boolean.valueOf(map.get("paymentBackup")), map.get("NCPDPReconFile"));
                Audit auditObj = new Audit(map.get("frequency"), Float.parseFloat(map.get("lookback")), map.get("numScreenshots"), Boolean.valueOf(map.get("allowThirdPartyAuditor")), Boolean.valueOf(map.get("auditScreenshots")));
                ContractDetailJson ContractDetailJsonObject = new ContractDetailJson(Float.parseFloat(map.get("schemaVersion")), val, map.get("locations"), map.get("billingCycle"), Float.parseFloat(map.get("submissionWindow")), Float.parseFloat(map.get("resubmissionWindow")), Float.parseFloat(map.get("paymentTerms")), Boolean.valueOf(map.get("thirdPartyAuth")), map.get("opsAssignee"), map.get("opsQCer"), paymentObj, auditObj);
                ContractDetail ContractDetailObject = new ContractDetail(Float.parseFloat(map.get("rowKey")), map.get("contractId"), Float.parseFloat(map.get("submissionWindow")), map.get("amendmentName"), map.get("lifecycleStatus"), map.get("startDate"), map.get("endDate"), Float.parseFloat(map.get("versionNumber")), map.get("recCreatedBy"), map.get("recCreatedDate"), map.get("recUpdatedBy"), map.get("recUpdatedDate"), ContractDetailJsonObject);
                InitManufactureContractObject = new InitManufactureContract(manufacturerObject, ContractHeaderObject, ContractDetailObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Then("^User hits the \"([^\"]*)\" with post request$")
    public void userHitsTheWithPostRequest(String endPoint) throws Throwable {
        postInitiateNewManufactureContract.initiateNewManufactureContractPostCall(endPoint, InitManufactureContractObject);
    }

    @Then("^User verify InitiateNewManufacturerContract status code \"([^\"]*)\" for the response$")
    public void userVerifyPostInitiateNewManufacturerContractStatusCodeForTheResponse(int statusCode) throws Throwable {
        postInitiateNewManufactureContract.postInitiateNewManufactureContractStatusCode(statusCode);
    }

    @Then("^User verify the valid Response body key \"([^\"]*)\" and expected value \"([^\"]*)\"$")
    public void userVerifyTheValidResponseBodyKeyValueAndExpectedValue(String actualValue,String expectedValue) throws Throwable {
        postInitiateNewManufactureContract.validationResults(actualValue,expectedValue);
    }


    @Then("^User executes the query \"([^\"]*)\" and \"([^\"]*)\" for contract & Amendment name \"([^\"]*)\" to delete the record from the database$")
    public void userExecutesTheQueryAndForContractAmendmentNameToDeleteTheRecordFromTheDatabase(String contractHeaderQuery,String contractDetailQuery,String contractName) throws Throwable {
        postInitiateNewManufactureContract.deleteContractRecordFromDB(contractHeaderQuery,contractDetailQuery,contractName);
    }

    @Then("^User verify the valid Response body key \"([^\"]*)\" and expected value \"([^\"]*)\" of string$")
    public void userVerifyTheValidResponseBodyKeyAndExpectedValueOfString(String actualValue,String expectedValue) throws Throwable {
        postInitiateNewManufactureContract.validationResultsString(actualValue,expectedValue);
    }
}
