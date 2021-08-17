package stepdefs;

import TestBase.TestBase;
import baseSteps.CreateBenefitRuleForAContract;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateBenefitRuleForAContractStepDefinition extends TestBase {
    CreateBenefitRuleForAContract createAndValidateBenefitRule=new CreateBenefitRuleForAContract();

    @And("^User create the Benefitruledata$")
    public void userCreateTheBenefitruledata(DataTable dataTable) {
        createAndValidateBenefitRule.createBenefitRuleJSONBody(dataTable);
    }

    @When("^User hits createBenefitRuleAPI \"([^\"]*)\" with Post Request$")
    public void userHitsCreateBenefitRuleAPIWithPostRequest(String endpoint){
        createAndValidateBenefitRule.hitCreateBenefitRuleAPI(endpoint);
    }

    @Given("^User runs the Query \"([^\"]*)\"and Get the Contract ContractId$")
    public void userRunsTheQueryAndGetTheContractContractId(String query)  {
        createAndValidateBenefitRule.getContractIdToUpdateAndCreateBenefitRule(query);
    }

    @And("^User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader \"([^\"]*)\" and ContractDetails \"([^\"]*)\" to InProgress$")
    public void userRunsUpdateQueryToUpdateLifeCycleStatusOfContractIDOfContractHeaderAndContractDetailsToInProgress(String contractheaderQuery, String contractDetailsQuery)  {
        createAndValidateBenefitRule.updateLifeCycleStatusToInprogressForContract(contractheaderQuery,contractDetailsQuery);
    }

    @Then("^User Verifies the Status Code is \"([^\"]*)\"$")
    public void userVerifiesTheStatusCodeIs(int statusCode) {
      createAndValidateBenefitRule.verifyStatusCodeofCreateBenefitRuleAPI(statusCode);
    }

    @Then("^user runs Deletes the BenefitRule Created by Executing the Query \"([^\"]*)\"$")
    public void userRunsDeletesTheBenefitRuleCreatedByExecutingTheQuery(String deleteQuery) {
       createAndValidateBenefitRule.deleteCreatedBenefitRule(deleteQuery);
    }

    @Then("^User User Redo's Change made to Lifecycle Status for both ContractHeader \"([^\"]*)\" and ContractDetail \"([^\"]*)\"$")
    public void userUserRedoSChangeMadeToLifecycleStatusForBothContractHeaderAndContractDetail(String contractHeaderQuery, String ContractDetailQuery){
        createAndValidateBenefitRule.updateLifeCycleStatusToNewForContract(contractHeaderQuery,ContractDetailQuery);
    }

    @Then("^User verify the valid Response body key of CreateBenefitRuleAPI \"([^\"]*)\" and expected value \"([^\"]*)\"$")
    public void userVerifyTheValidResponseBodyKeyOfCreateBenefitRuleAPIAndExpectedValue(String actualValue, String expectedValue)  {
       createAndValidateBenefitRule.validationCreateBenefitResultStatus(actualValue,expectedValue);
    }

    @Then("^User Verifies the headercode for this response is \"([^\"]*)\"$")
    public void userVerifiesTheHeadercodeForThisResponseIs(String headerCode){
       createAndValidateBenefitRule.validateHeaderCodeOfResponse(headerCode);
    }

    @Then("^User executes the query \"([^\"]*)\" by contract name \"([^\"]*)\" to get contractID to create benefit rule$")
    public void userExecutesTheQueryByContractNameToGetContractIDToCreateBenefitRule(String query, String contractName) throws Throwable {
        createAndValidateBenefitRule.getContractIDByContractName(query,contractName);
    }

    @Then("^User executes the query \"([^\"]*)\" by benefitRuleName \"([^\"]*)\"  for update benefit rule status to approved by contractID$")
    public void userExecutesTheQueryByBenefitRuleNameForUpdateBenefitRuleStatusToApprovedByContractID(String query, String benefitRuleName) throws Throwable {
        createAndValidateBenefitRule.updateBenefitRuleStatusApprove(query,benefitRuleName);
    }

    @Then("^User executes the query \"([^\"]*)\" by benefitRuleName \"([^\"]*)\" for delete benefit rule by contractID$")
    public void userExecutesTheQueryByBenefitRuleNameForDeleteBenefitRuleByContractID(String query, String benefitRuleName) throws Throwable {
        createAndValidateBenefitRule.deleteBenefitRule(query,benefitRuleName);
    }

//    @And("^User create the MutipleBenefitruledata$")
//    public void userCreateTheMutipleBenefitruledata(DataTable dataTable) {
//        createAndValidateBenefitRule.createMultipleBenefitRuleJSONBody(dataTable);
//    }
}
