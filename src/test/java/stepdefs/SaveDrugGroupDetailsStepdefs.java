package stepdefs;

import baseSteps.SaveDrugGroupDetail;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SaveDrugGroupDetailsStepdefs {
    SaveDrugGroupDetail saveDrugGroupDetail=new SaveDrugGroupDetail();
    @Then("^User executes the query \"([^\"]*)\" by drugGroup name \"([^\"]*)\" to get MFR_DrugList_ID to save drug group details$")
    public void userExecutesTheQueryByContractNameToGetMFR_DrugList_IDToSaveDrugGroupDetails(String query, String drugGroupName) throws Throwable {
        saveDrugGroupDetail.getDrugListId(query,drugGroupName);
    }

    @Given("^User create the save drug group details data$")
    public void userCreateTheSaveDrugGroupDetailsData(DataTable dataTable) {
        saveDrugGroupDetail.saveDrugGroupDetailData(dataTable);
    }

    @Then("^User hits the \"([^\"]*)\" with post request of saveDrugGroupDetails API$")
    public void userHitsTheWithPostRequestOfSaveDrugGroupDetailsAPI(String endPoint) throws Throwable {
        saveDrugGroupDetail.saveDrugGroupDetailsPostCall(endPoint);
    }

    @Then("^User verify save drug drug group status code \"([^\"]*)\" for the response$")
    public void userVerifySaveDrugDrugGroupStatusCodeForTheResponse(int statusCode) throws Throwable {
        saveDrugGroupDetail.verifySaveDrugGroupDetailsStatusCode(statusCode);
    }

    @Then("^User executes the query \"([^\"]*)\" by drugGroup name \"([^\"]*)\" to get row key for drug group to save drug group details$")
    public void userExecutesTheQueryByDrugGroupNameToGetRowKeyForDrugGroupToSaveDrugGroupDetails(String query, String drugGroupName) throws Throwable {
        saveDrugGroupDetail.getDrugGroupRowKey(query,drugGroupName);
    }

    @Then("^User verify the NDC details And matches returned by API and DB by NDCJson path \"([^\"]*)\" and columnName \"([^\"]*)\"$")
    public void userVerifyTheNDCDetailsAndMatchesReturnedByAPIAndDBByNDCJsonPathAndColumnName(String jsonPath, String columnName) throws Throwable {
        saveDrugGroupDetail.verifyNDCDetails(jsonPath,columnName);
    }

    @Then("^User verify the NDC details Row Key And matches returned by API and DB by NDCJson path \"([^\"]*)\" and columnName \"([^\"]*)\"$")
    public void userVerifyTheNDCDetailsRowKeyAndMatchesReturnedByAPIAndDBByNDCJsonPathAndColumnName(String jsonPath, String columnName) throws Throwable {
        saveDrugGroupDetail.verifyNDCDetailsRowKey(jsonPath,columnName);
    }

    @Then("^User verify the saveDrugGroupDetails valid Response body key \"([^\"]*)\" and expected value \"([^\"]*)\" of string$")
    public void userVerifyTheSaveDrugGroupDetailsValidResponseBodyKeyAndExpectedValueOfString(String actualVal, String expectedVal) throws Throwable {
        saveDrugGroupDetail.validationResultsString(actualVal,expectedVal);
    }
}
