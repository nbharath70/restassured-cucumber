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

    @And("^User hits the Endpoint \"([^\"]*)\" JobID and ProgramRowkey$")
    public void userHitsTheEndpointJobIDAndProgramRowkey(String endpoint){
      fetchFQDetailsByTxnJobIdAndProgramRowKey.hitEndpoint(endpoint);
    }

    @Then("^User verifies the fetchFQDetailsByFQHIdAndProgramRowKeyAPI Status Code is \"([^\"]*)\"$")
    public void userVerifiesTheFetchFQDetailsByFQHIdAndProgramRowKeyAPIStatusCodeIs(int statusCode)  {
      fetchFQDetailsByTxnJobIdAndProgramRowKey.verifyStatusCode(statusCode);
    }

    @And("^User verifies the Response in JSON Format of API$")
    public void userVerifiesTheResponseInJSONFormatOfAPI() {
        fetchFQDetailsByTxnJobIdAndProgramRowKey.verifyFormatJSON();
    }

    @And("^User hits the Endpoint \"([^\"]*)\" JobID with inValid ProgramRowkey$")
    public void userHitsTheEndpointJobIDWithInValidProgramRowkey(String endpoint)  {
        fetchFQDetailsByTxnJobIdAndProgramRowKey.hitEndpointwithInvalidProgramID(endpoint);
    }

    @And("^User hits the Endpoint \"([^\"]*)\" JobID with inValid processTrasactionJOBID$")
    public void userHitsTheEndpointJobIDWithInValidProcessTrasactionJOBID(String endpoint){
        fetchFQDetailsByTxnJobIdAndProgramRowKey.hitEndpointwithInvalidTransactionJobID(endpoint);
    }

    @Given("^User hits the Endpoint \"([^\"]*)\" JobID with both BlankValues$")
    public void userHitsTheEndpointJobIDWithBothBlankValues(String endpoint)  {
        fetchFQDetailsByTxnJobIdAndProgramRowKey.hitEndpointwithBothBlankvalues(endpoint);

    }

    @Then("^User verifies the invalid Message \"([^\"]*)\" using jsonPath \"([^\"]*)\"$")
    public void userVerifiesTheInvalidMessageUsingJsonPath(String invalidMessageKey, String jsonPath){
        fetchFQDetailsByTxnJobIdAndProgramRowKey.validateInvalidValues(invalidMessageKey,jsonPath);
    }

    @And("^User Runs the Query \"([^\"]*)\" and fetch the RebateoptionsRowkey from DB using ColumnName\"([^\"]*)\"$")
    public void userRunsTheQueryAndFetchTheRebateoptionsRowkeyFromDBUsingColumnName(String query, String columnName) {
     fetchFQDetailsByTxnJobIdAndProgramRowKey.getRebateOptionRowkey(query,columnName);
    }

    @And("^User Runs the Query \"([^\"]*)\" and Validate the Rebate option response with DB Columnname \"([^\"]*)\"$")
    public void userRunsTheQueryAndValidateTheRebateOptionResponseWithDBColumnname(String query, String columnName) throws Throwable {
        fetchFQDetailsByTxnJobIdAndProgramRowKey.validateRebateOptions(query,columnName);
    }
}
