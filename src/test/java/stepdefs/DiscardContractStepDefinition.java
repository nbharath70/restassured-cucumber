package stepdefs;

import baseSteps.DiscardContract;
import baseSteps.GetContractDetailsByID;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DiscardContractStepDefinition {
    DiscardContract discardContract = new DiscardContract();
    @Given("^User executes the \"([^\"]*)\" query and retrieves rowKey and ContractID$")
    public void getContractIDAndRowKey(String query) {
        discardContract.getRowKey(query);
        discardContract.getContractID(query);
    }

    @Given("^User hits the \"([^\"]*)\" with delete API request$")
    public void userHitsDiscardContractRequest(String endpoint)  {
        discardContract.discardContract(endpoint);
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

