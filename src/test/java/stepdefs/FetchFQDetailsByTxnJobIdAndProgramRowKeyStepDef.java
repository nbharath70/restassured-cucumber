package stepdefs;

import TestBase.TestBase;
import baseSteps.FetchFQDetailsByTxnJobIdAndProgramRowKey;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class FetchFQDetailsByTxnJobIdAndProgramRowKeyStepDef extends TestBase {
    FetchFQDetailsByTxnJobIdAndProgramRowKey fetchFQDetailsByTxnJobIdAndProgramRowKey=new FetchFQDetailsByTxnJobIdAndProgramRowKey();

    @Given("^User runs the query \"([^\"]*)\" and fetches valid jobID and ProgramRowKey$")
    public void fetchesValidJobidAndProgramrowkey(String query)  {
        fetchFQDetailsByTxnJobIdAndProgramRowKey.getJobIDAndProgramRowKey(query);
    }

    @And("^User hits the endpoint \"([^\"]*)\" jobID and programRowkey$")
    public void userHitsTheEndpointJobIDAndProgramRowkey(String endpoint){
      fetchFQDetailsByTxnJobIdAndProgramRowKey.hitEndpoint(endpoint);
    }

    @Then("^User verifies the fetchFQDetailsByFQHIdAndProgramRowKeyAPI statusCode is \"([^\"]*)\"$")
    public void userVerifiesTheFetchFQDetailsByFQHIdAndProgramRowKeyAPIStatusCodeIs(int statusCode)  {
      fetchFQDetailsByTxnJobIdAndProgramRowKey.verifyStatusCode(statusCode);
    }

    @And("^User verifies the Response in JSON Format of API$")
    public void userVerifiesTheResponseInJSONFormatOfAPI() {
        fetchFQDetailsByTxnJobIdAndProgramRowKey.verifyFormatJSON();
    }

    @And("^User hits the endpoint \"([^\"]*)\" jobID with invalid programRowkey$")
    public void userHitsTheEndpointJobIDWithInValidProgramRowkey(String endpoint)  {
        fetchFQDetailsByTxnJobIdAndProgramRowKey.hitEndpointwithInvalidProgramID(endpoint);
    }

    @And("^User hits the endpoint \"([^\"]*)\" jobID with invalid processTrasactionJOBID$")
    public void userHitsTheEndpointJobIDWithInValidProcessTrasactionJOBID(String endpoint){
        fetchFQDetailsByTxnJobIdAndProgramRowKey.hitEndpointwithInvalidTransactionJobID(endpoint);
    }

    @Given("^User hits the Endpoint \"([^\"]*)\" jobID with both blankValues$")
    public void userHitsTheEndpointJobIDWithBothBlankValues(String endpoint)  {
        fetchFQDetailsByTxnJobIdAndProgramRowKey.hitEndpointwithBothBlankvalues(endpoint);

    }

    @Then("^User verifies the invalid message \"([^\"]*)\" using jsonPath \"([^\"]*)\"$")
    public void userVerifiesTheInvalidMessageUsingJsonPath(String invalidMessageKey, String jsonPath){
        fetchFQDetailsByTxnJobIdAndProgramRowKey.validateInvalidValues(invalidMessageKey,jsonPath);
    }

    @And("^User Runs the query \"([^\"]*)\" and fetch the rebateOptionsRowkey from DB using columnName\"([^\"]*)\"$")
    public void userRunsTheQueryAndFetchTheRebateoptionsRowkeyFromDBUsingColumnName(String query, String columnName) {
     fetchFQDetailsByTxnJobIdAndProgramRowKey.getRebateOptionRowkey(query,columnName);
    }

    @And("^User Runs the query \"([^\"]*)\" and validate the rebate options response with DB columnname \"([^\"]*)\"$")
    public void userRunsTheQueryAndValidateTheRebateOptionResponseWithDBColumnname(String query, String columnName) throws Throwable {
        fetchFQDetailsByTxnJobIdAndProgramRowKey.validateRebateOptions(query,columnName);
    }
}
