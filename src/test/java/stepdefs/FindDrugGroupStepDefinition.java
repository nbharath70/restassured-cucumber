package stepdefs;

import TestBase.TestBase;
import baseSteps.FindDrugGroup;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class FindDrugGroupStepDefinition extends TestBase {
    FindDrugGroup findDrugGroup=new FindDrugGroup();
    @Given("^User runs the query \"([^\"]*)\" and fetch the manufactuereName from DB$")
    public void fetchManufacturerNameFromDb(String query) {
        findDrugGroup.getManufactuereNameforDrugGroup(query);
    }
    @When("^User hits the findDrugGroup \"([^\"]*)\" endpoint$")
    public void hitEndpoint(String endpoint)  {
        findDrugGroup.hitFindDruggroupEndpoint(endpoint);
    }
    @Then("^User verifies the API response statusCode is \"([^\"]*)\" for findDrugGroup API$")
    public void verifyStatusCode(int statusCode)  {
        findDrugGroup.verifiyStatusCode(statusCode);
    }

    @Then("^User verifies the findDrugGroup API response is in JSON format$")
    public void userVerifiesTheFindDrugGroupAPIResponseIsInJSONFormat() {
        findDrugGroup.verifyFormatOfJSONBody();
    }

    @Then("^User Runs the query \"([^\"]*)\" and matches the DrugGroupSummary Column \"([^\"]*)\" and JSON from response$")
    public void userRunsTheQueryAndMatchesTheDrugGroupSummaryColumnAndJSONFromResponse(String query, String columnName)  {
        findDrugGroup.verifyFindDrugGroupSummaryWithDbTable(query,columnName);
    }

    @And("^User execute the getManufacturerDruglistNAme Query \"([^\"]*)\" and get the DetialJSOn from DB$")
    public void userExecuteTheAndGetTheDetialJSOnFromDB(String query){
        findDrugGroup.getManufactuereNameforDrugList(query);
    }

    @Then("^User Runs the Query \"([^\"]*)\" and matches the DrugListRuleId and DruggroupDescription \"([^\"]*)\" and JSON from response$")
    public void userRunsTheQueryAndMatchesTheDrugListRuleIdAndDruggroupDescriptionAndJSONFromResponse(String query, String columnName) {

      findDrugGroup.verifyFindDrugListRuleIDAndDrugListDescription(query,columnName);
    }

    @When("^User hits the \"([^\"]*)\" endpoint with manufactuereName \"([^\"]*)\"$")
    public void userHitsTheFindDrugGroupEndpointWithGetRequestForGivenManufactuereName(String endpoint, String manufName) throws Throwable {
        findDrugGroup.hitFindDruggroupEndpoint(endpoint,manufName);
    }

    @Then("^User verifies the valid response using findDrugGroup bodyKey \"([^\"]*)\" and expected value \"([^\"]*)\"$")
    public void userVerifyTheValidResponseFindDrugGroupBodyKeyAndExpectedValueOfString(String actual, String expected) throws Throwable {
        findDrugGroup.validateFindDrugGroupResponse(actual,expected);
    }

    @Then("^User verifies the findDrugGroup response header errorCode value \"([^\"]*)\"$")
    public void userVerifyTheFindDrugGroupResponseHeaderErrorCodeValue(String expectedErrorCode) throws Throwable {
        findDrugGroup.verifyFindDrugGroupResponseResponseHeaderErrorCode(expectedErrorCode);
    }

    @Then("^User verifies json response with DB using query \"([^\"]*)\" and columnName \"([^\"]*)\"$")
    public void userVerifiesJsonResponseWithDBUsingQueryAndColumnName(String query, String columnName) throws Throwable {
       findDrugGroup.validateJSONResponse(query,columnName);
    }
}
