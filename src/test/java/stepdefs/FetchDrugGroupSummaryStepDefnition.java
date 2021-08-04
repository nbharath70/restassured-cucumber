package stepdefs;

import TestBase.TestBase;
import baseSteps.FetchDrugGroupSummaryBaseStep;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FetchDrugGroupSummaryStepDefnition extends TestBase {
    FetchDrugGroupSummaryBaseStep fetchDrugGroupSummary=new FetchDrugGroupSummaryBaseStep();
    @Given("^User runs the Query and get the \"([^\"]*)\" and get the Rowkey of the DrugGroup$")
    public void runQueryAndFetchRowkeyOfDrugGroup(String query)  {
    fetchDrugGroupSummary.getRowKeyOfDrugGroup(query);
    }

    @When("^User hits the API with Endpoint \"([^\"]*)\"$")
    public void userHitsTheAPIWithEndpoint(String endpoint) {
        fetchDrugGroupSummary.hitEndpoint(endpoint);
    }

    @Then("^User check the Status Code is \"([^\"]*)\"$")
    public void userCheckTheStatusCodeIs(int statusCode) {
       fetchDrugGroupSummary.verifyFetchDrugGroupSummaryStatusCode(statusCode);
    }

    @Then("^User check Response is in JSON format$")
    public void userCheckResponseIsInJSONFormat() {
        fetchDrugGroupSummary.verifyFetchDrugGroupSummaryAPIResponseFormatJSON();
    }

    @And("^User Runs the DrugGroupQuery \"([^\"]*)\" to fetch Coulumn \"([^\"]*)\" and Verify with Response by JSONpath \"([^\"]*)\"$")
    public void userRunsTheDrugGroupQueryToFetchCoulumnAndVerifyWithResponseByJSONpath(String query, String columnName, String jsonPath) {
        fetchDrugGroupSummary.verifyFetchDruGroupSummary(query,columnName,jsonPath);
    }
}