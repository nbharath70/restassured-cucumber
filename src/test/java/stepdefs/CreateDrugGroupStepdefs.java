package stepdefs;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import baseSteps.CreateDrugGroup;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.After;


public class CreateDrugGroupStepdefs extends TestBase {
    CreateDrugGroup createDrugGroup=new CreateDrugGroup();

    @Given("^User creates a new drug group$")
    public void userCreateTheCreateNewGroupDetailsData(DataTable dataTable) {
        createDrugGroup.createNewDrugGroupDetails(dataTable);
    }

    @Then("^User verify create new drug group status code \"([^\"]*)\" for the response$")
    public void userVerifyCreateNewDrugGroupStatusCodeForTheResponse(int statusCode) {
        createDrugGroup.verifyCreateNewDrugGroupStatusCode(statusCode);
    }

    @Then("^User verify the valid CreateNewDrugGroup Response body key \"([^\"]*)\" and expected value \"([^\"]*)\"$")
    public void userVerifyTheValidCreateNewDrugGroupResponseBodyKeyAndExpectedValue(String actualResult, String expectedResult) {
        createDrugGroup.validateResponseBodyCreateDrugGroup(actualResult,expectedResult);
    }

    @Then("^User verify the valid Response CreateNewDrugGroup body key \"([^\"]*)\" and expected value \"([^\"]*)\" of string$")
    public void userVerifyTheValidResponseCreateNewDrugGroupBodyKeyAndExpectedValueOfString(String actualResult, String expectedResult){
        createDrugGroup.validateCreateDrugGroupResponseByString(actualResult,expectedResult);
    }

    @Then("^User verify the valid Response body key \"([^\"]*)\" and execute the query \"([^\"]*)\" matches the Drug groupID return from API and DB$")
    public void userVerifyTheValidResponseBodyKeyAndExecuteTheQueryMatchesTheDrugGroupIDReturnFromAPIAndDB(String jsonPath, String query)  {
        createDrugGroup.verifyManufactureDrugGroupID(jsonPath,query);
    }

    @Then("^User verify the Create new group details response header Error Code value \"([^\"]*)\"$")
    public void userVerifyTheCreateNewGroupDetailsResponseHeaderErrorCodeValue(String expectedErrorCode)  {
        createDrugGroup.verifyCreateDrugGroupResponseHeaderErrorCode(expectedErrorCode);
    }

    @Then("^User hits the \"([^\"]*)\" with post request of CreateNewDrugGroup with query \"([^\"]*)\"$")
    public void userHitsTheWithPostRequestOfCreateNewDrugGroupWithQuery(String endpoint, String query) {
       createDrugGroup.createNewDrugGroup(endpoint,query);
    }

    @Then("^User discards the drugGroup and deletes the record from DB$")
    public void userDiscardsTheDrugGroupAndFinallyDeletesTheRecord() {
        createDrugGroup.discardDrugGroup();
        createDrugGroup.deleteDrugGroup();
    }

}
