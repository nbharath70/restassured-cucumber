package stepdefs;

import TestBase.TestBase;
import baseSteps.InitiateNewManufactureContract;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InitNewManufacturerContractStepDefs extends TestBase {
    InitiateNewManufactureContract postInitiateNewManufactureContract=new InitiateNewManufactureContract();
    @Given("^User create the Initiate New Manufacture Contract date$")
    public void userCreateTheInitiateNewManufactureContractDate(DataTable dataTable) {
        postInitiateNewManufactureContract.createNewManufactureContract(dataTable);
    }

    @Then("^User hits the \"([^\"]*)\" with post request$")
    public void userHitsTheWithPostRequest(String endPoint) throws Throwable {
        postInitiateNewManufactureContract.initiateNewManufactureContractPostCall(endPoint);
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

    @When("^User hits the \"([^\"]*)\" Endpoint with delete API request to discard Initiate New Manufacture Contract by \"([^\"]*)\" and contractName \"([^\"]*)\"$")
    public void userHitsTheEndpointWithDeleteAPIRequestToDiscardInitiateNewManufactureContractByAndContractName(String endPoint, String rowKey,String ContractName) throws Throwable {
        postInitiateNewManufactureContract.discardContract(endPoint,rowKey,ContractName);
    }

    @Then("^User verifies the isManufacturerContractDiscarded as true value in response for new manufacture contract$")
    public void userVerifiesTheIsManufacturerContractDiscardedAsTrueValueInResponseForNewManufactureContract() {
        postInitiateNewManufactureContract.verifyIfIsManufacturerContractIDDiscarded();
    }

    @Then("^User verify the InitiateNewManufacturerContract response header Error Code value \"([^\"]*)\"$")
    public void userVerifyTheInitiateNewManufacturerContractResponseHeaderErrorCodeValue(String expectedErrorCode) throws Throwable {
        postInitiateNewManufactureContract.verifyInitiateNewManufactureContractResponseHeaderErrorCode(expectedErrorCode);
    }
}
