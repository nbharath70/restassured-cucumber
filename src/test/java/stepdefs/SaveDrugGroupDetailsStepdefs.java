package stepdefs;

import baseSteps.SaveDrugGroupDetail;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SaveDrugGroupDetailsStepdefs {
    SaveDrugGroupDetail saveDrugGroupDetail=new SaveDrugGroupDetail();
    @Then("^User executes the query \"([^\"]*)\" by drugGroup name \"([^\"]*)\" to get MFR_DrugList_ID to save drug group details$")
    public void userExecutesTheQueryByContractNameToGetMFR_DrugList_IDToSaveDrugGroupDetails(String query, String drugGroupName) {
        saveDrugGroupDetail.getDrugListId(query,drugGroupName);
        saveDrugGroupDetail.getdrugGroupRowKey(query,drugGroupName);
    }

    @Given("^User create the save drug group details data$")
    public void userCreateTheSaveDrugGroupDetailsData(DataTable dataTable) {
        saveDrugGroupDetail.saveDrugGroupDetailData(dataTable);
    }

    @Then("^User hits the \"([^\"]*)\" Save Drug Group Details API$")
    public void userHitsTheWithPostRequestOfSaveDrugGroupDetailsAPI(String endPoint) {
        saveDrugGroupDetail.saveDrugGroupDetailsPostCall(endPoint);
    }

    @Then("^User verify save drug drug group status code \"([^\"]*)\" for the response$")
    public void userVerifySaveDrugDrugGroupStatusCodeForTheResponse(int statusCode)  {
        saveDrugGroupDetail.verifySaveDrugGroupDetailsStatusCode(statusCode);
    }

    @Then("^User executes the query \"([^\"]*)\" by drugGroup name \"([^\"]*)\" to get row key for drug group to save drug group details$")
    public void userExecutesTheQueryByDrugGroupNameToGetRowKeyForDrugGroupToSaveDrugGroupDetails(String query, String drugGroupName) {
        saveDrugGroupDetail.getdrugGroupRowKey(query,drugGroupName);
    }

    @Then("^User verify the NDC details And matches returned by API and DB by NDCJson path \"([^\"]*)\" and columnName \"([^\"]*)\"$")
    public void userVerifyTheNDCDetailsAndMatchesReturnedByAPIAndDBByNDCJsonPathAndColumnName(String jsonPath, String columnName) {
        saveDrugGroupDetail.verifyNDCDetails(jsonPath,columnName);
    }

    @Then("^User verify the NDC details Row Key And matches returned by API and DB by NDCJson path \"([^\"]*)\" and columnName \"([^\"]*)\"$")
    public void userVerifyTheNDCDetailsRowKeyAndMatchesReturnedByAPIAndDBByNDCJsonPathAndColumnName(String jsonPath, String columnName)  {
        saveDrugGroupDetail.verifyNDCDetailsRowKey(jsonPath,columnName);
    }

    @Then("^User verify the saveDrugGroupDetails valid Response body key \"([^\"]*)\" and expected value \"([^\"]*)\" of string$")
    public void userVerifyTheSaveDrugGroupDetailsValidResponseBodyKeyAndExpectedValueOfString(String actualVal, String expectedVal) {
        saveDrugGroupDetail.validationResultsString(actualVal,expectedVal);
    }


    @Then("^User Verifies the response \"([^\"]*)\" with error message \"([^\"]*)\"$")
    public void userVerifiesTheResponseWithErrorMessage(String jsonPath, String messageKey)  {
        saveDrugGroupDetail.validateInvalidResponseDynamically(jsonPath,messageKey);
    }

    @Then("^User hits the \"([^\"]*)\" API and fetches its rowKey using query \"([^\"]*)\"$")
    public void userHitsTheAPIAndFetchesItsRowKeyUsingQuery(String endpoint, String query)  {
      saveDrugGroupDetail.createNewDrugGroup(endpoint,query);
    }

    @Then("^User discards the drugGroup and deletes the created record of drugGroup from DB$")
    public void userDiscardsTheDrugGroupAndDeletesTheCreatedRecordOfDrugGroupFromDB() {
        saveDrugGroupDetail.discardDrugGroup();
        saveDrugGroupDetail.deleteDrugGroup();
    }

    @Given("^User creates a new drug group to add some drugs to it and test$")
    public void userCreatesANewDrugGroupToAddSomeDrugsToItAndTest(DataTable dataTable) {
        saveDrugGroupDetail.createNewDrugGroupDetails(dataTable);
    }
}
