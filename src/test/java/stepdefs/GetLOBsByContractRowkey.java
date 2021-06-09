package stepdefs;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import baseSteps.GetLOBsByContractRowkeyBaseStep;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import TestBase.TestBase;
import org.junit.Test;
import baseSteps.GetLOBsByContractRowkeyBaseStep;

public class GetLOBsByContractRowkey {

    GetLOBsByContractRowkeyBaseStep getLobsBaseSteps=new GetLOBsByContractRowkeyBaseStep();

    /*Glue Code for
        Scenario: get LOBS for a valid contract and verify the correct status code is returned */

    @Given("^User executes query \"([^\"]*)\" and gets value for column \"([^\"]*)\"$")
    public void user_connects_to_db_and_executes_query_and_gets_value_for_rowkey_col(String queryKey, String columnNameKey) {
        getLobsBaseSteps.get_value_for_rowkey_col_from_DB(queryKey,columnNameKey);
    }

    @And("^User Hits \"([^\"]*)\" with Get API request$")
    public void userHitsEndPointWithGetAPIRequest(String endPointUrl)  {
        getLobsBaseSteps.hitEndPointWithGetAPIRequest(endPointUrl);
    }

    @Then("^User verifies the valid status code \"([^\"]*)\" in get LOBs API response$")
    public void userVerifiesTheValidStatusCodeInGetLOBsAPIResponse(int expectedStatusCode) throws Throwable {
        getLobsBaseSteps.VerifyTheValidStatusCodeInGetLOBsAPIResponse(expectedStatusCode);
    }


    /*Glue Code for
        Scenario: get LOBS for a valid contract and verify it from DB */

    @And("^User executes query \"([^\"]*)\" and gets Contract \"([^\"]*)\" from DB$")
    public void userExecutesQueryAndGetsContractFromDB(String queryKey, String columnNameKey) throws Throwable {
        getLobsBaseSteps.executeQueryAndGetContractFromDB(queryKey,columnNameKey);
    }

    @Then("^User verifies API response with DB response$")
    public void userVerifiesAPIResponseWithDBResponse() {
        getLobsBaseSteps.verifiyAPIResponseWithDBResponse();
    }

    @Given("^User executes \"([^\"]*)\" and gets value for column \"([^\"]*)\"$")
    public void userExecutesAndGetsValueForColumn(String queryKey, String columnNameKey)  {
        getLobsBaseSteps.get_value_for_rowkey_from_DB_and_generate_invalidRowkey(queryKey,columnNameKey);

    }

    @Then("^User verifies API response with error msg \"([^\"]*)\"$")
    public void userVerifiesAPIResponseWithErrorMsg(String errorMsgKey) throws Throwable {
        getLobsBaseSteps.verifyAPIResponsewithErrorMsg(errorMsgKey);
    }

    @Given("^User Hits \"([^\"]*)\" with blank rowKey and Get API request$")
    public void userHitsEndPointWithBlankRowKeyAndGetAPIRequest(String endPointKey) throws Throwable {
        getLobsBaseSteps.hitsEndPointWithBlankRowKeyAndGetAPIRequest(endPointKey);
    }

    @Then("^User verifies API response with Blank Contract RowKey error msg \"([^\"]*)\"$")
    public void userVerifiesAPIResponseWithBlankContractRowKeyErrorMsg(String errorMsgKey) throws Throwable {
        getLobsBaseSteps.verifyAPIResponsewithBlankContractRowKeyErrorMsg(errorMsgKey);
    }

    @Given("^User Hits \"([^\"]*)\" with rowKey \"([^\"]*)\" and Get API request$")
    public void userHitsWithRowKeyAndGetAPIRequest(String endPointKey, String typeMismatchRowkey)  {
        getLobsBaseSteps.hitEndPointWithMismatchRowKeyandGetAPIRequest(endPointKey,typeMismatchRowkey);
    }

    @Then("^User verifies API response with type mismatch error msg \"([^\"]*)\"$")
    public void userVerifiesAPIResponseWithTypeMismatchErrorMsg(String errorMsgkey)  {
        getLobsBaseSteps.verifiesAPIResponseWithTypeMismatchErrorMsg(errorMsgkey);
    }


    @Then("^User verifies the LOBS for a contract response header Error Code value \"([^\"]*)\"$")
    public void userVerifiesTheLOBSForAContractResponseHeaderErrorCodeValue(String expectedHeaderErrorCode)  {
        getLobsBaseSteps.verifiesTheLOBsForAContractResponseHeaderErrorCodeValue(expectedHeaderErrorCode);
    }
}
