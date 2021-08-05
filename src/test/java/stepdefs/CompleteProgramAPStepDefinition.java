package stepdefs;

import baseSteps.CompleteProgramAPI;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class CompleteProgramAPStepDefinition {
    CompleteProgramAPI inProgressToCompleted= new CompleteProgramAPI();
    @Given("^User runs the Query \"([^\"]*)\" and picks Program \"([^\"]*)\"$")
    public void userRunsTheQueryAndPicksProgramRowkey(String queryKey, String columnNameKey)  {
        inProgressToCompleted.runQueryAndPickProgramRowkey(queryKey,columnNameKey);

    }

    @And("^User Hits \"([^\"]*)\" with Post API request and try to change the Program status$")
    public void userHitsAPIWithPostRequestAndTryToChangeTheProgramStatus(String endPoint) {
        inProgressToCompleted.hitAPIWithPostRequestAndTryToChangeTheProgramStatus(endPoint);
    }

    @And("^User verifies the valid error msg \"([^\"]*)\" from response$")
    public void userVerifiesTheValidErrorMsgFromResponse(String errorMsgKey)  {
        inProgressToCompleted.verifyErrorMsgFromResponse(errorMsgKey);
    }

    @And("^User verifies the valid status code \"([^\"]*)\" in API response$")
    public void userVerifiesTheValidStatusCodeInAPIResponse(int statusCode)  {
        inProgressToCompleted.verifyTheValidStatusCodeInAPIResponse(statusCode);
    }

    @And("^User redoes the changes made by executing \"([^\"]*)\"$")
    public void userRedoesTheChangesMadeByExecutingUpdateQuery(String queryKey)  {
        inProgressToCompleted.redoTheChangesMadeByExecutingUpdateQuery(queryKey);

    }

    @And("^User verifies the success msg \"([^\"]*)\" from API response for recordupdated \"([^\"]*)\"$")
    public void userVerifiesTheSuccessMsgFromAPIResponseForRecordupdated(String jsonPathKey, String expectedValue)  {
        inProgressToCompleted.verifyTheSuccessMsgFromAPIResponseForRecordupdated(jsonPathKey,expectedValue);
    }
}
