package stepdefs;

import baseSteps.GetAllMFR;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetAllMFRStepDefinition {
    GetAllMFR mfr = new GetAllMFR();

    @Given("^User hits the MFRContractEndpoint with get request$")
    public void userHitsGetAllMFRContracts() {
        mfr.hitGetMFREndpoint();
    }

    @Given("^API  processes the request$")
    public void processGetRequest() {

        mfr.processGetRequest();
    }

    @Then("^User gets the correct statusCode$")
    public void userGetsCorrectResponseCode() {
        mfr.verifyGetRequestStatusCode200();
    }

    @Then("^matches the count of MFR returned by API and  DB$")
    public void matchesTheCountOfMFRReturnedByAPIAndDB() {
        mfr.matchCountOfMFRRecords();
    }
    @Then("^matches the MFRID returned by API and  DB$")
    public void matchesTheMFRIDReturnedByAPIAndDB() {
        mfr.matchMFRIDS();
    }
    @Then("^matches the MFR Name returned by API and  DB$")
    public void matchesTheMFRNameReturnedByAPIAndDB() {
        mfr.matchMFRName();
    }
    @Then("^User gets the response  in JSON format$")
    public void verifyTheResponseInJSON() {
        mfr.verifyRsponseIsInJSONformat();
    }
}

