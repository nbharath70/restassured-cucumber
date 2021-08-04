# This feature file defines the automation scenarios to be developed for API to fetch Benefit Rules for a given Contract ID
Feature: Retrieve and validate the Approved Benefit Rules for a Contract by a given Contract ID to use in access rebate section
  @Smoke
  Scenario: get Benefit Rules for a valid contract and verify the correct status code is returned
    Given User executes query "SQLtoPickaValidContractIDfromBenefitRulestable" and gets contractID from column "columnNameToGetContractID"
    And User Hits API "getBenefitRulesOfaContractIdResource" with Get request
    Then User verifies the valid status code "200" in get BenefitRules API response

  @Functional
  Scenario: get Benefit Rules for a valid contract and verify it from DB
    Given User executes query "SQLtoPickaValidContractIDfromBenefitRulestable" and gets contractID from column "columnNameToGetContractID"
    And User executes query "SQLtoGetBenefitRuleDetailsOfaContractAsJson" and gets Json for columns benefitRuleId and benefitRuleName
    And User Hits API "getBenefitRulesOfaContractIdResource" with Get request
    Then User verifies response from API with DB benefitRulesJson

  @Regression @Functional
  Scenario: get Benefit Rules for an Invalid contract and verify the error code returned
    Given User Hits API "getBenefitRulesOfaContractIdResource" with Get request and Invalid contract Id "ABCD0011468"
    Then User verifies error msg "expectedErrorMsgJsonForBenefitRulesofInvalidContractIdAsString" from API response
    Then User verifies the BenefitRules for a contract response header Error Code value "-1"

  @Regression @Functional
  Scenario: get Benefit Rules for a blank contract and verify API the error code returned
    Given User Hits API "getBenefitRulesOfaContractIdResource" with Get request and Invalid contract Id
    Then User verifies error msg got from API with "expectedErrorMsgJsonForBenefitRulesofBlankContractIdAsString"
    Then User verifies the BenefitRules for a contract response header Error Code value "-1"





