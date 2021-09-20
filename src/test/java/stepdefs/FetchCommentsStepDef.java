package stepdefs;
import TestBase.TestBase;
import baseSteps.FetchComments;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class FetchCommentsStepDef extends TestBase {
    FetchComments fetchComments= new FetchComments();

    @Then("^User creates the request Body for Capture Comments API$")
    public void userCreatesTheRequestBodyOfAPIForCaptureCommentsAPI(DataTable dataTable) {
        fetchComments.createRequestBody(dataTable);
    }

    @Then("^User hits the \"([^\"]*)\" API with request body$")
    public void userHitsTheAPIWithRequestBody(String endPoint)  {
        fetchComments.hitEndpoint(endPoint);
    }

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
        fetchComments.verifyStatusCodeOfFetchCommentsApi(StatusCode);
    }

    @Then("^User verifies fetchComments API the response in JSON Format$")
    public void userVerifiesFetchCommentsAPITheResponseInJSONFormat() {
        fetchComments.verifyJsonResponse();
    }

    @Then("^User verifies the Response With DB using query \"([^\"]*)\"$")
    public void userVerifiesTheResponseWithDBUsingQuery(String query){
        fetchComments.verifyResponseWithDB(query);
    }

    @Given("^User creates the Contract and send it to rebate ops$")
    public void userCreatesTheContractAndSendItToRebateOps() {
        String procInsQueryKey="sqlToGetLatestTaskIdWithProcInstId";
        fetchComments.createNewContractUpdateAndSendToRebateOps();
        fetchComments.getTaskIdWithProcessInstanceId(procInsQueryKey);
    }

    @Then("^User deletes the contract using query \"([^\"]*)\"$")
    public void userDeletesTheContractUsingQuery(String query)  {
        fetchComments.deleteTheQAAutomationContract(query);
    }

    @Then("^User hits the \"([^\"]*)\" API and deletes the contract created$")
    public void userHitsTheAPIAndDeletesTheContractCreated(String endpoint) {
       fetchComments.discardContract(endpoint);
    }

    @Then("^User deletes the task associated with this contract \"([^\"]*)\"$")
    public void userDeletesTheTaskAssociatedWithThisContract(String query)  {
        fetchComments.deleteTask(query);
    }

    @Then("^User executes the query \"([^\"]*)\" and deletes the comments$")
    public void userExecutesTheQueryAndDeletesTheComments(String query) {
        fetchComments.deleteComments(query);
    }
}
