package stepdefs;

import TestBase.TestBase;
import baseSteps.UpdateManufactureContract;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateManufactureContractStepDefs extends TestBase {
    UpdateManufactureContract putUpdateManufactureContract=new UpdateManufactureContract();

    @Then("^User update manufacture contract data$")
    public void userUpdateManufactureContractData(DataTable dataTable) {
        putUpdateManufactureContract.updateExistingManufactureContractDetails(dataTable);
    }

    @Then("^User hits the \"([^\"]*)\" update manufacture contract post request$")
    public void userHitsTheUpdateManufactureContractPostRequest(String endPoint) throws Throwable {
        putUpdateManufactureContract.updateManufactureContractPutCall(endPoint);
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

    @Then("^User verify the updateManufacturerContract response header Error Code value \"([^\"]*)\"$")
    public void userVerifyTheUpdateManufacturerContractResponseHeaderErrorCodeValue(String expectedErrorCode) throws Throwable {
        putUpdateManufactureContract.verifyUpdateManufactureContractResponseHeaderErrorCode(expectedErrorCode);
    }

    // Glue code for Update Mfr contract and send to rebate Ops
    @Then("^User executes the query \"([^\"]*)\" and validates for LCS \"([^\"]*)\"$")
    public void userExecutesTheQueryAndValidatesForLCS(String queryKey, String expectedLCS) throws Throwable {
        putUpdateManufactureContract.executeTheQueryAndValidateForLCS(queryKey,expectedLCS);
    }

    @Then("^User connect to \"([^\"]*)\" and verify processInsID by execute query \"([^\"]*)\"$")
    public void userConnectToAndVerifyProcessInsIDByExecuteQuery(String env, String processInstID) throws Throwable {
        putUpdateManufactureContract.verifyProcessInstID(env,processInstID);
    }

    @Then("^User verify the LifeCycle status of contract as UnAssigned$")
    public void userVerifyTheLifeCycleStatusOfContractAsUnAssigned() {
        putUpdateManufactureContract.verifyContractStatus();
    }

}
