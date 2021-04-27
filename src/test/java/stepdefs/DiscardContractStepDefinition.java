package stepdefs;

import baseSteps.DiscardContract;
import baseSteps.GetContractDetailsByID;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DiscardContractStepDefinition {
    DiscardContract discardContract = new DiscardContract();

    @Given("^User executes the \"([^\"]*)\" query and retrieves rowKey and ContractID$")
    public void userExecutesTheQueryAndRetrievesRowKeyAndContractID(String query) {
        discardContract.getRowKey(query);
        discardContract.getContractID(query);
    }

    @When("^User hits the \"([^\"]*)\" Endpoint with delete API request$")
    public void userHitsTheEndpointWithDeleteAPIRequest(String endpoint){
        discardContract.discardContract(endpoint);
    }

    @Then("^User verifies the valid status code \"([^\"]*)\" in the Discard contract response$")
    public void userVerifiesTheValidStatusCodeInTheDiscardContractResponse(int StatusCode) throws Throwable {
        discardContract.verifyStatusCodeofDiscardContractAPI(StatusCode);
    }

    @And("^user runs \"([^\"]*)\" query and \"([^\"]*)\" query to activate the contract$")
    public void userRunsQueryAndQueryToActivateTheContract(String contractHeader, String ContractDetail){
       discardContract.reactivateContracts(contractHeader,ContractDetail);
    }
//    @Then("^User verifies the valid status code \"([^\"]*)\" in the response$")
//    public void userGetsCorrectResponseCode(int statusCode) {
//        mfr.getAllMFRResponseStatusCode(statusCode);
//    }
//
//    @When("^User executes the query \"([^\"]*)\" And matches the count of MFR returned by API and DB$")
//    public void matchesTheCountOfMFRReturnedByAPIAndDB(String query) {
//        mfr.verifyRecordCount(query);
//    }
//
//    @Then("^response is in JSON format$")
//    public void verifyTheResponseInJSON() {
//        mfr.verifyResponseFormatJSON();
//    }
//
//    @Then("^User executes the query \"([^\"]*)\" And matches the MFR ID returned by API and DB$")
//    public void matchesMFRIDsReturnedByAPIAndDB(String query) {
//        mfr.verifyMFRDetails(query);
//    }
//    @Then("^User executes the query \"([^\"]*)\" And matches the MFR Name returned by API and DB$")
//    public void matchesMFRNameReturnedByAPIAndDB(String query){
//        mfr.verifyMFRNameDetails(query);
//    }
}

