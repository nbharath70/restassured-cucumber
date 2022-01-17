package stepdefs;

import baseSteps.UpdateDrugGroup;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class UpdateDrugGroupStepdefs {
    UpdateDrugGroup updateDrugGroup=new UpdateDrugGroup();
    @And("^User update group details data$")
    public void userUpdateGroupDetailsData(DataTable dataTable) {
        updateDrugGroup.updateDrugGroupDetails(dataTable);
    }

    @Then("^User verify update drug group status code \"([^\"]*)\" for the response$")
    public void userVerifyUpdateDrugGroupStatusCodeForTheResponse(int statusCode)  {
        updateDrugGroup.verifyUpdateDrugGroupStatusCode(statusCode);
    }

    @Then("^User verify the valid UpdateNewDrugGroup Response body key \"([^\"]*)\" and expected value \"([^\"]*)\"$")
    public void userVerifyTheValidUpdateNewDrugGroupResponseBodyKeyAndExpectedValue(String actualValue, String expectedValue)  {
        updateDrugGroup.validateResponseBodyUpdateDrugGroup(actualValue,expectedValue);
    }

    @Then("^User hits the \"([^\"]*)\" with put request of UpdateNewDrugGroup$")
    public void userHitsTheWithPostRequestOfUpdateNewDrugGroup(String endPoint)  {
        updateDrugGroup.updateDrugGroupPutCall(endPoint);
    }

    @Then("^User verify the valid Response UpdateDrugGroup body key \"([^\"]*)\" and expected value \"([^\"]*)\" of string$")
    public void userVerifyTheValidResponseUpdateDrugGroupBodyKeyAndExpectedValueOfString(String actualValue, String expectedValue)  {
        updateDrugGroup.validateUpdateDrugGroupResponseByString(actualValue,expectedValue);
    }

    @Then("^User verify the UpdateDrugGroup response header Error Code value \"([^\"]*)\"$")
    public void userVerifyTheUpdateDrugGroupResponseHeaderErrorCodeValue(String expectedErrorCode)  {
        updateDrugGroup.verifyUpdateDrugGroupResponseHeaderErrorCode(expectedErrorCode);
    }

    @Given("^User creates a new drug group to update the record and test$")
    public void userCreatesANewDrugGroupToUpdateTheRecordAndTest(DataTable dataTable) {
        updateDrugGroup.createNewDrugGroupDetails(dataTable);
    }



    @Then("^User hits the \"([^\"]*)\" API fetches the RowKey of created drugGroup Using query \"([^\"]*)\"$")
    public void userHitsTheAPIFetchesTheRowKeyOfCreatedDrugGroupUsingQuery(String endPoint, String query) {
    updateDrugGroup.createNewDrugGroup(endPoint,query);
    }

    @Then("^User discards the drugGroup and deletes the created record from DB$")
    public void userDiscardsTheDrugGroupAndDeletesTheCreatedRecordFromDB() {
        updateDrugGroup.discardDrugGroup();
        updateDrugGroup.deleteDrugGroup();
    }
}
