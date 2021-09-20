package stepdefs;

import TestBase.TestBase;
import baseSteps.CaptureCommentsAPI;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en_tx.Andyall;

public class CaptureCommentsStepdef extends TestBase {
    CaptureCommentsAPI captureCommentsAPI=new CaptureCommentsAPI();

    @Then("^User creates the request Body of API for Capture Comments API$")
    public void userCreatesTheRequestBodyOfAPIForCaptureCommentsAPI(DataTable dataTable) {
        captureCommentsAPI.createRequestBody(dataTable);
    }

    @Then("^User hits the API \"([^\"]*)\" with request body$")
    public void userHitsTheAPIWithRequestBody(String endPoint)  {
        captureCommentsAPI.hitEndpoint(endPoint);
    }

    @Then("^User executes the query \"([^\"]*)\" and Deletes the Comments in comments Tables$")
    public void userExecutesTheQueryAndDeletesTheCommentsInCommentsTables(String query) {
        captureCommentsAPI.deleteComments(query);
    }


    @Then("^User deletes the task associated with this task with query \"([^\"]*)\"$")
    public void userDeletesTheTaskAsociatedWithThisTaskWithQuery(String query){
        captureCommentsAPI.deleteTask(query);
    }

    @And("^user verifies the API StatusCode is \"([^\"]*)\"$")
    public void userVerifiesTheAPIStatusCodeIs(int StatusCode) {
        captureCommentsAPI.verifyStatusCodeOFapi(StatusCode);
    }

    @And("^User verifies the response is json format$")
    public void userVerifiesTheResponseIsJsonFormat() {
        captureCommentsAPI.verifyJsonResponse();
    }

    @And("^User creates the Invalid request Body of API for Capture Comments API$")
    public void userCreatesTheInvalidRequestBodyOfAPIForCaptureCommentsAPI(DataTable dataTable) {
        captureCommentsAPI.createInValidRequestBody(dataTable);
    }

    @Then("^User hits the API \"([^\"]*)\" with Invalid request body$")
    public void userHitsTheAPIWithInvalidRequestBody(String endpoint){
        captureCommentsAPI.hitEndpointWithInvalidRquest(endpoint);
    }

    @Then("^User verifies the Response with DB using query \"([^\"]*)\"$")
    public void userVerifiesTheResponseWithDBUsingQuery(String query) {
        captureCommentsAPI.verifyCommentsResponseWithDB(query);
    }

    @Then("^verify invalid Message from Response$")
    public void verifyInvalidMessageFromResponse() {
        captureCommentsAPI.verifyErrorMessage();
    }

    @Given("^User creates the contract and send that contract to rebateOps$")
    public void userCreatesTheContractAndSendThatContractToRabateOps() {
        String procInsQueryKey="sqlToGetLatestTaskIdWithProcInstId";
        captureCommentsAPI.createNewContractUpdateAndSendToRebateOps();
        captureCommentsAPI.getTaskIdWithProcessInstanceId(procInsQueryKey);
    }

    @Then("^User runs the query \"([^\"]*)\" and delete the contract created$")
    public void userRunsTheQueryAndDeleteTheContractCreated(String query) {
       captureCommentsAPI.deleteTheQAAutomationContract(query);
    }

    @Then("^User hits the \"([^\"]*)\" API and discards the contract$")
    public void userHitsTheAPIAndDiscardsTheContract(String endpoint) {
        captureCommentsAPI.discardContract(endpoint);
    }
}
