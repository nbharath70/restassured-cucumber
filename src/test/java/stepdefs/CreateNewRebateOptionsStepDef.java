package stepdefs;

import TestBase.TestBase;
import baseSteps.CreateNewRebateOption;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CreateNewRebateOptionsStepDef extends TestBase {
    CreateNewRebateOption createNewRebateOption=new CreateNewRebateOption();
    @Given("^User create new rebate options details$")
    public void userCreateNewRebateOptionsDetails(DataTable dataTable) {
        createNewRebateOption.createNewRebateOptionDetailsData(dataTable);
    }

    @Then("^User hits the \"([^\"]*)\" with post request of CreateNewRebateOptions save progress API$")
    public void userHitsTheWithPostRequestOfCreateNewRebateOptionsSaveProgressAPI(String endPoint) throws Throwable {
        createNewRebateOption.createNewRebateOptionPostCall(endPoint);
    }

    @Then("^User executes the query \"([^\"]*)\" by contract name \"([^\"]*)\" to get contractID to create rebate options$")
    public void userExecutesTheQueryByContractNameToGetContractIDToCreateRebateOptions(String query, String contractName) throws Throwable {
        createNewRebateOption.getContractIDByContractName(query,contractName);
    }

    @Then("^User executes the query \"([^\"]*)\" by BenefitRulename \"([^\"]*)\" to get benefitRuleID to create rebate options$")
    public void userExecutesTheQueryByBenefitRulenameToGetBenefitRuleIDToCreateRebateOptions(String query, String benefitRuleName) throws Throwable {
        createNewRebateOption.getBenefitRuleID(query,benefitRuleName);
    }

    @Then("^User executes the query \"([^\"]*)\" to get programID from contractID to create rebate options$")
    public void userExecutesTheQueryToGetProgramIDFromContractIDToCreateRebateOptions(String query) throws Throwable {
        createNewRebateOption.getProgramID(query);
    }

    @Then("^User executes the query \"([^\"]*)\" to get rebateableDrugListID for create new rebate options$")
    public void userExecutesTheQueryToGetRebateableDrugListIDForCreateNewRebateOptions(String query) throws Throwable {
        createNewRebateOption.getRebateableDrugListID(query);
    }

    @Then("^User verify createNewRebateOptionsSaveProgress status code \"([^\"]*)\" for the response$")
    public void userVerifyCreateNewRebateOptionsSaveProgressStatusCodeForTheResponse(int statusCode) throws Throwable {
        createNewRebateOption.createNewRebateOptionPostCallStatusCode(statusCode);
    }

    @Then("^User verify the valid createNewRebateOptionsSaveProgress Response body key \"([^\"]*)\" and expected value \"([^\"]*)\"$")
    public void userVerifyTheValidCreateNewRebateOptionsSaveProgressResponseBodyKeyAndExpectedValue(String actualResult, String expectedResult) throws Throwable {
        createNewRebateOption.validationResults(actualResult,expectedResult);
    }

    @Then("^User verify the createNewRebateOptionsSaveProgress valid Response body key \"([^\"]*)\" and expected value \"([^\"]*)\" of string$")
    public void userVerifyTheCreateNewRebateOptionsSaveProgressValidResponseBodyKeyAndExpectedValueOfString(String actualResult, String expectedResult) throws Throwable {
        createNewRebateOption.validationResultsString(actualResult,expectedResult);
    }

    @Then("^validate the rebate option ID by executing query \"([^\"]*)\" by rebate option name \"([^\"]*)\" and response json \"([^\"]*)\"$")
    public void validateTheRebateOptionIDByExecutingQueryByRebateOptionNameAndResponseJson(String query, String rebateOtionName, String jsonPath) throws Throwable {
        createNewRebateOption.verifyRebateOptionID(query,rebateOtionName,jsonPath);
    }

    @Then("^User executes the query \"([^\"]*)\" by RebateOptionName \"([^\"]*)\" for deleting existing rebate option record from DB$")
    public void userExecutesTheQueryByRebateOptionNameForDeletingExistingRebateOptionRecordFromDB(String query, String rebateOptionName) throws Throwable {
        createNewRebateOption.deleteRebateOption(query,rebateOptionName);
    }

    @Then("^User send the invalid Program ID \"([^\"]*)\" for validation$")
    public void userSendTheInvalidProgramIDForValidation(int invalidProgramID) throws Throwable {
        createNewRebateOption.invalidProgramID(invalidProgramID);
    }

    @Then("^User verify the createNewRebateOptionsSaveProgress response header Error Code value \"([^\"]*)\"$")
    public void userVerifyTheCreateNewRebateOptionsSaveProgressResponseHeaderErrorCodeValue(String expectedErrorCode) throws Throwable {
        createNewRebateOption.verifyCreateNewRebateOptionSaveProgressResponseHeaderErrorCode(expectedErrorCode);
    }
}
