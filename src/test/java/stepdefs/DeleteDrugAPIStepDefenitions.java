package stepdefs;

import baseSteps.DeleteDrugAPI;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class DeleteDrugAPIStepDefenitions {
    DeleteDrugAPI deleteDrugAPI=new DeleteDrugAPI();
    @Given("^User executes the query \"([^\"]*)\"and gets a valid drug detail \"([^\"]*)\" , \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userExecutesTheQueryAndGetsAValidDrugDetail(String queryKey, String rowkeyKey, String mfrDLId, String ndc,String startDate, String endDate)  {
        deleteDrugAPI.executeTheQueryAndGetAValidDrugDetail(queryKey, rowkeyKey,mfrDLId,ndc,startDate,endDate);
    }

    @And("^User prepares request body with the list of drug rowkeys$")
    public void userPreparesRequestBodyWithTheListOfDrugRowkeys() {
        deleteDrugAPI.prepareRequestBodyWithTheListOfDrugRowkeys();
    }

    @Then("^User hits the \"([^\"]*)\" with Delete request$")
    public void userHitsTheWithPostRequest(String endPointKey)  {
        deleteDrugAPI.hitEndPointWithPostRequest(endPointKey);

    }
    @And("^User Verifies the correct status code \"([^\"]*)\" from API response$")
    public void userVerifiesTheCorrectStatusCodeFromAPIResponse(int statusCode)  {
        deleteDrugAPI.verifyTheCorrectStatusCode(statusCode);
    }

    @And("^User verifies APIresponse with \"([^\"]*)\"$")
    public void userVerifiesAPIresponseWith(String responseMsgKey)  {
        deleteDrugAPI.verifyAPIresponseWith(responseMsgKey);
    }


    @Then("^User executes the \"([^\"]*)\" verifies the data base for the deleted record$")
    public void userVerifiesTheDataBaseForTheDeletedDrug(String queryKey) {
        deleteDrugAPI.verifyTheDataBaseForTheDeletedDrug(queryKey);
    }


    @And("^User deletes the new deleted record from the DB by executing \"([^\"]*)\"$")
    public void userDeletesTheNewDeletedRecordFromTheDBByExecuting(String queryKey)  {
        deleteDrugAPI.deleteTheNewDeletedRecordFromDB(queryKey);
    }


    @And("^User verifies APIresponse message with \"([^\"]*)\"$")
    public void userVerifiesAPIresponseMessageWithDeleteAddedDrugResponse(String responseMsgKey)  {
        deleteDrugAPI.verifiesAPIresponseMessageWithDeleteAddedDrugResponse(responseMsgKey);

    }

    @Given("^User prepares request body with InValid RowKey \"([^\"]*)\" of drug$")
    public void userPreparesRequestBodyWithInValidRowKeyOfDrug(int rowKey)  {
        deleteDrugAPI.prepareRequestBodyWithInValidRowKeyOfDrug(rowKey);
    }

    @Given("^User executes the query \"([^\"]*)\"and gets a drug detail \"([^\"]*)\"$")
    public void userExecutesTheQueryAndGetsADrugDetail(String queryKey, String rowkeyKey)  {
        deleteDrugAPI.executesTheQueryAndGetsADrugDetailRowkey(queryKey,rowkeyKey);
    }

    @Given("^User prepares request body with valid and InValid RowKey \"([^\"]*)\" of drug$")
    public void userPreparesRequestBodyWithValidAndInValidRowKeysOfDrug(int rowKey)  {
        deleteDrugAPI.prepareRequestBodyWithValidAndInValidRowKeysOfDrug(rowKey);
    }

    @Given("^User prepares request body with InValid RowKeys \"([^\"]*)\" and \"([^\"]*)\" of drug$")
    public void userPreparesRequestBodyWithInValidRowKeysAndOfDrug(int rowKey1, int rowKey2)  {
        deleteDrugAPI.prepareRequestBodyWithInValidRowKeys(rowKey1,rowKey2);
    }

    @Given("^^User executes the query \"([^\"]*)\"and get multiple valid drug detail \"([^\"]*)\" , \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userExecutesTheQueryAndGetMultipleValidDrugDetail(String queryKey, String rowkeyKey, String mfrDLId, String ndc,String startDate, String endDate) {
        deleteDrugAPI.executeQueryAndGetMultipleValidDrugDetail(queryKey, rowkeyKey, mfrDLId, ndc,startDate, endDate);
    }


    @And("^User prepares request body with the multiple drug rowkeys$")
    public void userPreparesRequestBodyWithTheMultipleDrugRowkeys() {
        deleteDrugAPI.prepareRequestBodyWithMultipleDrugRowkeys();
    }

    @And("^User verifies APIresponse with \"([^\"]*)\" for two drugs$")
    public void userVerifiesAPIresponseWithSuccessMsgForTwoDrugs(String expectedMsgKey)  {
        deleteDrugAPI.verifyAPIresponseWithSuccessMsgforTwoDrugs(expectedMsgKey);
    }

    @Then("^User executes the \"([^\"]*)\" verifies the data base for the deleted record for two drugs$")
    public void userExecutesTheQueryAndVerifyTheDataBaseForTheDeletedRecordForTwoDrugs(String queryKey)  {
        deleteDrugAPI.executeQueryAndVerifyTheDataBaseForTheDeletedRecordForTwoDrugs(queryKey);
    }

    @And("^User deletes the new deleted record from the DB by executing \"([^\"]*)\" for two drugs$")
    public void userDeletesTheNewDeletedRecordsFromTheDBByExecutingDeleteQueryForTwoDrugs(String queryKey)  {
       deleteDrugAPI.deleteTheNewDeletedRecordsFromTheDBByExecutingDeleteQueryForTwoDrugs(queryKey);
    }
}
