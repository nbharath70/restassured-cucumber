package stepdefs;

import baseSteps.GetAllMFR;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetAllMFRStepDefinition {
    GetAllMFR mfr = new GetAllMFR();

    @Given("^User hits the \"([^\"]*)\" with get API request$")
    public void userHitsGetRequest(String endpoint) throws Throwable {
        mfr.getAllMFRResource(endpoint);
    }
    @Then("^User verifies the valid status code \"([^\"]*)\" in the response$")
    public void userGetsCorrectResponseCode(int statusCode) {
        mfr.getAllMFRResponseStatusCode(statusCode);
    }

    @When("^User executes the query \"([^\"]*)\" And matches the count of MFR returned by API and DB$")
    public void matchesTheCountOfMFRReturnedByAPIAndDB(String query) {
        mfr.verifyRecordCount(query);
    }
    @When("^User executes the query \"([^\"]*)\" And matches the MFRIDs returned by API and DB$")
    public void matchesMFRIDsReturnedByAPIAndDB(String query) {
        mfr.verifyRecordCount(query);
    }
    /*
    @Then("^matches the MFRID returned by API and  DB$")
    public void matchesTheMFRIDReturnedByAPIAndDB() {
        mfr.matchMFRIDS();
    }
    @Then("^matches the MFR Name returned by API and  DB$")
    public void matchesTheMFRNameReturnedByAPIAndDB() {
        mfr.matchMFRName();
    }*/
    @Then("^response is in JSON format$")
    public void verifyTheResponseInJSON() {
        mfr.verifyResponseFormatJSON();
    }

}

