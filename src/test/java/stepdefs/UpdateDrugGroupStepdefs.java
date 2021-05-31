package stepdefs;

import baseSteps.UpdateDrugGroup;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class UpdateDrugGroupStepdefs {
    UpdateDrugGroup updateDrugGroup=new UpdateDrugGroup();
    @Given("^User update group details data$")
    public void userUpdateGroupDetailsData(DataTable dataTable) {
        updateDrugGroup.updateDrugGroupDetails(dataTable);
    }

    @Then("^User verify update drug group status code \"([^\"]*)\" for the response$")
    public void userVerifyUpdateDrugGroupStatusCodeForTheResponse(int statusCode) throws Throwable {
        updateDrugGroup.verifyUpdateDrugGroupStatusCode(statusCode);
    }

    @Then("^User verify the valid UpdateNewDrugGroup Response body key \"([^\"]*)\" and expected value \"([^\"]*)\"$")
    public void userVerifyTheValidUpdateNewDrugGroupResponseBodyKeyAndExpectedValue(String actualValue, String expectedValue) throws Throwable {
        updateDrugGroup.validateResponseBodyUpdateDrugGroup(actualValue,expectedValue);
    }

    @Then("^User hits the \"([^\"]*)\" with put request of UpdateNewDrugGroup$")
    public void userHitsTheWithPostRequestOfUpdateNewDrugGroup(String endPoint) throws Throwable {
        updateDrugGroup.updateDrugGroupPutCall(endPoint);
    }

    @Then("^User verify the valid Response UpdateDrugGroup body key \"([^\"]*)\" and expected value \"([^\"]*)\" of string$")
    public void userVerifyTheValidResponseUpdateDrugGroupBodyKeyAndExpectedValueOfString(String actualValue, String expectedValue) throws Throwable {
        updateDrugGroup.validateUpdateDrugGroupResponseByString(actualValue,expectedValue);
    }

    @Then("^User verify the UpdateDrugGroup response header Error Code value \"([^\"]*)\"$")
    public void userVerifyTheUpdateDrugGroupResponseHeaderErrorCodeValue(String expectedErrorCode) throws Throwable {
        updateDrugGroup.verifyUpdateDrugGroupResponseHeaderErrorCode(expectedErrorCode);
    }
}
