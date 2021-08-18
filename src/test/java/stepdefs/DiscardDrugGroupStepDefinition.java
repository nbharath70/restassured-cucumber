package stepdefs;

import baseSteps.DiscardContract;
import baseSteps.DiscardDrugGroup;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DiscardDrugGroupStepDefinition {
    DiscardDrugGroup discardDrugGroup = new DiscardDrugGroup();
    @Given("^User executes the \"([^\"]*)\" query and retrieves rowkey$")
    public void userExecutesTheQueryAndRetrievesRowkey(String query)  {
        discardDrugGroup.getDrugGroupRowKey(query);
    }

    @When("^User hits the \"([^\"]*)\" Endpoint with delete request$")
    public void userHitsTheEndpointWithDeleteRequest(String endPoint) {
        {
            discardDrugGroup.discardDrugGroup(endPoint);
        }
    }

    @Then("^User verifies the valid status code \"([^\"]*)\" in the Discard drug group response$")
    public void userVerifiesTheValidStatusCodeInTheDiscardDrugGroupResponse(int statusCode) {
        discardDrugGroup.verifyStatusCodeOfDiscardDrugGroupAPI(statusCode);
    }

    @And("^user runs \"([^\"]*)\" query to activate the drug list$")
    public void userRunsQueryToActivateTheDrugList(String restoreTheDrugGroupStatusToNew) {
        discardDrugGroup.reactivateDrugListToNew(restoreTheDrugGroupStatusToNew);

    }

    @Then("^User verifies the discard Drug group response is in JSON format$")
    public void userVerifiesTheDiscardDrugGroupResponseIsInJSONFormat() {
        discardDrugGroup.verifyTheDiscardDrugGroupResponseFormat();
    }

    @Then("^User verifies the discard Drug group response message json value$")
    public void userVerifiesTheDiscardDrugGroupResponseMessageJsonValue() {
        discardDrugGroup.verifyResponseMessageValue();
    }
    @And("^user verifies the \"([^\"]*)\" as zero in database for discarded drug group$")
    public void userVerifiesTheAsZeroInDatabaseForDiscardedDrugGroup(String query) {
        discardDrugGroup.verifyDiscardedDrugGroupIsCurrentFlag(query);
    }


    @And("^User verifies the discarded Drug group response \"([^\"]*)\" json value$")
    public void userVerifiesTheDiscardedDrugGroupResponseJsonValue(String drugGroupResponseErrorMessage) {
        discardDrugGroup.verifyResponseErrorMessageValue(drugGroupResponseErrorMessage);
    }

    @And("^User verifies the discarded Drug group  \"([^\"]*)\" in json response$")
    public void userVerifiesTheDiscardedDrugGroupInJsonResponse(String discardedErrorMessage){
        discardDrugGroup.verifyTheDiscardedDrugGroupInJsonResponse(discardedErrorMessage);

    }

    @And("^User verifies the discarded Drug group response for \"([^\"]*)\" json value$")
    public void userVerifiesTheDiscardedDrugGroupResponseForJsonValue(String inReviewErrorMessage) {
        discardDrugGroup.verifyinReviewResponseInJSON(inReviewErrorMessage);
    }
}

