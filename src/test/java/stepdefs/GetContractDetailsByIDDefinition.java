
package stepdefs;
import baseSteps.GetContractDetailsByID;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
public class GetContractDetailsByIDDefinition {

    GetContractDetailsByID contractDetails=new GetContractDetailsByID();

    @Given("^User Runs the Query \"([^\"]*)\" and Get the Rowkey from DB$")
    public void getTheRowkeyFromDb(String Query){
        contractDetails.getRowKey(Query);
    }

    @Given("^User hits the \"([^\"]*)\" Endpoint with get API request$")
    public void HitEndpointwithGetRequest(String Endpoint){
        contractDetails.hitGetContractDetilsByIdEndpoint(Endpoint);
    }

    @Then("^User Verifies the API response Status code is \"([^\"]*)\"$")
    public void VerifyStatusCodeOfAPI(int StatusCode){
        contractDetails.verifyContractDetailsByIDResponse(StatusCode);
    }

    @Then("^verify the Response in JSON format$")
    public void verifyTheResponseInJSONFormat() {
        contractDetails.verifygetContractDetailsByIDJSONFormat();
    }

    @Given("^User Runs the Fetching ContractDetailsID Query \"([^\"]*)\" and Get the ContractDetailsId from DB$")
    public void userRunsTheFetchingContractDetailsIDQueryAndGetTheContractDetailsIdFromDB(String query) {
        contractDetails.getContractID(query);
    }


    @Given("^User Runs the Fetching ManufactuereID Query \"([^\"]*)\" and Get the ManufactuereID from DB$")
    public void userRunsTheFetchingManufactuereIDQueryAndGetTheManufactuereIDFromDB(String query) {
        contractDetails.getManufacturerID(query);
    }

    @Given("^user Runs the Fetching ManufacturerDetails Query \"([^\"]*)\" and get All Manufacturer details from DB$")
    public void userRunsTheFetchingManufacturerDetailsQueryAndGetAllManufacturerDetailsFromDB(String query){
        contractDetails.getManufacturerDetails(query);
    }
    
    @Then("^Verify both the ManufacturerDetails response from DB and API$")
    public void verifyBothTheManufacturerDetailsResponseFromDBAndAPI() {
        contractDetails.matchManufacturerDetails();
    }

    @Given("^User Runs the Fetching ContractHeaderDetail Query \"([^\"]*)\" and Get the ContractHeaderDetails from DB$")
    public void userRunsTheFetchingContractHeaderDetailQueryAndGetTheContractHeaderDetailsFromDB(String query){
        contractDetails.getContractHeaderDetails(query);

    }

    @Then("^verify both the ContractHeaderDetails response from DB and API$")
    public void verifyBothTheContractHeaderDetailsResponseFromDBAndAPI() {
        contractDetails.matchContractHeaderDetails();
    }

    @Given("^User Runs the Fetching ContractDetailsJSON Query \"([^\"]*)\" and Get the ContractDetailsJSON from DB$")
    public void userRunsTheFetchingContractDetailsJSONQueryAndGetTheContractDetailsJSONFromDB(String query){
        contractDetails.getContractDetailJSON(query);
    }

    @Then("^verify Both API ContractDetailJSON with JSONpath \"([^\"]*)\" with DB JSON with DB JSON \"([^\"]*)\"$")
    public void verifyBothAPIContractDetailJSONWithJSONpathWithDBJSONWithDBJSON(String apiJsonPath, String dbJsonPath){
        contractDetails.matchContractDetailsJSONwithTwoJSONPAths(apiJsonPath,dbJsonPath);
    }

    @Then("^User runs the Query \"([^\"]*)\" and Validate the response with DB column \"([^\"]*)\"$")
    public void userRunsTheQueryAndValidateTheResponseWithDBColumn(String query, String columnName)  {
        contractDetails.validateJSONResponse(query,columnName);
    }

//    @Then("^User Validates the task message In Db Response$")
//    public void userValidatesTheTaskMessageInDbResponse() {
//        contractDetails.
//    }
}