package stepdefs;

import baseSteps.GetBenefitRuleByContractIDForFetchApprovedInprogressBenefitRules;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetBenefitRuleByContractIDForFetchApprovedInprogressBenefitRulesStepdefs {
    GetBenefitRuleByContractIDForFetchApprovedInprogressBenefitRules getBenefitRuleByContractID=new GetBenefitRuleByContractIDForFetchApprovedInprogressBenefitRules();
    @Then("^User Hits API \"([^\"]*)\" with Get request for getBenefitRule by contractID and fetchApprovedBenefitRules \"([^\"]*)\"$")
    public void userHitsAPIWithGetRequestForGetBenefitRuleByContractIDAndFetchApprovedBenefitRules(String endPoint, Boolean fetchApprovedBenefitRules) throws Throwable {
        getBenefitRuleByContractID.getOperationOfBenefitRuleByContractID(endPoint,fetchApprovedBenefitRules);
    }

    @Then("^User verifies the valid status code \"([^\"]*)\" in getBenefitRule API response$")
    public void userVerifiesTheValidStatusCodeInGetBenefitRuleAPIResponse(int statusCode) throws Throwable {
        getBenefitRuleByContractID.verifyBenefitRuleByContractID(statusCode);
    }

    @When("^User executes the query \"([^\"]*)\" And matches the Get BenefitRule by ContractID returned by API and DB$")
    public void userExecutesTheQueryAndMatchesTheGetBenefitRuleByContractIDReturnedByAPIAndDB(String query) throws Throwable {
        getBenefitRuleByContractID.validatingGetBenefitRuleByContractID(query);
    }

    @Given("^User executes query \"([^\"]*)\" and getBenifitRule contractID from column \"([^\"]*)\"$")
    public void userExecutesQueryAndGetBenifitRuleContractIDFromColumn(String query, String columnName) throws Throwable {
        getBenefitRuleByContractID.userExecutesQueryAndGetsContractID(query,columnName);
    }


    @Then("^User verify the get BenefitRule response header error code value \"([^\"]*)\"$")
    public void userVerifyTheGetBenefitRuleResponseHeaderValue(String expectedResponseHeaderValue) throws Throwable {
        getBenefitRuleByContractID.verifyGetBenefitRuleResponseHeader(expectedResponseHeaderValue);
    }
}
