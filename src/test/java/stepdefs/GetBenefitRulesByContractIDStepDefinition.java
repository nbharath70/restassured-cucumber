package stepdefs;

import baseSteps.GetBenefitRulesByContractID;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class GetBenefitRulesByContractIDStepDefinition {
    GetBenefitRulesByContractID getBenefitRules=new GetBenefitRulesByContractID();

    /* Glue code for
  Scenario: get Benefit Rules for a valid contract and verify the correct status code is returned
     */
    @Given("^User executes query \"([^\"]*)\" and gets contractID from column \"([^\"]*)\"$")
    public void userExecutesQueryAndGetsContractIDFromContractIDColumn(String queryKey, String columnKey) {
        getBenefitRules.userExecutesQueryAndGetsContractID(queryKey,columnKey);
    }

    @And("^User Hits API \"([^\"]*)\" with Get request$")
    public void userHitsAPIWithGetRequest(String endPointKey)  {
        getBenefitRules.userHitsAPI(endPointKey);
    }

    @Then("^User verifies the valid status code \"([^\"]*)\" in get BenefitRules API response$")
    public void userVerifiesTheValidStatusCodeInGetBenefitRulesAPIResponse(int expectedStatusCode)  {
        getBenefitRules.verifyStatusCodeReturnedForBenefitRulesAPI(expectedStatusCode);
    }

    /* Glue code for
        Scenario: get Benefit Rules for a valid contract and verify it from DB
     */
    @And("^User executes query \"([^\"]*)\" and gets Json for columns benefitRuleId and benefitRuleName$")
    public void userExecutesQueryAndGetsJsonForColumnsAnd(String queryKey) {
        getBenefitRules.executeQueryAndGet_benefitRuleId_And_benefitRuleNameAsJson(queryKey);

    }

    @Then("^User verifies response from API with DB benefitRulesJson$")
    public void userVerifiesResponseFromAPIWithDBBenefitRulesJson() {
        getBenefitRules.verifyAPIresponseJsonwithDBresponseAsJson();


    }


    @Given("^User Hits API \"([^\"]*)\" with Get request and Invalid contract Id \"([^\"]*)\"$")
    public void userHitsAPIWithGetRequestAndInvalidContractId(String endPointKey, String invalidContractId) {
        getBenefitRules.userHitsAPIWithInvalidContractId(endPointKey,invalidContractId);
    }

    @Then("^User verifies error msg \"([^\"]*)\" from API response$")
    public void userVerifiesErrorMsgFromAPIResponse(String errorMsgJsonKey)  {
        getBenefitRules.verifyAPIresponseErrorMsgWithExpectedErrorMsg(errorMsgJsonKey);
    }

    @Given("^User Hits API \"([^\"]*)\" with Get request and Invalid contract Id$")
    public void userHitsAPIWithGetRequestAndInvalidContractId(String endPointKey) {
        getBenefitRules.userHitsAPIWithInvalidContractId(endPointKey,"");

    }

    @Then("^User verifies error msg got from API with \"([^\"]*)\"$")
    public void userVerifiesErrorMsgGotFromAPIWith(String errorMsgJsonKey)  {
        getBenefitRules.verifyAPIresponseErrorMsgWithExpectedErrorMsg(errorMsgJsonKey);
    }

    @Then("^User verifies the BenefitRules for a contract response header Error Code value \"([^\"]*)\"$")
    public void userVerifiesTheBenefitRulesForAContractResponseHeaderErrorCodeValue(String expectedHeaderErrorCode)  {
        getBenefitRules.verifyTheBenefitRulesForAContractResponseHeaderErrorCodeValue(expectedHeaderErrorCode);
    }
}
