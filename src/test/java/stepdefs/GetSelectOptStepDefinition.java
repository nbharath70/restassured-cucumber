package stepdefs;

import baseSteps.GetSelectOpt;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetSelectOptStepDefinition {
    public GetSelectOpt getSelectOpt=new GetSelectOpt();
    @Given("^User hits the \"([^\"]*)\" API$")
    public void userHitsTheWithGetRequest(String endpoint) throws Throwable {
        getSelectOpt.getSelectOptionsResource(endpoint);
    }

    @Then("^User verify status code \"([^\"]*)\" for the response$")
    public void userVerifyStatusCodeForTheResponse(int statusCode) throws Throwable {
        getSelectOpt.getSelectOptionsResponseStatusCode(statusCode);
    }

    @Then("^The response is in JSON format$")
    public void theResponseIsInJSONFormate() {
        getSelectOpt.getSelectOptionsResponseBody();
    }

    @Then("^User Verifies with DB using Query \"([^\"]*)\" and ColumnName \"([^\"]*)\"$")
    public void userVerifiesWithDBUsingQueryAndColumnName(String query, String columnName){
     getSelectOpt.validateJSONResponse(query,columnName);
    }

}
