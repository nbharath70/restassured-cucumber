package stepdefs;

import TestBase.TestBase;
import baseSteps.FetchJobListBaseStep;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class FetchJobListStepDefnition extends TestBase {
    FetchJobListBaseStep fetchJobList=new FetchJobListBaseStep();
    @Given("^User hits the fetchJobList API with EndPoint \"([^\"]*)\" with get Request$")
    public void userHitsTheFetchjoblistApi(String endpoint)  {
        fetchJobList.hitFetchJobListAPI(endpoint);
    }

    @Then("^User verifies the Status Code is \"([^\"]*)\"$")
    public void verifieStatusCode(int StatusCode)  {
        fetchJobList.verifyFetchJobListAPIresponseCode(StatusCode);
    }

    @And("^User verifies the Response in JSON Format$")
    public void verifiesResponseFormat()  {
        fetchJobList.verifyFetchJobListAPIResponseFormatJSON();
    }

    @And("^User Runs the Query \"([^\"]*)\" to fetch Coulumn \"([^\"]*)\" and Verify with Response by JSONpath \"([^\"]*)\"$")
    public void userRunsTheQueryToFetchCoulumnAndVerifyWithResponseByJSONpath(String query, String ColumnName, String JsonPath) {
        fetchJobList.verifyFetchJobListAPIResponseDetails(query,ColumnName,JsonPath);
    }

    @Given("^This is Not Automated due to SQL Tool Compatibility$")
    public void thisIsNotAutomatedDueToSQLToolCompatibility() {
    }

    @And("^Then User hits the Query \"([^\"]*)\" and evaluates the Response with DB \"([^\"]*)\"$")
    public void thenUserHitsTheQueryAndEvaluatesTheResponseWithDB(String query, String columnName) {
        fetchJobList.validateJSONResponse(query,columnName);
    }
}
