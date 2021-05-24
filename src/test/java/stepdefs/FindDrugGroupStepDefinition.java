package stepdefs;

import baseSteps.FindDrugGroup;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class FindDrugGroupStepDefinition {
    FindDrugGroup findDrugGroup=new FindDrugGroup();
    @Given("^User Runs the Query \"([^\"]*)\" and Fetch the Manufactuere Name from DB$")
    public void fetchManufacturerNameFromDb(String query) {
        findDrugGroup.getManufactuereNameforDrugGroup(query);
    }
    @When("^User hits the FindDrugGroup \"([^\"]*)\" Endpoint with get request$")
    public void hitEndpoint(String endpoint)  {
        findDrugGroup.hitFindDruggroupEndpoint(endpoint);
    }
    @Then("^User verifies the API response Status code is \"([^\"]*)\" for FindDrugGroup API$")
    public void verifyStatusCode(int statusCode)  {
        findDrugGroup.verifiyStatusCode(statusCode);
    }

    @Then("^User verifies the FindDrugGroup API Response is in JSON Format$")
    public void userVerifiesTheFindDrugGroupAPIResponseIsInJSONFormat() {
        findDrugGroup.verifyFormatOfJSONBody();
    }

    @Then("^User Runs the Query \"([^\"]*)\" and matches the DrugGroupSummary Column \"([^\"]*)\" and JSON from response$")
    public void userRunsTheQueryAndMatchesTheDrugGroupSummaryColumnAndJSONFromResponse(String query, String columnName)  {
        findDrugGroup.verifyFindDrugGroupSummaryWithDbTable(query,columnName);
    }

    @And("^User execute the getManufacturerDruglistNAme Query \"([^\"]*)\" and get the DetialJSOn from DB$")
    public void userExecuteTheAndGetTheDetialJSOnFromDB(String query){
        findDrugGroup.getManufactuereNameforDrugList(query);
    }

    @Then("^Verifies the JSON response with DB JSOn by manufacturerDrugListIDJsonPath \"([^\"]*)\"$")
    public void verifiesTheJSONResponseWithDBJSOnByJsonPath(String jsonPath) {
        findDrugGroup.verifyManufactuereDrugListID(jsonPath);
    }

    @And("^User execute the getNumberofApprovedNDC Query \"([^\"]*)\" and get the DetialJSOn from DB$")
    public void userExecuteTheGetNumberofApprovedNDCQueryAndGetTheDetialJSOnFromDB(String query){
    findDrugGroup.getNumberOfApprovedNdc(query);
    }

    @Then("^Verifies the JSON response with DB JSOn by numberOfApprovedNDCJsonPath \"([^\"]*)\"$")
    public void verifiesTheJSONResponseWithDBJSOnByNumberOfApprovedNDCJsonPath(String jsonPath){
       findDrugGroup.verifyNumberOfApprovedNDC(jsonPath);
    }

    @And("^User execute the getNumberofPendingNDC Query \"([^\"]*)\" and get the DetialJSOn from DB$")
    public void userExecuteTheGetNumberofPendingNDCQueryAndGetTheDetialJSOnFromDB(String query) {
        findDrugGroup.getNumberOfPendingNdc(query);
    }

    @Then("^Verifies the JSON response with DB JSOn by numberOfPendingNDCJsonPath \"([^\"]*)\"$")
    public void verifiesTheJSONResponseWithDBJSOnByNumberOfPendingNDCJsonPath(String jsonPath) {
        findDrugGroup.verfyNumberOfPendingNDC(jsonPath);
    }

    @Then("^User Runs the Query \"([^\"]*)\" and matches the DrugListRuleId and DruggroupDescription \"([^\"]*)\" and JSON from response$")
    public void userRunsTheQueryAndMatchesTheDrugListRuleIdAndDruggroupDescriptionAndJSONFromResponse(String query, String columnName) {

      findDrugGroup.verifyFindDrugListRuleIDAndDrugListDescription(query,columnName);
    }
}