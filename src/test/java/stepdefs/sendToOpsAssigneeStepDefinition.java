package stepdefs;

import HelperClass.UtilitiesClass;
import baseSteps.SendToOpsAssignee;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class sendToOpsAssigneeStepDefinition {
    SendToOpsAssignee sendToOpsAssignee=new SendToOpsAssignee();

//    @Given("^User executes \"([^\"]*)\" and captures the \"([^\"]*)\"$")
//    public void userExecutesQueryAndCapturesTheContractId(String queryKey, String columnNameKey)  {
//        sendToOpsAssignee.executeQueryAndCapturesTheContractId(queryKey,columnNameKey);
//    }
//
//    @And("^User Hits \"([^\"]*)\" resource to get the contract details$")
//    public void userHitsResourceToGetTheContractDetails(String endPoint)  {
//        sendToOpsAssignee.hitResourceToGetTheContractDetails(endPoint);
//    }
//
//    @Then("^User Hits \"([^\"]*)\" and sends the contract to rebate Ops$")
//    public void userHitsAndSendsTheContractToRebateOps(String arg0) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @And("^User updates the contract details and adds Ops Assignee \"([^\"]*)\" and Ops QCer \"([^\"]*)\"$")
//    public void userUpdatesTheContractDetailsAndAddsOpsAssigneeAndOpsQCer(String arg0, String arg1) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }

    @Given("^User creates a new contract and updates it and sends to Rebate Ops$")
    public void userCreatesANewContract() {
        sendToOpsAssignee.createNewContractUpdateAndSendToRebateOps();

    }
    @And("^User gets the task Id with Process Instance Id by executing the \"([^\"]*)\"$")
    public void userGetsTheTaskIdWithProcessInstanceId(String queryKey) {
        sendToOpsAssignee.getTaskIdWithProcessInstanceId(queryKey);
    }

    @Then("^User updates \"([^\"]*)\" as Ops assignee and \"([^\"]*)\" as Ops QCer$")
    public void userUpdatesOpsAssigneeAndOpsQCer(String opsAssingeeName, String opsQCerName)  {
        sendToOpsAssignee.updateOpsAssigneeAndOpsQCer(opsAssingeeName,opsQCerName);
    }

    @And("^User Hits \"([^\"]*)\" with Post request$")
    public void userHitsOpsAssigneeWithPostRequest(String endPointKey)  {
        sendToOpsAssignee.hitSendToOpsAssigneePostRequest(endPointKey);
    }

    @Then("^User prepares expected result json after sending to OpsAssignee$")
    public void userPreparesExpectedResultJsonAfterSendingToOpsAssignee() {
        sendToOpsAssignee.prepareExpectedResultJsonAfterSendingToOpsAssignee();
    }

    @And("^User verifies the actual response with expected response$")
    public void userVerifiesTheActualResponseWithExpectedResponse() {
        sendToOpsAssignee.verifyActualResponseWithExpectedResponse();
    }

    @And("^User verifies the lifecycle status of contract changed to \"([^\"]*)\"$")
    public void userVerifiesTheLifecycleStatusOfContractChangedToInProgress(String lifecycleStatus)  {
        sendToOpsAssignee.verifyLifecycleStatusOfContractChangedToInProgress(lifecycleStatus);
    }

    @Then("^User deletes the flowable records for the Process Instance Id$")
    public void userDeletesTheFlowableRecordsForTheProcessInstanceId() {
        sendToOpsAssignee.userDeletesTheFlowableRecordsForTheProcessInstanceId();
    }

    @And("^User deletes the QAAutomation Contract by executing \"([^\"]*)\"$")
    public void userDeletesTheQAAutomationContract(String queryKey) {
        sendToOpsAssignee.deleteTheQAAutomationContract(queryKey);
    }


    @Then("^User checks the status code \"([^\"]*)\" from the response$")
    public void userChecksTheStatusCodeFromTheResponse(int statusCode)  {
        sendToOpsAssignee.checkStatusCodeFromTheResponse(statusCode);
    }

    @Then("^User updates \"([^\"]*)\" as Ops assignee \"([^\"]*)\" as Ops QCer and Invalid taskId$")
    public void userUpdatesAsOpsAssigneeAsOpsQCerAndInvalidTaskId(String opsAssignee, String opsQCer)  {
        sendToOpsAssignee.updateOpsAssigneeOpsQCerAndInvalidTaskId(opsAssignee,opsQCer);
    }

    @Then("^User prepares expected result json after sending to OpsAssignee for invalid taskId$")
    public void userPreparesExpectedResultJsonAfterSendingToOpsAssigneeForInvalidTaskId() {
        sendToOpsAssignee.prepareExpectedResultJsonAfterSendingToOpsAssigneeForInvalidTaskId();
    }


}
