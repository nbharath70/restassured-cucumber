package stepdefs;

import baseSteps.Task;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class workFlowTaskStepdefs {
    Task task=new Task();
    @And("^User fetchs all the Details from the Result and create request payload for workflow task$")
    public void userFetchsAllTheDetailsFromTheResultAndCreateRequestPayloadForWorkflowTask() {
        task.taskData();
    }

    @Then("^User hits the \"([^\"]*)\" work task API post request$")
    public void userHitsTheWorkTaskAPIPostRequest(String endPoint) throws Throwable {
        task.taskPostCall(endPoint);
    }

    @Then("^User verifies work flow task the Status Code of the Response is \"([^\"]*)\"$")
    public void userVerifiesWorkFlowTaskTheStatusCodeOfTheResponseIs(int statusCode) throws Throwable {
        task.verifyWorkFlowTaskStatusCode(statusCode);
    }

    @Then("^User verifies the work flow task response in JSON format$")
    public void userVerifiesTheWorkFlowTaskResponseInJSONFormat() {
        task.verifyWorkFlowTaskAPIResponseFormatJSON();
    }

    @Then("^User verifies the API response validation$")
    public void userVerifiesTheAPIResponseValidation() {
        task.workFlowTaskResponseValidation();
    }
}
