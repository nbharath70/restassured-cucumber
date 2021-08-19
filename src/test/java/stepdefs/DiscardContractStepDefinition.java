package stepdefs;

import RequestPojo.DisContractPojo.DiscardContractPojo;
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


    @Then("^User verifies the response is in JSON format$")
    public void userVerifiesTheResponseIsInJSONFormat() {
        discardContract.verifyResponseFormatJSON();
    }

    @Then("^User verifies the isManufacturerContractDiscarded as true value in response$")
    public void userVerifiesTheIsManufacturerContractDiscardedAsTrueValueInResponse() {
        discardContract.verifyIfIsManufacturerContractDiscarded();
    }
    @Then("^user  runs \"([^\"]*)\" query and \"([^\"]*)\" query to verify is_current_flag$")
    public void userRunsQueryAndQueryToVerifyIs_current_flag(String verifyContractHeaderQuery, String verifyContractDetailsQuery) throws Throwable {
        discardContract.verifyis_current_flagInContractHeaderAndDetailsTable(verifyContractHeaderQuery,verifyContractDetailsQuery);
    }

    @And("^user runs \"([^\"]*)\" query and \"([^\"]*)\" query to activate the contract$")
    public void userRunsQueryAndQueryToActivateTheContract(String contractHeader, String ContractDetail){
        discardContract.reactivateContracts(contractHeader,ContractDetail);
    }

    @Then("^User runs \"([^\"]*)\" query and verify is_Current_Flag value as zero in database$")
    public void userRunsQueryAndVerifyIs_Current_FlagValueAsZeroInDatabase(String query) throws Throwable {
        discardContract.verifyDiscardContractFromDB(query);
    }

    @Then("^User valid Response discarded contract body key \"([^\"]*)\" of string$")
    public void userValidResponseDiscardedContractBodyKeyAndExpectedValueOfString(String actual) throws Throwable {
        discardContract.validateCreateDrugGroupResponseByString(actual);
    }

    @Then("^User verify the discard contract response header Error Code value \"([^\"]*)\"$")
    public void userVerifyTheDiscardContractResponseHeaderErrorCodeValue(String expectedErrorCode) throws Throwable {
        discardContract.verifyDiscardContractResponseHeaderErrorCode(expectedErrorCode);
    }
}

