package stepdefs;

import baseSteps.ApproveOrRejectDrugGroup;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ApproveOrReturnToOpsAssigneeDrugGroupStepDef {
    ApproveOrRejectDrugGroup approveOrRejectDrugGroup = new ApproveOrRejectDrugGroup();

    @Given("^User creates the DrugGroup Using this data for Approving or Rejecting DrugGroup$")
    public void userCreatesTheDrugGroupUsingThisDataForApprovingOrRejectingDrugGroup(DataTable dataTable) {
        approveOrRejectDrugGroup.createNewDrugGroup(dataTable);
    }

    @And("^User hits the API \"([^\"]*)\" and create the DrugGroup for Approving or Rejecting DrugGroup and get the DrugGroupRowkey \"([^\"]*)\"$")
    public void userHitsTheAPIAndCreateTheDrugGroupForApprovingOrRejectingDrugGroupAndGetTheDrugGroupRowkey(String endPoint, String query) {
        approveOrRejectDrugGroup.hitCreateDrugGroupAPI(endPoint);
        approveOrRejectDrugGroup.getRowKeyAndDrugListIDOfDrugGroupCreated(query);
    }


    @And("^User create data to Add Some Drugs to created DruGroup to Approve or Reject that DrugGroup$")
    public void userCreateDataToAddSomeDrugsToCreatedDruGroupToApproveOrRejectThatDrugGroup(DataTable dataTable) {
        approveOrRejectDrugGroup.AddingSomeDrugsToDrugGroupJsonRequest(dataTable);
    }

    @And("^User hits the API \"([^\"]*)\"to add drugs to DrugGroup to Approve or Reject that DrugGroup$")
    public void userHitsTheAPIToAddDrugsToDrugGroupToApproveOrRejectThatDrugGroup(String endpoint) {
        approveOrRejectDrugGroup.hitAddDrugsToDrugGroupAPI(endpoint);
    }

    @And("^User creates the Data to send the DrugGroup to QC to Approve or Reject that DrugGroup$")
    public void userCreatesTheDataToSendTheDrugGroupToOpsAssigneeToApproveOrRejectThatDrugGroup(DataTable dataTable) {
        approveOrRejectDrugGroup.createSendToQCJsonData(dataTable);
    }

    @And("^User hits the SendToQC for DrugGroup API \"([^\"]*)\" to Approve or Reject that DrugGroup$")
    public void userHitsTheSendToQCForDrugGroupAPIToApproveOrRejectThatDrugGroup(String endPoint) {
        approveOrRejectDrugGroup.hitSendToQCForDrugGroupAPI(endPoint);
    }

    @And("^User fetches the Task ID of the DruGroup sent to QC$")
    public void userFetchesTheTaskIDOfTheDruGroupSentToQC() {
        approveOrRejectDrugGroup.getProcessInstanceAndtaskIdForDrugGroup();
    }

    @And("^User creates the data for Approve or Reject API$")
    public void userCreatesTheDataForApproveOrRejectAPI(DataTable dataTable) {
        approveOrRejectDrugGroup.createdataForApproveOrrejectDrugGroup(dataTable);
    }


    @When("^User hits the ApproveOrReject DrugGroup API \"([^\"]*)\"$")
    public void userHitsTheApproveOrRejectDrugGroupAPI(String endpoint)  {
        approveOrRejectDrugGroup.hitApproveOrRejectDrugGroupAPI(endpoint);
    }

    @And("^User verifies that the Status code of approveOrrejectDrugGroupAPi is \"([^\"]*)\"$")
    public void userVerifiesThatTheStatusCodeOfApproveOrrejectDrugGroupAPiIs(int statusCode)  {
        approveOrRejectDrugGroup.verifyStatusCode(statusCode);
    }

    @Then("^User deletes the records created to verify drugGroup API$")
    public void userDeletesTheRecordsCreatedToVerifyDrugGroupAPI() {
        approveOrRejectDrugGroup.deleteDrugGroupAndDrugGroupDetails();
    }

    @And("^User Discards the DrugGroup created$")
    public void userDiscardsTheDrugGroupCreated() {
        approveOrRejectDrugGroup.discardDrugGroup();
    }

    @And("^User verifies the Life Cycle Status of the Drug Group is \"([^\"]*)\"$")
    public void userVerifiesTheLifeCycleStatusOfTheDrugGroupIs(String lifeCycleStatus)  {
       approveOrRejectDrugGroup.verifyLifeCycleStatus(lifeCycleStatus);
    }
}
