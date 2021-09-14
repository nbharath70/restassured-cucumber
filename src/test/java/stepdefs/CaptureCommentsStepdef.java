package stepdefs;

import TestBase.TestBase;
import baseSteps.CaptureCommentsAPI;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en_tx.Andyall;

public class CaptureCommentsStepdef extends TestBase {
    CaptureCommentsAPI captureCommentsAPI=new CaptureCommentsAPI();

    @Then("^User update the manufacture contract data$")
    public void userUpdateManufactureContractData(DataTable dataTable) {
        captureCommentsAPI.updateExistingManufactureContractDetails(dataTable);
    }

    @Then("^User hits the API \"([^\"]*)\" update manufacture contract post request$")
    public void userHitsTheUpdateManufactureContractPostRequest(String endPoint) {
        captureCommentsAPI.updateManufactureContractPutCall(endPoint);
        captureCommentsAPI.getProcessInstance();
    }

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


    @Then("^User Deletes the task asociated with this task with query \"([^\"]*)\"$")
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

    @And("^User hits the API \"([^\"]*)\" Endpoint with delete API request to discard Initiate New Manufacture Contract by \"([^\"]*)\" and contractName \"([^\"]*)\"$")
    public void userHitsTheAPIEndpointWithDeleteAPIRequestToDiscardInitiateNewManufactureContractByAndContractName(String endpoint, String rowkey, String contractName){
        String taskIDQueryKey="fetchTaskID";
        captureCommentsAPI.fetchTaskID(taskIDQueryKey);
        captureCommentsAPI.discardContract(endpoint,rowkey,contractName);
    }
}
