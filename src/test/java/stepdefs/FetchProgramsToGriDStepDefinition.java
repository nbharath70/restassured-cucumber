package stepdefs;
import baseSteps.FetchProgramsToGrid;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class FetchProgramsToGriDStepDefinition {
    FetchProgramsToGrid fetchProgramsToGrid=new FetchProgramsToGrid();
    @Given("^User Runs the Query \"([^\"]*)\" and Get the Contract Id of Program$")
    public void userRunsTheQueryAndGetTheContractIdOfProgram(String query) {
        fetchProgramsToGrid.programContractID(query);
    }
    @When("^User hits the \"([^\"]*)\" Endpoint with get request$")
    public void userHitsEndpointWithGetRequest(String endpoint) {
        fetchProgramsToGrid.hitFetchProgramsToGridEndpoint(endpoint);
    }
    @Then("^User Verifies the API response Status code is \"([^\"]*)\" for FetchProgramsToGridApi$")
    public void userVerifiesTheApiResponseStatusCode(int statusCode){
        fetchProgramsToGrid.verifyFetchProgramsToGridAPIResponse(statusCode);
    }
    @Then("^Verify response is in JSON format$")
    public void verifyResponseFormat(){
        fetchProgramsToGrid.verifyFetchProgramToGridResponseJSONFormat();
    }

    @Then("^User Runs the Query \"([^\"]*)\" and matches the Colums \"([^\"]*)\" and JSON from response$")
    public void userRunsTheQueryAndMatchesTheColumsAndJSONFromResponse(String query, String columnName) {
        fetchProgramsToGrid.verifyFetchProgramResponseDetailsWithDbTable(query,columnName);
    }

    @And("^User executes the query \"([^\"]*)\" and get the ProgramDetailJSON from DB$")
    public void userExecutesTheQueryAndGetTheProgramDetailJSONFromDB(String query){
        fetchProgramsToGrid.getProgramDetailJSON(query);
    }

    @Then("^User validate the details with JSON by \"([^\"]*)\" JSonpaths for Progarms to programgrid$")
    public void userValidateTheDetailsWithJSONByJSonpathsForProgarmsToProgramgrid(String jsonpath)  {
        fetchProgramsToGrid.verifyProgarmDetailJSONWithDB(jsonpath);
    }

    @Then("^User validate the DrugGroupID Details with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userValidateTheDrugGroupIDDetailsWithAnd(String dBJSONPath, String aPIJSONPath){
       fetchProgramsToGrid.verifyDrugGroupIDWithDB(dBJSONPath,aPIJSONPath);
    }

    @And("^User takes the DrugGroupID of drugs by \"([^\"]*)\" from Program DetailJSON$")
    public void userTakesTheDrugGroupIDOfDrugsByFromProgramDetailJSON(String jSONPaths) {
        fetchProgramsToGrid.getRebatableDrugGroupIDfromJSON(jSONPaths);
    }

    @Then("^User validate the ListName with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userValidateTheListNameWithAnd(String dBJSONPath, String aPIJSONPAth) {
        fetchProgramsToGrid.verifyDrugListNameAndListType(dBJSONPath,aPIJSONPAth);
    }

    @Then("^User validate the ListType with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userValidateTheListTypeWithAnd(String dBJSONPath, String aPIJSONPAth) throws Throwable {
        fetchProgramsToGrid.verifyDrugListNameAndListType(dBJSONPath,aPIJSONPAth);
    }

    @And("^User executes the query\"([^\"]*)\" ,\"([^\"]*)\"and get drugDetailJSON from DB$")
    public void userExecutesTheQueryAndGetDrugDetailJSONFromDB(String initialSplitQuery, String finalSplitQuery) {
        fetchProgramsToGrid.getDruggroupDetails(initialSplitQuery,finalSplitQuery);

    }

    @When("^User hits the \"([^\"]*)\" Endpoint with get request for given programID \"([^\"]*)\"$")
    public void userHitsTheEndpointWithGetRequestForGivenProgramID(String endpoint, String ProgramID) throws Throwable {
        fetchProgramsToGrid.hitFetchProgramsToGridEndpoint(endpoint,ProgramID);
    }

    @Then("^User verify the fetchProgramsToGrid response header Error Code value \"([^\"]*)\"$")
    public void userVerifyTheFetchProgramsToGridResponseHeaderErrorCodeValue(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
