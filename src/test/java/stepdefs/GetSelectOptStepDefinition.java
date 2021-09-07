package stepdefs;

import baseSteps.GetSelectOpt;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetSelectOptStepDefinition {
    public GetSelectOpt getSelectOpt=new GetSelectOpt();
    @Given("^User hits the \"([^\"]*)\" with get request$")
    public void userHitsTheWithGetRequest(String endpoint) throws Throwable {
        getSelectOpt.getSelectOptionsResource(endpoint);
    }

    @Then("^User verify status code \"([^\"]*)\" for the response$")
    public void userVerifyStatusCodeForTheResponse(int statusCode) throws Throwable {
        getSelectOpt.getSelectOptionsResponseStatusCode(statusCode);
    }

    @Then("^User validate the getSelectOptions Json response body with data base where Jsonpath=\"([^\"]*)\" Query=\"([^\"]*)\" and ColumnName\"([^\"]*)\"$")
    public void userValidateTheJsonResponseBodyWithDataBaseWhereJsonpathQueryAndColumnName(String jsonPath,String query,String columnName) throws Throwable {
        getSelectOpt.validatingGetSelectOptionsResponseBody(jsonPath,query,columnName);
    }

    @Then("^The response is in JSON format$")
    public void theResponseIsInJSONFormate() {
        getSelectOpt.getSelectOptionsResponseBody();
    }

    @When("^User executes the query \"([^\"]*)\" And matches the CodeValue and CodeDescription returned by API and DB$")
    public void userExecutesTheQueryAndMatchesTheCodeValueAndCodeDescriptionReturnedByAPIAndDB(String query) throws Throwable {
        getSelectOpt.validatingCodeValueAndCodeDescription(query);
    }

    @Then("^User Verifies with DB using Query \"([^\"]*)\" and ColumnName \"([^\"]*)\"$")
    public void userVerifiesWithDBUsingQueryAndColumnName(String query, String columnName){
     getSelectOpt.validateJSONResponse(query,columnName);
    }

}
