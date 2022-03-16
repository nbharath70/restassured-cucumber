package stepdefs;

import TestBase.TestBase;
import baseSteps.InitiateNewManufactureContract;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InitNewManufacturerContractStepDefs extends TestBase {
    InitiateNewManufactureContract postInitiateNewManufactureContract=new InitiateNewManufactureContract();
    @And("^User creates the New Manufacture Contract data$")
    public void userCreateTheInitiateNewManufactureContractDate() {
        postInitiateNewManufactureContract.initiateContract();
    }



    @Then("^User hits the \"([^\"]*)\" with post request$")
    public void userHitsTheWithPostRequest(String endPoint){
        postInitiateNewManufactureContract.initiateNewManufactureContractPostCall(endPoint);
    }

    @Then("^User verify InitiateNewManufacturerContract status code \"([^\"]*)\" for the response$")
    public void userVerifyPostInitiateNewManufacturerContractStatusCodeForTheResponse(int statusCode) {
        postInitiateNewManufactureContract.postInitiateNewManufactureContractStatusCode(statusCode);
    }

    @Then("^User verify the valid Response body key \"([^\"]*)\" and expected value \"([^\"]*)\"$")
    public void userVerifyTheValidResponseBodyKeyValueAndExpectedValue(String actualValue,String expectedValue)  {
        postInitiateNewManufactureContract.validationResults(actualValue,expectedValue);
    }


    @And("^User executes the query \"([^\"]*)\" and \"([^\"]*)\" for contract & Amendment name \"([^\"]*)\" to delete the record from the database$")
    public void userExecutesTheQueryAndForContractAmendmentNameToDeleteTheRecordFromTheDatabase(String contractHeaderQuery,String contractDetailQuery,String contractName){
        postInitiateNewManufactureContract.deleteContractRecordFromDB(contractHeaderQuery,contractDetailQuery,contractName);
    }


    @And("^User executes the query \"([^\"]*)\" and \"([^\"]*)\" to delete the contract records from the database$")
    public void userExecutesTheQueryToDeleteTheContractRecordsFromTheDatabase(String contractHeaderQuery,String contractDetailQuery){
        postInitiateNewManufactureContract.deleteContractRecordFromDB(contractHeaderQuery,contractDetailQuery);
    }



    @Then("^User verify the valid Response body key \"([^\"]*)\" and expected value \"([^\"]*)\" of string$")
    public void userVerifyTheValidResponseBodyKeyAndExpectedValueOfString(String actualValue,String expectedValue){
        postInitiateNewManufactureContract.validationResultsString(actualValue,expectedValue);
    }

    @When("^User hits the \"([^\"]*)\" Endpoint with delete API request to discard Initiate New Manufacture Contract by \"([^\"]*)\" and contractName \"([^\"]*)\"$")
    public void userHitsTheEndpointWithDeleteAPIRequestToDiscardInitiateNewManufactureContractByAndContractName(String endPoint, String rowKey,String ContractName){
        postInitiateNewManufactureContract.discardContract(endPoint,rowKey,ContractName);
    }

    @Then("^User verifies the isManufacturerContractDiscarded as true value in response for new manufacture contract$")
    public void userVerifiesTheIsManufacturerContractDiscardedAsTrueValueInResponseForNewManufactureContract() {
        postInitiateNewManufactureContract.verifyIfIsManufacturerContractIDDiscarded();
    }

    @Then("^User verify the InitiateNewManufacturerContract response header Error Code value \"([^\"]*)\"$")
    public void userVerifyTheInitiateNewManufacturerContractResponseHeaderErrorCodeValue(String expectedErrorCode) {
        postInitiateNewManufactureContract.verifyInitiateNewManufactureContractResponseHeaderErrorCode(expectedErrorCode);
    }

    @Given("^User creates the New Manufacture Contract data with following details$")
    public void userCreatesTheNewManufactureContractDataWithFollowingDetails(DataTable datatable) {
        postInitiateNewManufactureContract.newContractRequestPayload(datatable);
    }



//    @Given("^User creates the New Manufacture Contract data$")
//    public void userCreatesTheNewManufactureContractData() {
//
//    }
//
//    @Given("^User creates the Valid data for creating new Contract$")
//    public void userCreatesTheValidDataForCreatingNewContract() {
//
//    }

//    @Then("^User hits the \"([^\"]*)\" Endpoint with delete API request to discard Initiate New Manufacture Contract by \"([^\"]*)\" and contractName \"([^\"]*)\"$")
//    public void userHitsTheEndpointWithDeleteAPIRequestToDiscardInitiateNewManufactureContractByAndContractName(String endPoint, String rowKey, String contractName) throws Throwable {
//        postInitiateNewManufactureContract.discardContract(endPoint,rowKey,contractName);
//    }
}
