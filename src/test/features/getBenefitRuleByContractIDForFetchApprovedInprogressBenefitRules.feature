Feature:Retrieve and validate the get BenefitRule by ContractID
  @Smoke
  Scenario: Validate Get BenefitRule by ContractID and verify the correct status code is returned
    Given User executes query "getBenefitRuleContractidApproved" and getBenifitRule contractID from column "contractId"
    Then User Hits API "getBenefitRuleByContractID" with Get request for getBenefitRule by contractID and fetchApprovedBenefitRules "true"
    Then User verifies the valid status code "200" in getBenefitRule API response

  @Regression @Functional
  Scenario Outline: Validate Get BenefitRule by ContractID is returned from API and DB and validate fetchApprovedBenefitRules response as false
    Given User executes query "getBenefitRuleContractidInprogress" and getBenifitRule contractID from column "contractId"
    Then User Hits API "getBenefitRuleByContractID" with Get request for getBenefitRule by contractID and fetchApprovedBenefitRules "false"
    Then User verifies the valid status code "200" in getBenefitRule API response
    When User executes the query "<Query>" And matches the Get BenefitRule by ContractID returned by API and DB
    Examples:
      | Query                                         |
      | getBenefitRuleByContractIDOfBenefit_Rule_ID   |
      | getBenefitRuleByContractIDOfBenefit_Rule_Name |
      | getBenefitRuleByContractIDOfFormulary_Type    |
#      | getBenefitRuleByContractIDOfAbove_Value       |
#      | getBenefitRuleByContractIDOfBelow_Value       |

  @Regression @Functional
  Scenario Outline: Validate Get BenefitRule by ContractID is returned from API and DB and validate fetchApprovedBenefitRules response as true
    Given User executes query "getBenefitRuleContractidApproved" and getBenifitRule contractID from column "contractId"
    Then User Hits API "getBenefitRuleByContractID" with Get request for getBenefitRule by contractID and fetchApprovedBenefitRules "true"
    Then User verifies the valid status code "200" in getBenefitRule API response
    When User executes the query "<Query>" And matches the Get BenefitRule by ContractID returned by API and DB
    Examples:
      | Query                                         |
      | getBenefitRuleByContractIDOfBenefit_Rule_ID   |
      | getBenefitRuleByContractIDOfBenefit_Rule_Name |
      | getBenefitRuleByContractIDOfFormulary_Type    |
#      | getBenefitRuleByContractIDOfAbove_Value       |
#      | getBenefitRuleByContractIDOfBelow_Value       |

  @Regression @Functional
  Scenario: Validate Get BenefitRule by ContractID error code & status code for invalid url for
    Given User executes query "getBenefitRuleContractidApproved" and getBenifitRule contractID from column "contractId"
    Then User Hits API "getDrugGroupDetailsByMFRDrugListID" with Get request for getBenefitRule by contractID and fetchApprovedBenefitRules "true"
    Then User verify the get BenefitRule response header error code value "-1"
    Then User verifies the valid status code "200" in getBenefitRule API response
