package stepdefs;

import TestBase.TestBase;
import baseSteps.CancelJob;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CancelJobStepdefs extends TestBase {
    CancelJob cancelJob=new CancelJob();
    @Then("^User executes the query \"([^\"]*)\" to get the processing job id for processing job status \"([^\"]*)\"$")
    public void userExecutesTheQueryToGetTheProcessingJobIdForProcessingJobStatus(String query, String status) throws Throwable {
        cancelJob.getProcessingJobId(query,status);
    }

    @Given("^User hits the \"([^\"]*)\" with post request for cancel job$")
    public void userHitsTheWithPostRequestForCancelJob(String endPoint) {
        cancelJob.cancelJob(endPoint);
    }

    @Then("^User verifies cancelJob status code \"([^\"]*)\" for the response$")
    public void userVerifyCancelJobStatusCodeForTheResponse(int statusCode) {
        cancelJob.cancelJobStatusCode(statusCode);
    }

    @Then("^User execute the query to update existing processing job status to the database$")
    public void userExecuteTheQueryToUpdateExistingProcessingJobStatusToTheDatabase() {
        cancelJob.updateStatus();
    }

    @Then("^User verifies API response isCanceled is true$")
    public void userVerifyAPIResponseIsCanceledIsTrue() {
        cancelJob.verifyIsCanceledIsTrue();
    }

    @Then("^User verifies the response is in JSON format for cancel job API$")
    public void userVerifiesTheResponseIsInJSONFormatForCancelJobAPI() {
        cancelJob.verifyResponseFormatIsJSON();
    }

    @Then("^User verifies API response isCanceled is false$")
    public void userVerifyAPIResponseIsCanceledIsFalse() {
        cancelJob.verifyIsCanceledIsFalse();
    }

    @Then("^User verifies API response error message$")
    public void userVerifyAPIResponseErrorMessage() {
        cancelJob.verifyErrorMessage();
    }

    @Then("^User enter the invalid processing job ID$")
    public void userEnterTheInvalidProcessingJobID() {
        cancelJob.invalidProcessingJobID();
    }

    @Then("^User verify API response error message no job exists$")
    public void userVerifyAPIResponseErrorMessageNoJobExists() {
        cancelJob.verifyNoJobExistsErrorMessage();
    }
}
