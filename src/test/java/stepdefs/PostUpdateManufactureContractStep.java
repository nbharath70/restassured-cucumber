package stepdefs;

import HelperClass.DataBaseHelper;
import RequestPojo.*;
import TestBase.TestBase;
import baseSteps.PostUpdateManufactureContract;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostUpdateManufactureContractStep extends TestBase {
    PostUpdateManufactureContract putUpdateManufactureContract=new PostUpdateManufactureContract();
    InitManufactureContract InitManufactureContractObject;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    @Then("^User update manufacture contract data$")
    public void userUpdateManufactureContractData(DataTable dataTable) {
        try {
            List<Map<String, String>> Date1 = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : Date1) {
                ResultSet res = dataBaseHelper.executePreparedQuery("getContractIdByContractName", map.get("contractName"));
                res.next();
                String contractId=res.getString("Contract_ID");
                ResultSet res2 = dataBaseHelper.executePreparedQuery("getRowKeyByContractName", map.get("contractName"));
                res2.next();
                String rowKey=res2.getString("Row_Key");
                ContractDetailJson obj1 = new ContractDetailJson();
                obj1.setLineOfBusiness(map.get("lineOfBusiness"));
                ArrayList val = obj1.getLineOfBusiness();
                Manufacturer manufacturerObject = new Manufacturer(map.get("ManufacturerId"), map.get("name"), Boolean.valueOf(map.get("currentFlag")));
                ContractHeader ContractHeaderObject = new ContractHeader(Float.parseFloat(rowKey) ,contractId, map.get("ManufacturerId"), map.get("contractType"), map.get("contractName"), map.get("startDate"), map.get("endDate"), map.get("recCreatedDate"), map.get("recCreatedBy"), map.get("recUpdatedDate"), map.get("recUpdatedBy"), map.get("lifecycleStatus"), map.get("contractDocReference"), map.get("notes"));
                Payment paymentObj = new Payment(Float.parseFloat(map.get("disputeDays")), map.get("lateFee"), map.get("lateFeeFixed"), map.get("lateFeePct"), Boolean.valueOf(map.get("paymentBackup")), map.get("NCPDPReconFile"));
                Audit auditObj = new Audit(map.get("frequency"), Float.parseFloat(map.get("lookback")), map.get("numScreenshots"), Boolean.valueOf(map.get("allowThirdPartyAuditor")), Boolean.valueOf(map.get("auditScreenshots")));
                ContractDetailJson ContractDetailJsonObject = new ContractDetailJson(Float.parseFloat(map.get("schemaVersion")), val, map.get("locations"), map.get("billingCycle"), Float.parseFloat(map.get("submissionWindow")), Float.parseFloat(map.get("resubmissionWindow")), Float.parseFloat(map.get("paymentTerms")), Boolean.valueOf(map.get("thirdPartyAuth")), map.get("opsAssignee"), map.get("opsQCer"), paymentObj, auditObj);
                ContractDetail ContractDetailObject = new ContractDetail(Float.parseFloat(rowKey),contractId, Float.parseFloat(map.get("amendmentNumber")), map.get("amendmentName"), map.get("lifecycleStatus"), map.get("startDate"), map.get("endDate"), Float.parseFloat(map.get("versionNumber")), map.get("recCreatedBy"), map.get("recCreatedDate"), map.get("recUpdatedBy"), map.get("recUpdatedDate"), ContractDetailJsonObject);
                InitManufactureContractObject = new InitManufactureContract(manufacturerObject, ContractHeaderObject, ContractDetailObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Then("^User hits the \"([^\"]*)\" update manufacture contract post request$")
    public void userHitsTheUpdateManufactureContractPostRequest(String endPoint) throws Throwable {
        putUpdateManufactureContract.updateManufactureContractPutCall(endPoint, InitManufactureContractObject);
    }

    @Then("^User verify the updateManufacturerContract valid Response body key \"([^\"]*)\" and expected value \"([^\"]*)\"$")
    public void userVerifyTheUpdateManufacturerContractValidResponseBodyKeyAndExpectedValue(String actualValue,String expectedValue) throws Throwable {
        putUpdateManufactureContract.validationResultsBooleanforUpdateManfCont(actualValue,expectedValue);
    }

    @Then("^User verify the updateManufacturerContract valid Response body key \"([^\"]*)\" and expected value \"([^\"]*)\" of string$")
    public void userVerifyTheUpdateManufacturerContractValidResponseBodyKeyAndExpectedValueOfString(String actualValue,String expectedValue) throws Throwable {
        putUpdateManufactureContract.validationResultsStringforUpdateManfCont(actualValue,expectedValue);
    }

    @Then("^User verify UpdateManufacturerContract status code \"([^\"]*)\" for the response$")
    public void userVerifyUpdateManufacturerContractStatusCodeForTheResponse(int statusCode) throws Throwable {
        putUpdateManufactureContract.postUpdateManufactureContractStatusCode(statusCode);
    }
}
