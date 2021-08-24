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


    @Given("^User Hits the API endpoint \"([^\"]*)\" with Typemissmatch datatype Druglist ID \"([^\"]*)\"$")
    public void userHitsTheAPIEndpointWithTypemissmatchDatatypeDruglistID(String endpoint, String drugListID){
        fetchDrugGroupSummary.hitInvalidEndpoint(endpoint,drugListID);
    }

    @Then("^User Verifies and Validate the Response of \"([^\"]*)\" and expected value \"([^\"]*)\"$")
    public void userVerifiesAndValidateTheResponseOfAndExpectedValue(String jsonpath, String invalidMessage) {
       fetchDrugGroupSummary.verifiesAPIResponseWithTypeMismatchErrorMsg(jsonpath,invalidMessage);
    }

    @Then("^User verify the valid Response fetchDrugGroupSummary body key \"([^\"]*)\" and expected value \"([^\"]*)\" of string$")
    public void userVerifyTheValidResponseFetchDrugGroupSummaryBodyKeyAndExpectedValueOfString(String json, String message) throws Throwable {
        fetchDrugGroupSummary.validateFectDrugGroupSummaryResponse(json,message);
    }

    @Then("^User hits the Query \"([^\"]*)\" and evaluates the Response with DB \"([^\"]*)\"$")
    public void userHitsTheQueryAndEvaluatesTheResponseWithDB(String query, String columnName) {
        fetchDrugGroupSummary.validateJSONResponse(query,columnName);
    }
}
