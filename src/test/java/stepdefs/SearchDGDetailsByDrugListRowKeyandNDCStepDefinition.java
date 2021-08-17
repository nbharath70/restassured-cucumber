package stepdefs;

import baseSteps.SearchDGDetailsByDrugListRowKeyandNDC;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SearchDGDetailsByDrugListRowKeyandNDCStepDefinition {
    SearchDGDetailsByDrugListRowKeyandNDC searchDGDetails=new SearchDGDetailsByDrugListRowKeyandNDC();

    @Given("^User executes the \"([^\"]*)\" and captures DL \"([^\"]*)\"$")
    public void userExecutesTheSQLAndCapturesDGRowkey(String queryKey, String columnNameKey)  {
        searchDGDetails.executeSQLAndCapturesDGRowkey(queryKey,columnNameKey);

    }

    @And("^User executes the \"([^\"]*)\" and captures the NDC from \"([^\"]*)\"$")
    public void userExecutesTheQueryAndCapturesTheNDC(String queryKey, String columnNameKey)  {
        searchDGDetails.executeQueryAndCaptureTheNDC(queryKey,columnNameKey);
    }

    @And("^User Hits resource \"([^\"]*)\" with Get API request$")
    public void userHitsResourceWithGetAPIRequest(String endPointKey)  {
        searchDGDetails.hitsResourceWithGetAPIRequest(endPointKey);

    }

    @And("^User verifies the correct status code \"([^\"]*)\" from API response$")
    public void userVerifiesTheCorrectStatusCodeFromAPIResponse(int statusCode) {
        searchDGDetails.verifyCorrectStatusCodeFromAPIResponse(statusCode);
    }




    @Then("^User executes the query \"([^\"]*)\" and gets Drug details from column \"([^\"]*)\"$")
    public void userExecutesTheQueryAndGetsDrugDetails(String queryKey,String columnName)  {
        searchDGDetails.executesTheQueryAndGetsDrugDetails(queryKey,columnName);
    }


    @And("^User Verifies the DBJson with API response Json$")
    public void verifyTheDBJsonWithAPIResponseJson(DataTable dataTable) {
        searchDGDetails.verifyTheDBJsonWithAPIResponseJson(dataTable);

    }

    @Given("^User sets null in DL rowkey$")
    public void userSetsNullInDLRowkey() {
        searchDGDetails.setsNullInDLRowkey();
    }

    @Then("^User validates the correct \"([^\"]*)\" and validates \"([^\"]*)\"$")
    public void userValidatesTheCorrectAndValidates(String errorMsgJsonKey, String errorMsgJsonPathKey)  {
        searchDGDetails.validatesTheCorrectAndValidates(errorMsgJsonKey,errorMsgJsonPathKey);
    }

    @Given("^User sets null in NDC$")
    public void userSetsNullInNDC() {
        searchDGDetails.setNullInNDC();
    }

    @And("^User Hits resource \"([^\"]*)\" with Get API request with (\\d+) NDC chars$")
    public void userHitsResourceWithGetAPIRequestWithNDCChars(String endPointKey, int x)  {
        searchDGDetails.hitsResourceWithGetAPIRequestWithNDCChars(endPointKey);
    }
}
