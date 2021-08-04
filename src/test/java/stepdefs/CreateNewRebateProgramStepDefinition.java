package stepdefs;

import TestBase.TestBase;
import baseSteps.CreateNewRebateProgram;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CreateNewRebateProgramStepDefinition extends TestBase {
    CreateNewRebateProgram createNewRebateProgram=new CreateNewRebateProgram();
    @Given("^User create & sava new rebate program$")
    public void userCreateSavaNewRebateProgram(DataTable dataTable) {
        createNewRebateProgram.createNewRebateProgramDetailsData(dataTable);
    }
    @Then("^User hits the \"([^\"]*)\" with post request of CreateNewRebateProgram$")
    public void userHitsTheWithPostRequestOfCreateNewRebateProgram(String endPoint) throws Throwable {
        createNewRebateProgram.createNewRebateProgramPostCall(endPoint);
    }

    @Then("^User executes \"([^\"]*)\" query to delete existing rebate program by Contract_ID \"([^\"]*)\" from the database$")
    public void userExecutesQueryToDeleteExistingRebateProgramByContract_IDFromTheDatabase(String deleteRebateProgram, String contractID) throws Throwable {
        createNewRebateProgram.deleteRebateProgramFromDB(deleteRebateProgram,contractID);
    }

    @Then("^User executes the query \"([^\"]*)\" and \"([^\"]*)\" for contract & Amendment name \"([^\"]*)\" to update the contract record status from New to InProgress from the database$")
    public void userExecutesTheQueryAndForContractAmendmentNameToUpdateTheContractRecordStatusFromNewToInProgressFromTheDatabase(String contractHeaderQuery,String contractDetailQuery,String contractName) throws Throwable {
        createNewRebateProgram.updateContractRecordToInProgressStatusFromDB(contractHeaderQuery,contractDetailQuery,contractName);
    }



    @Then("^User validate the programID by executing query \"([^\"]*)\" by passing jsonPath \"([^\"]*)\" and columnName \"([^\"]*)\"$")
    public void userValidateTheProgramIDByExecutingQueryByPassingJsonPathAndColumnName(String query, String json, String columnName) throws Throwable {
        createNewRebateProgram.valid(query,json,columnName);
    }

    @Then("^User verify createNewRebateProgram status code \"([^\"]*)\" for the response$")
    public void userVerifyCreateNewRebateProgramStatusCodeForTheResponse(Integer statusCode) throws Throwable {
        createNewRebateProgram.createNewRebateProgramPostCallStatusCode(statusCode);
    }

    @Then("^User verify the valid createNewRebateProgram Response body key \"([^\"]*)\" and expected value \"([^\"]*)\"$")
    public void userVerifyTheValidCreateNewRebateProgramResponseBodyKeyAndExpectedValue(String actualResult, String expectedResult) throws Throwable {
        createNewRebateProgram.validationResults(actualResult,expectedResult);
    }

    @Then("^User verify the createNewRebateProgram valid Response body key \"([^\"]*)\" and expected value \"([^\"]*)\" of string$")
    public void userVerifyTheCreateNewRebateProgramValidResponseBodyKeyAndExpectedValueOfString(String actualResult, String expectedResult) throws Throwable {
        createNewRebateProgram.validationResultsString(actualResult,expectedResult);
    }

    @Then("^User verify the createNewRebateProgram response header Error Code value \"([^\"]*)\"$")
    public void userVerifyTheCreateNewRebateProgramResponseHeaderErrorCodeValue(String errorCode) throws Throwable {
        createNewRebateProgram.verifyCreateNewRebateProgramResponseHeaderErrorCode(errorCode);
    }
}
