package stepdefs;
import TestBase.TestBase;
import baseSteps.CreateDrugGroup;
import baseSteps.GetJobStatusSummary;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class GetJobStatusSummaryStepDefinition extends TestBase {
    GetJobStatusSummary jobStatus=new GetJobStatusSummary();
    @Given("^User hits the \"([^\"]*)\" end point with get API request$")
    public void userHitsTheEndPointWithGetAPIRequest(String endpoint)  {
        jobStatus.getJobStatusResource(endpoint);

    }

    @Then("^User verifies the valid status code \"([^\"]*)\" in the API response$")
    public void userVerifiesTheValidStatusCodeInTheAPIResponse(int statusCode) {
        jobStatus.getJobStatusSummaryResponseStatusCode(statusCode);
        }

    @And("^Job status summary API response is in JSON format$")
    public void jobStatusSummaryAPIResponseIsInJSONFormat() {
        jobStatus.verifyResponseFormatJSON();
    }
    @And("^user runs \"([^\"]*)\" query and asserts the Scheduled job count from API with DB$")
    public void userRunsQueryAndAssertsTheScheduledJobCountFromAPIWithDB(String query) {
        jobStatus.verifyScheduledRecordCount(query);
    }

    @And("^user runs \"([^\"]*)\" query and asserts the running job count from API with DB$")
    public void userRunsQueryAndAssertsTheRunningJobCountFromAPIWithDB(String query){
        jobStatus.verifyRunningRecordCount(query);
    }

    @And("^user runs \"([^\"]*)\" query and asserts the finished job count from API with DB$")
    public void userRunsQueryAndAssertsTheFinishedJobCountFromAPIWithDB(String query) {
        jobStatus.verifyFinishedRecordCount(query);
    }

    @And("^user runs \"([^\"]*)\" query and asserts the cancelled job count from API with DB$")
    public void userRunsQueryAndAssertsTheCancelledJobCountFromAPIWithDB(String query) {
        jobStatus.verifyCancelledRecordCount(query);
    }
    @And("^user runs \"([^\"]*)\" query and asserts the aborted job count from API with DB$")
    public void userRunsQueryAndAssertsTheAbortedJobCountFromAPIWithDB(String query) {
        jobStatus.verifyAbortedRecordCount(query);
    }
}
