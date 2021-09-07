package stepdefs;

import baseSteps.GetAllMFR;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetAllMFRStepDefinition {
    GetAllMFR mfr = new GetAllMFR();

    @Given("^User hits the \"([^\"]*)\" with get API request$")
    public void userHitsGetRequest(String endpoint) {
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

    @Then("^response is in JSON format$")
    public void verifyTheResponseInJSON() {
        mfr.verifyResponseFormatJSON();
    }

    @Then("^User executes the query \"([^\"]*)\" And matches the MFR ID returned by API and DB$")
    public void matchesMFRIDsReturnedByAPIAndDB(String query) {
        mfr.verifyMFRDetails(query);
    }
    @Then("^User executes the query \"([^\"]*)\" And matches the MFR Name returned by API and DB$")
    public void matchesMFRNameReturnedByAPIAndDB(String query){
        mfr.verifyMFRNameDetails(query);
    }

    @Then("^User executes the query \"([^\"]*)\" And matches the Details returned by API and DB ColumnName \"([^\"]*)\"$")
    public void userExecutesTheQueryAndMatchesTheDetailsReturnedByAPIAndDBColumnName(String query, String columnName) {
       mfr.validateJSONResponse(query,columnName);
    }
}

