package stepdefs;

import TestBase.TestBase;
import baseSteps.InitiateNewManufactureContract;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

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
}
