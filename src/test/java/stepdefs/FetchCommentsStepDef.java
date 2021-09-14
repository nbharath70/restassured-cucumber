package stepdefs;
import TestBase.TestBase;
import baseSteps.FetchComments;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class FetchCommentsStepDef extends TestBase {
    FetchComments fetchComments= new FetchComments();

    @Then("^User creates the manufacture contract data requestBody$")
    public void userUpdateManufactureContractData(DataTable dataTable) {
        fetchComments.updateExistingManufactureContractDetails(dataTable);
    }

    @Then("^User hits the \"([^\"]*)\" API update manufacture contract post request$")
    public void userHitsTheUpdateManufactureContractPostRequest(String endPoint) {
        fetchComments.updateManufactureContractPutCall(endPoint);
        fetchComments.getProcessInstance();
    }

    @Then("^User creates the request Body for Capture Comments API$")
    public void userCreatesTheRequestBodyOfAPIForCaptureCommentsAPI(DataTable dataTable) {
        fetchComments.createRequestBody(dataTable);
    }

    @Then("^User hits the \"([^\"]*)\" API with request body$")
    public void userHitsTheAPIWithRequestBody(String endPoint)  {
        fetchComments.hitEndpoint(endPoint);
    }

//    @Then("^User Deletes the task asociated with this task with query \"([^\"]*)\"$")
//    public void userDeletesTheTaskAsociatedWithThisTaskWithQuery(String query){
//        fetchComments.deleteTask(query);
//    }

    @And("^User hits the API \"([^\"]*)\" API with request body$")
    public void thenUserHitsTheAPIAPIWithRequestBody(String endpoint) {
        fetchComments.hitFetchCommentsAPI(endpoint);
    }

    @Then("^User creates the Request Body for fetchComments API$")
    public void userCreatesTheRequestBodyForFetchCommentsAPI() {
        String taskIDQueryKey="fetchTaskID";
        fetchComments.createRequestBodyForFetchCommentsAPI(taskIDQueryKey);

    }

    @And("^User verifies the CaptureAPI StatusCode is \"([^\"]*)\"$")
    public void userVerifiesTheCaptureAPIStatusCodeIs(int StatusCode){
       fetchComments.verifyStatusCodeOfCaptureComments(StatusCode);
    }

    @Then("^User verifies the FetchCommentsAPI StatusCode is \"([^\"]*)\"$")
    public void userVerifiesTheFetchCommentsAPIStatusCodeIs(int StatusCode) {
        fetchComments.verifyStatusCodeOfFetchCommentsapi(StatusCode);
    }

    @Then("^User verifies fetchComments API the response in JSON Format$")
    public void userVerifiesFetchCommentsAPITheResponseInJSONFormat() {
        fetchComments.verifyJsonResponse();
    }

    @Then("^User verifies the Response With DB using query \"([^\"]*)\"$")
    public void userVerifiesTheResponseWithDBUsingQuery(String query){
        fetchComments.verifyResponseWithDB(query);
    }
}
