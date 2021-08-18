package stepdefs;

import baseSteps.DeleteDrugAPI;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class DeleteDrugAPIStepDefenitions {
    DeleteDrugAPI deleteDrugAPI=new DeleteDrugAPI();
    @Given("^User executes the query \"([^\"]*)\"and gets a valid drug detail \"([^\"]*)\"$")
    public void userExecutesTheQueryAndGetsAValidDrugDetail(String queryKey, String rowkeyKey)  {
        deleteDrugAPI.executeTheQueryAndGetAValidDrugDetail(queryKey,rowkeyKey);
    }

    @And("^User prepares request body with the list of drug rowkeys$")
    public void userPreparesRequestBodyWithTheListOfDrugRowkeys() {
        deleteDrugAPI.prepareRequestBodyWithTheListOfDrugRowkeys();
    }

    @Then("^User hits the \"([^\"]*)\" with Delete request$")
    public void userHitsTheWithPostRequest(String endPointKey)  {
        deleteDrugAPI.hitEndPointWithPostRequest(endPointKey);

    }

    @And("^User verifies APIresponse with \"([^\"]*)\"$")
    public void userVerifiesAPIresponseWith(String responseMsgKey)  {
        deleteDrugAPI.verifyAPIresponseWith(responseMsgKey);
    }
}
