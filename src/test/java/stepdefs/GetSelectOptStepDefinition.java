package stepdefs;

import baseSteps.GetSelectOpt;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

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
}
