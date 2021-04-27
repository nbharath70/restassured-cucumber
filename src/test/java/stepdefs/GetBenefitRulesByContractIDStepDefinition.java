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
}
