package stepdefs;

import baseSteps.TermDrugsFromDrugGroup;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TermDrugsfromDrugGroupStepDefnition {
    TermDrugsFromDrugGroup termDrugs =new TermDrugsFromDrugGroup();




    @Given("^User Runs the Query \"([^\"]*)\" and get valid Drug List Detail with columName \"([^\"]*)\"$")
    public void userRunsTheQueryAndGetValidDrugListDetailWithColumName(String query, String columnName) {
        termDrugs.executeQueryandfetchDrugListDetails(query,columnName);
    }

    @And("^User fetchs all the Details from the Result and createRequestBody$")
    public void userFetchsAllTheDetailsFromTheResultAndCreateRequestBody() {
        termDrugs.fetchAllTheDrugListDetails();
    }


    @When("^User Hits the API endpoint \"([^\"]*)\" with Post Request along with RequestBody$")
    public void userHitsTheAPIEndpointWithPostRequestAlongWithRequestBody(String enpoint) {
     termDrugs.hitEndpoint(enpoint);
    }

    @Then("^User Checks the Status Code of the Response is \"([^\"]*)\"$")
    public void userChecksTheStatusCodeOfTheResponseIs(int statuscode)  {
        termDrugs.verifyreponsecode(statuscode);
    }


    @Then("^User Runs the Query \"([^\"]*)\" to Update the EndDate and \"([^\"]*)\" Life Cycle Status of DrugList Detail$")
    public void userRunsTheQueryToUpdateTheEndDateAndLifeCycleStatusOfDrugListDetail(String updateStartDate, String upDateLifeCycleStatus){
        termDrugs.updatingBackDetailsinDB(updateStartDate,upDateLifeCycleStatus);
    }

    @Then("^User verifies the Response in JSON format$")
    public void userVerifiesTheResponseInJSONFormat() {
        termDrugs.verifyTermDrugAPIResponseFormatJSON();
    }

    @Then("^User Runs the Query\"([^\"]*)\" and Check the LifeCycle Status in Dbcolumn \"([^\"]*)\" as \"([^\"]*)\"$")
    public void userRunsTheQueryAndCheckTheLifeCycleStatusInDbcolumnAs(String query, String dbColumn, String expectedvalue) throws Throwable {
        termDrugs.verifyWithDB(query,dbColumn,expectedvalue);
    }
}
