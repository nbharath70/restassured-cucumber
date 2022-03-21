package stepdefs;

import baseSteps.SendToQCDrugGroup;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SendToQCDrugGroupStepDef {
        SendToQCDrugGroup sendToQCDrugGroup=new SendToQCDrugGroup();
     @Given("^User creates the DrugGroupUsing this data$")
    public void user_creates_the_druggroupusing_this_data(DataTable dataTable) {
         sendToQCDrugGroup.createNewDrugGroup(dataTable);

     }

    @And("^User hits the API \"([^\"]*)\" and create the DrugGroup and get the DrugGroupRowkey \"([^\"]*)\"$")
    public void userHitsTheAPIAndCreateTheDrugGroupAndGetTheDrugGroupRowkey(String endPoint, String query) throws Throwable {
        sendToQCDrugGroup.hitCreateDrugGroupAPI(endPoint);
        sendToQCDrugGroup.getRowKeyAndDrugListIDOfDrugGroupCreated(query);

    }


    @And("^User create data to Add Some Drugs to created DruGroup$")
    public void userCreateDataToAddSomeDrugsToCreatedDruGroup(DataTable dataTable) {
         sendToQCDrugGroup.AddinSomeDrugsToDrugGroupJsonRequest(dataTable);
    }

    @And("^User hits the API \"([^\"]*)\"to add drugs to DrugGroup$")
    public void userHitsTheAPIToAddDrugsToDrugGroup(String endpoint)   {
      sendToQCDrugGroup.hitAddDrugsToDrugGroupAPI(endpoint);
    }

    @And("^User creates the Data to send the DrugGroup to Ops Assignee$")
    public void userCreatesTheDataToSendTheDrugGroupToOpsAssignee(DataTable dataTable) {
         sendToQCDrugGroup.createSendToQCJsonData(dataTable);
    }

    @And("^User hits the SendToQC for DrugGroup API \"([^\"]*)\"$")
    public void userHitsTheSendToOpsAssigneeForDrugGroupAPI(String endpoint)  {
       sendToQCDrugGroup.hitSendToQCForDrugGroupAPI(endpoint);

    }

    @And("^User check the Status code of SendingDrugGroup to ops assignee is \"([^\"]*)\"$")
    public void userCheckTheStatusCodeOfSendingDrugGroupToOpsAssigneeIs(int statusCode)  {
        sendToQCDrugGroup.verifyStatusCode(statusCode);
    }

    @Then("^User discards and deletes the DrugGroup and DrugDetails created$")
    public void userDiscardsAndDeletesTheDrugGroupAndDrugDetailsCreated() {
        sendToQCDrugGroup.deleteDrugGroupAndDrugGroupDetails();
    }



    @And("^User checks the Life cycle Status of the drugGroup is changes to \"([^\"]*)\"$")
    public void userChecksTheLifeCycleStatusOfTheDrugGroupIsChangesTo(String lifeCycleStatus)  {
       sendToQCDrugGroup.verifyLifeCycleStatus(lifeCycleStatus);
    }

    @And("^Then user verifies the response \"([^\"]*)\"$")
    public void thenUserVerifiesTheResponse(String errorMessage)  {
        sendToQCDrugGroup.verifyErrorMessage(errorMessage);
    }
}


