## This feature file defines the automation scenarios to be developed for API to fetch Benefit Rules for a given Contract ID
#Feature:Retrieve and validate the Benefit Rules for a Contract by a given Contract ID
#
#  Scenario: get Benefit Rules for a valid contract and verify the correct status code is returned
#    Given User executes query "SQLtoPickaValidContractIDfromBenefitRulestable" and gets contractID from column "columnNameToGetContractID"
#    And User Hits API "getBenefitRulesOfaContractIdResource" with Get request
#    Then User verifies the valid status code "200" in get BenefitRules API response
#
#
#  Scenario: get Benefit Rules for a valid contract and verify it from DB
#    Given User executes query "SQLtoPickaValidContractIDfromBenefitRulestable" and gets contractID from column "columnNameToGetContractID"
#    And User executes query "SQLtoGetBenefitRuleDetailsOfaContractAsJson" and gets Json for columns benefitRuleId and benefitRuleName
#    And User Hits API "getBenefitRulesOfaContractIdResource" with Get request
#    Then User verifies response from API with DB benefitRulesJson
#
#
